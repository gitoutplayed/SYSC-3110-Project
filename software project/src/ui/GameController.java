package ui;

import java.awt.event.*;
import javax.swing.*;

import game.Game;
import game.GameState;
import tile.Tile;

/**
 * This class represents the GameController.
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
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

		// Menu Listener
		gameView.getStart().addActionListener(new MenuListener());
		gameView.getLoadNextLevel().addActionListener(new MenuListener());
		gameView.getLoadLevel().addActionListener(new MenuListener());
		gameView.getLoadPreviousLevel().addActionListener(new MenuListener());

		// End turn
		gameView.getEndTurn().addActionListener(new EndTurnListener());

		// Shovel
		gameView.getShovel().addActionListener(new ShovelListener());
	}

	/**
	 * The MenuListener
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 9, 2018
	 */
	private class MenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();

			if(item == gameView.getStart()) {
				game.start();
			} else if(item == gameView.getLoadNextLevel()) {
				game.loadNextLevel();
			} else if(item == gameView.getLoadLevel()) {
				JOptionPane.showMessageDialog(gameView, "Will be implemented later when there are more level");
			} else if(item == gameView.getLoadPreviousLevel()) {
				JOptionPane.showMessageDialog(gameView, "Will be implemented later when there are more level");
			}
		}
	}

	private class EndTurnListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.endTurn();
		}
	}

	private class ShovelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			game.selectShovel(!game.isShovelSelected());
		}
	}

	private class GridListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			JButton[][] buttonGrid = gameView.getButtonGrid();

			for(int row = 0; row < GameState.ROW; row++) {
				for(int col = 0; col < GameState.COL; col++) {
					if(buttonGrid[row][col] == button) {
						if(game.isShovelSelected()) {
							game.shovel(row, col);
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		GameController controller = new GameController();
	}
}
