/**
 * A test case for class zombie
 */
package test;

import junit.framework.TestCase;
import zombie.BucketHat;
import zombie.ConeHat;
import zombie.Walker;
import zombie.ZombieTypes;

public class TestZombie extends TestCase {
	private Walker walker;
	private BucketHat bucketHat;
	private ConeHat coneHat;
	
	protected void setUp() {
		walker = new Walker();
		bucketHat = new BucketHat();
		coneHat = new ConeHat();
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
	public void testConeHat() {
		assertEquals("A cone hat zombie should have a damage value of 10", coneHat.getDamage(), 20);
		assertEquals("A cone hat zombie should have a start health of 30", coneHat.getHealth(), 50);
		assertEquals("A cone hat zombie should have a base movement speed of 3", coneHat.getCurrentMovementSpeed(), 4);
		assertEquals("The cone hat should have 0 for its movement counter", coneHat.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a cone hat", coneHat.getZombieType(), ZombieTypes.CONEHAT);
		assertEquals("The cone hat zombie should have an attack range of 1", coneHat.getAtkRange(), 1);
	}
	public void testBucketHat() {
		assertEquals("A bucket hat zombie should have a damage value of 30", bucketHat.getDamage(), 30);
		assertEquals("A bucket hat zombie should have a start health of 100", bucketHat.getHealth(), 100);
		assertEquals("A bucket hat zombie should have a base movement speed of 5", bucketHat.getCurrentMovementSpeed(), 5);
		assertEquals("The bucket hat should have 0 for its movement counter",bucketHat.getMovementCounter(), 0);
		assertEquals("The bucket hat of zombie should be a walker", bucketHat.getZombieType(), ZombieTypes.BUCKETHAT);
		assertEquals("The bucket hat zombie should have an attack range of 1", bucketHat.getAtkRange(), 1);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestZombie.class);
	}
	
}
