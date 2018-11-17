package test;

import junit.framework.TestCase;
import plant.PeaShooter;
import plant.PlantName;
import plant.SunFlower;

public class TestPlant extends TestCase{
	private SunFlower sunflower;
	private PeaShooter peashooter;
	
	protected void setUp() {
		sunflower = new SunFlower();
		peashooter = new PeaShooter();
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
		assertEquals("The cooldown for purchasing a sunflower should be 1", sunflower.getCooldown(), 1);
		assertFalse("The sunflower should not have an ailment effect", sunflower.hasAilment());
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
		assertEquals("The cooldown for purchasing a peashooter should be 3", peashooter.getCooldown(), 3);
		assertFalse("The peashooter should not have an ailment effect", peashooter.hasAilment());
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestPlant.class);
	}
}
