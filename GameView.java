import java.util.List;

import javax.swing.JButton;

public class GameView {

	//	private  JButton grid [][];

	private Game game; 

	public GameView(Game game) {
		this.game = game;
	}



	//	public String printZombie(){
	//		
	//	}

	//	public void printPlants(){
	//	}


	public void printGrid(){
		//loop through the state and print line by line
		String[][] readOnly = game.getGrid();
		for(int i = 0; i < readOnly.length; i++) {
			for(int j = 0; j < readOnly[i].length; j++) {
				System.out.print(readOnly[i][j]);
			}	
			System.out.println();
		}
	}

	public void printShop(){
		//		System.out.println(currentPlants );
		//		System.out.println(sunCounter);
	}
}


