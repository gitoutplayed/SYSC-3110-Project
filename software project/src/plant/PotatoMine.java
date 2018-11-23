package plant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * This class defines a PotatoMine's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 16th
 *
 */
public class PotatoMine extends Plant{
	
	private static final int PRICE = 50;
	private static final int HEALTH = 1000;
	private static final int DAMAGE = 1000;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 1;
	private static final int ATKRANGE_Y = 1;
	private static final int COOLDOWN = 2;
	
	private static Map<TileTypes, BufferedImage> images;

	
	/**
	 * Constructs a new PotatoMine
	 */
	public PotatoMine() {
		super(PlantName.PotatoMine, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		loadImages();
	}
	
	/**
	 * Constructs a new PotatoMine that is a copy of specified PotatoMine.
	 */
	public PotatoMine(Plant plant) {
		super(plant);
	}
	
	/**
	 * Loads the images for the plant
	 */
	protected void loadImages() {
		if(images == null) {
			images = new HashMap<TileTypes, BufferedImage>();

			try {
				images.putIfAbsent(TileTypes.GRASS,  loadImage("potatoMine"));
				images.putIfAbsent(TileTypes.SHOP,  loadImage("potatoMineShop"));
			}
			catch(IOException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Returns the icon of the plant given the type of the tile.
	 * 
	 * @param tileType the type of the tile
	 * 
	 * @return the icon of the plant
	 */
	public ImageIcon getIcon(TileTypes tileType) {
		icon.setImage(images.get(tileType));
		return icon;
	}
}
