package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import plant.PlantFactory;
import plant.PlantName;
import tile.TileTypes;
import zombie.ZombieFactory;
import zombie.ZombieTypes;

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
	private List<ShopButton> chosenPlants;
	private List<ZombieButton> chosenZombies;

	private JPanel chosenPlantsPane;
	private JPanel chosenZombiesPane;
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
		chosenPlants = new LinkedList<ShopButton>();
		chosenZombies = new LinkedList<ZombieButton>();
		
		setPreferredSize(new Dimension(2 * (PANEL_WIDTH + 15), GameView.SQUARE_SIZE * 7));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		setBackground(Color.WHITE);

		// Spawn rates, amount and base sun gain
		JPanel upperPane = getJPanel(new Dimension(2 * PANEL_WIDTH, GameView.SQUARE_SIZE - 20),
				new GridLayout(1, 3));
		
		JPanel spawnRatePane = getJPanel(null, null);
		spawnRate = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spawnRate.setPreferredSize(new Dimension(GameView.SQUARE_SIZE / 2, GameView.SQUARE_SIZE / 4));
		spawnRate.setEditor(new JSpinner.DefaultEditor(spawnRate));
		spawnRatePane.add(new JLabel("Spawn Rate: "));
		spawnRatePane.add(spawnRate);
		
		JPanel spawnAmountPane = getJPanel(null, null);
		spawnAmount = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
		spawnAmount.setPreferredSize(new Dimension(GameView.SQUARE_SIZE / 2, GameView.SQUARE_SIZE / 4));
		spawnAmount.setEditor(new JSpinner.DefaultEditor(spawnAmount));
		spawnAmountPane.add(new JLabel("Spawn Amount: "));
		spawnAmountPane.add(spawnAmount);
		
		JPanel baseSunGainPane = getJPanel(null, null);
		baseSunGain = new JSpinner(new SpinnerNumberModel(25, 1, 100, 1));
		baseSunGain.setPreferredSize(new Dimension(GameView.SQUARE_SIZE / 2, GameView.SQUARE_SIZE / 4));
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
				new Dimension(PANEL_WIDTH + 15, GameView.SQUARE_SIZE));
		
		for(PlantName plant : PlantName.values()) {
			ShopButton button = new ShopButton();
			button.setPlant(plant);
			button.setIcon(PlantFactory.createPlant(plant).getIcon(TileTypes.GRASS));
			button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
			availablePlantsPane.add(button);
			availablePlants.add(button);
		}

		// Available zombies
		JPanel availableZombiesPane = getJPanel(new Dimension(2 * PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));
		JScrollPane scrollPaneAvailableZombies = getJScrollPane(availableZombiesPane,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER,
				new Dimension(2 * (PANEL_WIDTH + 15), GameView.SQUARE_SIZE));
		
		for(ZombieTypes zombie : ZombieTypes.values()) {
			ZombieButton button = new ZombieButton();
			button.setZombie(zombie);
			button.setIcon(ZombieFactory.createZombie(zombie).getIcon(TileTypes.GRASS));
			button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
			availableZombiesPane.add(button);
			availableZombies.add(button);
		}

		// Chosen plants
		chosenPlantsPane = getJPanel(new Dimension(PANEL_WIDTH, PANEL_HEIGHT),
				new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		JScrollPane scrollPaneChosenPlants = getJScrollPane(chosenPlantsPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, new Dimension(PANEL_WIDTH + 15, GameView.SQUARE_SIZE));

		// Chosen zombies
		chosenZombiesPane = getJPanel(new Dimension(2 * (PANEL_WIDTH), PANEL_HEIGHT * 50),
				new FlowLayout(FlowLayout.LEADING, 0, 0));

		JScrollPane scrollPaneChosenZombies = getJScrollPane(chosenZombiesPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER, new Dimension(2 * (PANEL_WIDTH + 15), GameView.SQUARE_SIZE * 3));
		
		// Button panel
		JPanel buttonPane = getJPanel(new Dimension(2 * (PANEL_WIDTH + 15), GameView.SQUARE_SIZE - 20),
				new FlowLayout(FlowLayout.TRAILING));
		
		done = new JButton("Done");
		cancel = new JButton("Cancel");
		
		buttonPane.add(done);
		buttonPane.add(cancel);
		
		add(upperPane);
		add(getJLabel(" Available Plants", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(getJLabel("chosen Plants", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(scrollPaneAvailablePlants);
		add(scrollPaneChosenPlants);
		add(getJLabel(" Available Zombies", new Dimension(PANEL_WIDTH + 10, GameView.SQUARE_SIZE / 4)));
		add(scrollPaneAvailableZombies);
		add(getJLabel("chosen Zombies", new Dimension(2 * (PANEL_WIDTH + 10), GameView.SQUARE_SIZE / 4)));
		add(scrollPaneChosenZombies);
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
	
	/**
	 * Add a chosen plant to the custom level shop.
	 * 
	 * @param plant the plant that is chosen
	 * 
	 * @return the button that has the chosen plant
	 */
	public ShopButton addChosenPlant(ShopButton plant) {
		if(!chosenPlants.isEmpty()) {
			if(hasShopButton(plant.getPlant())) {
				return null;
			}
		}
		
		ShopButton button = new ShopButton();
		button.setPlant(plant.getPlant());
		button.setIcon(plant.getIcon());
		button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
		chosenPlants.add(button);
		chosenPlantsPane.add(button);
		chosenPlantsPane.revalidate();
		chosenPlantsPane.repaint();
		
		return button;
	}
	
	/**
	 * Returns true if the button containing the specified plant is already in the chosen plant list or false otherwise.
	 * 
	 * @return true if the button containing the specified plant is already in the chosen plant list or false otherwise
	 */
	private boolean hasShopButton(PlantName plant) {
		for(ShopButton button : chosenPlants) {
			if(button.getPlant() == plant) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the available plants.
	 * 
	 * @return the available plants
	 */
	public List<ShopButton> getAvailablePlants() {
		return availablePlants;
	}
	
	/**
	 * Returns the available zombies.
	 * 
	 * @return the available zombies
	 */
	public List<ZombieButton> getAvailableZombies() {
		return availableZombies;
	}
	
	/**
	 * Returns true if the button is in the chosen plant list or false otherwise.
	 * 
	 * @return true if the button is in the chosen plant list or false otherwise
	 */
	public boolean isChosenPlant(ShopButton button) {
		return chosenPlants.contains(button);
	}
	
	/**
	 * Remove chosen plant.
	 * 
	 * @param button the plant to be removed
	 */
	public void removeChosenPlant(ShopButton button) {
		chosenPlants.remove(button);
		chosenPlantsPane.remove(button);
		chosenPlantsPane.revalidate();
		chosenPlantsPane.repaint();
	}
	
	/**
	 * Add a chosen zombie.
	 * 
	 * @param zombie the zombie that is chosen
	 * 
	 * @return the button that has the chosen zombie
	 */
	public ZombieButton addChosenZombie(ZombieButton zombie) {
		ZombieButton button = new ZombieButton();
		button.setZombie(zombie.getZombie());
		button.setIcon(zombie.getIcon());
		button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
		chosenZombies.add(button);
		chosenZombiesPane.add(button);
		chosenZombiesPane.revalidate();
		chosenZombiesPane.repaint();
		
		return button;
	}
	
	/**
	 * Returns true if the button is in the chosen zombie list or false otherwise.
	 * 
	 * @param button the button to check
	 * 
	 * @return true if the button is in the chosen zombie list or false otherwise
	 */
	public boolean isChosenZombie(ZombieButton button) {
		return chosenZombies.contains(button);
	}
	
	/**
	 * Remove chosen zombie.
	 * 
	 * @param button the button to remove
	 * 
	 * @param button the zombie to be removed
	 */
	public void removeChosenZombie(ZombieButton button) {
		chosenZombies.remove(button);
		chosenZombiesPane.remove(button);
		chosenZombiesPane.revalidate();
		chosenZombiesPane.repaint();
	}
	
	/**
	 * Returns the chosen plants.
	 * 
	 * @return the chosen plants
	 */
	public List<ShopButton> getChosenPlants() {
		return chosenPlants;
	}
	
	/**
	 * Returns the chosen zombies
	 * 
	 * @return chosen zombies
	 */
	public List<ZombieButton> getChosenZombies() {
		return chosenZombies;
	}
	
	/**
	 * Returns spawn rate JSpinner.
	 * 
	 * @return spawn rate JSpinner
	 */
	public JSpinner getSpawnRate() {
		return spawnRate;
	}
	
	/**
	 * Returns spawn amount JSpinner.
	 * 
	 * @return spawn amount JSpinner
	 */
	public JSpinner getSpawnAmount() {
		return spawnAmount;
	}
	
	/**
	 * Returns base sun gain JSpinner.
	 * 
	 * @return base sun gain JSpinner
	 */
	public JSpinner getBaseSunGain() {
		return baseSunGain;
	}
	
	/**
	 * Clear level chosen plants and zombies panel.
	 */
	public void clearPanels() {
		chosenPlants.removeAll(chosenPlants);
		chosenPlantsPane.removeAll();
		chosenPlantsPane.revalidate();
		chosenPlantsPane.repaint();
		chosenZombies.removeAll(chosenZombies);
		chosenZombiesPane.removeAll();
		chosenZombiesPane.removeAll();
		chosenZombiesPane.repaint();
	}
}
