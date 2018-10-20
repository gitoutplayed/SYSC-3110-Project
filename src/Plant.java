/**
 * 
 */

/**
 * @author Souheil
 *
 */
public abstract class Plant {
	
	private PlantName name;
	private int health, price, damage, resrc_gen;;
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param health
	 */
	public Plant(PlantName name, int price, int health, int damage, int resrc_gen) {
		this.name = name;
		this.price = price;
		this.health = health;
		this.damage = damage;
		this.resrc_gen = resrc_gen;
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
	
	/**
	 * 
	 * @param damage
	 */
	private void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * 
	 * @return
	 */
	private int getDamage() {
		return damage;
	}
	
	/**
	 * 
	 * @param Resrc_gen
	 */
	private void setResrc_gen(int Resrc_gen) {
		this.resrc_gen = Resrc_gen;
	}
	
	/**
	 * 
	 * @return
	 */
	private int getResrc_gen() {
		return resrc_gen;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean canAttack() {
		return damage == 0;
	}
	
	/**
	 * 
	 * @return
	 */
	private boolean canResrc_gen() {
		return resrc_gen == 0;
	}
	
}
