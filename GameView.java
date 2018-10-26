import java.util.List;

import javax.swing.JButton;

public class GameView {

	private  JButton grid [][];
	private int currentTurn;
	private Zombie currentZombie;
	private int sunCounter;
	private List <Plant> currentPlants;
	private List <Zombie> currentZombies;
	
	public int getCurrentTurn() {
		return currentTurn;
	}

public Zombie printZombie(){
	return currentZombie;
	
}

public void printPlants(){
		System.out.println("Plants:"+ currentPlants);
}
public int getSunCounter(){
	return sunCounter;
	}
public void printGrid(){
	System.out.println(grid);
	System.out.println(currentZombies);
	System.out.println(currentPlants);
}

public void printShop(){
	System.out.println(currentPlants );
	System.out.println(sunCounter);
}
}
	

