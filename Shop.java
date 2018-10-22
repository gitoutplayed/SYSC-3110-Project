import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the Shop. The shop that appear in the shop depends on
 * the level.
 *
 * @author Michael Fan 101029934
 */

public class Shop {
    private Map<PlantName, Integer> shop;

    public Shop() {
	shop = new HashMap<PlantName, Integer>();
    }

    public void addPlants(Set<PlantName> shop) {
	for (PlantName p : shop) {
	    Plant plant = PlantFactory.createPlant(p);
	    this.shop.putIfAbsent(p, plant.getPrice());
	}
    }

    public String getShopItems() {
	StringBuilder sb = new StringBuilder();
	
	for(PlantName p : shop.keySet()) {
	    sb.append(p.name() + " " + shop.get(p) + '\n');
	}
	
	return sb.toString();
    }

    public Plant purchase(PlantName plant, int sunCounter) {
	if (!shop.containsKey(plant)) {
	    return null;
	}

	Plant newPlant = PlantFactory.createPlant(plant);

	if (sunCounter < shop.get(plant)) {
	    return null;
	}

	return newPlant;
    }
}
