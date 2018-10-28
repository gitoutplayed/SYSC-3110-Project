import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the Shop. The shop manages the purchasing of plants. 
 * The plants that appear in the shop depends on the level.
 *
 * @author Michael Fan 101029934
 * @version Oct 28, 2018
 */

public class Shop {
	private Map<PlantName, Integer> shop;
	private Map<PlantName, Integer> cooldowns;
	private Map<PlantName, Integer> currentCoolDowns;
	
	/**
	 * Creates a new shop.
	 */
	public Shop() {
		shop = new HashMap<PlantName, Integer>();
		cooldowns = new HashMap<PlantName, Integer>();
		currentCoolDowns = new HashMap<PlantName, Integer>();
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
			currentCoolDowns.putIfAbsent(p, 0);
		}
	}
	
	/**
	 * Returns the plants and their prices as a string.
	 * 
	 * @return the plants and their prices
	 */
	public String getShopPlants() {
		StringBuilder sb = new StringBuilder();

		for(PlantName p : shop.keySet()) {
			sb.append(p.name() + " " + shop.get(p) + "(Cooldown: " + currentCoolDowns.get(p) + " turn(s) left)" + '\n');
		}

		return sb.toString();
	}
	
	/**
	 * Purchase a plant.
	 * 
	 * @param plant the plant to be purchased
	 * @param sunCounterthe current amount of sun counter
	 * 
	 * @return the purchased plant if purchased successfully or false otherwise
	 */
	public Plant purchase(PlantName plant, int sunCounter) {
		if(!shop.containsKey(plant)) {
			return null;
		} else if(sunCounter < shop.get(plant)) {
			return null;
		} else if(currentCoolDowns.get(plant) > 0) {
			return null;
		}
		
		currentCoolDowns.put(plant, cooldowns.get(plant));

		return PlantFactory.createPlant(plant);
	}
	
	/**
	 * Reduces the cooldowns for purchasing plants.
	 */
	public void reduceCoolDown() {
		for(PlantName p : currentCoolDowns.keySet()) {
			int cooldown = currentCoolDowns.get(p);
			if(cooldown > 0) {
				currentCoolDowns.put(p, cooldown - 1);
			}
		}
	}
}
