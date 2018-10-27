import java.util.List;

import javax.swing.JButton;

/**
 * This class represents the GameView.
 * 
 * @author Tamer Ibrahim 101032919
 */
public class GameView {

	private Game game;

	public GameView(Game game) {
		this.game = game;
	}
	
	/**
	 * prints the current turn number
	 */
	public void printCurrentTurn() {
		int currentTurn= game.getTurnNumber();
		System.out.println("Turn: " + currentTurn); // Just adding a descriptive label. You can put any label you want.
	}
	/**
	 * prints the sun counter.
	 * 
	 */
	
	public void prtinSunCounter() {
		int sunCounter = game.getSunCounter();
		System.out.println("Sun Counter: " + sunCounter); // Just adding a descriptive label. You can put any label you want.
	}

	/**
	 * prints the types of zombies.
	 * 
	 */
	public void printZombie() {
		String zombieType = game.getAvailableZombies();
		System.out.println("Zombies: " + zombieType); // Just adding a descriptive label. You can put any label you want.
	}

	/**
	 * prints the grid.
	 * 
	 */
	public void printGrid() {
		// loop through the state and print line by line
		String[][] readOnly = game.getGrid();
		for (int i = 0; i < readOnly.length; i++) {
			for (int j = 0; j < readOnly[i].length; j++) {
				System.out.print(readOnly[i][j] + " ");
			}
			System.out.println();
		}
	}

	/**
	 * Prints the plants in the shop and their prices.
	 * 
	 */
	public void printShop() {
		String Printshop = game.getShopPlants();
		System.out.println("Shop: "); // Just adding a descriptive label. You can put any label you want.
		System.out.println(Printshop);
	}
	
	/**
	 * Prints the total number of zombies that will appear in this game.
	 */
	public void printTotalNumberOfZombies() {
		int total = game.getTotalNumberOfZombies();
		System.out.println("Total number of zombies: " + total);
	}
	
	/**
	 * Prints the number of zombies are pending
	 */
	public void printNumberOfPendingZombies() {
		int pending = game.getNumberOfZombiesPending();
		System.out.println("Number of zombies pending: " + pending);
	}
	
	/**
	 * Prints the number of zombies of left.
	 */
	public void printNumberOfZombiesLeft() {
		int left = game.getNumberOfZombiesLeft(); 
		System.out.println("Number of zombies left: "+ left);
	}
}
