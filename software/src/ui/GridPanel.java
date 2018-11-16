package ui;
import java.awt.*;
import javax.swing.*;

import game.GameState;

/**
 * This class represents the GridPanel. The GridPanel contains the buttons that represents the grid in the game.
 * 
 * @author Michael Fan 101029934
 * @version Nov 16, 2018
 */
public class GridPanel extends JPanel {
	private JButton[][] grid;
	
	/**
	 * Constructs a new GridPanel
	 */
	public GridPanel() {
		setLayout(new GridLayout(GameState.ROW, GameState.COL));
		
		grid = new JButton[GameState.ROW][GameState.COL];
		
		for(int row = 0; row < GameState.ROW; row++) {
			for(int col = 0; col < GameState.COL; col++) {
				grid[row][col] =  new JButton();
				add(grid[row][col]);
			}
		}
	}
	
	/**
	 * Returns the buttons in the grid.
	 * 
	 * @return the buttons in the grid
	 */
	public JButton[][] getButtonGrid() {
		return grid;
	}
}
