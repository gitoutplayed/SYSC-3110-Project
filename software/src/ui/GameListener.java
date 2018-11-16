package ui;
/**
 *  The GameListener interface.
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
 */
public interface GameListener {
	/**
	 * Called when trying to load a level.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void levelLoaded(GameEvent e);
	
	/**
	 * Called when trying start a game(level).
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void gameStarted(GameEvent e);
	
	/**
	 * Called when trying to buy a plant.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void plantBought(GameEvent e);
	
	/**
	 * Called when trying to shovel a plant.
	 * 
	 *  @param e the GameEvent
	 * 
	 * @see GameEvent
	 */
	public void plantShoveled(GameEvent e);
	
	/**
	 * Called when trying to end a turn
	 * 
	 * @param e GameEvent 
	 * 
	 * @see GameEvent
	 */
	public void turnEnded(GameEvent e);
	
	/**
	 * Called when the current level is finished.
	 * 
	 * @param e GameEvent
	 * 
	 * @see GameEvent
	 */
	public void levelFinished(GameEvent e);
}
