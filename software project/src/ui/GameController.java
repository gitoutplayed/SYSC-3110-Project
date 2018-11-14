package ui;
import java.awt.event.*;
import javax.swing.*;

import game.Game;

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
		gameView.getStart().addActionListener(new MenuListener());
		gameView.getLoadNextLevel().addActionListener(new MenuListener());
		gameView.getLoadLevel().addActionListener(new MenuListener());
		gameView.getLoadPreviousLevel().addActionListener(new MenuListener());
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
	
	public static void main(String[] args) {
		GameController controller = new GameController();
		
	}
}
