/**
 * A test case for class zombie
 */
package test;

import junit.framework.TestCase;
import zombie.Walker;
import zombie.ZombieTypes;

public class TestZombie extends TestCase {
	private Walker walker;
	
	protected void setUp() {
		walker = new Walker();
	}
	
	public void testZombiesHealth() {
		walker.takeDamage(10);
		assertEquals("The zombie should have a health of 20", walker.getHealth(), 20);
		assertFalse("The zombie should not be dead", walker.isDead());
		walker.takeDamage(20);
		assertEquals("The zombie should have a health of 0", walker.getHealth(), 0);
		assertTrue("The zombie should be dead", walker.isDead());
	}
	
	public void testZombiesMovement() {
		walker.incrementMovementCounter();
		walker.incrementMovementCounter();
		assertEquals("The zombie should have a movement coutner value of 2", walker.getMovementCounter(), 2);
		assertFalse("A current zombie should not be ready to move to the next tile", walker.isReadyToMove());
		walker.incrementMovementCounter();
		assertTrue("A current zombie should be ready to move to the next tile", walker.isReadyToMove());
		walker.resetMovementCounter();
		assertEquals("The zombie should have a movement coutner value of 0", walker.getMovementCounter(), 0);
		assertFalse("A current zombie should not be ready to move to the next tile", walker.isReadyToMove());
	}
	
	public void testWalker() {
		assertEquals("A walker zombie should have a damage value of 10", walker.getDamage(), 10);
		assertEquals("A walker zombie should have a start health of 30", walker.getHealth(), 30);
		assertEquals("A walker zombie should have a base movement speed of 3", walker.getCurrentMovementSpeed(), 3);
		assertEquals("The walker should have 0 for its movement counter",walker.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a walker", walker.getZombieType(), ZombieTypes.WALKER);
		assertEquals("The walker zombie should have an attack range of 1", walker.getAtkRange(), 1);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestZombie.class);
	}
	
}
