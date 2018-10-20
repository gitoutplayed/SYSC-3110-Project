import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * This class represents the Shop. The plants that appear in the shop depends on
 * the level.
 *
 * @author Michael Fan 101029934
 */

public class Shop {
    private Map<PlantName, Integer> plants;

    public Shop() {
	plants = new HashMap<PlantName, Integer>();
    }

    public void addPlants(Set<PlantName> plants) {
	for (PlantName p : plants) {
	    Plant plant = PlantFactory.createPlant(p);
	    this.plants.putIfAbsent(p, plant.getPrice());
	}
    }

    public Set<PlantName> getPlants() {
	return plants.keySet();
    }

    public Plant purchase(PlantName plant, int sunCounter) {
	if (!plants.containsKey(plant)) {
	    return null;
	}

	Plant newPlant = PlantFactory.createPlant(plant);

	if (sunCounter < plants.get(plant)) {
	    return null;
	}

	return newPlant;
    }
}
