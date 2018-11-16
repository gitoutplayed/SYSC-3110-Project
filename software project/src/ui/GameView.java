package ui;
import javax.swing.*;

import game.Game;
import game.GameState;
import tile.Tile;
import tile.TileTypes;

import java.awt.*;
import java.util.ArrayList;

/**
 * This class represents the GameView.
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
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
	
	public void levelLoaded(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		
		showMessage(e.getMessage());
	}

	public void gameStarted(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		
		showMessage(e.getMessage());
		
		upperPane.loadShop(((Game) e.getSource()).getShopPlants());
		updateView(e);
	}

	public void plantBought(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
		
		updateView(e);
	}

	public void plantShoveled(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
		
		updateView(e);
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
	
	public void turnEnded(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		}
		updateView(e);
	}
	
	public void levelFinished(GameEvent e) {
		showMessage(e.getMessage());
		updateView(e);
	}
	
	public JMenuItem getStart() {
		return start;
	}
	
	public JMenuItem getLoadNextLevel() {
		return loadNextLevel;
	}
	
	public JMenuItem getLoadLevel() {
		return loadLevel;
	}
	
	public JMenuItem getLoadPreviousLevel() {
		return loadPreviousLevel;
	}
	
	public JButton getEndTurn() {
		return upperPane.getEndTurn();
	}
	
	public JButton getShovel() {
		return upperPane.getShovel();
	}
	
	public JButton[][] getButtonGrid() {
		return gridPane.getButtonGrid();
	}
	
	public ArrayList<ShopButton> getShopButtons() {
		return upperPane.getShopButtons();
	}
}
