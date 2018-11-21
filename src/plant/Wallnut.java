package plant;
/**
 * This class defines a Wallnut's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 18th
 *
 */
public class Wallnut extends Plant {
	
	private static final int PRICE = 25;
	private static final int HEALTH = 300;
	private static final int DAMAGE = 0;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 0;
	private static final int ATKRANGE_Y = 0;
	private static final int COOLDOWN = 5;

	
	public Wallnut() {
		super(PlantName.Wallnut, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		
		try {
			icon = loadIcon("wallnut");
			shopIcon = loadIcon("wallnutShop");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructs a new Wallnut that is a copy of specified Wallnut.
	 */
	public Wallnut(Plant plant) {
		super(plant);
	}
}
