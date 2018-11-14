package game;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import plant.Plant;
import plant.PlantName;
import tile.Tile;
import ui.GameEvent;
import ui.GameListener;
import zombie.Zombie;
import zombie.ZombieTypes;

/**
 * This class represents the Game. All gameplay related logic is here.
 * 
 * @author Michael Fan 101029934
 * @version Nov 9, 2018
 */

public class Game {
	private Shop shop;
	private LevelManager levelManager;
	private GameState gameState;
	private Level currentLevel;
	private PlantName selectedPlant;
	private boolean shovel;

	public static Random random = new Random();

	private boolean levelLoaded;

	private GameListener gameListener;

	/**
	 * Creates a new game.
	 */
	public Game(GameListener gameListener) {
		shop = new Shop();
		levelManager = new LevelManager();

		levelLoaded = false;
		selectedPlant = null;
		shovel = false;

		this.gameListener = gameListener;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Gameplay

	/**
	 * Starts the current level. Call this method after a level is loaded. Calling
	 * this method before a level is loaded will have no effect. Calling this method
	 * when the current level is not done will cause the current level to restart.
	 */
	public void start() {
		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not loaded");
			gameListener.gameStarted(gameEvent);
			return;
		}

		if(gameState != null && levelLoaded && !gameState.isLevelFinished()) {
			gameEvent.setSuccess(true).setMessage("Level will be restarted");
		} else {
			gameEvent.setSuccess(true).setMessage("Level Started");
		}

		gameState = new GameState();
		gameState.addPendingZombies(currentLevel.getZombies());
		shop.addPlants(currentLevel.getPlants());

		gameListener.gameStarted(gameEvent);
	}

	/**
	 * When a turn ends all the actions will take place. If the level is finished
	 * then calling this method will have no effect.
	 */
	public void endTurn() {
		GameEvent gameEvent = new GameEvent(this);
		
		if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level aready finished");
			gameListener.turnEnded(gameEvent);
			return;
		}

		spawnZombies();

		gainBaseSun();

		action();

		if(isLevelDone()) {
			gameState.levelFinished();
			levelLoaded = false;
			
			gameEvent.setSuccess(true).setMessage("Level completed");
			gameListener.levelFinished(gameEvent);
			return;
		}

		shop.reduceCooldowns();

		gameState.nextTurn();
		
