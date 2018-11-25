package game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.ImageIcon;

import plant.Plant;
import plant.PlantName;
import tile.Tile;
import tile.TileTypes;
import ui.GameEvent;
import ui.GameListener;
import zombie.Zombie;
import zombie.ZombieTypes;

/**
 * This class represents the Game. All gameplay related logic is here.
 * 
 * @author Michael Fan 101029934
 * @editor Souheil Yazji 101007994
 * @version Nov 16, 2018
 */

public class Game {
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
	 * 
	 * @param gameListener the game listener (in this case it will be the view)
	 */
	public Game(GameListener gameListener) {
		levelManager = new LevelManager();

		levelLoaded = false;
		selectedPlant = null;
		shovel = false;

		this.gameListener = gameListener;

		gameListener.gameCreated(new GameEvent(this));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Gameplay

	/**
	 * Starts the current level.
	 */
	private void start() {
		gameState = new GameState();
		gameState.addPendingZombies(currentLevel.getZombies());
		gameState.addPlants(currentLevel.getPlants());
	}

	/**
	 * Restarts the current level.
	 */
	public void restart() {
		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded && !gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level not started");
			gameListener.levelRestarted(gameEvent);
			return;
		}

		start();
		levelLoaded = true;
		gameEvent.setSuccess(true).setMessage("Level restarted");
		gameListener.levelRestarted(gameEvent);
	}

	/**
	 * When a turn ends all the actions will take place. If the level is finished
	 * then calling this method will have no effect.
	 */
	public void endTurn() {
		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not loaded");
			gameListener.turnEnded(gameEvent);
			return;
		}

