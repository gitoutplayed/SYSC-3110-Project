package plant;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import tile.TileTypes;

/**
 * This class defines a Repeater's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 18th
 *
 */
public class Repeater extends Plant{
	
	private static final int PRICE = 200;
	private static final int HEALTH = 30;
	private static final int DAMAGE = 20;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 100;
	private static final int ATKRANGE_Y = 1;
	private static final int COOLDOWN = 3;
	
	private static Map<TileTypes, BufferedImage> images;
	/**
	 * Constructs a new Repeater
	 */
	public Repeater() {
		super(PlantName.Repeater, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		loadImages();
	}
	
	/**
	 * Constructs a new Repeater that is a copy of specified Repeater.
	 */
	public Repeater(Plant plant) {
		super(plant);
	}
	
	/**
	 * Loads the images for the plant
	 */
	protected void loadImages() {
		if(images == null) {
			images = new HashMap<TileTypes, BufferedImage>();

			try {
				images.putIfAbsent(TileTypes.GRASS,  loadImage("repeater"));
				images.putIfAbsent(TileTypes.SHOP,  loadImage("repeaterShop"));
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
		try {
			icon.setImage(images.get(tileType));
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
			System.err.println(images.get(tileType).toString());
		}
		return icon;
	}
	
}