package test;

import game.Level;
import game.LevelManager;
import game.Shop;
import junit.framework.TestCase;
import plant.PlantName;
/**
 * A test class to test the shop.
 * @author Hoang Bui, 101029049
 * @version 1
 */
public class TestShop extends TestCase{
	private Level level;
	private LevelManager manager;
	private Shop shop;
	private int sunCounter;
	
	protected void setUp() {
		manager = new LevelManager();
		shop = new Shop();
		level = manager.getLevel(0);
		sunCounter = 50;
		shop.addPlants(level.getPlants());
	}
	
	public void testPurchase() {
		assertTrue("Should be able to purchase a sunflower", shop.canPurchase(PlantName.SunFlower, sunCounter));
		assertFalse("Should not be able to purchase a peashooter", shop.canPurchase(PlantName.PeaShooter, sunCounter));
		shop.purchase(PlantName.SunFlower);
		assertTrue("Sunflower should be on cooldown", shop.isPlantOnCooldown(PlantName.SunFlower));
		assertFalse("Peashooter should not be on cooldown", shop.isPlantOnCooldown(PlantName.PeaShooter));
		shop.reduceCooldowns();
		shop.reduceCooldowns();
		shop.reduceCooldowns();
		assertFalse("Sunflower should not be on cooldown", shop.isPlantOnCooldown(PlantName.SunFlower));
	}
	
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestShop.class);
	}
}
