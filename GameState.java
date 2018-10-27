import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is class represents a GameState.
 * 
 * @author Michael Fan 101029934
 * @version Oct 25, 2018
 */

public class GameState {
	private Tile[][] grid;
	private int sunCounter;
	private boolean levelFinished;
	private int turnNumber;
	private List<Zombie> pendingZombies;
	private Set<Integer> clearedRow;
	private int totalNumberOfZombies;
	private int numberOfZombiesLeft;

	public static int ROW = 5;
	public static int COL = 11;
	public static int FIRST = 1;
	public static int LAST = 9;
	public static int ZOMBIE_SPAWN = 10;
	public static int LAWN_MOWER = 0;

	/**
	 * Constructs a new GameState.
	 */
	public GameState() {
		grid = new Tile[ROW][COL];
		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				grid[row][col] = new Tile();
			}
		}

		sunCounter = 0;
		levelFinished = false;
		turnNumber = 1;
		totalNumberOfZombies = 0;
		numberOfZombiesLeft = 0;
		pendingZombies = new LinkedList<Zombie>();
		clearedRow = new HashSet<Integer>();
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
	 * More than one zombie is dead so the number of zombies left are decreased by the specified amount.
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
	 * Returns the total number of zombies. This number is persistent throughout a level.
	 * 
	 * @return the total number of zombies
	 */
	public int getTotalNumberOfZombies() {
		return totalNumberOfZombies;
	}
	
	/**
	 * Adds a row that has been cleared.
	 * 
	 * @param row that has been cleared
	 */
	public void addClearedRow(int row) {
		clearedRow.add(row);
	}
	
	/**
	 * Returns true if the specified row has been cleared or false otherwise.
	 * 
	 * @return true if the specified row has been cleared or false otherwise
	 */
	public boolean isRowCleared(int row) {
		return clearedRow.contains(row);
	}
}