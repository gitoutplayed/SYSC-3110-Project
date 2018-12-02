package ui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

/**
 * This class represents the LevelBuilderPanel. This is where a custom level is built.
 * 
 * @author Michael Fan
 * @version Dec 1, 2018
 */
public class LevelBuilderPanel extends JPanel {
	private List<ShopButton> plants;
	private List<ZombieButton> zombies;
	
	/**
	 * Constructs a new LevelBuilderPanel
	 */
	public LevelBuilderPanel() {
		plants = new ArrayList<ShopButton>();
		zombies = new ArrayList<ZombieButton>();
	}
}
