package ui;

import javax.swing.JButton;

import plant.PlantName;

/**
 * This class represents a ShopButton. The ShopButton is essentially a JBution but has the ability to hold
 * name of a plant.
 * 
 * @author Michael Fan 101029934
 * @version Nov 16, 2018
 *
 */
public class ShopButton extends JButton {
	private PlantName plant;
	
	/**
	 * Constructs a new ShopButton.
	 */
	public ShopButton() {
		plant = null;
	}
	
	/**
	 * Sets the plant that this ShopButton should hold.
	 * 
	 * @param plant the plant that this ShopButton should hold
	 */
	public void setPlant(PlantName plant) {
		this.plant = plant;
	}
	
	/**
	 * Returns the plant that this ShopButton holds.
	 * 
	 * @return the plant that this ShopButton holds
	 */
	public PlantName getPlant() {
		return plant;
	}
}
