package game;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import plant.PlantName;
import zombie.ZombieTypes;

/**
 * This class represents a Level. A level has plants(the name of the plants that are available to purchase),
 * zombies(the name of the zombies that will appear), base sun gain(the base amount of sun gain),
 *  spawn rate(how fast are zombies spawning) and spawn amonut(how many per spawn).
 * 
 * @author Michael Fan 101029934
 * @version Oct 25, 2018
 */

public class Level {
	private Set<PlantName> plants;
	private Map<ZombieTypes, Integer> zombies;
	private int baseSunGain;
	private int levelID;
	private int spawnRate;
	private int spawnAmount;

	/**
	 * Creates a new level with the specified level ID.
	 * 
	 * @param levelID th ID of the level
	 */
	public Level(int levelID) {
		plants = new HashSet<PlantName>();
		zombies = new HashMap<ZombieTypes, Integer>();
		this.levelID = levelID;
	}

	/**
	 * Adds a plant that is available to purchase in this level.
	 * 
	 * @param plant the plant that is available to purchase in this level
	 */
	public void addPlant(PlantName plant) {
		plants.add(plant);
	}

	/**
	 * Adds a zombie that will appear in this level.
	 * 
	 * @param zombie the zombie that will appear in this level
	 * @param amount the amout that will appear
	 */
	public void addZombie(ZombieTypes zombie, int amount) {
		zombies.putIfAbsent(zombie, amount);
	}

	/**
	 * Returns the set of plants that are available to purchase in this level.
	 * 
	 * @return the set of plants that are available to purchase in this level
	 */
	public Set<PlantName> getPlants() {
		return plants;
	}

	/**
	 * Returns the set of zombies that will appear in this level.
	 * 
	 * @return the set of zombies that will appear in this level
	 */
	public Set<ZombieTypes> getAvailableZombies() {
		return zombies.keySet();
	}

	/**
	 * Returns a map of the zombie names and how many will appear.
	 * 
	 * @return a map of the zombie names and how many will appear
	 */
	public Map<ZombieTypes, Integer> getZombies() {
		return zombies;
	}
	
	/**
	 * Sets the base amount of sun gain.
	 * 
	 * @param sunGain the base amount of sun gain
	 */
	public void setBaseSunGain(int sunGain) {
		this.baseSunGain = sunGain;
	}
	
	/**
	 * Returns the base amount of sun gain.
	 * 
	 * @return the base amount of sun gain
	 */
	public int getBaseSunGain() {
		return baseSunGain;
	}
	
	/**
	 * Returns the level ID.
	 * 
	 * @return the level ID
	 */
	public int getLevelID() {
		return levelID;
	}
	
	/**
	 * Sets the spawn rate to the specified rate.
	 * 
	 * @param spawnRate the specified spawn rate
	 */
	public void setSpawnRate(int spawnRate) {
		this.spawnRate = spawnRate;
	}

	/**
	 * Returns the spawn rate.
	 * 
	 * @return the spawn rate
	 */
	public int getSpawnRate() {
		return spawnRate;
	}
	
	/**
	 * Sets the spawn amount to the specified amount.
	 * 
	 * @param spawnAmount the specified spawn amount
	 */
	public void setSpawnAmount(int spawnAmount) {
		this.spawnAmount = spawnAmount;
	}
	
	/**
	 * Returns the spawn amount.
	 * 
	 * @return the spawn amount
	 */
	public int getSpawnAmount() {
		return spawnAmount;
	}
}