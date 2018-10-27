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
		System.out.println(currentTurn);
	}
	/**
	 * prints the sun counter.
	 * 
	 */
	
	public void prtinSunCounter() {
		int sunCounter = game.getSunCounter();
		System.out.println(sunCounter);
	}

	/**
	 * prints the types of zombies.
	 * 
	 */
	public void printZombie() {
		String zombieType = game.getAvailableZombies();
		System.out.println(zombieType);
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
				System.out.print(readOnly[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * Prints the plants in the shop and their prices.
	 * 
	 */
	public void printShop() {
		String Printshop = game.getShopItems();
		System.out.println(Printshop);
	}
}
