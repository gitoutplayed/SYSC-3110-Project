/**
 * A class for tiles that is part of zombies vs plants grid
 * 
 * @author Hoang Bui 101029049
 * @version 3, November 10, 2018
 */
package tile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import plant.Plant;
import zombie.Zombie;

public class Tile {
	private Plant residingPlant;
	private List<Zombie> residingZombie;
	private TileTypes tileType;
	private ImageIcon icon;

	/**
	 * Constructor method to create a tile with a specified tile type
	 * 
	 * @param tileType the type of tile
	 */
	public Tile(TileTypes tileType) {
		this.residingPlant = null;
		this.residingZombie = new ArrayList<Zombie>();
		this.tileType = tileType;

		updateIcon(tileType);
	}

	/**
	 * Constructor method to create a tile with a plant already residing on it (Used
	 * for special levels)
	 * 
	 * @param tileType the type of tile
	 * @param residingPlant The plant that will reside in the tile
	 */
	public Tile(Plant residingPlant, TileTypes tileType) {
		this.residingPlant = residingPlant;
		this.residingZombie = new ArrayList<Zombie>();
		this.tileType = tileType;
	}

	/**
	 * Getter method to get the residing plant
	 * 
	 * @return the plant residing in the tile
	 */
	public Plant getResidingPlant() {
		return this.residingPlant;
	}

	/**
	 * Setter method to set the residing plant
	 * 
	 * @param residingPlant the plant that will reside in the tile
	 */
	public void setResidingPlant(Plant residingPlant) {
		this.residingPlant = residingPlant;
	}

	/**
	 * Getter method to get the type of the tile
	 * 
	 * @return the type of the tile
	 */
	public TileTypes getTileType() {
		return this.tileType;
	}

	/**
	 * Setter method to set the type of the tile
	 * 
	 * @param tileType the new type of tile
	 */
	public void setResidingPlant(TileTypes tileType) {
		this.tileType = tileType;
	}

	/**
	 * Getter method to obtain the list of zombies on the tile
	 * 
	 * @return the list of zombies on the tile
	 */
	public List<Zombie> getResidingZombie() {
		return this.residingZombie;
	}

	/**
	 * Method to clear the zombie list
	 */
	public void clearResidingZombie() {
		this.residingZombie = new ArrayList<Zombie>();
	}

	/**
	 * Method to add a new zombie in the zombie list
	 * 
	 * @param zombie the new zombie on the tile
	 */
	public void addZombie(Zombie zombie) {
		residingZombie.add(zombie);
	}

	/**
	 * Method to remove a zombie from the zombie list
	 * 
	 * @param zombie the zombie to remove
	 */
	public void removeZombie(Zombie zombie) {
		residingZombie.remove(zombie);
	}

	/**
	 * Method to determine which zombie is in front of the other zombies
	 * 
	 * @return null if there are no zombies on the tile, returns the front zombie
	 */
	public Zombie getFirstZombie() {
		Zombie frontZombie = null;
		for(Zombie zombie : this.residingZombie) {
			if(frontZombie == null) {
				frontZombie = zombie;
			} else {
				if(zombie.getZombieProgress() > frontZombie.getZombieProgress()) {
					frontZombie = zombie;
				}
			}
		}
		return frontZombie;
	}

	/**
	 * Method to determine if there is a plant on the tile
	 * 
	 * @return true if there is a plant, false if there isn't
	 */
	public boolean hasPlant() {
		if(this.residingPlant != null) {
			return true;
		}
		return false;
	}

	/**
	 * Method to determine if there is a plant on the tile
	 * 
	 * @return true if there is a zombie or more, false if there are none
	 */
	public boolean hasZombie() {
		if(!(this.residingZombie.isEmpty())) {
			return true;
		}
		return false;
	}

	/**
	 * Method to remove the plant
	 */
	public void removePlant() {
		this.residingPlant = null;
	}

	/**
	 * Method to check if the tile is empty
	 * 
	 * @return true if there are no plants and no zombies
	 */
	public boolean isEmpty() {
		return !hasPlant() && !hasZombie();
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
		return new ImageIcon(ImageIO.read(getClass().getClassLoader().getResource(name + ".png")));
	}
	
	public void setTileType(TileTypes tileType) {
		this.tileType = tileType;
	}
	
	public void updateIcon(TileTypes tileType) {
		try {
			if(tileType == TileTypes.GRASS) {
				icon = loadIcon("grass");
			} 
			else if(tileType == TileTypes.LAWNMOWER) {
				icon = loadIcon("lawnmower");
			} 
			else if(tileType == TileTypes.ZOMBIE_SPAWN) {
				icon = loadIcon("road");
			} else if(tileType == TileTypes.CONCRETE) {
				icon = loadIcon("concrete");
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
