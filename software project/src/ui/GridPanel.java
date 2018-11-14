package ui;
import java.awt.*;
import javax.swing.*;

import game.GameState;

public class GridPanel extends JPanel {
	private JButton[][] grid;
	
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
	
	public JButton[][] getButtonGrid() {
		return grid;
	}
}
