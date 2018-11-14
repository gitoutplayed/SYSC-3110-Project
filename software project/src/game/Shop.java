package game;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import plant.Plant;
import plant.PlantFactory;
import plant.PlantName;

/**
 * This class represents the Shop. The shop manages the purchasing of plants. 
 * The plants that appear in the shop depends on the level.
 *
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
 */

public class Shop {
	private Map<PlantName, Integer> shop;
	private Map<PlantName, Integer> cooldowns;
	private Map<PlantName, Integer> currentCooldowns;
	
	/**
	 * Creates a new shop.
	 */
	public Shop() {
		shop = new HashMap<PlantName, Integer>();
		cooldowns = new HashMap<PlantName, Integer>();
		currentCooldowns = new HashMap<PlantName, Integer>();
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
		}
	}
	
	/**
	 * Returns the plants and their prices as a string.
	 * 
	 * @return the plants and their prices
	 */
	public Map<PlantName, Integer> getShopPlants() {
		return shop;
	}
	
	/**
	 * Purchase a plant.
	 * 
	 * @param plant the plant to be purchased
	 * @param sunCounter the current amount of sun counter
	 * 
	 * @return the purchased plant if purchased successfully or false otherwise
	 */
	public Plant purchase(PlantName plant, int sunCounter) {
		if(sunCounter < shop.get(plant)) {
			return null;
		} else if(currentCooldowns.get(plant) > 0) {
			return null;
		}
		
		currentCooldowns.put(plant, cooldowns.get(plant));

		return PlantFactory.createPlant(plant);
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
}
