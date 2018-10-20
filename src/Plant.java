/**
 * 
 */

/**
 * @author Souheil
 *
 */
public abstract class Plant {
	
	private PlantName name;
	private int health, price, damage, resrc_gen, atkRange_X, atkRange_Y;
	
	/**
	 * 
	 * @param name
	 * @param price
	 * @param health
	 */
	public Plant(PlantName name, int price, int health, int damage, int resrc_gen, int atkRange_X, int atkRange_Y) {
		this.name = name;
		this.price = price;
		this.health = health;
		this.damage = damage;
		this.resrc_gen = resrc_gen;
		this.atkRange_X = atkRange_X;
		this.atkRange_Y = atkRange_Y;
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
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * 
	 * @param Resrc_gen
	 */
	public void setResrc_gen(int Resrc_gen) {
		this.resrc_gen = Resrc_gen;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getResrc_gen() {
		return resrc_gen;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean canAttack() {
		return damage != 0;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean canResrc_gen() {
		return resrc_gen != 0;
	}
	
	/**
	 * 
	 * @param x
	 */
	public void setAtkRange_X(int atkRange_X) {
		this.atkRange_X = atkRange_X;
	}
	
	/**
	 * 
	 * @param y
	 */
	public void setAtkRange_Y(int atkRange_Y) {
		this.atkRange_Y = atkRange_Y;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAtkRange_X() {
		return atkRange_X;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getAtkRange_Y() {
		return atkRange_Y;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isDead() {
		return health<=0;
	}
	
	/**
	 * 
	 * @param dmg
	 */
	public void takeDmg(int dmg) {
		health = health - dmg;
	}
}
