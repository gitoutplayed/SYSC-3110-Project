/**
 * @author Souheil
 *
 */
public abstract class CombatPlant extends Plant{
	
	private int damage;
	
	public CombatPlant(PlantName name, int price, int health, int damage) {
		super(name, price, health);
		this.damage = damage;
	}
	
	private void setDamage(int damage) {
		this.damage = damage;
	}
	
	private int getDamage() {
		return damage;
	}
	
}
