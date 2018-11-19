package zombie;

import java.io.IOException;

import tile.TileTypes;

/**
 * Class for bucket head zombie
 * 
 * @author Tamer Ibrahim 101032919
 * @version 19, November 
 */
public class BucketHead extends Zombie {
	/**
	 * Constructs a new BucketHead Zombie.
	 */
	public BucketHead() {
		super(10, 40, 35, 1, ZombieTypes.CONEHAT);
		
		try {
			icons.putIfAbsent(TileTypes.GRASS, loadIcon("bucketGrass"));
			icons.putIfAbsent(TileTypes.CONCRETE, loadIcon("bucketConcrete"));
			icons.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadIcon("bucektRoad"));
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructs a BucketHead that is a copy of specified conehat.
	 */
	public BucketHead(Zombie zombie) {
		super(zombie);
		
		try {
			icons.putIfAbsent(TileTypes.GRASS, loadIcon("bucketGrass"));
			icons.putIfAbsent(TileTypes.CONCRETE, loadIcon("bucketConcrete"));
			icons.putIfAbsent(TileTypes.ZOMBIE_SPAWN, loadIcon("bucketRoad"));
		}
		catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}
}