import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * This class represents the GameController.
 * 
 * @author Tamer Ibrahim 101032919
 */
public class GameController {

	Game game;
	GameView gameView;


	public GameController() {
		game = new Game();
		gameView = new GameView(game);		
	}


	/**
	 * starts a new game object. 
	 * Checks for user's inputs and calls the appropriate methods
	 */
	public void start() {
		game.loadLevel(1); 
		game.start();
		try {
			BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));

			while(true) {
				System.out.println("\n\n#####################################\n\n\nThe current grid is:");
				gameView.printGrid();

				System.out.print("\nEnter command:  ");
				String command = reader.readLine().toLowerCase().trim();

				if (command.equals("buy plant")){
					boolean plantAdded = buyPlant();
					if(plantAdded) { System.out.println("Error, please try again."); }
					break;
				} 

				else if (command.equals("quit")) {
					boolean didQuit = quit();
					if(didQuit) { System.exit(1); }
					break;
				} 


				else if (command.equals("help")){
					printHelp();
					break;
				} 

				else {
					System.out.println("Invalid command");
				}
			}
		}

		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}



	/**
	 * Print out help information for each command.
	 *
	 */
	public void printHelp() {
		System.out.println("\n 1. write buy plant to buy plants \n");
		System.out.println(" 2. write quit to quite the game \n");
	}


	/** 
	 * "Quit" was entered. Check the rest of the command to see
	 * whether we really quit the game.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private boolean quit(){
		System.out.println("are you sure you want to quit?");
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		String word = null;
		try {
			word = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if(word.equals("quit")) {
			System.out.println("Quitting the game....");
			return false;
		}
		else {
			return true;  // signal that we want to quit
		}
	}


	/** 
	 * "buy plant" was entered. Asks the user for the Plant's type, the row number and the column's number
	 * @return true, if the plant type entered is , false otherwise.
	 */

	public boolean buyPlant() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
			System.out.println("Enter Type");
			
			String type = reader.readLine();
			PlantName name = PlantName.PeaShooter ; 
			PlantName name2= PlantName.SunFlower;// convert name to a plantName type
			

			System.out.println("Enter ROW");
			String xS = reader.readLine();
			int row = Integer.parseInt(xS); //convert x and y to integers

			System.out.println("Enter COLUMN");
			String yS = reader.readLine();
			int column = Integer.parseInt(yS); //convert x and y to integers

			return game.buyPlant(name, row, column);

		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}


	public static void main(String[] args) {
		GameController controller = new GameController();
		controller.start();
	}
}
