import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.Stack;
public class GameController {
	
	Game game;
	GameView gameView;
	private Stack<GameState > undoStack = new Stack<GameState  >();
	private Stack<GameState  > redoStack= new Stack <GameState >();
	

	public GameController() {
		game = new Game();
		gameView= new GameView();
		
	try {
		BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			System.out.print("Enter command:");
			String command = reader.readLine();
			if (command.equals("restart")){
				//restart();
				break;
			} else if (command.equals("Help")){
		
			printHelp();
			break;
			} else if (command.equals("quit")) {
				quit();
				break;
			}else if (command.equals("undo")) {
				undo();
				break;
			}else if (command.equals("redo")) {
				redo();
				break;
			} else if (command.equals("shop:")) {
				shop();
				break;
			} else if (command.equals("shovel")) {
				shovel();
				break;
			}
			
			
		}
	}
	
		catch (IOException ioe) {
		ioe.printStackTrace();
		}
	}
	


	
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
	public void printHelp() {
		
		System.out.println("What do you need help with?");
		
		
	}
	//public void restart () {
		//return 
	//}
	
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit() 
    {
    	System.out.println("are you sure you want to quit?");
    	BufferedReader reader= new BufferedReader(new InputStreamReader(System.in));
    	String word = null;
		try {
			word = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
        if(word.equals("quit")) {
            System.out.println("Are you sure you want to quit?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    	
    public void redo() {
    		if (!redoStack.isEmpty()) {
    			try {
    				GameState command= redoStack.pop();

    				undoStack.push(command);
    				
    			}catch (IllegalStateException e) {
    				// report and log
    			}
    		}
    		
    }
    public void undo() {
    		if(!undoStack.isEmpty()) {
    			try {
    				
    				GameState command= undoStack.pop();
    			
    				redoStack.push(command);
    				
    			}catch (IllegalStateException e) {
    				//report and log
    			}
    		}
    }
	public void cancel () {
		
	}
	//public String shop() {
		//return shop.getShopPlants();
	//}
	//public Plant buyPlant() {
		
	//
//}
}
