/**
 * This class creates a "factory" for creating plants.
 * 
 * @author Souheil Yazji 101007994
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
		return null;
	}
}
