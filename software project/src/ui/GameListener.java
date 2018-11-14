package ui;
/**
 * Interface 
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
 */
public interface GameListener {
	public void levelLoaded(GameEvent e);

	public void gameStarted(GameEvent e);

	public void plantBought(GameEvent e);

	public void plantShoveled(GameEvent e);

	public void turnEnded(GameEvent e);
	
	public void levelFinished(GameEvent e);
}
