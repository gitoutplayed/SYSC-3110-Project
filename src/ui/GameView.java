package ui;
import javax.swing.*;

import game.Game;
import game.GameState;
import tile.Tile;
import tile.TileTypes;

import java.awt.*;

/**
 * This class represents the GameView.
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
 */
public class GameView extends JFrame implements GameListener {
	GridPanel gridPane;
	UpperPanel upperlPane;
	
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
		upperlPane = new UpperPanel();

		contentPane.add(gridPane, BorderLayout.CENTER);
		contentPane.add(upperlPane, BorderLayout.NORTH);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
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
		
		Tile[][] grid = ((Game) e.getSource()).getGrid();
		JButton[][] buttonGrid = gridPane.getButtonGrid();
		
		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				Tile tile = grid[row][col];
				String type = "";
				
				if(tile.getTileType() == TileTypes.ZOMBIE_SPAWN) {
					type = "Zombie Spawn";
				} else if(tile.getTileType() == TileTypes.LAWNMOWER) {
					type = "Lawnmower";
				} else {
					type = "Grass";
				}
				
				buttonGrid[row][col].setText(type);
			}
		}
	}

	public void plantBought(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
	}

	public void plantShoveled(GameEvent e) {
		if(!e.getSuccess()) {
			showMessage(e.getMessage());
			return;
		} 
	}

	public void turnEnded(GameEvent e) {

	}
	
	public void levelFinished(GameEvent e) {
		
	}
	
	public JMenuItem getStart() {
		return start;
	}
	
	public JMenuItem getLoadNextLevel() {
		return loadNextLevel;
	}
	
	private void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}
}
