package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import plant.PlantName;
import zombie.ZombieTypes;

/**
 * This class represents the LevelManager. The level manager handles level
 * loading. 
 * 
 * @author Michael Fan 101029934
 * @editor Hoang Bui 101029049
 * @version Oct 25, 2018
 */

public class LevelManager {
	private List<Level> levels;
	private int currentLevel;

	/**
	 * Creates a new LevelManager.
	 */
	public LevelManager() {
		levels = new LinkedList<Level>();
		currentLevel = -1;
		createLevels();
	}

	/**
	 * Adds a new level.
	 * 
	 * @param level the level to be added
	 */
	public void addLevel(Level level) {
		levels.add(level);
	}

	/**
	 * Returns the next level.
	 * 
	 * @return the next level
	 */
	public Level getNextLevel() {
		if((currentLevel + 1) >= levels.size()) {
			return null;
		}

		return levels.get(++currentLevel);
	}

	/**
	 * Returns the previous level.
	 * 
	 * @return the previous level
	 */
	public Level getPreviousLevel() {
		if(currentLevel == -1) {
			return null;
		}

		return levels.get(--currentLevel);
	}

	/**
	 * Returns the level at the specified index.
	 * 
	 * @param index the index of the level
	 * 
	 * @return the level at the specified index
	 */
	public Level getLevel(int index) {
		if(index < 0 || index >= levels.size()) {
			return null;
		}

		currentLevel = index;
		return levels.get(index);
	}

	/**
	 * Returns the total number of levels.
	 * 
	 * @return the total number of levels
	 */
	public int size() {
		return levels.size();
	}

	/**
	 * Returns the index of the current level.
	 * 
	 * @return the index of the current level
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
	
	/**
	 * Creates and appends new levels.
	 */
	public void createLevels() {
		//Create variables
		int levelCounter = 1;
		Set<PlantName> plants = new HashSet<PlantName>();
		Map<ZombieTypes, Integer> zombies = new HashMap<ZombieTypes, Integer>();
		
		//Add values into lists
		plants.add(PlantName.PeaShooter);
		plants.add(PlantName.SunFlower);
		zombies.put(ZombieTypes.WALKER, 10);
		
		//Create level 1
		createLevel(levelCounter, plants, zombies, 25, 2, 1);
		
		//Create level 2
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 20);
		zombies.put(ZombieTypes.CONEHAT, 25);
		
		createLevel(levelCounter, plants, zombies,  25, 2, 4);
		
		//Create level 3
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 30);
		zombies.put(ZombieTypes.CONEHAT, 30);
		zombies.put(ZombieTypes.BUCKETHAT, 20);
		
		createLevel(levelCounter, plants, zombies, 25, 5, 4);
		
		//Create level 4
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 40);
		zombies.put(ZombieTypes.CONEHAT, 30);
		zombies.put(ZombieTypes.BUCKETHAT, 25);
		zombies.put(ZombieTypes.FOOTBALL, 15);
		createLevel(levelCounter, plants, zombies, 25, 5, 4);
		
		//Create level 5
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 50);
		zombies.put(ZombieTypes.CONEHAT, 35);
		zombies.put(ZombieTypes.BUCKETHAT, 25);
		zombies.put(ZombieTypes.FOOTBALL, 15);
		zombies.put(ZombieTypes.NEWSPAPER, 15);
		createLevel(levelCounter, plants, zombies, 25, 5, 4);
		
	}
	
	/**
	 * 
	 */
	private void createLevel(int levelCounter, Set<PlantName> plants, Map<ZombieTypes, Integer> zombies, int baseSunGain, int spawnRate, int spawnAmount) {
		//Create new level
		Level level = new Level(levelCounter);
		
		//add plants
		level.addPlant(plants);
		
		//add zombies
		level.addZombie(zombies);
		
		//add spawn and generation values
		level.setBaseSunGain(baseSunGain);
		level.setSpawnRate(spawnRate);
		level.setSpawnAmount(spawnAmount);
		
		levels.add(level);
	}
	
	/**
	 * Returns a list of all the level IDs of the predefined levels.
	 * 
	 * @return a list of all the level IDs of the predefined levels
	 */
	public List<Integer> getAllPredefinedLevelID() {
		List<Integer> id = new ArrayList<Integer>();
		
		for(Level level : levels) {
			id.add(level.getLevelID());
		}
		
		return id;
	} 
}