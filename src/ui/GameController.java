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

	public GameController() {
		gameView = new GameView();
		game = new Game(gameView);
		
		setupMenu();
	}
	
	private void setupMenu() {
		gameView.getStart().addActionListener(new MenuListener());
		gameView.getLoadNextLevel().addActionListener(new MenuListener());
	}
	
	private class MenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			
			if(item == gameView.getStart()) {
				game.start();
			} else if(item == gameView.getLoadNextLevel()) {
				game.loadNextLevel();
			}
		}	
	}
	
	public static void main(String[] args) {
		GameController controller = new GameController();
		
	}
}
