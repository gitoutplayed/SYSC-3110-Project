/**
 * Super class for zombies
 * 
 * @author Hoang Bui 1010129049
 * @version 2, October 15 2018
 */
abstract public class Zombie {
	private int movementSpeed;
	private int damage;
	private int health;
	private int atkRange;
	private ZombieTypes zombieType;
	
	/**
	 * Constructor method for class Zombie
	 * @param movementSpeed
	 * @param damage
	 * @param health
	 * @param zombieType
	 */
	public Zombie(int movementSpeed, int damage, int health, int atkRange, ZombieTypes zombieType) {
		this.movementSpeed = movementSpeed;
		this.damage = damage;
		this.health = health;
		this.atkRange = atkRange;
		this.zombieType = zombieType;
	}

	/**
	 * Getter method to get the movement speed of the zombie
	 * @return movementSpeed; returns the zombie's movement speed
	 */
	public int getMovementSpeed() {
		return movementSpeed;
	}

	/**
	 * Setter method to set the zombie's movement speed
	 * @param movementSpeed; new value for the zombie's movement speed
	 */
	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * Getter method to get the zombie's damage value
	 * @return damage; returns the zombie's damage value
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * Setter method to set the zombie's damage
	 * @param damage; new value for the zombie's damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Getter method to get the zombie's health
	 * @return health; returns the zombie's current health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Setter method to set the zombie's health
	 * @param health; the new health of the zombie
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Getter method to get the zombie's atk range
	 * @return atkRange; returns the zombie's atk rnage value
	 */
	public int getAtkRange() {
		return atkRange;
	}
	/**
	* Setter method to set the zombie's atk range
	* @param atkRange; the new value for the zombie's atk range
	*/
	public void setAtkRange(int atkRange) {
		this.atkRange = atkRange;
	}

	/**
	 * Getter method to determine the type of zombie
	 * @return zombieType; returns the zombie type
	 */
	public ZombieTypes getZombieType() {
		return zombieType;
	}
	
	/**
	 * Method to determine if the zombie is dead
	 * @return health<=0; check if the health is less than zero
	 */
	public boolean isDead() {
		return health<=0;
	}
}
