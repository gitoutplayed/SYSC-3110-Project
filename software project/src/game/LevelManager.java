package game;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
		//Create variables
		int levelCounter = 1;
		ArrayList<PlantName> plants = new ArrayList<PlantName>();
		ArrayList<ZombieTypes> zombies = new ArrayList<ZombieTypes>();
		ArrayList<Integer> numZombies = new ArrayList<Integer>();
		
		//Add values into lists
		plants.add(PlantName.PeaShooter);
		plants.add(PlantName.SunFlower);
		zombies.add(ZombieTypes.WALKER);
		numZombies.add(10);
		
		//Create level 1
		createLevelsHelper(levelCounter, plants, zombies, numZombies, 25, 2, 1);
		
		//Create level 2
		levelCounter++;
		zombies.add(ZombieTypes.CONEHAT);
		numZombies.set(0, 25);
		numZombies.add(20);
		
		createLevelsHelper(levelCounter, plants, zombies, numZombies, 25, 4, 3);
		
		//Create level 3
		levelCounter++;
		zombies.add(ZombieTypes.BUCKETHAT);
		numZombies.set(0, 30);
		numZombies.set(1, 25);
		numZombies.add(20);
		
		createLevelsHelper(levelCounter, plants, zombies, numZombies, 25, 5, 3);
		
//		Level level = new Level(1);
//		level.addPlant(PlantName.PeaShooter);
//		level.addPlant(PlantName.SunFlower);
//		level.addZombie(ZombieTypes.WALKER, 10);
//		level.setBaseSunGain(25);
//		level.setSpawnRate(2);
//		level.setSpawnAmount(1);

//		levels.add(level);
	}
	
	/**
	 * 
	 */
	private void createLevelsHelper(int levelCounter, ArrayList<PlantName> plants, ArrayList<ZombieTypes> zombies, ArrayList<Integer> zombieNum, int baseSunGain, int spawnRate, int spawnAmount) {
		//Create new level
		Level level = new Level(levelCounter);
		
		//add plants
		for(PlantName plant : plants) {
			level.addPlant(plant);
		}	
		
		//add zombies
		for(int i = 0; i < zombies.size(); i++) {
			level.addZombie(zombies.get(i), zombieNum.get(i));
		}
		
		//add spawn and generation values
		level.setBaseSunGain(baseSunGain);
		level.setSpawnRate(spawnRate);
		level.setSpawnAmount(spawnAmount);
		
		levels.add(level);
	}
}