package test;

import java.util.HashMap;
import java.util.Map;

import game.GameState;
import junit.framework.TestCase;
import zombie.ZombieTypes;

/**
 * A test class to test the game state
 * @author Hoang Bui, 101029049
 * @version 1
 */
public class TestGameState extends TestCase {
	private GameState gameState;

	protected void setUp() {
		gameState = new GameState();
	}

	public void testInitiation() {
		assertEquals("The initial sun counter should be 50", gameState.getSunCounter(), 50);
		assertEquals("Level number should start at 1", gameState.getTurnNumber(), 1);
		assertEquals("The number of zombies at the start of the game should be 0", gameState.getTotalNumberOfZombies(),
				0);
		assertEquals("The number of zombies left on the grid at the start of the game should be 0",
				gameState.getNumberOfZombiesLeft(), 0);
		assertEquals("The number of zombies pending should be 0", gameState.getNumberOfZombiesPending(), 0);
	}

	public void testSunCounter() {
		gameState.gainSun(50);
		assertEquals("The sun counter should be 100", gameState.getSunCounter(), 100);
		gameState.spendSun(100);
		assertEquals("The sun counter should be 0", gameState.getSunCounter(), 0);
	}

	public void testTurnNumber() {
		gameState.nextTurn();
		assertEquals("Level number should be 2", gameState.getTurnNumber(), 2);
	}

	public void testZombie() {
		Map<ZombieTypes, Integer> zombies = new HashMap<ZombieTypes, Integer>();
		zombies.put(ZombieTypes.WALKER, 5);
		gameState.addPendingZombies(zombies);

		assertEquals("The number of zombies should be 5", gameState.getTotalNumberOfZombies(), 5);
		assertEquals("The number of zombies left should be 5", gameState.getNumberOfZombiesLeft(), 5);
		assertEquals("The number of zombies pending should be 5", gameState.getNumberOfZombiesPending(), 5);

		gameState.addZombie(0);
		gameState.addZombie(1);
		assertEquals("The number of zombies should be 5", gameState.getTotalNumberOfZombies(), 5);
		assertEquals("The number of zombies left should be 2", gameState.getNumberOfZombiesLeft(), 5);
		assertEquals("The number of zombies pending should be 3", gameState.getNumberOfZombiesPending(), 3);

		gameState.zombieDied();
		gameState.zombieDied();
		gameState.addZombie(0);
		assertEquals("The number of zombies should be 5", gameState.getTotalNumberOfZombies(), 5);
		assertEquals("The number of zombies left should be 3", gameState.getNumberOfZombiesLeft(), 3);
		assertEquals("The number of zombies pending should be 2", gameState.getNumberOfZombiesPending(), 2);
	}

	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestGameState.class);
	}

}
