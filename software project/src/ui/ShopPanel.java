package ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import plant.PlantName;

public class ShopPanel extends JPanel {
	private ArrayList<ShopButton> shop;
	
	public static int WIDTH = 4 * GameView.SQUARE_SIZE;
	public static int HEIGHT = GameView.SQUARE_SIZE;
	public static int SIZE = 4;
	
	public ShopPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		shopInit();
	}
	
	private void shopInit() {
		shop = new ArrayList<ShopButton>();
		for(int i = 0; i < SIZE; i++) {
			ShopButton button = new ShopButton();
			button.setPreferredSize(new Dimension(GameView.SQUARE_SIZE, GameView.SQUARE_SIZE));
			shop.add(button);
			add(button);
		}
	}
	
	public void loadShop(Map<PlantName, ImageIcon> plants) {
		int i = 0;
		for(PlantName plant : plants.keySet()) {
			ShopButton button = shop.get(i++);
			button.setPlant(plant);
			button.setIcon(plants.get(plant));
		}
	}
	
	public ArrayList<ShopButton> getShopButtons() {
		return shop;
	}
}
