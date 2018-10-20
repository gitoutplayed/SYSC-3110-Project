import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class represents a Level.
 * 
 * @author Michael Fan 101029934
 */

public class Level {
    private Set<PlantName> plants;
    private Map<ZombieTypes, Integer> zombies;
    private int baseSunCounterGain;
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
    
    public Map<ZombieTypes, Integer> getZombies() {
	return zombies;
    }

    public void setBaseSunCounterGain(int sunCounterGain) {
	this.baseSunCounterGain = sunCounterGain;
    }

    public int getBaseSunCounterGain() {
	return baseSunCounterGain;
    }

    public int getLevelID() {
	return levelID;
    }
    
    public void setSpawnRate(int spawnRate) {
	this.spawnRate = spawnRate;
    }
    
    public int getSpawnRate() {
	return spawnRate;
    }
    
    public void setSpawnAmount(int spawnAmount) {
	this.spawnAmount = spawnAmount;
    }
    
    public int getSpawnAmount() {
	return spawnAmount;
    }
}