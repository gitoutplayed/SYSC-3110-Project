package plant;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * This abstract class defines the overall Plant object. 
 * All specific plants must be a subclass of this class.
 * All specific plants must have constant values set for all fields of this class.
 * 
 * @author Souheil Yazji 101007994
 * @author Hoang Bui 101029049
 * @version Nov 16th
 *
 */
public abstract class Plant implements java.io.Serializable {
	
	private PlantName name;
	private int health, price, damage, resrc_gen, atkRange_X, atkRange_Y, cooldown;
	protected ImageIcon icon;
	
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
		icon = new ImageIcon();
	}
	
	/**
	 * Constructs a new Plant that is a copy of specified Plant.
	 * 
	 * @param plant the Plant that is to be copied
	 */
	public Plant(Plant plant) {
		name = plant.name;
		price = plant.price;
		health = plant.health;
		damage = plant.damage;
		resrc_gen = plant.resrc_gen;
		atkRange_X = plant.atkRange_X;
		atkRange_Y = plant.atkRange_Y;
		cooldown = plant.cooldown;
		icon = plant.icon;
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
	 * Get the plant's price
	 * 
	 * @return price the cost of the plant in Sun points
	 */
	public int getPrice() {
		return price;
	}
	
	/**
	 * Set the plant's health
	 * 
	 * @param health the health of the plant
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * Get the plant's health
	 * 
	 * @return health the health of the plant
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Set the plant's damage
	 * 
	 * @param damage the plant's damage output per turn
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Get the plant's damage
	 * 
	 * @return damage the plant's damage output per turn
	 */
	public int getDamage() {
		return damage;
	}
	
	/**
	 * Set the plant's resource generation rate
	 * 
	 * @param resrc_gen the plant's Sun point generation rate per turn
	 */
	public void setResrc_gen(int resrc_gen) {
		this.resrc_gen = resrc_gen;
	}
	
	/**
	 * Get the plant's resource generation rate
	 * 
	 * @return resrc_gen the plant's Sun point generation rate per turn
	 */
	public int getResrc_gen() {
		return resrc_gen;
	}
	
	/**
	 * Check if the plant can attack
	 * 
	 * @return true if damage is greater than 0, false otherwise
	 */
	public boolean canAttack() {
		return damage > 0;
	}
	
	/**
	 * Check if the plant can generate resource
	 * 
	 * @return true if resrc_gen is greater than 0, false otherwise
	 */
	public boolean canResrc_gen() {
		return resrc_gen > 0;
	}
	
	/**
	 * Used for implementation of area of effect attacks
	 * 
	 * @param atkRange_X the plant's attack range in the X axis
	 */
	public void setAtkRange_X(int atkRange_X) {
		this.atkRange_X = atkRange_X;
	}
	
	/**
	 * Used for implementation of area of effect attacks
	 * 
	 * @param atkRange_Y the plant's attack range in the Y axis
	 */
	public void setAtkRange_Y(int atkRange_Y) {
		this.atkRange_Y = atkRange_Y;
	}
	
	/**
	 * Used for implementation of area of effect attacks
	 * 
	 * @return atkRange_X the plant's attack range in the X axis
	 */
	public int getAtkRange_X() {
		return atkRange_X;
	}
	
	/**
	 * Used for implementation of area of effect attacks
	 * 
	 * @return atkRange_Y the plant's attack range in the Y axis
	 */
	public int getAtkRange_Y() {
		return atkRange_Y;
	}
	
	/**
	 * Check if the plant is dead
	 * 
	 * @return true if the plant's health is less than or equal to 0, false otherwise
	 */
	public boolean isDead() {
		return health<=0;
	}
	
	/**
	 * Calculate the health of the plant upon taking damage
	 * 
	 * @param dmg the damage the plant has received
	 */
	public void takeDmg(int dmg) {
		health = health - dmg;
	}
	
	/**
	 * Get the plant purchase cooldown turn timer
	 * 
	 * @return cooldown the plant's purchase cooldown in turns
	 */
	public int getCooldown() {
		return cooldown;
	}
	
	/**
	 * Returns an image given the name of the image file.
	 * 
	 * @param name the name of the image file
	 * 
	 * @return an image given the name of the image file
	 * 
	 * @throws IOException
	 */
	protected BufferedImage loadImage(String name) throws IOException {
		return ImageIO.read(getClass().getClassLoader().getResource("plant/" + name + ".png"));
	}
	
	/**
	 * Returns the icon of the plant given the type of the tile.
	 * 
	 * @param tileType the type of the tile
	 * 
	 * @return the icon of the plant
	 */
	public abstract ImageIcon getIcon(TileTypes tileType);
	
	/**
	 * Loads the images for the plant
	 */
	protected abstract void loadImages();
}
