package game;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import plant.Plant;
import plant.PlantFactory;
import plant.PlantName;
import tile.TileTypes;

/**
 * This class represents the Shop. The shop manages the purchasing of plants. 
 * The plants that appear in the shop depends on the level.
 *
 * @author Michael Fan 101029934
 * @version Nov 17, 2018
 */

public class Shop {
	private Map<PlantName, Integer> shop;
	private Map<PlantName, Integer> cooldowns;
	private Map<PlantName, Integer> currentCooldowns;
	private Map<PlantName, ImageIcon> shopIcons;
	
	/**
	 * Creates a new shop.
	 */
	public Shop() {
		shop = new HashMap<PlantName, Integer>();
		cooldowns = new HashMap<PlantName, Integer>();
		currentCooldowns = new HashMap<PlantName, Integer>();
		shopIcons = new HashMap<PlantName, ImageIcon>();
	}
	
	/**
	 * Constructs a new Shop that is a copy of the specified Shop.
	 * 
	 * @param otherShop the Shop that is to be copied
	 */
	public Shop(Shop otherShop) {
		shop = otherShop.shop;
		cooldowns = otherShop.cooldowns;
		shopIcons = otherShop.shopIcons;
		currentCooldowns = new HashMap<PlantName, Integer>();
		for(PlantName plant : otherShop.currentCooldowns.keySet()) {
			currentCooldowns.putIfAbsent(plant, otherShop.currentCooldowns.get(plant));
		}
	}
	
	/**
	 * Adds plants that are available purchase into the shop.
	 * 
	 * @param plants the plants that are to be added to the shop
	 */
	public void addPlants(Set<PlantName> plants) {
		for(PlantName p : plants) {
			Plant plant = PlantFactory.createPlant(p);
			shop.putIfAbsent(p, plant.getPrice());
			cooldowns.putIfAbsent(p, plant.getCooldown());
			currentCooldowns.putIfAbsent(p, 0);
			shopIcons.putIfAbsent(p, plant.getIcon(TileTypes.SHOP));
		}
	}
	
	/**
	 * Returns the plants and their icons.
	 * 
	 * @return the plants and their icons
	 */
	public Map<PlantName, ImageIcon> getShopPlants() {
		return shopIcons;
	}
	
	/**
	 * Purchase a plant.
	 * 
	 * @param plant the plant to be purchased
	 * 
	 * @return the purchased plant 
	 */
	public Plant purchase(PlantName plant) {
		currentCooldowns.put(plant, cooldowns.get(plant));

		return PlantFactory.createPlant(plant);
	}
	
	/**
	 * Returns true if the specified plant can be purchased.
	 * 
	 * @param plant the plant to be purchased
	 * @param sunCounter the current amount of sun counter
	 * 
	 * @return true if the specified plant can be purchased
	 */
	public boolean canPurchase(PlantName plant, int sunCounter) {
		return sunCounter >= shop.get(plant);
	}
	
	/**
	 * Reduces the cooldowns for purchasing plants.
	 */
	public void reduceCooldowns() {
		for(PlantName p : currentCooldowns.keySet()) {
			int cooldown = currentCooldowns.get(p);
			if(cooldown > 0) {
				currentCooldowns.put(p, cooldown - 1);
			}
		}
	}
	
	/**
	 * Returns true if the specified plant is on cooldown.
	 * 
	 * @param plant the plant to check
	 * 
	 * @return true if the specified plant is on cooldown
	 */
	public boolean isPlantOnCooldown(PlantName plant) {
		if(plant == null) {
			return true;
		}
		
		return currentCooldowns.get(plant) > 0;
	}
}
