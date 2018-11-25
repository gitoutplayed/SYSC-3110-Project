package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import plant.PlantName;

/**
 * This class represents the ShopPanel. 
 * 
 * @author Michael Fan 101029934
 * @version Nov 16, 2018
 */
public class ShopPanel extends JPanel {
	private List<ShopButton> shop;
	
	public static int WIDTH = 4 * GameView.SQUARE_SIZE;
	public static int HEIGHT = GameView.SQUARE_SIZE;
	public static int SIZE = 4; //
	
	/**
	 * Constructs a new ShopPanel
	 */
	public ShopPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		shopInit();
	}
	
	/**
	 * Initializes the shop will empty ShopButtons. A ShopButton is empty if it does not
	 * have an icon and does not hold a plant. Right now the shop has 4 ShopButtons which means
	 * the shop can hold 4 different plants at most. The size can be changed later to allow for more 
	 * plants.
	 */
	private void shopInit() {
		shop = new ArrayList<ShopButton>();
		for(int i = 0; i < SIZE; i++) {
			ShopButton button = new ShopButton();
			button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
			shop.add(button);
			add(button);
		}
	}
	
	/**
	 * Load the plants and icons into the shop.
	 * 
	 * @param plants the plants that are to be loaded into the shop
	 */
	public void loadShop(Map<PlantName, ImageIcon> plants) {
		int i = 0;
		for(PlantName plant : plants.keySet()) {
			ShopButton button = shop.get(i++);
			button.setPlant(plant);
			button.setIcon(plants.get(plant));
		}
	}
	
	/**
	 * Returns all the ShopButtons in the shop.
	 * 
	 * @return all the ShopButtons in the shop
	 */
	public List<ShopButton> getShopButtons() {
		return shop;
	}
}
