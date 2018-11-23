package ui;
import java.util.EventObject;

/**
 * This class represents a GameEvent.
 * 
 * @author Michael Fan 101029934
 * @version Nov 7, 2018
 */
public class GameEvent extends EventObject {
	private boolean success;
	private String message;

	/**
	 * Constrcuts a new GameEvent.
	 * 
	 * @param source the source of the event
	 */
	public GameEvent(Object source) {
		super(source);
	}
	
	/**
	 * Sets success to true if the attempted operation is successful or false otherwise. 
	 * This method returns itself for chaining purpose.
	 * 
	 * @param success true if the attempted operation is successful or false otherwise
	 * 
	 * @return this (the current GameEvent object)
	 */
	public GameEvent setSuccess(boolean success) {
		this.success = success;
		return this;
	}
	
	/**
	 * Sets the message to the specified message. This method returns itself for chaining purpose.
	 * 
	 * @param message the specified message
	 * 
	 * @return this (the current GameEvent object)
	 */
	public GameEvent setMessage(String message) {
		this.message = message;
		return this;
	}

	/**
	 * Returns true if the attempted operation is successful or false otherwise.
	 * 
	 * @return true if the attempted operation is successful or false otherwise
	 */
	public boolean getSuccess() {
		return success;
	}
	
	/**
	 * Returns the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
