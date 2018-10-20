/**
 * @author Souheil
 *
 */
public class PeaShooter extends Plant{
	
	private static final int PRICE = 100;
	private static final int HEALTH = 40;
	private static final int DAMAGE = 10;
	private static final int RESRC_GEN = 0;
	private static final int ATKRANGE_X = 100;
	private static final int ATKRANGE_Y = 1;
	
		
	public PeaShooter() {
		super(PlantName.PeaShooter, PRICE, HEALTH, DAMAGE, RESRC_GEN, ATKRANGE_X, ATKRANGE_Y);
	}
	
}
