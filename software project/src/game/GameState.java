package game;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.ImageIcon;

import plant.Plant;
import plant.PlantFactory;
import plant.PlantName;
import tile.Tile;
import tile.TileTypes;
import zombie.Zombie;
import zombie.ZombieFactory;
import zombie.ZombieTypes;

/**
 * This class represents the GameState. The state includes information about the
 * current level. It includes information like grid, sun counter, pending
 * zombies and etc.
 * 
 * @author Michael Fan 101029934
 * @version Nov 17, 2018
 */

public class GameState {
	private Tile[][] grid;
	private int sunCounter;
	private boolean levelFinished;
	private int turnNumber;
	private List<Zombie> pendingZombies;
	private int totalNumberOfZombies;
	private int numberOfZombiesLeft;
	private Shop shop;

	public static int ROW = 5;
	public static int COL = 11;
	public static int FIRST = 1;
	public static int LAST = 9;
	public static int ZOMBIE_SPAWN = 10;
	public static int LAWN_MOWER = 0;
	public static int ROAD = 11;

	private static List<GameState> undo;
	private static List<GameState> redo;

	/**
	 * Constructs a new GameState.
	 */
	public GameState() {
		grid = new Tile[ROW][COL];
		for(int row = 0; row < ROW; row++) {
			for(int col = 0; col < COL; col++) {
				TileTypes tileType;
				if(col == LAWN_MOWER) {
					tileType = TileTypes.LAWNMOWER;
				} else if(col == ZOMBIE_SPAWN) {
					tileType = TileTypes.ZOMBIE_SPAWN;
				} else {
					tileType = TileTypes.GRASS;
				}

				grid[row][col] = new Tile(tileType);
			}
		}

		sunCounter = 50;
		levelFinished = false;
		turnNumber = 1;
		totalNumberOfZombies = 0;
		numberOfZombiesLeft = 0;
		pendingZombies = new LinkedList<Zombie>();
		undo = new LinkedList<GameState>();
		redo = new LinkedList<GameState>();
		shop = new Shop();
	}

	/**
	 * Constructs a new GameState that is a copy of the specified GameState
	 * 
	 * @param gameState the GameState that is to be copied
	 */
	public GameState(GameState gameState) {
		// Copy the grid
		grid = new Tile[ROW][COL];
		for(int row = 0; row < ROW; row++) {
			for(int col = 0; col < COL; col++) {
				grid[row][col] = new Tile(gameState.grid[row][col]);
			}
		}
		
		// Copy game info
		turnNumber = gameState.turnNumber;
		sunCounter = gameState.sunCounter;
		levelFinished = gameState.levelFinished;
		totalNumberOfZombies = gameState.numberOfZombiesLeft;
		numberOfZombiesLeft = gameState.numberOfZombiesLeft;
		
		// Copy pendingZombies
		pendingZombies = new LinkedList<Zombie>();
		for(Zombie z : gameState.pendingZombies) {
			pendingZombies.add(ZombieFactory.createZombie(z.getZombieType()));
		}
		
		// Copy shop
		shop = new Shop(gameState.shop);
	}

	/**
	 * Adds a plant to the grid.
	 * 
	 * @param plant the plant to be added
	 * @param row the row where the plant will be planted
	 * @param col the column where the plant will be planted
	 */
	public void addPlant(Plant plant, int row, int col) {
		grid[row][col].setResidingPlant(plant);
	}

	/**
	 * Loads the zombies that will appear in this level.
	 * 
	 * @param zombies the zombies that will appear in this level
	 */
	public void addPendingZombies(Map<ZombieTypes, Integer> zombies) {
		for(ZombieTypes z : zombies.keySet()) {
			for(int i = 0; i < zombies.get(z); i++) {
				pendingZombies.add(ZombieFactory.createZombie(z));
			}
		}
		totalNumberOfZombies = pendingZombies.size();
		numberOfZombiesLeft = totalNumberOfZombies;
		Collections.shuffle(pendingZombies);
	}

	/**
	 * Adds a zombie to the grid. Zombie will always spawn at the last column.
	 * 
	 * @param row the row where the zombie will be added
	 */
	public void addZombie(int row) {
		if(pendingZombies.size() == 0) {
			return;
		}
		
		grid[row][ZOMBIE_SPAWN].addZombie(pendingZombies.get(0));
		pendingZombies.remove(0);
	}

	/**
	 * Returns the number of zombies that are pending.
	 * 
	 * @return the number of zombies that are pending
	 */
	public int getNumberOfZombiesPending() {
		return pendingZombies.size();
	}

	/**
	 * Returns the current sun counter.
	 * 
	 * @return the current sun count
	 */
	public int getSunCounter() {
		return sunCounter;
	}

