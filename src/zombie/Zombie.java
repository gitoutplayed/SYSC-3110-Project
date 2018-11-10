package zombie;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * Super class for zombies
 * 
 * @author Hoang Bui 1010129049
 * @version 3, November 10 2018
 */
abstract public class Zombie {
	private int damage;
	private int health;
	private int atkRange;
	private int currentMovementSpeed;
	private int originalMovementSpeed;
	private int movementCounter;
	private int slowDuration;
	private ZombieTypes zombieType;
	private ImageIcon icon;
	
	/**
	 * Constructor method for class Zombie
	 * @param movementSpeed the zombie's movement speed
	 * @param damage the zombie's damage
	 * @param health the zombie's health
	 * @param atkRange the zombie's attack range
	 * @param zombieType the zombie's type
	 */
	public Zombie(int movementSpeed, int damage, int health, int atkRange, ZombieTypes zombieType) {
		this.damage = damage;
		this.health = health;
		this.atkRange = atkRange;
		this.currentMovementSpeed = movementSpeed;
		this.originalMovementSpeed = movementSpeed;
		this.movementCounter = 0;
		this.slowDuration = 0;
		this.zombieType = zombieType;
		
		try {
			if(zombieType == ZombieTypes.WALKER) {
				icon = loadIcon("zombieGrass");
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Getter method to get the zombie's damage value
	 * @return returns the zombie's damage value
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Setter method to set the zombie's damage
	 * @param damage new value for the zombie's damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Getter method to get the zombie's health
	 * @return returns the zombie's current health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Setter method to set the zombie's health
	 * @param health the new health of the zombie
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Getter method to get the zombie's atk range
	 * @return returns the zombie's atk rnage value
	 */
	public int getAtkRange() {
		return atkRange;
	}
	/**
	* Setter method to set the zombie's atk range
	* @param atkRange the new value for the zombie's atk range
	*/
	public void setAtkRange(int atkRange) {
		this.atkRange = atkRange;
	}

	/**
	 * Getter method to get the current movement speed of the zombie
	 * @return returns the zombie's current movement speed
	 */
	public int getCurrentMovementSpeed() {
		return this.currentMovementSpeed;
	}

	/**
	 * Setter method to set the zombie's current movement speed
	 * @param movementSpeed new value for the zombie's current movement speed
	 */
	public void setCurrentMovementSpeed(int movementSpeed) {
		this.currentMovementSpeed = movementSpeed;
	}

	/**
	 * Getter method to get the zombie's movement Counter
	 * @return the zombie's movement counter
	 */
	public int getMovementCounter() {
		return this.movementCounter;
	}
	
	/**
	 * Getter method to determine the type of zombie
	 * @return zombieType returns the zombie type
	 */
	public ZombieTypes getZombieType() {
		return this.zombieType;
	}
	
	/**
	 * Method to determine if the zombie is dead
	 * @return true if the health is less than or equal zero
	 */
	public boolean isDead() {
		return this.health<=0;
	}
	
	/**
	 * Method to determine if the zombie has move accross a tile
	 * @return true if the zombie's movement counter exceeds it's movement speed
	 */
	public boolean isDoneTile() {
		return this.movementCounter >= this.currentMovementSpeed;
	}
	
	/**
	 * Method to determine if the zombie is affected by a slow effect
	 * @return true if the value of slowDuration is above 0
	 */
	public boolean isSlowed() {
		return this.slowDuration > 0;
	}
	
	/**
	 * Method to decrement the zombie's slow duration
	 */
	public void decrementSlowDuration() {
		if(this.isSlowed()) {
			slowDuration--;
			if(!(this.isSlowed())) {
				this.currentMovementSpeed = this.originalMovementSpeed;
			}
		}
	}
	
	/**
	 * Method to increment the zombie's movement counter
	 */
	public void incrementMovementCounter() {
		this.movementCounter++;
	}
	
	/**
	 * Method to reset the movement counter when entering a new tile
	 */
	public void resetMovementCounter(){
		this.movementCounter = 0;
	}
	
	/**
	 * Method to adjust the movement counter when entering a new tile
	 */
	public void adjustMovementCounter() {
		this.movementCounter -= this.currentMovementSpeed;
	}
	
	/**
	 * Method to obtain the movement progress as a comparable value
	 * @return the zombies progress
	 */
	public double getZombieProgress() {
		return ((double)this.movementCounter)/this.currentMovementSpeed;
	}
	
	/**
	 * Method for when the zombie takes damage
	 * @param damage The damage taken for the zombie
	 */
	public void takeDamage(int damage) {
		this.health -= damage; 
	}
	
	/**
	 * Method to check if the zombie is ready to move
	 * @return true if the movement counter is the same as the movement speed
	 */
	public boolean isReadyToMove() {
		return this.movementCounter == this.currentMovementSpeed;
	}
	
	/**
	 * Method to get the tile's image
	 * @return icon the tile's image
	 */
	public ImageIcon getIcon() {
		return icon;
	}

	/**
	 * Method to generate the tile's image
	 * @param name the name of the file
	 * @return new ImageIcon(ImageIO.read(new File("..\\..\\images\\" + name + ".png"))) a new image icon
	 * @throws IOException when the method fails to generate the image
	 */
	private ImageIcon loadIcon(String name) throws IOException {
		return new ImageIcon(ImageIO.read(new File("..\\..\\images\\" + name + ".png")));
	}
}
