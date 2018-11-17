package zombie;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * Super class for zombies
 * 
 * @author Hoang Bui 1010129049
 * @version 4, November 16 2018
 */
abstract public class Zombie {
	private int damage;
	private int health;
	private int atkRange;
	private int movementSpeed;
	private int movementCounter;
	protected ZombieTypes zombieType;
	protected Map<TileTypes, ImageIcon> icons;

	/**
	 * Constructor method for class Zombie
	 * 
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
		this.movementSpeed = movementSpeed;
		this.movementCounter = 0;
		this.zombieType = zombieType;
		icons = new HashMap<TileTypes, ImageIcon>();
	}

	/**
	 * Getter method to get the zombie's damage value
	 * 
	 * @return returns the zombie's damage value
	 */
	public int getDamage() {
		return this.damage;
	}

	/**
	 * Setter method to set the zombie's damage
	 * 
	 * @param damage new value for the zombie's damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Getter method to get the zombie's health
	 * 
	 * @return returns the zombie's current health
	 */
	public int getHealth() {
		return this.health;
	}

	/**
	 * Setter method to set the zombie's health
	 * 
	 * @param health the new health of the zombie
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Getter method to get the zombie's atk range
	 * 
	 * @return returns the zombie's atk rnage value
	 */
	public int getAtkRange() {
		return atkRange;
	}

	/**
	 * Setter method to set the zombie's atk range
	 * 
	 * @param atkRange the new value for the zombie's atk range
	 */
	public void setAtkRange(int atkRange) {
		this.atkRange = atkRange;
	}

	/**
	 * Getter method to get the current movement speed of the zombie
	 * 
	 * @return returns the zombie's current movement speed
	 */
	public int getCurrentMovementSpeed() {
		return this.movementSpeed;
	}

	/**
	 * Setter method to set the zombie's current movement speed
	 * 
	 * @param movementSpeed new value for the zombie's current movement speed
	 */
	public void setCurrentMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	/**
	 * Getter method to get the zombie's movement Counter
	 * 
	 * @return the zombie's movement counter
	 */
	public int getMovementCounter() {
		return this.movementCounter;
	}

	/**
	 * Getter method to determine the type of zombie
	 * 
	 * @return zombieType returns the zombie type
	 */
	public ZombieTypes getZombieType() {
		return this.zombieType;
	}

	/**
	 * Method to determine if the zombie is dead
	 * 
	 * @return true if the health is less than or equal zero
	 */
	public boolean isDead() {
		return this.health <= 0;
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
	public void resetMovementCounter() {
		this.movementCounter = 0;
	}

	/**
	 * Method to obtain the movement progress as a comparable value
	 * 
	 * @return the zombies progress
	 */
	public double getZombieProgress() {
		return ((double) this.movementCounter) / this.movementSpeed;
	}

	/**
	 * Method for when the zombie takes damage
	 * 
	 * @param damage The damage taken for the zombie
	 */
	public void takeDamage(int damage) {
		this.health -= damage;
	}

	/**
	 * Method to check if the zombie is ready to move
	 * 
	 * @return true if the movement counter is the same or greater as the movement speed
	 */
	public boolean isReadyToMove() {
		return this.movementCounter == this.movementSpeed;
	}

	/**
	 * Return an icon given the type of the tile
	 * 
	 * @param tileType the type of the tile that the zombie is on
	 * 
	 * @return icon the tile's image
	 */
	public ImageIcon getIcon(TileTypes tileType) {
		return icons.get(tileType);
	}

	/**
	 * Method to generate the tile's image
	 * 
	 * @param name the name of the file
	 * @return new ImageIcon(ImageIO.read(new File("..\\..\\images\\" + name +
	 *         ".png"))) a new image icon
	 * @throws IOException when the method fails to generate the image
	 */
	protected ImageIcon loadIcon(String name) throws IOException {
		return new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource("zombie/" + name + ".png")));
	}
}
