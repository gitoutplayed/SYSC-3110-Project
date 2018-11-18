package plant;
/**
 * This class defines a PeaShooter's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 16th
 *
 */
public class PeaShooter extends Plant{
	
	private static final int PRICE = 100;
	private static final int HEALTH = 40;
	private static final int DAMAGE = 10;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 100;
	private static final int ATKRANGE_Y = 1;
	private static final int COOLDOWN = 3;
	
	/**
	 * Constructs a new PeaShooter
	 */
	public PeaShooter() {
		super(PlantName.PeaShooter, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		
		try {
			icon = loadIcon("peashooter");
			shopIcon = loadIcon("peashooterShop");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructs a new PeaShooter that is a copy of specified PeaShooter.
	 */
	public PeaShooter(Plant plant) {
		super(plant);
	}
	
}
