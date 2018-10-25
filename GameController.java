
public class GameController {
	GameState gameState;
	private Parser parser;

	public GameController() {
		gameState = new GameState();
	}
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
	private boolean proccessCommand (Command command)
	{
		boolean wantToQuit=false;
		if(command.isUnknown()) {
			System.out.println("can't quit");
			return false;
		}
		String commandWord = command.getCommandWord();
		if(commandWord.contentEquals("help")) {
			printHelp();
		}
		else if (commandWord.equals("quit")){
			wantToQuit= quit(command);
		}
		//else command not recognised.
		return wantToQuit;
		}
		
		
	
    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
	public void printHelp() {
		System.out.println("What do you need help with?");
		parser.showCommands();
		
	}
    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

	public void cancel () {
		
	}
	//public Plant buyPlant() {
		
	//
//}
}
