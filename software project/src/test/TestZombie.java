package test;

import junit.framework.TestCase;
import zombie.BucketHat;
import zombie.ConeHat;
import zombie.Football;
import zombie.Newspaper;
import zombie.Walker;
import zombie.ZombieTypes;

/**
 * A test class to test the zombies.
 * @author Hoang Bui, 101029049
 * @version 1
 */
public class TestZombie extends TestCase {
	private Walker walker;
	private BucketHat bucketHat;
	private ConeHat coneHat;
	private Football football;
	private Newspaper newspaper;
	
	protected void setUp() {
		walker = new Walker();
		bucketHat = new BucketHat();
		coneHat = new ConeHat();
		football = new Football();
		newspaper = new Newspaper();
	}
	
	public void testZombiesHealth() {
		walker.takeDamage(10);
		assertEquals("The zombie should have a health of 40", walker.getHealth(), 40);
		assertFalse("The zombie should not be dead", walker.isDead());
		walker.takeDamage(40);
		assertEquals("The zombie should have a health of 0", walker.getHealth(), 0);
		assertTrue("The zombie should be dead", walker.isDead());
	}
	
	public void testZombiesMovement() {
		walker.incrementMovementCounter();
		walker.incrementMovementCounter();
		assertEquals("The zombie should have a movement counter value of 2", walker.getMovementCounter(), 2);
		assertFalse("A current zombie should not be ready to move to the next tile", walker.isReadyToMove());
		walker.incrementMovementCounter();
		assertTrue("A current zombie should be ready to move to the next tile", walker.isReadyToMove());
		walker.resetMovementCounter();
		assertEquals("The zombie should have a movement counter value of 0", walker.getMovementCounter(), 0);
		assertFalse("A current zombie should not be ready to move to the next tile", walker.isReadyToMove());
	}
	
	public void testWalker() {
		assertEquals("A walker zombie should have a damage value of 30", walker.getDamage(), 30);
		assertEquals("A walker zombie should have a start health of 50", walker.getHealth(), 50);
		assertEquals("A walker zombie should have a base movement speed of 3", walker.getCurrentMovementSpeed(), 3);
		assertEquals("The walker should have 0 for its movement counter",walker.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a walker", walker.getZombieType(), ZombieTypes.WALKER);
		assertEquals("The walker zombie should have an attack range of 1", walker.getAtkRange(), 1);
	}
	public void testConeHat() {
		assertEquals("A cone hat zombie should have a damage value of 50", coneHat.getDamage(), 50);
		assertEquals("A cone hat zombie should have a start health of 120", coneHat.getHealth(), 120);
		assertEquals("A cone hat zombie should have a base movement speed of 3", coneHat.getCurrentMovementSpeed(), 4);
		assertEquals("The cone hat should have 0 for its movement counter", coneHat.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a cone hat", coneHat.getZombieType(), ZombieTypes.CONEHAT);
		assertEquals("The cone hat zombie should have an attack range of 1", coneHat.getAtkRange(), 1);
	}
	public void testBucketHat() {
		assertEquals("A bucket hat zombie should have a damage value of 100", bucketHat.getDamage(), 100);
		assertEquals("A bucket hat zombie should have a start health of 200", bucketHat.getHealth(), 200);
		assertEquals("A bucket hat zombie should have a base movement speed of 5", bucketHat.getCurrentMovementSpeed(), 5);
		assertEquals("The bucket hat should have 0 for its movement counter",bucketHat.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a bucket hat zombie", bucketHat.getZombieType(), ZombieTypes.BUCKETHAT);
		assertEquals("The bucket hat zombie should have an attack range of 1", bucketHat.getAtkRange(), 1);
	}
	public void testFootball() {
		assertEquals("A football zombie should have a damage value of 30", football.getDamage(), 30);
		assertEquals("A football zombie should have a start health of 100", football.getHealth(), 100);
		assertEquals("A football zombie should have a base movement speed of 1", football.getCurrentMovementSpeed(), 1);
		assertEquals("The football zombie should have 0 for its movement counter",football.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a football zombie", football.getZombieType(), ZombieTypes.FOOTBALL);
		assertEquals("The football zombie should have an attack range of 1", football.getAtkRange(), 1);
	}
	public void testNewspaper() {
		assertEquals("A Newspaper zombie should have a damage value of 50", newspaper.getDamage(), 50);
		assertEquals("A Newspaper zombie should have a start health of 150", newspaper.getHealth(), 150);
		assertEquals("A Newspaper zombie should have a base movement speed of 2", newspaper.getCurrentMovementSpeed(), 2);
		assertEquals("The Newspaper should have 0 for its movement counter",newspaper.getMovementCounter(), 0);
		assertEquals("The type of zombie should be a Newspaper", newspaper.getZombieType(), ZombieTypes.NEWSPAPER);
		assertEquals("The Newspaper zombie should have an attack range of 1", newspaper.getAtkRange(), 1);
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestZombie.class);
	}
	
}
