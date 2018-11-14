package plant;
/**
 * This class defines a SunFlower's fields for instantiation.
 * 
 * @author Souheil Yazji 101007994
 * @version Oct 29th
 *
 */
public class SunFlower extends Plant {
	
	private static final int PRICE = 50;
	private static final int HEALTH = 40;
	private static final int DAMAGE = 0;
	private static final int RESRC_GEN = 25;
	private static final int ATKRANGE_X = 0;
	private static final int ATKRANGE_Y = 0;
	private static final int COOLDOWN = 1;

	
	public SunFlower() {
		super(PlantName.SunFlower, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y, COOLDOWN);
	}
}
