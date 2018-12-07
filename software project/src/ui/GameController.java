package ui;

import java.awt.event.*;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.event.PopupMenuListener;

import game.Game;
import game.GameState;
import game.LevelManager;
import plant.PlantName;
import tile.Tile;
import zombie.ZombieTypes;

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
		gameView.getBuildLevel().addActionListener(new MenuListener());
		gameView.getLoadCustomLevel().addActionListener(new MenuListener());

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
		gameView.getCloseButton().addActionListener(new PopupListener());
		gameView.getLoadButton().addActionListener(new PopupListener());
		gameView.getDoneButton().addActionListener(new PopupListener());
		gameView.getCancelButton().addActionListener(new PopupListener());

		// LevelBuilderPanel Listener
		for(ShopButton button : gameView.getAvailablePlants()) {
			button.addActionListener(new LevelBuilderListener());
		}
		for(ZombieButton button : gameView.getAvailableZombies()) {
			button.addActionListener(new LevelBuilderListener());
		}
	}

	/**
	 * The MenuListener. This class contains the actionPerformed method that will be
	 * called when a menu item is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Nov 9, 2018
	 */
	private class MenuListener implements ActionListener {
		/**
		 * The action that is performed when a menu item is clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();

			if(item == gameView.getLoadNextLevel()) {
				game.loadNextLevel();
			} else if(item == gameView.getLoadLevel()) {
				if(game.isLevelLoaded()) {
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
				if(game.isLevelLoaded()) {
					return;
				}
				game.loadSave(load());
			} else if(item == gameView.getSave()) {
				game.save(save());
			} else if(item == gameView.getBuildLevel()) {
				if(game.isLevelLoaded()) {
					return;
				}
				
				if(gameView.getPopup() == null) {
					gameView.setUpLevelBuilderPanel();
					gameView.getPopup().show();
				}
			} else if(item == gameView.getLoadCustomLevel()) {
				if(game.isLevelLoaded()) {
					return;
				}
				
				game.loadCustomLevels(loadMultiFile());
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
			else if(button == gameView.getLoadButton()) {
				if(gameView.getPredefinedLevelsList().getSelectedIndex() > -1) {
					game.setLevelManagerMode(LevelManager.Mode.PREDEFINED);
					game.loadLevel(gameView.getPredefinedLevelsList().getSelectedIndex());
				} else if(gameView.getCustomLevelsList().getSelectedIndex() > -1) {
					game.setLevelManagerMode(LevelManager.Mode.CUSTOM);
					game.loadLevel(gameView.getCustomLevelsList().getSelectedIndex());
				} 
			}
			// LevelBuilderPanel
			else if(button == gameView.getDoneButton()) {
				if(gameView.getChosenPlants().isEmpty() || gameView.getChosenZombies().isEmpty()) {
					gameView.disposePopup();
					gameView.showMessage("No plant or zombie is chosen");
					return;
				}
				
				Set<PlantName> plants = new HashSet<PlantName>();
				for(ShopButton plant : gameView.getChosenPlants()) {
					plants.add(plant.getPlant());
				}
				
				Map<ZombieTypes, Integer> zombies = new HashMap<ZombieTypes, Integer>();
				for(ZombieButton zombie : gameView.getChosenZombies()) {
					zombies.put(zombie.getZombie(), zombies.getOrDefault(zombie.getZombie(), 0) + 1);
				}
				
				Integer spawnRate = (Integer) gameView.getSpawnRate().getValue();
				Integer spawnAmount = (Integer) gameView.getSpawnAmount().getValue();
				Integer baseSunGain = (Integer) gameView.getBaseSunGain().getValue();
				
				gameView.clearPanels();
				gameView.disposePopup();
				game.createCustomLevel(save(), plants, zombies, baseSunGain, spawnRate, spawnAmount);
				return;
			}
			
			gameView.clearPanels();
			gameView.disposePopup();
		}
	}

	/**
	 * The PopupListener. This class contains the actionPerformed method that will
	 * be called when any button on the LevelBuilderPanel is clicked.
	 * 
	 * @author Michael Fan 101029934
	 * @version Dec 2, 2018
	 */
	private class LevelBuilderListener implements ActionListener {
		/**
		 * The action that is performed when any button on the LevelBuilderPanel is
		 * clicked.
		 * 
		 * @param e the ActionEvent
		 */
		public void actionPerformed(ActionEvent e) {
			// Available plants
			if(e.getSource() instanceof ShopButton) {
				ShopButton button = (ShopButton) e.getSource();

				if(!gameView.isChosenPlant(button)) {
					ShopButton addedButton = gameView.addChoosenPlant(button);

					if(addedButton != null) {
						addedButton.addActionListener(this);
					}
				} else {
					gameView.removeChosenPlant(button);
				}
			} else if(e.getSource() instanceof ZombieButton) {
				ZombieButton button = (ZombieButton) e.getSource();
				
				if(!gameView.isChosenZombie(button)) {
					gameView.addChosenZombie(button).addActionListener(this);;
				} else {
					gameView.removeChosenZombie(button);
				}
			}
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
	 * Opens up the file chooser(allow multi-file selection)
	 */
	private File[] loadMultiFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);

		int option = fileChooser.showOpenDialog(gameView);

		if(option == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFiles();
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