		if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level aready finished");
			gameListener.turnEnded(gameEvent);
			return;
		}

		try {
			gameState.cacheUndo(gameState);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		spawnZombies();

		gainBaseSun();

		action();

		if(isLevelDone()) {
			gameState.levelFinished();
			levelLoaded = false;
			return;
		}

		gameState.reduceCooldowns();

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

		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not started");
			gameListener.plantBought(gameEvent);
			return;
		} else if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level already finished");
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

		if(!gameState.canPurchase(selectedPlant, gameState.getSunCounter())) {
			gameEvent.setSuccess(false).setMessage("Not enough sun counter");
			gameListener.plantBought(gameEvent);
			return;
		}

		gameState.cacheUndo(gameState);

		Plant newPlant = gameState.purchase(selectedPlant);
		gameState.addPlant(newPlant, row, col);
		gameState.spendSun(newPlant.getPrice());

		selectedPlant = null;

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
		shovel = false;

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not started");
			gameListener.plantShoveled(gameEvent);
			return;
		} else if(gameState.isLevelFinished()) {
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

		gameState.cacheUndo(gameState);

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
		// check if there are zombies left. win if there are no zombies left
		if(gameState.getNumberOfZombiesLeft() == 0) {
			GameEvent gameEvent = new GameEvent(this).setSuccess(true).setMessage("Victory");
			gameListener.levelFinished(gameEvent);
			return true;
		}

		// check if a lawn mower tile has any zombie
		Tile[][] grid = gameState.getGrid();
		for(int row = 0; row < GameState.ROW; row++) {
			if(grid[row][GameState.LAWN_MOWER].hasZombie()) {
				GameEvent gameEvent = new GameEvent(this).setSuccess(true).setMessage("Defeat");
				gameListener.levelFinished(gameEvent);
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
				if(tile.hasZombie()) {
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
		}

		// Attack Normally if not a mine
		else if(plant.canAttack()) {
			attack_X(plant, grid, row, col);
		}
	}

	/**
	 * Processes attacks in the X-axis.
	 * 
	 * @param plant the plant that is performing the action
	 * @param grid the grid of the current level
	 * @param row the row of the plant that is performing the action
	 * @param col row column of the plant that is performing the action
	 */
	private void attack_X(Plant plant, Tile[][] grid, int row, int col) {
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
		Tile nextTile = grid[row][col - 1];

		while(zombieIter.hasNext()) {
			Zombie zombie = zombieIter.next();
			boolean zstatus = zombie.isDead();

			if(zombie.isReadyToMove() && !zstatus) {
				// If the current row still has the lawn mover then the lawn mower is triggered
				if(nextTile.getTileType() == TileTypes.LAWNMOWER) {
					nextTile.setTileType(TileTypes.CONCRETE);
					for(Tile t : grid[row]) {
						gameState.zombieDied(t.getResidingZombie().size());
						t.removePlant();
						t.clearResidingZombie();
					}
					break;
				} else {
					if(nextTile.hasPlant()) {
						Plant plant = nextTile.getResidingPlant();
						plant.takeDmg(zombie.getDamage());
						
						if(!plant.isDead()) {
							return;
						}
						nextTile.removePlant();
					}
					if (!zstatus) {
						nextTile.addZombie(zombie);
					}
					zombieIter.remove();
				}
				zombie.resetMovementCounter();
			} else {
				zombie.incrementMovementCounter();
			}
		}
		
		Iterator<Zombie> zombieIterClean = zombies.iterator();
		while(zombieIterClean.hasNext()) {
			if(zombieIterClean.next().isDead()) {
				zombieIterClean.remove();
				gameState.zombieDied();
			}
		}
	}

	/**
	 * Spawn zombies based the spawn rate and spawn amount of the current level.
	 */
	private void spawnZombies() {
		if(gameState.getTurnNumber() % currentLevel.getSpawnRate() != 0) {
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
	 * Returns true if a plant is selected or false otherwise.
	 * 
	 * @return true if a plant is selected or false otherwise
	 */
	public boolean isPlantSelected() {
		return !(selectedPlant == null);
	}

	/**
	 * Undo
	 */
	public void undo() {
		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not started");
			gameListener.gameUndo(gameEvent);
			return;
		} else if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level not finished");
			gameListener.gameUndo(gameEvent);
			return;
		}

		GameState previousState = gameState.undo();

		if(previousState == null) {
			gameEvent.setSuccess(false).setMessage("Nothing to undo");
			gameListener.gameUndo(gameEvent);
			return;
		}

		gameState.cacheRedo(gameState);
		gameState = previousState;
		gameEvent.setSuccess(true);
		gameListener.gameUndo(gameEvent);
	}

	/**
	 * Redo
	 */
	public void redo() {
		GameEvent gameEvent = new GameEvent(this);

		if(!levelLoaded) {
			gameEvent.setSuccess(false).setMessage("Level not started");
			gameListener.gameRedo(gameEvent);
			return;
		} else if(gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Level not finished");
			gameListener.gameRedo(gameEvent);
			return;
		}

		GameState previousState = gameState.redo();

		if(previousState == null) {
			gameEvent.setSuccess(false).setMessage("Nothing to redo");
			gameListener.gameRedo(gameEvent);
			return;
		}

		gameState.cacheUndo(gameState);
		gameState = previousState;
		gameEvent.setSuccess(true);
		gameListener.gameRedo(gameEvent);
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
	 * Returns the plants in the shop and their icons
	 * 
	 * @return the plants in the shop and their icon
	 */
	public Map<PlantName, ImageIcon> getShopPlants() {
		return gameState.getShopPlants();
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

	/**
	 * Returns true if the specified plant is on cooldown or false otherwise.
	 * 
	 * @param plant the plant to check
	 * 
	 * @return true if the specified plant is on cooldown or false otherwise
	 */
	public boolean isPlantOnCooldown(PlantName plant) {
		return gameState.isPlantOnCooldown(plant);
	}

	/**
	 * Returns the level number.
	 * 
	 * @return the level number
	 */
	public int getLevelNumber() {
		return currentLevel.getLevelID();
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
		GameEvent gameEvent = new GameEvent(this);

		if(levelLoaded && !gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Current level not finished");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		currentLevel = levelManager.getLevel(levelID - 1);

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("Level does not exist");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		start();
		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 */
	public void loadNextLevel() {
		GameEvent gameEvent = new GameEvent(this);

		if(levelLoaded && !gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Current level not finished");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		currentLevel = levelManager.getNextLevel();

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("Already at the end");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		start();
		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 */
	public void loadPreviousLevel() {
		GameEvent gameEvent = new GameEvent(this);

		if(levelLoaded && !gameState.isLevelFinished()) {
			gameEvent.setSuccess(false).setMessage("Current level not finished");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		currentLevel = levelManager.getPreviousLevel();

		if(currentLevel == null) {
			gameEvent.setSuccess(false).setMessage("This is the first level");
			gameListener.levelLoaded(gameEvent);
			return;
		}

		levelLoaded = true;

		start();
		gameEvent.setSuccess(true).setMessage("Level loaded successfully");
		gameListener.levelLoaded(gameEvent);
	}

	/**
	 * Returns a list of all the level IDs of the predefined levels.
	 * 
	 * @return a list of all the level IDs of the predefined levels
	 */
	public List<Integer> getAllPredefinedLevelID() {
		return levelManager.getAllPredefinedLevelID();
	}

	// End Level loading
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}