/**
 * A "factory" for zombies.
 * 
 * @author Hoang Bui 1010129049
 * @version 1, October 15 2018
 */
public class ZombieFactory {
	public ZombieFactory() {}
	public static Zombie createZombie(ZombieTypes zombieType) {
		if(zombieType == ZombieTypes.WALKER) {
			return new Walker();
		}
		return null;
	}
}
