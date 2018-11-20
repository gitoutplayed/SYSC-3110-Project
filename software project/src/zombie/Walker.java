package zombie;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * Class for walker zombie
 * 
 * @author Hoang Bui 1010129049
 * @version 17, November 2018
 */
public class Walker extends Zombie {
	private static Map<TileTypes, BufferedImage> images;

	/**
	 * Constructs a new Walker.
	 */
	public Walker() {
		super(3, 30, 50, 1, ZombieTypes.WALKER);
		loadImages();
	}

	/**
	 * Constructs a Walker that is a copy of specified Walker.
	 */
	public Walker(Zombie zombie) {
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
				images.putIfAbsent(TileTypes.GRASS, loadImage("zombieGrass"));
				images.putIfAbsent(TileTypes.CONCRETE, loadImage("zombieConcrete"));
				images.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadImage("zombieRoad"));
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
