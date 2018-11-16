package zombie;

import java.io.IOException;

import tile.TileTypes;

/**
 * Class for walker zombie
 * 
 * @author Hoang Bui 1010129049
 * @version 2, October 16 2018
 */
public class Walker extends Zombie{
	public Walker() {
		super(3, 10, 30, 1, ZombieTypes.WALKER);
	}
	
	/**
	 * Method to update the zombie's icon
	 * 
	 * @param tileType
	 *            the tile type
	 */
	public Zombie updateIcon(TileTypes tileType) {
		try {
			if (tileType == TileTypes.GRASS) {
				icon = loadIcon("zombieGrass");
			} else if (tileType == TileTypes.LAWNMOWER) {
				icon = loadIcon("zombieConcrete");
			} else if (tileType == TileTypes.ZOMBIE_SPAWN) {
				icon = loadIcon("zombieRoad");
			}
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		return this;
	}
}
