package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
 * @author Hoang Bui 101029049
 * @version Dec 2, 2018
 */

public class LevelManager implements java.io.Serializable {
	private List<Level> predefinedLevels;
	private List<Level> customLevels;
	private int currentLevel;
	public enum Mode {CUSTOM, PREDEFINED};
	private Mode mode;

	/**
	 * Creates a new LevelManager.
	 */
	public LevelManager() {
		predefinedLevels = new ArrayList<Level>();
		customLevels = new ArrayList<Level>();
		currentLevel = -1;
		mode = Mode.PREDEFINED;
		createLevels();
	}

	/**
	 * Returns the next level.
	 * 
	 * @return the next level
	 */
	public Level getNextLevel() {
		List<Level> levels = (mode == Mode.CUSTOM) ? customLevels : predefinedLevels;
		
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
		List<Level> levels = (mode == Mode.CUSTOM) ? customLevels : predefinedLevels;
		
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
		List<Level> levels = (mode == Mode.CUSTOM) ? customLevels : predefinedLevels;
		
		if(index < 0 || index > levels.size()) {
			return null;
		}

		currentLevel = index;
		return levels.get(index);
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
		// Create variables
		int levelCounter = 1;
		Set<PlantName> plants = new HashSet<PlantName>();
		Map<ZombieTypes, Integer> zombies = new HashMap<ZombieTypes, Integer>();

		// Add values into lists
		plants.add(PlantName.PeaShooter);
		plants.add(PlantName.SunFlower);
		zombies.put(ZombieTypes.WALKER, 10);

		// Create level 1
		predefinedLevels.add(createLevel(levelCounter, plants, zombies, 25, 2, 1));

		// Create level 2
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 20);
		zombies.put(ZombieTypes.CONEHAT, 25);

		predefinedLevels.add(createLevel(levelCounter, plants, zombies, 25, 2, 4));

		// Create level 3
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 30);
		zombies.put(ZombieTypes.CONEHAT, 30);
		zombies.put(ZombieTypes.BUCKETHAT, 20);
		plants.add(PlantName.Wallnut);

		predefinedLevels.add(createLevel(levelCounter, plants, zombies, 25, 5, 4));

		// Create level 4
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 40);
		zombies.put(ZombieTypes.CONEHAT, 30);
		zombies.put(ZombieTypes.BUCKETHAT, 25);
		zombies.put(ZombieTypes.FOOTBALL, 15);
		plants.add(PlantName.DuelSunflower);

		predefinedLevels.add(createLevel(levelCounter, plants, zombies, 25, 5, 4));

		// Create level 5
		levelCounter++;
		zombies.put(ZombieTypes.WALKER, 50);
		zombies.put(ZombieTypes.CONEHAT, 35);
		zombies.put(ZombieTypes.BUCKETHAT, 25);
		zombies.put(ZombieTypes.FOOTBALL, 15);
		zombies.put(ZombieTypes.NEWSPAPER, 15);
		plants.add(PlantName.Repeater);

		predefinedLevels.add(createLevel(levelCounter, plants, zombies, 25, 5, 4));

	}

	/**
	 * Creates a new level.
	 * 
	 * @param levelCounter the level counter
	 * @param plants the plants in the level
	 * @param zombies the zombies in the level
	 * @param baseSunGain the base sun gain
	 * @param spawnRate the spawn rate
	 * @param spawnAmount the spawn amount
	 * 
	 * @return the new level
	 */
	public Level createLevel(int levelCounter, Set<PlantName> plants, Map<ZombieTypes, Integer> zombies,
			int baseSunGain, int spawnRate, int spawnAmount) {
		// Create new level
		Level level = new Level(levelCounter);

		// add plants
		level.addPlant(plants);

		// add zombies
		level.addZombie(zombies);

		// add spawn and generation values
		level.setBaseSunGain(baseSunGain);
		level.setSpawnRate(spawnRate);
		level.setSpawnAmount(spawnAmount);

		return level;
	}

	/**
	 * Sets the level ID of the current level.
	 * 
	 * @param levelID the level ID of the current level
	 */
	public void setLevelID(int levelID) {
		currentLevel = levelID;
	}

	/**
	 * Returns a list of all the level IDs of the predefined levels.
	 * 
	 * @return a list of all the level IDs of the predefined levels
	 */
	public List<Integer> getAllPredefinedLevelID() {
		List<Integer> id = new ArrayList<Integer>();

		for(Level level : predefinedLevels) {
			id.add(level.getLevelID());
		}

		return id;
	}
	
	/**
	 * Returns a list of all the custom levels.
	 * 
	 * @return a list of all the custom levels
	 */
	public List<String> getAllCustomLevels() {
		List<String> levels = new ArrayList<String>();
		
		for(Level level : customLevels) {
			levels.add(level.getName());
		}
		
		return levels;
	}
	
	/**
	 * Creates a custom level.
	 *
	 * @param plants the plants in the level
	 * @param zombies the zombies in the level
	 * @param baseSunGain the base sun gain
	 * @param spawnRate the spawn rate
	 * @param spawnAmount the spawn amount
	 * 
	 * @return the custom level
	 */
	public Level createCustomLevel(String name, Set<PlantName> plants, Map<ZombieTypes, Integer> zombies,
			int baseSunGain, int spawnRate, int spawnAmount) {
		Level level = createLevel(-1, plants, zombies, baseSunGain, spawnRate, spawnAmount);
		level.setName(name);
		
		return level;
	}
	
	/**
	 * Adds a custom level.
	 * 
	 * @param level the custom level to be added
	 */
	public void addCustomLevel(Level level) {
		customLevels.add(level);
	}
	
	/**
	 * Sets the mode of level manager.
	 * 
	 * @param mode the mode of the level manager
	 */
	public void setMode(Mode mode) {
		this.mode = mode;
	}
}