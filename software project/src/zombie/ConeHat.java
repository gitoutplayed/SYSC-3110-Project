package zombie;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * Class for cone hat zombie
 * 
 * @author Tamer Ibrahim 101032919
 * @author Hoang Bui 101029049
 * @version 19, November 
 */
public class ConeHat extends Zombie {
	private static Map<TileTypes, BufferedImage> images;
	
	/**
	 * Constructs a new coneHat Zombie.
	 */
	public ConeHat() {
		super(4, 50, 120, 1, ZombieTypes.CONEHAT);
		loadImages();
	}
	
	
	/**
	 * Constructs a cone hat zombie that is a copy of specified cone hat zombie
	 * @param zombie the zombie to copy
	 */
	public ConeHat(Zombie zombie) {
		super(zombie);
	}

	/**
	 * Return an icon of the zombie given the type of the tile
	 * 
	 * @param tileType the type of the tile that the zombie is on
	 * 
	 * @return icon the tile's image
	 */
	public ImageIcon getIcon(TileTypes tileType) {
		icon.setImage(images.get(tileType));
		return icon;
	}

	/**
	 * Loads the images for the zombie
	 */
	private void loadImages() {
		if(images == null) {
			images = new HashMap<TileTypes, BufferedImage>();

			try {
				images.putIfAbsent(TileTypes.GRASS, loadImage("coneGrass"));
				images.putIfAbsent(TileTypes.CONCRETE, loadImage("coneConcrete"));
				images.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadImage("coneRoad"));
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}