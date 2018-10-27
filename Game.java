import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * This class represents the Game. All gameplay related logic is here.
 * 
 * @author Michael Fan 101029934
 * @version Oct 25, 2018
 */

public class Game {
	private Shop shop;
	private LevelManager levelManager;
	private GameState gameState;
	private Level currentLevel;

	public static Random random = new Random();

	private boolean levelLoaded;

	/**
	 * Creates a new game.
	 */
	public Game() {
		shop = new Shop();
		levelManager = new LevelManager();

		levelLoaded = false;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Gameplay

	/**
	 * Starts the current level. Call this method after a level is loaded.
	 * Calling this method before a level is loaded or the current level is
	 * not finished, will have no effect.
	 */
	public void start() {
		if(!levelLoaded) {
			return;
		} else if(gameState != null && levelLoaded && !gameState.isLevelFinished()) {
			return;
		}
		
		gameState = new GameState();
		gameState.addPendingZombies(currentLevel.getZombies());
		shop.addPlants(currentLevel.getPlants());
	}

	/**
	 * When a turn ends all the actions will take place. If the level is finished
	 * then calling this method will have no effect.
	 */
	public void endTurn() {
		if(isLevelDone()) {
			gameState.levelFinished();
			levelLoaded = false;
			return;
		}

		// Spawn new zombies
		spawnZombies();

		// Gain base sun 
		gainBaseSun();

		action();

		gameState.nextTurn();

	}

	/**
	 * Buys a plant and plant in the grid. Returns true if a plant is purchased and
	 * planted successfully or false otherwise.
	 * 
	 * @param plant the name(PlantName) of the plant
	 * @param row the row in the grid
	 * @param col the column in the grid
	 * 
	 * @return true if a plant is purchased and planted successfully or false
	 *         otherwise
	 */
	public boolean buyPlant(PlantName plant, int row, int col) {
		Plant newPlant = shop.purchase(plant, gameState.getSunCounter());

		if(gameState.isLevelFinished()) {
			return false;
		} else if(newPlant == null) {
			return false;
		} else if(row < 0 || row >= GameState.ROW) {
			return false;
		} else if(col < GameState.FIRST || col > GameState.LAST) {
			return false;
		} else if(gameState.isRowDisabled(row)) {
			return false;
		} else if(!gameState.getGrid()[row][col].isEmpty()) {
			return false;
		}

		gameState.addPlant(newPlant, row, col);
		gameState.spendSun(newPlant.getPrice());

		return true;
	}
	
	/**
	 * Removes a plant at the specified row and col. Returns true if the plant is removed 
	 * successfully or false otherwise.
	 * 
	 * @param row the row of the plant that is to be removed
	 * @param col the column of the the plant that is to be removed
	 * 
	 * @return true if the plant is removed successfully or false otherwise
	 */
	public boolean shovel(int row, int col) {
		if(gameState.isLevelFinished()) {
			return false;
		} else if(row < 0 || row >= GameState.ROW) {
			return false;
		} else if(col < GameState.FIRST || col > GameState.LAST) {
			return false;
		}

		Tile tile = gameState.getGrid()[row][col];

		if(!tile.hasPlant()) {
			return false;
		}

		tile.removePlant();

		return true;
	}
	
	/**
	 * Returns the true if the level is done or false otherwise. A level is done when all rows are disabled
	 * or when there are no zombies left.
	 * 
	 * @return true if the level is done or false otherwise
	 */
	private boolean isLevelDone() {
		return gameState.getNumberOfDisabledRows() == GameState.ROW || gameState.getNumberOfZombiesLeft() == 0;
	}

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
				// If the next tile is the lawn mower then this row is disabled and everything from this row is removed
				if(col - 1 == GameState.LAWN_MOWER) {
					gameState.addDisabledRow(row);
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
			int row = random.nextInt(GameState.ROW);
			while(gameState.isRowDisabled(row)) {
				row = random.nextInt(GameState.ROW);
			}
			gameState.addZombie(row);
		}
	}

	// End Gameplay
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Game info

	/**
	 * Returns a read-only version of the grid.
	 * 
	 * @return a read-only version of the grid
	 */
	public String[][] getGrid() {
		Tile[][] grid = gameState.getGrid();
		String[][] readOnly = new String[GameState.ROW][GameState.COL];

		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				if(gameState.isRowDisabled(row)) {
					readOnly[row][col] = "X";
					continue;
				} else if(col == GameState.LAWN_MOWER) {
					readOnly[row][col] = "L";
					continue;
				}

				Tile tile = grid[row][col];

				if(tile.hasPlant()) {
					switch(tile.getResidingPlant().getName()) {
					case PeaShooter:
						readOnly[row][col] = "P";
						break;
					case SunFlower:
						readOnly[row][col] = "S";
						break;
					}
				} else if(tile.hasZombie()) {
					readOnly[row][col] = tile.getResidingZombie().size() + "Z";
				} else {
					readOnly[row][col] = " ";
				}
			}
		}

		return readOnly;
	}

	/**
	 * Returns the plants in the shop and their prices as a string.
	 * 
	 * @return the plants in the shop and their prices as a string
	 */
	public String getShopItems() {
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
	 * 
	 * @return true if loaded successfully or false otherwise
	 */
	public boolean loadLevel(int levelID) {
		currentLevel = levelManager.getLevel(levelID - 1);

		if(currentLevel == null) {
			return false;
		}

		levelLoaded = true;
		return true;
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 * 
	 * @return true if loaded successfully or false otherwise
	 */
	public boolean loadNextLevel() {
		currentLevel = levelManager.getNextLevel();

		if(currentLevel == null) {
			return false;
		}

		levelLoaded = true;
		return true;
	}

	/**
	 * Returns true if loaded successfully or false otherwise.
	 * 
	 * @return true if loaded successfully or false otherwise
	 */
	public boolean loadPreviousLevel() {
		currentLevel = levelManager.getPreviousLevel();

		if(currentLevel == null) {
			return false;
		}

		levelLoaded = true;
		return true;
	}

	// End Level loading
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}