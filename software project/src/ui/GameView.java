package ui;
import javax.swing.*;

import game.Game;
import game.GameState;
import tile.Tile;
import tile.TileTypes;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the GameView. The GameView has two main panels: GridPanel and UpperPanel.
 * GridPanel has the grid and UpperPanel has shop, shovel button and end turn button as well as some
 * labels that will display information about the game (i.e. turn number, sun counter etc.)
 * 
 * @author Michael Fan 101029934
 * @version Nov 16, 2018
 */
public class GameView extends JFrame implements GameListener {
	GridPanel gridPane;
	UpperPanel upperPane;
	
	JMenuItem start;
	JMenuItem loadLevel;
	JMenuItem loadNextLevel;
	JMenuItem loadPreviousLevel;

	public static int SQUARE_SIZE = 80;
	public static int WIDTH = SQUARE_SIZE * GameState.COL;
	public static int HEIGHT = SQUARE_SIZE * GameState.ROW + UpperPanel.HEIGHT;

	/*
	 * Constructs a new game view.
	 */
	public GameView() {
		setTitle("Zombies vs Plants");
		
		Container contentPane = getContentPane();

		contentPane.setLayout(new BorderLayout());
		
		// Sets up the menu
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		JMenu load = new JMenu("Load");
		
		start = new JMenuItem("Start");
		loadLevel = new JMenuItem("Load Level");
		loadNextLevel = new JMenuItem("Load Next Level");
		loadPreviousLevel = new JMenuItem("Load Previous Level");
		
		menu.add(start);
		
		load.add(loadLevel);
		load.add(loadNextLevel);
		load.add(loadPreviousLevel);
		
		menu.add(load);
		
		menuBar.add(menu);
		
		setJMenuBar(menuBar);
		
		// Add the panels
		gridPane = new GridPanel();
		upperPane = new UpperPanel();

		contentPane.add(gridPane, BorderLayout.CENTER);
		contentPane.add(upperPane, BorderLayout.NORTH);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	/**
	 * Updates the view.
	 * 
	 * @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	private void updateView(GameEvent e) {
		Game game = (Game) e.getSource();
		Tile[][] grid =game.getGrid();
		JButton[][] buttonGrid = gridPane.getButtonGrid();
		
		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				Tile tile = grid[row][col];
				JButton button = buttonGrid[row][col]; 
				
				// Check if the tile has plant
				if(tile.hasPlant()) {
					button.setIcon(tile.getResidingPlant().getIcon());
				} 
				// Check if the tile has zombie
				else if(tile.hasZombie()) {
					button.setIcon(tile.getFirstZombie().getIcon(tile.getTileType()));
				} 
				// Empty tile
				else {
					button.setIcon(tile.getIcon());
				}
			}
		}
		
		upperPane.setTurnNumber(game.getTurnNumber());
		upperPane.setZombiesLeft(game.getNumberOfZombiesLeft());
		upperPane.setSunCtouner(game.getSunCounter());
		
		for(ShopButton button : upperPane.getShopButtons()) {
			if(game.isPlantOnCooldown(button.getPlant())) {
				button.setEnabled(false);
			} else {
				button.setEnabled(true);
			}
		}
	}
	
	/**
	 * Called when trying to load a level.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void levelLoaded(GameEvent e) {
		// Display the error message when level loading is not successful
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		
		showMessage(e.getMessage());
	}
	
	/**
	 * Called when trying start a game(level).
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void gameStarted(GameEvent e) {
		// Display the error message when game start is not successful
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		
		showMessage(e.getMessage());
		
		upperPane.loadShop(((Game) e.getSource()).getShopPlants()); // load plants into the shop
		updateView(e);
	}

	/**
	 * Called when trying to buy a plant.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void plantBought(GameEvent e) {
		// Display the error message when buying plant is not successful
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
		
		updateView(e);
	}
	
	/**
	 * Called when trying to shovel a plant.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void plantShoveled(GameEvent e) {
		// Display the error message when shovel is not successful
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
		
		updateView(e);
	}
	
	/**
	 * Called when trying to end a turn
	 * 
	 * @param e GameEvent 
	 * 
	 * @see GameEvent
	 */
	public void turnEnded(GameEvent e) {
		// Display the error message when turn ending is not successful
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		updateView(e);
	}
	
	/**
	 * Called when the current level is finished.
	 * 
	 * @param e GameEvent
	 * 
	 * @see GameEvent
	 */
	public void levelFinished(GameEvent e) {
		showMessage(e.getMessage());
		updateView(e);
	}
	
	/**
	 * Display the given message in a JOptionPane dialog box. 
	 * 
	 * @param message the message to be displayed
	 */
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	/**
	 * Returns the start menu item.
	 * 
	 * @return the start menu item
	 */
	public JMenuItem getStart() {
		return start;
	}
	
	/**
	 * Returns the loadNextLevel menu item.
	 * 
	 * @return the loadNextLevel menu item
	 */
	public JMenuItem getLoadNextLevel() {
		return loadNextLevel;
	}
	
	/**
	 * Returns the loadLevel menu item.
	 * 
	 * @return the loadLevel menu item
	 */
	public JMenuItem getLoadLevel() {
		return loadLevel;
	}
	
	/**
	 * Returns the loadPreviousLevel menu item.
	 * 
	 * @return the loadPreviousLevel menu item
	 */
	public JMenuItem getLoadPreviousLevel() {
		return loadPreviousLevel;
	}
	
	/**
	 * Returns the end turn button.
	 * 
	 * @return the end turn button
	 */
	public JButton getEndTurn() {
		return upperPane.getEndTurn();
	}
	
	/**
	 * Returns the shovel button.
	 * 
	 * @return the shovel button
	 */
	public JButton getShovel() {
		return upperPane.getShovel();
	}
	
	/**
	 * Returns the buttons in the grid.
	 * 
	 * @return the buttons in the grid
	 */
	public JButton[][] getButtonGrid() {
		return gridPane.getButtonGrid();
	}
	
	/**
	 * Returns the buttons in the shop.
	 * 
	 * @return the buttons in the shop
	 */
	public ArrayList<ShopButton> getShopButtons() {
		return upperPane.getShopButtons();
	}
}