		gameEvent.setSuccess(true);
		gameListener.turnEnded(gameEvent);
	}

	/**
	 * Buys a plant and plant in the grid. Returns true if a plant is purchased and
	 * planted successfully or false otherwise.
	 * 
	 * @param row the row in the grid
	 * @param col the column in the grid
	 */
	public void buyPlant(int row, int col) {
		if(selectedPlant == null) {
			return;
		}
		
		Plant newPlant = shop.purchase(selectedPlant, gameState.getSunCounter());
		
		GameEvent gameEvent = new GameEvent(this);
		
		if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level already finished");
			gameListener.plantBought(gameEvent);
			return;
		} else if(newPlant == null) {
			gameEvent.setSuccess(false).setMessage("Cannot purchase the plant (The plant is on cooldown or insuffcient sun counter");
			gameListener.plantBought(gameEvent);
			return;
		} else if(row < 0 || row >= GameState.ROW) {
			gameEvent.setSuccess(false).setMessage("Cannot place the plant here.");
			gameListener.plantBought(gameEvent);
			return;
		} else if(col < GameState.FIRST || col > GameState.LAST) {
			gameEvent.setSuccess(false).setMessage("Cannot place the plant here.");
			gameListener.plantBought(gameEvent);
			return;
		} else if(!gameState.getGrid()[row][col].isEmpty()) {
			gameEvent.setSuccess(false).setMessage("Cannot place the plant here.");
			gameListener.plantBought(gameEvent);
			return;
		}
		
		selectedPlant = null;
		gameState.addPlant(newPlant, row, col);
		gameState.spendSun(newPlant.getPrice());
		
		gameEvent.setSuccess(true);
		gameListener.plantBought(gameEvent);
	}

	/**
	 * Removes a plant at the specified row and col. Returns true if the plant is
	 * removed successfully or false otherwise.
	 * 
	 * @param row the row of the plant that is to be removed
	 * @param col the column of the the plant that is to be removed
	 */
	public void shovel(int row, int col) {
		GameEvent gameEvent = new GameEvent(this);
		
		if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level already finished");
			gameListener.plantShoveled(gameEvent);
			return;
		} else if(row < 0 || row >= GameState.ROW) {
			gameEvent.setSuccess(false).setMessage("Cannot shovel here");
			gameListener.plantShoveled(gameEvent);
			return;
		} else if(col < GameState.FIRST || col > GameState.LAST) {
			gameEvent.setSuccess(false).setMessage("Cannot shovel here");
			gameListener.plantShoveled(gameEvent);
			return;
		}

		Tile tile = gameState.getGrid()[row][col];

		if(!tile.hasPlant()) {
			gameEvent.setSuccess(false).setMessage("Tile has no plant to shovel");
			gameListener.plantShoveled(gameEvent);
			return;
		}

		tile.removePlant();
		
		gameEvent.setSuccess(true);
		gameListener.plantShoveled(gameEvent);
	}

	/**
	 * Returns true if the current level is finished or false otherwise. The game is
	 * finished when a zombie has reached the lawn mower tile when the lawn mower
	 * has been triggered already or when there are no more zombies left.
	 * 
	 * @return true if the current level is finished or false otherwise
	 */
	private boolean isLevelDone() {
		// check if there are zombies left
		if(gameState.getNumberOfZombiesLeft() == 0) {
			return true;
		}

		// check if a lawn mower tile has any zombie
		Tile[][] grid = gameState.getGrid();
		for(int row = 0; row < GameState.ROW; row++) {
			if(grid[row][GameState.LAWN_MOWER].hasZombie() && gameState.isRowCleared(row)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * The actions that will take place when a turn ends. The actions include plant
	 * actions and zombie actions.
	 */
	private void action() {
		Tile[][] grid = gameState.getGrid();
		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				Tile tile = grid[row][col];
				// The tile holds a plant
				if(tile.hasPlant()) {
					plantAction(tile.getResidingPlant(), grid, row, col);
				}
				// The tile holds a zombie
				else if(tile.hasZombie()) {
					zombieAction(tile.getResidingZombie(), grid, row, col);
				}
			}
		}
	}

	/**
	 * Gains the base amount of sun counter.
	 */
	private void gainBaseSun() {
		gameState.gainSun(currentLevel.getBaseSunGain());
	}

	/**
	 * Performs plant action. This includes generates resources and attack zombies.
	 * 
	 * @param plant the plant that is performing the action
	 * @param grid the grid of the current level
	 * @param row the row of the plant that is performing the action
	 * @param col row column of the plant that is performing the action
	 */
	private void plantAction(Plant plant, Tile[][] grid, int row, int col) {
		// Get resource if the plant generates resource
		if(plant.canResrc_gen()) {
			gameState.gainSun(plant.getResrc_gen());
		} // Attack if if can attack
		else if(plant.canAttack()) {
			// Attacking horizontally
			for(int x = 1; x <= plant.getAtkRange_X(); x++) {
				if(x + col > GameState.LAST) {
					break;
				}

				Tile tile = grid[row][col + x];

				// If the tile contains a zombie then the zombie will take damage
				if(tile.hasZombie()) {
					Zombie firstZombie = tile.getFirstZombie();
					firstZombie.takeDamage(plant.getDamage());

					if(firstZombie.isDead()) {
						tile.removeZombie(firstZombie);
						gameState.zombieDied();
					}

					break;
				}
			}
		}
	}

	/**
	 * Performs zombie actions. This includes attack and move.
	 * 
	 * @param zombies the zombies that are performing the actions
	 * @param grid the grid of the current level
	 * @param row the row of the zombies
	 * @param col the column of the zombies
	 */
	private void zombieAction(List<Zombie> zombies, Tile[][] grid, int row, int col) {
		Iterator<Zombie> zombieIter = zombies.iterator();

		while(zombieIter.hasNext()) {
			Zombie zombie = zombieIter.next();

			if(zombie.isReadyToMove()) {
				// If the current row still has the lawn mover then the lawn mower is triggered
				if(col - 1 == GameState.LAWN_MOWER && !gameState.isRowCleared(row)) {
					gameState.addClearedRow(row);
					for(Tile t : grid[row]) {
						gameState.zombieDied(t.getResidingZombie().size());
						t.removePlant();
						t.clearResidingZombie();
					}
				} else {
					Tile nextTile = grid[row][col - 1];

					// If the next tile contains a plant attack it
					if(nextTile.hasPlant()) {
						Plant plant = nextTile.getResidingPlant();

						plant.takeDmg(zombie.getDamage());

						if(plant.isDead()) {
							nextTile.removePlant();
						}
					}
					// If the next tile does not have plant then move to it
					else {
						nextTile.addZombie(zombie);
						zombieIter.remove();
					}
				}
				zombie.resetMovementCounter();
			} else {
				zombie.incrementMovementCounter();
			}
		}
	}

	/**
	 * Spawn zombies based the spawn rate and spawn amount of the current level.
	 */
	private void spawnZombies() {
		if(gameState.getTurnNumber() % currentLevel.getSpawnRate() != 0) {
			return;
		} else if(gameState.getNumberOfZombiesPending() == 0) {
			return;
		}

		for(int i = 0; i < currentLevel.getSpawnAmount(); i++) {
			gameState.addZombie(random.nextInt(GameState.ROW));
		}
	}
	
	/**
	 * Selects a plant in the shop.
	 * 
	 * @param plant the selected plant
	 */
	public void selectPlant(PlantName plant) {
		selectedPlant = plant;
	}
	
	/**
	 * Puts the game to shovel mode.
	 * 
	 * @param shovel true for shovel mode and false for not
	 */
	public void selectShovel(boolean shovel) {
		this.shovel = shovel;
	}
	
	/**
	 * Returns true if the game is shovel mode.
	 * 
	 * @return true if the game is in shovel mode
	 */
	public boolean isShovelSelected() {
		return shovel;
	}
	
	/**
	 * Returns the selected plant.
	 * 
	 * @return the selected plant
	 */
	public PlantName getSelectedPlant() {
		return selectedPlant;
	}

	// End Gameplay
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Game info
	
	/**
	 * Returns the grid.
	 * 
	 * @return the grid
	 */
	public Tile[][] getGrid() {
		return gameState.getGrid();
	}

	/**
	 * Returns the plants in the shop and their prices
	 * 
	 * @return the plants in the shop and their prices
	 */
	public Map<PlantName, Integer> getShopPlants() {
		return shop.getShopPlants();
	}

	/**
	 * Returns the current turn number.
	 * 
	 * @return the current turn number
	 */
	public int getTurnNumber() {
		return gameState.getTurnNumber();
	}

	/**
	 * Returns true if the current level is finished.
	 * 
	 * @return true if the current level is finished
	 */
	public boolean isLevelFinished() {
		return gameState.isLevelFinished();
	}

	/**
	 * Returns a string of all the zombies that will appear in the current level.
	 * 
	 * @return a string of all the zombies that will appear in the current level
	 */
	public String getAvailableZombies() {
		StringBuilder sb = new StringBuilder();

		for(ZombieTypes z : currentLevel.getAvailableZombies()) {
			sb.append(z.name() + " ");
		}

		return sb.toString();
	}

	/**
	 * Returns the number of zombies that are waiting to be spawned.
	 * 
	 * @return the number of zombies that are waiting to be spawned
	 */
	public int getNumberOfZombiesPending() {
		return gameState.getNumberOfZombiesPending();
	}

	/**
	 * Returns the amount of sun counter.
	 * 
	 * @return the amount of sun counter
	 */
	public int getSunCounter() {
		return gameState.getSunCounter();
	}

	/**
	 * Returns the number of zombies that are still alive.
	 * 
	 * @return the number of zombies that are still alive
	 */
	public int getNumberOfZombiesLeft() {
		return gameState.getNumberOfZombiesLeft();
	}

	/**
	 * Returns the total amount of zombie that will appear in this level. This
	 * number will not change during a level.
	 * 
	 * @return he total amount of zombie that will appear in this level
	 */
	public int getTotalNumberOfZombies() {
		return gameState.getTotalNumberOfZombies();
	}

	/**
	 * Returns true if a level is loaded or false otherwise.
	 * 
	 * @return true if a level is loaded or false otherwise
	 */
	public boolean isLevelLoaded() {
		return levelLoaded;
	}

	// End Game info
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Level loading

	/**
	 * Loads the specified level. Returns true if loaded successfully or false
	 * otherwise.
	 * 
	 * @param levelID the id of the level to load
	 */
	public void loadLevel(int levelID) {
		currentLevel = levelManager.getLevel(levelID - 1);

		GameEvent gameEvent = new GameEvent(this);

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("Level does not exist");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 */
	public void loadNextLevel() {
		currentLevel = levelManager.getNextLevel();

		GameEvent gameEvent = new GameEvent(this);

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("Already at the end");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 */
	public void loadPreviousLevel() {
		currentLevel = levelManager.getPreviousLevel();

		GameEvent gameEvent = new GameEvent(this);

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("This is the first level");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	// End Level loading
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}