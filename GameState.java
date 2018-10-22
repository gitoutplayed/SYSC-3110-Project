import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This is class represents a GameState. The state of the game includes the
 * grid, sunCounter.
 * 
 * @author Michael Fan 101029934
 */

public class GameState {
    private Tile[][] grid;
    private int sunCounter;
    private boolean levelFinished;
    private int turnNumber;
    private List<Zombie> pendingZombies;
    private Set<Integer> disabledRows;
    private int totalNumberOfZombies;
    private int numberOfZombiesLeft;

    public static int ROW = 5;
    public static int COL = 11;
    public static int FIRST = 1;
    public static int LAST = 9;
    public static int ZOMBIE_SPAWN = 10;
    public static int LAWN_MOWER = 0;

    public GameState() {
	grid = new Tile[ROW][COL];
	for (int row = 0; row < GameState.ROW; row++) {
	    for (int col = 0; col < GameState.COL; col++) {
		grid[row][col] = new Tile();
	    }
	}
	
	sunCounter = 0;
	levelFinished = false;
	turnNumber = 1;
	totalNumberOfZombies = 0;
	numberOfZombiesLeft = 0;
	pendingZombies = new LinkedList<Zombie>();
	disabledRows = new HashSet<Integer>();
    }

    public void addPlant(Plant plant, int row, int col) {
	grid[row][col].setResidingPlant(plant);
    }

    public void addPendingZombies(Map<ZombieTypes, Integer> zombies) {
	for (ZombieTypes z : zombies.keySet()) {
	    for (int i = 0; i < zombies.get(z); i++) {
		pendingZombies.add(ZombieFactory.createZombie(z));
	    }
	}
	totalNumberOfZombies = pendingZombies.size();
	numberOfZombiesLeft = totalNumberOfZombies;
	Collections.shuffle(pendingZombies);
    }
    
    public void addZombie(int row) {
	grid[row][ZOMBIE_SPAWN].addZombie(pendingZombies.get(0));
	pendingZombies.remove(0);
    }
    
    public int getNumberOfZombiesPending() {
	return pendingZombies.size();
    }

    public int getSunCounter() {
	return sunCounter;
    }

    public Tile[][] getGrid() {
	return grid;
    }

    public int getTurnNumber() {
	return turnNumber;
    }

    public void nextTurn() {
	turnNumber++;
    }

    public void gainSunCounter(int gain) {
	sunCounter += gain;
    }

    public boolean isLevelFinished() {
	return levelFinished;
    }

    public void addDisabledRow(int row) {
	disabledRows.add(row);
    }

    public boolean isRowDisabled(int row) {
	return disabledRows.contains(row);
    }
    
    public int getNumberOfDisabledRows() {
	return disabledRows.size();
    }
    
    public void spendSunCounter(int spent) {
	sunCounter -= spent;
    }
    
    public void levelDone() {
	levelFinished = true;
    }
    
    public void zombieDied() {
	numberOfZombiesLeft--;
    }
    
    public void zombieDied(int amount) {
	numberOfZombiesLeft -= amount;
    }
    
    public int getNumberOfZombiesLeft() {
	return numberOfZombiesLeft;
    }
    
    public int getTotalNumberOfZombies() {
	return totalNumberOfZombies;
    }
}