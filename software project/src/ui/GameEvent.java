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
	 * @param source
	 * @param success
	 * @param message
	 */
	public GameEvent(Object source) {
		super(source);
	}

	public GameEvent setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public GameEvent setMessage(String message) {
		this.message = message;
		return this;
	}

	public boolean getSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
}
