package plant;
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
	 * @param name ENUM name of the plant to be created
	 * @param price the cost of the plant in Sun points
	 * @param health the health of the plant
	 * @param damage the plant's damage output per turn
	 * @param resrc_gen the plant's Sun point generation rate per turn
	 * @param atkRange_X the plant's attack range in the X axis
	 * @param atkRange_Y the plant's attack range in the Y axis
	 * @param cooldown the plant's purchase cooldown in turns
	 *  
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
	 * @param name ENUM name of the plant to be created
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
	 * @param price the cost of the plant in Sun points
	 */
	public void setPrice(int price) {
		this.price = price;
	}
	
	/**
	 * Get the plant's price
	 * 
	 * @return price the cost of the plant in Sun points
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * set the plant's health
	 * 
	 * @param health the health of the plant
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * get the plant's health
	 * 
	 * @return health the health of the plant
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * set the plant's damage
	 * 
	 * @param damage the plant's damage output per turn
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * get the plant's damage
	 * 
	 * @return damage the plant's damage output per turn
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * set the plant's resource generation rate
	 * 
	 * @param resrc_gen the plant's Sun point generation rate per turn
	 */
	public void setResrc_gen(int resrc_gen) {
		this.resrc_gen = resrc_gen;
	}
	
	/**
	 * get the plant's resource generation rate
	 * 
	 * @return resrc_gen the plant's Sun point generation rate per turn
	 */
	public int getResrc_gen() {
		return resrc_gen;
	}
	
	/**
	 * check if the plant can attack
	 * 
	 * @return true if damage is greater than 0, false otherwise
	 */
	public boolean canAttack() {
		return damage > 0;
	}
	
	/**
	 * check if the plant can generate resource
	 * 
	 * @return true if resrc_gen is greater than 0, false otherwise
	 */
	public boolean canResrc_gen() {
		return resrc_gen > 0;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @param atkRange_X the plant's attack range in the X axis
	 */
	public void setAtkRange_X(int atkRange_X) {
		this.atkRange_X = atkRange_X;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @param atkRange_Y the plant's attack range in the Y axis
	 */
	public void setAtkRange_Y(int atkRange_Y) {
		this.atkRange_Y = atkRange_Y;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @return atkRange_X the plant's attack range in the X axis
	 */
	public int getAtkRange_X() {
		return atkRange_X;
	}
	
	/**
	 * used for implementation of area of effect attacks
	 * 
	 * @return atkRange_Y the plant's attack range in the Y axis
	 */
	public int getAtkRange_Y() {
		return atkRange_Y;
	}
	
	/**
	 * check if the plant is dead
	 * 
	 * @return true if the plant's health is less than or equal to 0, false otherwise
	 */
	public boolean isDead() {
		return health<=0;
	}
	
	/**
	 * calculate the health of the plant upon taking damage
	 * 
	 * @param dmg the damage the plant has received
	 */
	public void takeDmg(int dmg) {
		health = health - dmg;
	}
	
	/**
	 * get the plant purchase cooldown turn timer
	 * 
	 * @return cooldown the plant's purchase cooldown in turns
	 */
	public int getCooldown() {
		return cooldown;
	}
}
