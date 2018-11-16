package zombie;

import java.io.IOException;

import tile.TileTypes;

/**
 * Class for walker zombie
 * 
 * @author Hoang Bui 1010129049
 * @version 2, October 16 2018
 */
public class Walker extends Zombie {
	public Walker() {
		super(3, 10, 30, 1, ZombieTypes.WALKER);
		
		try {
			icons.putIfAbsent(TileTypes.GRASS, loadIcon("zombieGrass"));
			icons.putIfAbsent(TileTypes.CONCRETE, loadIcon("zombieConcrete"));
			icons.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadIcon("zombieRoad"));
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}
