package zombie;

import java.io.IOException;

import tile.TileTypes;

/**
 * Class for cone head zombie
 * 
 * @author Tamer Ibrahim 101032919
 * @version 19, November 
 */
public class ConeHat extends Zombie {
	/**
	 * Constructs a new coneHat Zombie.
	 */
	public ConeHat() {
		super(5, 20, 35, 1, ZombieTypes.CONEHAT);
		
		try {
			icons.putIfAbsent(TileTypes.GRASS, loadIcon("conehatGrass"));
			icons.putIfAbsent(TileTypes.CONCRETE, loadIcon("conehatConcrete"));
			icons.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadIcon("conehatRoad"));
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructs a CONEHAT that is a copy of specified conehat.
	 */
	public ConeHat(Zombie zombie) {
		super(zombie);
		
		try {
			icons.putIfAbsent(TileTypes.GRASS, loadIcon("conehatGrass"));
			icons.putIfAbsent(TileTypes.CONCRETE, loadIcon("conehatConcrete"));
			icons.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadIcon("conehatRoad"));
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}