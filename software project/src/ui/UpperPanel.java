package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import plant.PlantName;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class represents the UpperPanel. The UpperPanel has shop, shovel button and end turn button as well as some
 * labels that will display information about the game (i.e. turn number, sun counter etc.)
 * 
 * @author Michael Fan 101029934
 * @version Nov 16, 2018
 */
public class UpperPanel extends JPanel {
	private JButton endTurn;
	private JLabel turnNumber;
	private JLabel zombiesLeft;
	private JButton shovel;
	private JLabel sunCounter;
	private ShopPanel shopPane;

	public static int HEIGHT = GameView.SQUARE_SIZE;
	
	/**
	 * Constructs a new UpperPanel.
	 */
	public UpperPanel() {
		setPreferredSize(new Dimension(GameView.WIDTH, HEIGHT));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		// Sun counter panel
		JPanel sunCounterPane = new JPanel();
		int sunCounterPaneWidth = (GameView.WIDTH - ShopPanel.WIDTH - GameView.SQUARE_SIZE) / 2;
		sunCounterPane.setPreferredSize(new Dimension(sunCounterPaneWidth, HEIGHT));
		sunCounterPane.setLayout(new BoxLayout(sunCounterPane, BoxLayout.Y_AXIS));
		sunCounterPane.setBackground(Color.WHITE);
		JLabel sunIcon = new JLabel();
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("ui/sun.png")));
			sunIcon.setIcon(icon);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		sunCounter = new JLabel("Sun Counter: ");
		sunIcon.setAlignmentX(Component.CENTER_ALIGNMENT);
		sunCounter.setAlignmentX(Component.CENTER_ALIGNMENT);
		sunCounterPane.add(sunIcon);
		sunCounterPane.add(sunCounter);
		add(sunCounterPane);
		
		// Shop Panel
		shopPane = new ShopPanel();
		JScrollPane scrollPane = new JScrollPane(shopPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(ShopPanel.WIDTH + 18, HEIGHT));
		add(scrollPane);
		
		// Shovel button
		shovel = new JButton();
		shovel.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("ui/shovel.png")));
			shovel.setIcon(icon);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		add(shovel);
		
		// Info panel
		JPanel infoPane = new JPanel();
		infoPane.setLayout(new GridLayout(3, 1));
		int infoPaneWidth = (GameView.WIDTH - ShopPanel.WIDTH - GameView.SQUARE_SIZE - 50) / 2;
		infoPane.setPreferredSize(new Dimension(infoPaneWidth, HEIGHT));
		infoPane.setBackground(Color.WHITE);
		endTurn = new JButton("End Turn");
		turnNumber = new JLabel("  Turn #: ");
		zombiesLeft = new JLabel("  Zombies Left: ");
		infoPane.add(endTurn);
		infoPane.add(turnNumber);
		infoPane.add(zombiesLeft);
		add(infoPane);
	}

	/**
	 * Returns the end turn button.
	 * 
	 * @return the end turn button
	 */
	public JButton getEndTurn() {
		return endTurn;
	}
	
	/**
	 * Returns the shop button.
	 * 
	 * @return the shop button
	 */
	public JButton getShovel() {
		return shovel;
	}

	/**
	 * Display the turn number.
	 * 
	 * @param turnNumber the turn number to be displayed
	 */
	public void setTurnNumber(int turnNumber) {
		this.turnNumber.setText("  Turn #: " + turnNumber);
	}
	
	/**
	 * Display the number of zombies left..
	 * 
	 * @param zombiesLeft the number of zombies left to be displayed
	 */
	public void setZombiesLeft(int zombiesLeft) {
		this.zombiesLeft.setText("  Zombies Left: " + zombiesLeft);
	}
	
	/**
	 * Display the sun counter.
	 * 
	 * @param sunCounter the sun counter to be displayed
	 */
	public void setSunCtouner(int sunCounter) {
		this.sunCounter.setText("Sun Counter: " + sunCounter);
	}
	
	/**
	 * Returns all the buttons in the shop.
	 * 
	 * @return all the buttons in the shop
	 */
	public ArrayList<ShopButton> getShopButtons() {
		return shopPane.getShopButtons();
	}
	
	/**
	 * Load plants into the shop.
	 * 
	 * @param plants the plants that are to be loaded into the shop
	 */
	public void loadShop(Map<PlantName, ImageIcon> plants) {
		shopPane.loadShop(plants);
	}
}
