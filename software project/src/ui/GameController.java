package ui;

import java.awt.event.*;
import java.io.File;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

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
		gameView.getLoadSave().addActionListener(new MenuListener());
		gameView.getSave().addActionListener(new MenuListener());

		// End turn listener
		gameView.getEndTurn().addActionListener(new EndTurnListener());

		// Shovel listener
		gameView.getShovel().addActionListener(new ShovelListener());

		// Shop listener
		for(ShopButton button : gameView.getShopButtons()) {
			button.addActionListener(new ShopListener());
		}

		// Popup listener
		gameView.getPopupButton().addActionListener(new PopupListener());

		// LevelChooser listener
		gameView.getCloseButton().addActionListener(new PopupListener());
		gameView.getLoadButton().addActionListener(new PopupListener());
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
				if(game.isLevelLoaded() && !game.isLevelFinished()) {
					game.loadLevel(1); // this is just to trigger the error message
					return;
				}
				
				if(gameView.getPopup() == null) {
					gameView.setupPopup(gameView.getLevelChooserPanel());
					gameView.getPopup().show();
				}
			} else if(item == gameView.getLoadPreviousLevel()) {
				game.loadPreviousLevel();
			} else if(item == gameView.getUndo()) {
				game.undo();
			} else if(item == gameView.getRedo()) {
				game.redo();
			} else if(item == gameView.getRestart()) {
				game.restart();
			} else if(item == gameView.getLoadSave()) {
				game.loadSave(load());
			} else if(item == gameView.getSave()) {
				game.save(save());
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

						// Display all the zombies on a tile
						if(game.isLevelLoaded()) {
							if(game.getGrid()[row][col].hasZombie() && gameView.getPopup() == null) {
								Tile tile = game.getGrid()[row][col];
								gameView.setupPopup(gameView.getPopupPanel());
								gameView.addZombiesToPopup(tile.getResidingZombie(), tile.getTileType());
								gameView.getPopup().show();
							}
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
	 * The PopupListener. This class contains the actionPerformed method that will
	 * be called when any button on the popup panel is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 25, 2018
	 */
	private class PopupListener implements ActionListener {
		/**
		 * The action that is performed when any button on the popup panel is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			JButton button = (JButton) e.getSource();
			
			// PopupPanel
			if(button == gameView.getPopupButton()) {
				gameView.clearPopupPanel();
			}
			
			// LevelChooserPanel
			if(button == gameView.getLoadButton()) {
				// Do nothing if no level is selected
				if(gameView.getPredefinedLevelsList().getSelectedIndex() < 0) {
					return;
				}
				int levelID = gameView.getPredefinedLevelsList().getSelectedValue();
				game.loadLevel(levelID);
			}
			
			gameView.disposePopup();
		}
	}
	
	/**
	 * Opens up the save file chooser
	 */
	private File save() {
		JFileChooser fileChooser = new JFileChooser();
		
		int option = fileChooser.showSaveDialog(gameView);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
		}
	}
	
	/**
	 * Opens up the load file chooser
	 */
	private File load() {
JFileChooser fileChooser = new JFileChooser();
		
		int option = fileChooser.showOpenDialog(gameView);
		
		if(option == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		} else {
			return null;
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
