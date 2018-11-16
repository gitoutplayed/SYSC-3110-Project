package ui;

import javax.swing.JButton;

import plant.PlantName;

public class ShopButton extends JButton {
	private PlantName plant;
	
	public ShopButton() {
		plant = null;
	}
	
	public void setPlant(PlantName plant) {
		this.plant = plant;
	}
	
	public PlantName getPlant() {
		return plant;
	}
}
