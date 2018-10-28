/**
 * This abstract class defines the overall Plant object. 
 * All specific plants must be a subclass of this class.
 * All specific plants must have constant values set for all fields of this class.
 * 
 * @author Souheil Yazji 101007994
 * @version Oct 29th
 *
 */
public abstract class Plant {
	
	private PlantName name;
	private int health, price, damage, resrc_gen, atkRange_X, atkRange_Y, cooldown;
	
	/**
	 * Plant's constructor method
	 * 
	 * @param name
	 * @param price
	 * @param health
	 */
	public Plant(PlantName name, int price, int health, int damage, int resrc_gen, int atkRange_X, int atkRange_Y, int cooldown) {
		this.name = name;
		this.price = price;
		this.health = health;
		this.damage = damage;
		this.resrc_gen = resrc_gen;
		this.atkRange_X = atkRange_X;
		this.atkRange_Y = atkRange_Y;
		this.cooldown = cooldown;
	}
	
	/**
	 * Set the name of the plant
	 * 
	 * @param name
	 */
	public void setName(PlantName name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the plant
	 * 
	 * @return the ENUM name of the plant
	 */
	public PlantName getName() {
		return name;
	}
	
	/**
	 * Set the plant's price
	 * 
	 * @param price
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Get the plant's price
	 * 
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * set the plant's health
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * get the plant's health
	 * 
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * set the plant's damage
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * get the plant's damage
	 * 
	 * @return damage
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * set the plant's resource generation rate
	 * 
	 * @param Resrc_gen
	 */
	public void setResrc_gen(int resrc_gen) {
		this.resrc_gen = resrc_gen;
	}
	
	/**
	 * get the plant's resource generation rate
	 * 
	 * @return resrc_gen
	 */
	public int getResrc_gen() {
		return resrc_gen;
	}
	
	/**
	 * check if the plant can attack
	 * 
	 * @return true if damage > 0, false otherwise
	 */
	public boolean canAttack() {
		return damage > 0;
	}
	
	/**
	 * check if the plant can generate resource
	 * 
	 * @return true if resrc_gen > 0, false otherwise
	 */
	public boolean canResrc_gen() {
		return resrc_gen > 0;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @param x
	 */
	public void setAtkRange_X(int atkRange_X) {
		this.atkRange_X = atkRange_X;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @param y
	 */
	public void setAtkRange_Y(int atkRange_Y) {
		this.atkRange_Y = atkRange_Y;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @return int atkRange_X
	 */
	public int getAtkRange_X() {
		return atkRange_X;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @return int atkRange_Y
	 */
	public int getAtkRange_Y() {
		return atkRange_Y;
	}
	
	/**
	 * check if the plant is dead
	 * 
	 * @return health of the plant
	 */
	public boolean isDead() {
		return health<=0;
	}
	
	/**
	 * calculate the health of the plant upon taking damage
	 * 
	 * @param dmg
	 */
	public void takeDmg(int dmg) {
		health = health - dmg;
	}
	
	/**
	 * get the plant purchase cooldown turn timer
	 * 
	 * @return int cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}
}
