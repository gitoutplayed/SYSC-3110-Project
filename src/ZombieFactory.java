
public class ZombieFactory {
	public ZombieFactory() {}
	public static Zombie createZombie(zombieTypes zombieType) {
		if(zombieType == zombieTypes.WALKER) {
			return new Walker();
		}
		return null;
	}
}