	/**
	 * Returns the grid.
	 * 
	 * @return the grid
	 */
	public Tile[][] getGrid() {
		return grid;
	}

	/**
	 * Returns the current turn number.
	 * 
	 * @return the current turn number
	 */
	public int getTurnNumber() {
		return turnNumber;
	}

	/**
	 * Increases the turn number.
	 */
	public void nextTurn() {
		turnNumber++;
	}

	/**
	 * Gain a certain amount of sun.
	 * 
	 * @param gain the amount of sun to be gained.
	 */
	public void gainSun(int gain) {
		sunCounter += gain;
	}

	/**
	 * Returns true if the level is finished or false otherwise.
	 * 
	 * @return true if the level is finished or false otherwise
	 */
	public boolean isLevelFinished() {
		return levelFinished;
	}

	/**
	 * Spends a certain of sun.
	 * 
	 * @param spent the amount of sun to be spent
	 */
	public void spendSun(int spent) {
		sunCounter -= spent;
	}

	/**
	 * Finishes the current level.
	 */
	public void levelFinished() {
		levelFinished = true;
	}

	/**
	 * Only one zombie died so the number of zombies left is decreased by 1.
	 */
	public void zombieDied() {
		numberOfZombiesLeft--;
	}

	/**
	 * More than one zombie is dead so the number of zombies left are decreased by
	 * the specified amount.
	 * 
	 * @param amount the amount zombies that are dead
	 */
	public void zombieDied(int amount) {
		numberOfZombiesLeft -= amount;
	}

	/**
	 * Returns the number of zombies left.
	 * 
	 * @return the number of zombies left
	 */
	public int getNumberOfZombiesLeft() {
		return numberOfZombiesLeft;
	}

	/**
	 * Returns the total number of zombies. This number is persistent throughout a
	 * level.
	 * 
	 * @return the total number of zombies
	 */
	public int getTotalNumberOfZombies() {
		return totalNumberOfZombies;
	}
	
	/**
	 * Adds plants that are available purchase into the shop.
	 * 
	 * @param plants the plants that are to be added to the shop
	 */
	public void addPlants(Set<PlantName> plants) {
		shop.addPlants(plants);
	}
	
	/**
	 * Returns the plants and their icons.
	 * 
	 * @return the plants and their icons
	 */
	public Map<PlantName, ImageIcon> getShopPlants() {
		return shop.getShopPlants();
	}
	
	/**
	 * Purchase a plant.
	 * 
	 * @param plant the plant to be purchased
	 * 
	 * @return the purchased plant
	 */
	public Plant purchase(PlantName plant) {
		return shop.purchase(plant);
	}
	
	/**
	 * Returns true if the specified plant can be purchased.
	 * 
	 * @param plant the plant to be purchased
	 * @param sunCounter the current amount of sun counter
	 * 
	 * @return true if the specified plant can be purchased
	 */
	public boolean canPurchase(PlantName plant, int sunCounter) {
		return shop.canPurchase(plant, sunCounter);
	}
	
	/**
	 * Reduces the cooldowns for purchasing plants.
	 */
	public void reduceCooldowns() {
		shop.reduceCooldowns();
	}
	
	/**
	 * Returns true if the specified plant is on cooldown.
	 * 
	 * @param plant the plant to check
	 * 
	 * @return true if the specified plant is on cooldown
	 */
	public boolean isPlantOnCooldown(PlantName plant) {
		return shop.isPlantOnCooldown(plant);
	}

	/**
	 * Caches GameState for undo
	 * 
	 * @param gameState the GameState to cache
	 */
	public void cacheUndo(GameState gameState) {
		undo.add(new GameState(gameState));
	}
	
	/**
	 * Returns the last GameState 
	 * 
	 * @returnn the last GameState 
	 */
	public GameState undo() {
		int last = undo.size() - 1;
		if(last < 0) {
			return null;
		}
		GameState lastChange = undo.get(last);
		undo.remove(last);
		return lastChange;
	}
	
	/**
	 * Caches GameState for redo
	 * 
	 * @param gameState the GameState to cache
	 */
	public void cacheRedo(GameState gameState) {
		redo.add(new GameState(gameState));
	}
	
	/**
	 * Returns the last undo
	 * 
	 * @returnn the last undo
	 */
	public GameState redo() {
		int last = redo.size() - 1;
		if(last < 0) {
			return null;
		}
		GameState lastChange = redo.get(last);
		redo.remove(last);
		return lastChange;
	}
	
	/**
	 * Clears the redo stack
	 */
	public void clearRedo() {
		redo.clear();
	}
}