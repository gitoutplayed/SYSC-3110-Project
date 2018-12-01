package test;

import junit.framework.TestCase;
import plant.DuelSunflower;
import plant.PeaShooter;
import plant.PlantName;
import plant.Repeater;
import plant.SunFlower;
import plant.Wallnut;

/**
 * A test class to test the plants.
 * @author Hoang Bui, 101029049
 * @version 1
 */
public class TestPlant extends TestCase{
	private SunFlower sunflower;
	private PeaShooter peashooter;
	private Repeater repeater;
	private Wallnut wallnut;
	private DuelSunflower duelSunflower;
	
	protected void setUp() {
		sunflower = new SunFlower();
		peashooter = new PeaShooter();
		repeater = new Repeater();
		wallnut = new Wallnut();
		duelSunflower = new DuelSunflower();
	}
	
	public void testPlantHealth() {
		sunflower.takeDmg(30);
		assertEquals("The plants health shoud be 10", sunflower.getHealth(), 10);
		assertFalse("The plants should not be dead", sunflower.isDead());
		sunflower.takeDmg(10);
		assertEquals("The plants health shoud be 0", sunflower.getHealth(), 0);
		assertTrue("The plants should be dead", sunflower.isDead());
	}
	
	public void testSunflower() {
		assertEquals("The plant's type should be sunflower", sunflower.getName(), PlantName.SunFlower);
		assertEquals("The sunflower's base health should be 40", sunflower.getHealth(), 40);
		assertEquals("The sunflower's price should be 50", sunflower.getPrice(), 50);
		assertEquals("The resource generation should be 25 per turn", sunflower.getResrc_gen(), 25);
		assertEquals("The atk range should be 0 in the x", sunflower.getAtkRange_X(), 0);
		assertEquals("The atk range should be 0 in the y", sunflower.getAtkRange_Y(), 0);
		assertTrue("The sunflower should be a ressource generator", sunflower.canResrc_gen());
		assertFalse("The sunflower should not be able to attack", sunflower.canAttack());
		assertEquals("A sunflower should have a base dmg of 0", sunflower.getDamage(), 0);
		assertEquals("The cooldown for purchasing a sunflower should be 1", sunflower.getCooldown(), 3);
	}
	
	public void testDuelSunflower() {
		assertEquals("The plant's type should be a duel sunflower", duelSunflower.getName(), PlantName.DuelSunflower);
		assertEquals("The duel sunflower's base health should be 40", duelSunflower.getHealth(), 40);
		assertEquals("The duel sunflower's price should be 200", duelSunflower.getPrice(), 200);
		assertEquals("The resource generation should be 50 per turn", duelSunflower.getResrc_gen(), 50);
		assertEquals("The atk range should be 0 in the x", duelSunflower.getAtkRange_X(), 0);
		assertEquals("The atk range should be 0 in the y", duelSunflower.getAtkRange_Y(), 0);
		assertTrue("The duel sunflower should be a ressource generator", duelSunflower.canResrc_gen());
		assertFalse("The duel sunflower should not be able to attack", duelSunflower.canAttack());
		assertEquals("A duel sunflower should have a base dmg of 0", duelSunflower.getDamage(), 0);
		assertEquals("The cooldown for purchasing a duel sunflower should be 5", duelSunflower.getCooldown(), 5);
	}
	
	public void testPeashooter() {
		assertEquals("The plant's type should be peashooter", peashooter.getName(), PlantName.PeaShooter);
		assertEquals("The peashooter's base health should be 40", peashooter.getHealth(), 40);
		assertEquals("The peashooter's price should be 100", peashooter.getPrice(), 100);
		assertEquals("The resource generation should be 0 per turn", peashooter.getResrc_gen(), 0);
		assertEquals("The atk range should be 100 in the x", peashooter.getAtkRange_X(), 100);
		assertEquals("The atk range should be 1 in the y", peashooter.getAtkRange_Y(), 1);
		assertFalse("The peashooter not be should be a ressource generator", peashooter.canResrc_gen());
		assertTrue("The peashooter should be able to attack", peashooter.canAttack());
		assertEquals("A peashooter should have a base dmg of 10", peashooter.getDamage(), 10);
		assertEquals("The cooldown for purchasing a peashooter should be 3", peashooter.getCooldown(), 3);
	}
	
	public void testRepeater() {
		assertEquals("The plant's type should be repeater", repeater.getName(), PlantName.Repeater);
		assertEquals("The repeater's base health should be 30", repeater.getHealth(), 30);
		assertEquals("Therepeater's price should be 200", repeater.getPrice(), 200);
		assertEquals("The resource generation should be 0 per turn", repeater.getResrc_gen(), 0);
		assertEquals("The atk range should be 100 in the x", repeater.getAtkRange_X(), 100);
		assertEquals("The atk range should be 1 in the y", repeater.getAtkRange_Y(), 1);
		assertFalse("The repeater not be should be a ressource generator", repeater.canResrc_gen());
		assertTrue("The repeater should be able to attack", repeater.canAttack());
		assertEquals("A peashooter should have a base dmg of 20", repeater.getDamage(), 20);
		assertEquals("The cooldown for purchasing a repeater should be 3", repeater.getCooldown(), 3);
	}
	
	public void testWallnut() {
		assertEquals("The plant's type should be wallnut", wallnut.getName(), PlantName.Wallnut);
		assertEquals("The repeater's base health should be 30", wallnut.getHealth(), 100);
		assertEquals("The wallnut's price should be 25", wallnut.getPrice(), 25);
		assertEquals("The resource generation should be 0 per turn", wallnut.getResrc_gen(), 0);
		assertEquals("The atk range should be 0 in the x", wallnut.getAtkRange_X(), 0);
		assertEquals("The atk range should be 0 in the y", wallnut.getAtkRange_Y(), 0);
		assertFalse("The wallnut not be should be a ressource generator", wallnut.canResrc_gen());
		assertFalse("The wallnut should not be able to attack", wallnut.canAttack());
		assertEquals("A wallnut should have a base dmg of 0", wallnut.getDamage(), 0);
		assertEquals("The cooldown for purchasing a wallnut should be 5", wallnut.getCooldown(), 7);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestPlant.class);
	}
}
