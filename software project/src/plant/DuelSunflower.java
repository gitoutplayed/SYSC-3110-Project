package plant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import tile.TileTypes;

/**
 * This class defines a Duel SunFlower's fields for instantiation.
 * 
 * @author Hoang Bui, 101029049
 * @version Nov 25th
 */
public class DuelSunflower extends Plant{
	private static final int PRICE = 200;
	private static final int HEALTH = 40;
	private static final int DAMAGE = 0;
	private static final int RESRC_GEN = 50;
	private static final int ATKRANGE_X = 0;
	private static final int ATKRANGE_Y = 0;
	private static final int COOLDOWN = 5;

	private static Map<TileTypes, BufferedImage> images;
	
	public DuelSunflower() {
		super(PlantName.DuelSunflower, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		loadImages();
	}
	
	/**
	 * Constructs a new SunFlower that is a copy of specified SunFlower.
	 */
	public DuelSunflower(Plant plant) {
		super(plant);
	}
	
	/**
	 * Loads the images for the plant
	 */
	protected void loadImages() {
		if(images == null) {
			images = new HashMap<TileTypes, BufferedImage>();

			try {
				images.putIfAbsent(TileTypes.GRASS,   loadImage("duelSunflower"));
				images.putIfAbsent(TileTypes.SHOP,  loadImage("duelSunflowerShop"));
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
