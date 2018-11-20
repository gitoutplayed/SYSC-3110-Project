package zombie;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import tile.TileTypes;

/**
 * Class for Newspaper zombie
 * 
 * @author Hoang Bui 1010129049
 * @version 19, November 2018
 */

public class Newspaper extends Zombie {
	private static Map<TileTypes, BufferedImage> images;

	/**
	 * Constructs a new newspaper zombie
	 */
	public Newspaper() {
		super(2, 40, 100, 1, ZombieTypes.NEWSPAPER);
		loadImages();
	}

	/**
	 * Constructs a newspaper zombie that is a copy of specified football zombie
	 */
	public Newspaper(Zombie zombie) {
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
				images.putIfAbsent(TileTypes.GRASS, loadImage("newspaperGrass"));
				images.putIfAbsent(TileTypes.CONCRETE, loadImage("newspaperConcrete"));
				images.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadImage("newspaperRoad"));
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
}
