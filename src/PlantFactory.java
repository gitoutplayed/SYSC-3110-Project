/**
 * @author Souheil
 *
 */
public class PlantFactory {

	public PlantFactory() {
		
	}
	
	public static Plant createPlant(PlantName name) {
		if(name==PlantName.PeaShooter) return new PeaShooter();
		if(name==PlantName.SunFlower) return new SunFlower();
		return null;
	}
}
