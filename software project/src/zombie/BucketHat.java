package zombie;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * Class for bucket head zombie
 * 
 * @author Tamer Ibrahim 101032919
 * @author Hoang Bui 101029049
 * @version 19, November 
 */
public class BucketHat extends Zombie {
	private static Map<TileTypes, BufferedImage> images;
	
	/**
	 * Constructs a new bucket hat zombie
	 */
	public BucketHat() {
		super(5, 100, 200, 1, ZombieTypes.BUCKETHAT);
		loadImages();
	}
	
	/**
	 * Constructs a bucket hat zombie that is a copy of specified bucket hat zombie.
	 * @param zombie the zombie to copy
	 */
	public BucketHat(Zombie zombie) {
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
				images.putIfAbsent(TileTypes.GRASS, loadImage("bucketGrass"));
				images.putIfAbsent(TileTypes.CONCRETE, loadImage("bucketConcrete"));
				images.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadImage("bucketRoad"));
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}