package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 * This class represents the LevelBuilderPanel. This is where a custom level is
 * built.
 * 
 * @author Michael Fan
 * @version Dec 2, 2018
 */
public class LevelBuilderPanel extends JPanel {
	private List<ShopButton> availablePlants;
	private List<ZombieButton> availableZombies;
	private List<ShopButton> choosenPlants;
	private List<ZombieButton> choosenZombies;

	private JPanel choosenPlantsPane;
	private JPanel choosenZombiesPane;
	private JButton done;
	private JButton cancel;
	private JSpinner spawnRate;
	private JSpinner spawnAmount;
	private JSpinner baseSunGain;

	private static int PANEL_WIDTH = GameView.SQUARE_SIZE * 4;
	private static int PANEL_HEIGHT = GameView.SQUARE_SIZE * 10;

	/**
	 * Constructs a new LevelBuilderPanel
	 */
	public LevelBuilderPanel() {
		availablePlants = new ArrayList<ShopButton>();
		availableZombies = new ArrayList<ZombieButton>();
		choosenPlants = new LinkedList<ShopButton>();
		choosenZombies = new LinkedList<ZombieButton>();
		
		setPreferredSize(new Dimension(2 * (PANEL_WIDTH + 10), GameView.SQUARE_SIZE * 4 - 20));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		setBackground(Color.WHITE);

		// Spawn rates, amount and base sun gain
		JPanel upperPane = getJPanel(new Dimension(2 * PANEL_WIDTH, GameView.SQUARE_SIZE - 20),
				new GridLayout(1, 3));
		
		JPanel spawnRatePane = getJPanel(null, null);
		spawnRate = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spawnRate.setEditor(new JSpinner.DefaultEditor(spawnRate));
		spawnRatePane.add(new JLabel("Spawn Rate: "));
		spawnRatePane.add(spawnRate);
		
		JPanel spawnAmountPane = getJPanel(null, null);
		spawnAmount = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spawnAmount.setEditor(new JSpinner.DefaultEditor(spawnAmount));
		spawnAmountPane.add(new JLabel("Spawn Amount: "));
		spawnAmountPane.add(spawnAmount);
		
		JPanel baseSunGainPane = getJPanel(null, null);
		baseSunGain = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		baseSunGain.setEditor(new JSpinner.DefaultEditor(baseSunGain));
		baseSunGainPane.add(new JLabel("Base Sun Gain: "));
		baseSunGainPane.add(baseSunGain);
		
		upperPane.add(spawnRatePane);
		upperPane.add(spawnAmountPane);
		upperPane.add(baseSunGainPane);

		// Available plants
		JPanel availablePlantsPane = getJPanel(new Dimension(PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));

		JScrollPane scrollPaneAvailablePlants = getJScrollPane(availablePlantsPane,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER,
				new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE));

		// Available zombies
		JPanel availableZombiesPane = getJPanel(new Dimension(PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));
		JScrollPane scrollPaneAvailableZombies = getJScrollPane(availableZombiesPane,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER,
				new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE));

		// Chosen plants
		choosenPlantsPane = getJPanel(new Dimension(PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));

		JScrollPane scrollPaneChoosenPlants = getJScrollPane(choosenPlantsPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE));

		// Available zombies
		choosenZombiesPane = getJPanel(new Dimension(PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));

		JScrollPane scrollPaneChoosenZombies = getJScrollPane(choosenZombiesPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE));
		
		// Button panel
		JPanel buttonPane = getJPanel(new Dimension(2 * (PANEL_WIDTH + 10), GameView.SQUARE_SIZE * 4 - 20),
				new FlowLayout(FlowLayout.TRAILING));
		
		done = new JButton("Done");
		cancel = new JButton("Cancel");
		
		buttonPane.add(done);
		buttonPane.add(cancel);
		
		add(upperPane);
		add(getJLabel(" Available Plants", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(getJLabel("Choosen Plants", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(scrollPaneAvailablePlants);
		add(scrollPaneChoosenPlants);
		add(getJLabel(" Available Zombies", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(getJLabel("Choosen Zombies", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(scrollPaneAvailableZombies);
		add(scrollPaneChoosenZombies);
		add(buttonPane);
	}

	/**
	 * Returns a JScrollPane containing the specified JPanel
	 * 
	 * @param panel the panel to be contained in the JScrollPane
	 * @param verPolicy the vertical scroll bar policy
	 * @param hozPolicy the horizontal scroll bar policy
	 * @param dimension the dimension of the JScrollPane
	 */
	private JScrollPane getJScrollPane(JPanel panel, int verPolicy, int horPolicy, Dimension dimension) {
		JScrollPane scrollPane = new JScrollPane(panel, verPolicy, horPolicy);
		scrollPane.setPreferredSize(dimension);

		return scrollPane;
	}

	/**
	 * Returns a JPanel with the specified dimension and layout.
	 * 
	 * @param dimension the dimension of the JPanel
	 * @param layout the layout of the JPanel
	 * 
	 * @return a JPanel with the specified dimension and layout
	 */
	private JPanel getJPanel(Dimension dimension, LayoutManager layout) {
		JPanel panel = new JPanel();
		if(dimension != null) {
			panel.setPreferredSize(dimension);
		}
		if(layout != null) {
			panel.setLayout(layout);
		}
		panel.setBackground(Color.WHITE);

		return panel;
	}
	
	/**
	 * Returns a JLabel with the specified text and dimension.
	 * 
	 * @param text the text displayed by the JLabel
	 * @param dimension the dimension of the JLabel
	 * 
	 * @return a JLabel with the specified text and dimension
	 */
	private JLabel getJLabel(String text, Dimension dimension) {
		JLabel label = new JLabel(text);
		label.setPreferredSize(dimension);
		
		return label;
	}
	
	/**
	 * Returns the done button
	 * 
	 * @return the done button
	 */
	public JButton getDoneButton() {
		return done;
	}
	
	/**
	 * Returns the cancel button
	 * 
	 * @return the cancel button
	 */
	public JButton getCancelButton() {
		return cancel;
	}
}
