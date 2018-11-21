package plant;
/**
 * This class creates a "factory" for creating plants.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 20th
 *
 */
public class PlantFactory {

	public PlantFactory() {
		
	}
	
	/**
	 * Takes a Plant name as a param and returns a new instance of the plant.
	 *
	 * @param name the name of the plant to be made.
	 * @return returns null by default if passed a name that's not included in the ENUM PlantName, otherwise returns the respective plant.
	 *
	 */
	public static Plant createPlant(PlantName name) {
		if(name==PlantName.PeaShooter) return new PeaShooter();
		if(name==PlantName.SunFlower) return new SunFlower();
		if(name==PlantName.Wallnut) return new Wallnut();
		if(name==PlantName.Repeater) return new Repeater();
		if(name==PlantName.PotatoMine) return new PotatoMine();
		return null;
	}
	
	/**
	 * Returns a Plant that is a copy of another Plant
	 */
	public static Plant createPlantCopy(Plant plant) {
		if(plant.getName() == PlantName.PeaShooter) {
			return new PeaShooter(plant);
		} else if(plant.getName() == PlantName.SunFlower) {
			return new SunFlower(plant);
		} else if(plant.getName() == PlantName.Wallnut) { 
			return new Wallnut(plant);
		} else if(plant.getName() == PlantName.Repeater) {
			return new Repeater(plant);
		} else if(plant.getName() == PlantName.PotatoMine) {
			return new PotatoMine(plant);
		}
		return null;
	}
}
