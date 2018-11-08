package game;
import java.util.LinkedList;
import java.util.List;

import plant.PlantName;
import zombie.ZombieTypes;

/**
 * This class represents the LevelManager. The level manager handles level
 * loading. 
 * 
 * @author Michael Fan 101029934
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
		if(currentLevel == 0) {
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
		// Create the levels here
		Level level = new Level(1);
		level.addPlant(PlantName.PeaShooter);
		level.addPlant(PlantName.SunFlower);
		level.addZombie(ZombieTypes.WALKER, 10);
		level.setBaseSunGain(25);
		level.setSpawnRate(2);
		level.setSpawnAmount(1);

		levels.add(level);
	}
}