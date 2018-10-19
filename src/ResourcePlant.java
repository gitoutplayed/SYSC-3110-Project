/**
 * @author Souheil
 *
 */
public abstract class ResourcePlant extends Plant {
	private int Resrc_gen;
	
	public ResourcePlant(PlantName name, int price, int health, int Resrc_gen) {
		super(name, price, health);
		this.Resrc_gen = Resrc_gen;
	}
	
	private void setResrc_gen(int Resrc_gen) {
		this.Resrc_gen = Resrc_gen;
	}
	
	private int getResrc_gen() {
		return Resrc_gen;
	}
	
}
