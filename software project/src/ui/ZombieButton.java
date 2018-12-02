package ui;

import javax.swing.JButton;
import zombie.ZombieTypes;

/**
 * This class represents a ZombieButton. The ZombieButton is essentially a JBution but has the ability to hold
 * name of a zombie.
 * 
 * @author Michael Fan 101029934
 * @version Dec 1, 2018
 *
 */
public class ZombieButton extends JButton {
	private ZombieTypes zombie;
	
	/**
	 * Constructs a new ZombieButton.
	 */
	public ZombieButton() {
		zombie = null;
	}
	
	/**
	 * Sets the zombie that this ZombieButton should hold.
	 * 
	 * @param zombie the zombie that this ZombieButton should hold
	 */
	public void setZombie(ZombieTypes zombie) {
		this.zombie = zombie;
	}
	
	/**
	 * Returns the zombie that this ZombieButton holds.
	 * 
	 * @return the plant that this ZombieButton holds
	 */
	public ZombieTypes getZombie() {
		return zombie;
	}
}
