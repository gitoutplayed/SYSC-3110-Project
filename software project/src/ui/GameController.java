package ui;

import java.awt.event.*;
import javax.swing.*;

import game.Game;
import game.GameState;
import tile.Tile;

/**
 * This class represents the GameController. This class contains the main
 * method.
 * 
 * @author Michael Fan 101029934
 * @version Nov 17, 2018
 */
public class GameController {

	Game game;
	GameView gameView;

	/**
	 * Constructs a new GameController.
	 */
	public GameController() {
		gameView = new GameView();
		game = new Game(gameView);

		setupListeners();
	}

	/**
	 * Attaches the listeners to the buttons
	 */
	private void setupListeners() {
		// Grid listener
		JButton[][] buttonGrid = gameView.getButtonGrid();

		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				buttonGrid[row][col].addActionListener(new GridListener());
			}
		}

		// Menu listener
		gameView.getLoadNextLevel().addActionListener(new MenuListener());
		gameView.getLoadLevel().addActionListener(new MenuListener());
		gameView.getLoadPreviousLevel().addActionListener(new MenuListener());
		gameView.getUndo().addActionListener(new MenuListener());
		gameView.getRedo().addActionListener(new MenuListener());
		gameView.getRestart().addActionListener(new MenuListener());

		// End turn listener
		gameView.getEndTurn().addActionListener(new EndTurnListener());

		// Shovel listener
		gameView.getShovel().addActionListener(new ShovelListener());

		// Shop listener
		for(ShopButton button : gameView.getShopButtons()) {
			button.addActionListener(new ShopListener());
		}
	}

	/**
	 * The MenuListener. This class contains the actionPerformed method that will be
	 * called when a meun item is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 9, 2018
	 */
	private class MenuListener implements ActionListener {
		/**
		 * The action that is performed when a meun item is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();

			if(item == gameView.getLoadNextLevel()) {
				game.loadNextLevel();
			} else if(item == gameView.getLoadLevel()) {
				JOptionPane.showMessageDialog(gameView, "Will be implemented later when there are more level");
			} else if(item == gameView.getLoadPreviousLevel()) {
				JOptionPane.showMessageDialog(gameView, "Will be implemented later when there are more level");
			} else if(item == gameView.getUndo()) {
				game.undo();
			} else if(item == gameView.getRedo()) {
				game.redo();
			} else if(item == gameView.getRestart()) {
				game.restart();
			}
		}
	}

	/**
	 * The EndTurnListener. This class contains the actionPerformed method that will
	 * be called when the end turn button is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 16, 2018
	 */
	private class EndTurnListener implements ActionListener {
		/**
		 * The action that is performed when the end turn button is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			game.endTurn();
		}
	}

	/**
	 * The ShovelListener. This class contains the actionPerformed method that will
	 * be called when the shovel button is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 16, 2018
	 */
	private class ShovelListener implements ActionListener {
		/**
		 * The action that is performed when the shovel button is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			game.selectShovel(!game.isShovelSelected());
			game.selectPlant(null); // unselect plant when the shovel button is clicked
		}
	}

	/**
	 * The GridListener. This class contains the actionPerformed method that will be
	 * called when any button in the grid is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 16, 2018
	 */
	private class GridListener implements ActionListener {
		/**
		 * The action that is performed when any button in the grid is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			JButton[][] buttonGrid = gameView.getButtonGrid();

			for(int row = 0; row < GameState.ROW; row++) {
				for(int col = 0; col < GameState.COL; col++) {
					if(buttonGrid[row][col] == button) {
						if(game.isPlantSelected()) {
							game.buyPlant(row, col);
						} else if(game.isShovelSelected()) {
							game.shovel(row, col);
						}
					}
				}
			}
		}
	}

	/**
	 * The ShovelListener. This class contains the actionPerformed method that will
	 * be called when any button in the shop is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 16, 2018
	 */
	private class ShopListener implements ActionListener {
		/**
		 * The action that is performed when any button in the shop is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			ShopButton button = (ShopButton) e.getSource();
			game.selectPlant(button.getPlant());
			game.selectShovel(false); // unselect shovel when clicked in shop
		}
	}

	/**
	 * The main method.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		GameController controller = new GameController();
	}
}
