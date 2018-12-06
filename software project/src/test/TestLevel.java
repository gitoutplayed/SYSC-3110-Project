package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game.Level;
import game.LevelManager;
import junit.framework.TestCase;
import plant.PlantName;
import zombie.ZombieTypes;

/**
 * A test class to test class Game
 * @author Hoang Bui, 101029049
 * @version 1
 */

public class TestLevel extends TestCase{
	private Level level;
	private LevelManager manager;
	private Set<PlantName> plants;
	private Map<ZombieTypes, Integer> zombies;
	
	protected void setUp() {
		manager = new LevelManager();
		plants = new HashSet<PlantName>();
		zombies = new HashMap<ZombieTypes, Integer>();
		
		plants.add(PlantName.PeaShooter);
		plants.add(PlantName.SunFlower);
		zombies.put(ZombieTypes.WALKER, 10);

		level = manager.getLevel(0);
	}

	public void testLevelCreation() {
		//works the same way as custom levels
		assertEquals("The level should have a set of plants with peashooter and sunflower", level.getPlants(), plants);
		assertEquals("The level should have a set of zombies that holds 10 walkers", level.getZombies(), zombies);
		assertEquals("The base sun gain should be 25", level.getBaseSunGain(), 25);
		assertEquals("The level id should be 1", level.getLevelID(), 1);
		assertEquals("The level spawn rate should be 2", level.getSpawnRate(), 2);
		assertEquals("The level spawn amount should be 1", level.getSpawnAmount(), 1);
	}
	
	public void testManager() {
		//Prepare for more tests
		List<Integer> id = new ArrayList<Integer>();
		id.add(1);
		id.add(2);
		id.add(3);
		id.add(4);
		id.add(5);
		
		assertEquals("The level ids should be the same", manager.getAllPredefinedLevelID(), id);
		assertEquals("The current level should be -1", manager.getCurrentLevel(), 0);
		assertEquals("Both levels should be Level 1", manager.getLevel(0), level);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestLevel.class);
	}
}
