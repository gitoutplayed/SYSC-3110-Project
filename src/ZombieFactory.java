
public class ZombieFactory {
	public ZombieFactory() {}
	public static Zombie createZombie(ZombieTypes zombieType) {
		if(zombieType == ZombieTypes.WALKER) {
			return new Walker();
		}
		return null;
	}
}
