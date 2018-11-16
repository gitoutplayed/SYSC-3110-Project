package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import plant.PlantName;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
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
	 * Constructs a new 
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
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("sun.png")));
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
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("shovel.png")));
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

	public JButton getEndTurn() {
		return endTurn;
	}
	
	public JButton getShovel() {
		return shovel;
	}

	public void setTurnNumber(int turnNumber) {
		this.turnNumber.setText("  Turn #: " + turnNumber);
	}

	public void setZombiesLeft(int zombiesLeft) {
		this.zombiesLeft.setText("  Zombies Left: " + zombiesLeft);
	}
	
	public void setSunCtouner(int sunCounter) {
		this.sunCounter.setText("Sun Counter: " + sunCounter);
	}
	
	public ArrayList<ShopButton> getShopButtons() {
		return shopPane.getShopButtons();
	}
	
	public void loadShop(Map<PlantName, ImageIcon> plants) {
		shopPane.loadShop(plants);
	}
}
