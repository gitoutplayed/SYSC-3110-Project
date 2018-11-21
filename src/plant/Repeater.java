package plant;
/**
 * This class defines a Repeater's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Nov 18th
 *
 */
public class Repeater extends Plant{
	
	private static final int PRICE = 200;
	private static final int HEALTH = 30;
	private static final int DAMAGE = 20;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 100;
	private static final int ATKRANGE_Y = 1;
	private static final int COOLDOWN = 3;
	
	/**
	 * Constructs a new Repeater
	 */
	public Repeater() {
		super(PlantName.Repeater, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
		
		try {	
			icon = loadIcon("repeater");
			shopIcon = loadIcon("repeaterShop");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Constructs a new Repeater that is a copy of specified Repeater.
	 */
	public Repeater(Plant plant) {
		super(plant);
	}
	
}
