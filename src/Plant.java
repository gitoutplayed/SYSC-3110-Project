/**
 * 
 */

/**
 * @author Souheil
 *
 */
public abstract class Plant {
	
	public enum PlantName { PeaShooter, SunFlower };
	private PlantName name;
	private int health, price;
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param health
	 */
	public Plant(PlantName name, int price, int health) {
		this.name = name;
		this.price = price;
		this.health = health;
	}
	
	/**
	 * 
	 * @param name
	 */
	public void setName(PlantName name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public PlantName getName() {
		return name;
	}
	
	/**
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	
}
