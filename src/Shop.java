import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the Shop. The shop manages the purchasing of plants. 
 * The plants that appear in the shop depends on the level.
 *
 * @author Michael Fan 101029934
 * @version Oct 25, 2018
 */

public class Shop {
	private Map<PlantName, Integer> shop;
	
	/**
	 * Creates a new shop.
	 */
	public Shop() {
		shop = new HashMap<PlantName, Integer>();
	}
	
	/**
	 * Adds plants that are available purchase into the shop.
	 * 
	 * @param plants the plants that are to be added to the shop
	 */
	public void addPlants(Set<PlantName> plants) {
		for(PlantName p : plants) {
			Plant plant = PlantFactory.createPlant(p);
			this.shop.putIfAbsent(p, plant.getPrice());
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
			sb.append(p.name() + " " + shop.get(p) + '\n');
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
		}

		Plant newPlant = PlantFactory.createPlant(plant);

		if(sunCounter < shop.get(plant)) {
			return null;
		}

		return newPlant;
	}
}
