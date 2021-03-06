package zombie;
/**
 * A "factory" for zombies.
 * 
 * @author Hoang Bui 1010129049
 * @version 1, October 15 2018
 */
public class ZombieFactory {
	/**
	 * Returns a Zombie of the specified zombie type.
	 * 
	 * @param zombieType the type of the zombie
	 * 
	 * @return a Zombie of the specified zombie type
	 */
	public static Zombie createZombie(ZombieTypes zombieType) {
		if(zombieType == ZombieTypes.WALKER) {
			return new Walker();
		}
		else if(zombieType == ZombieTypes.CONEHAT) {
			return new ConeHat();
		}
		else if(zombieType == ZombieTypes.BUCKETHAT) {
			return new BucketHat();
		}
		else if(zombieType == ZombieTypes.FOOTBALL) {
			return new Football();
		}
		else if(zombieType == ZombieTypes.NEWSPAPER) {
			return new Newspaper();
		}
		return null;
	}
	
	/**
	 * Returns a Zombie that is a copy of the specified Zombie.
	 * 
	 * @param zombie the Zombie that is to be copied
	 * 
	 * @return a Zombie that is a copy of the specified Zombie
	 */
	public static Zombie createZombieCopy(Zombie zombie) {
		if(zombie.getZombieType() == ZombieTypes.WALKER) {
			return new Walker();
		}
		else if(zombie.getZombieType() == ZombieTypes.CONEHAT) {
			return new ConeHat();
		}
		else if(zombie.getZombieType() == ZombieTypes.BUCKETHAT) {
			return new BucketHat();
		}
		else if(zombie.getZombieType() == ZombieTypes.FOOTBALL) {
			return new Football();
		}
		else if(zombie.getZombieType() == ZombieTypes.NEWSPAPER) {
			return new Newspaper();
		}
		return null;
	}
}
