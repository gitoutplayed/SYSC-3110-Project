package ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;

public class UpperPanel extends JPanel {
	JButton endTurn;
	JLabel turnNumber;
	JLabel zombiesLeft;
	JButton shovel;
	JLabel sunCounter;

	public static int HEIGHT = 120;

	public UpperPanel() {
		setPreferredSize(new Dimension(GameView.WIDTH, HEIGHT));
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weighty = 1.0;
		
		// Sun counter panel
		JPanel sunCounterPane = new JPanel();
		sunCounter = new JLabel("Sun Counter: ");
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.2;
		sunCounterPane.add(sunCounter);
		add(sunCounterPane, c);
		
		// Shop
		JScrollPane shopPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		c.gridx = 1;
		c.gridy = 0;
		c.weightx = 0.5;
		add(shopPane, c);
		
		// Shovel button
		shovel = new JButton();
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("shovel.png")));
			shovel.setIcon(icon);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		c.gridx = 2;
		c.gridy = 0;
		c.weightx = 0.02;
		add(shovel, c);
		
		// Info panel
		JPanel infoPane = new JPanel();
		infoPane.setLayout(new GridLayout(3, 1));
		endTurn = new JButton("End Turn");
		turnNumber = new JLabel("  Turn #: ");
		zombiesLeft = new JLabel("  Zombies Left: ");
		infoPane.add(endTurn);
		infoPane.add(turnNumber);
		infoPane.add(zombiesLeft);
		c.gridx = 3;
		c.gridy = 0;
		c.weightx = 0.1;
		add(infoPane, c);
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
}
