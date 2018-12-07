package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import game.Game;
import game.GameState;
import game.Level;
import game.LevelManager;
import junit.framework.TestCase;
import plant.Plant;
import plant.PlantName;
import tile.Tile;
import ui.GameEvent;
import ui.GameListener;
import ui.GameView;
import zombie.ZombieTypes;

/**
 * A test class for class game's controller
 * GameController uses Game to interact with GameState
 * 
 * @author Hoang Bui, 101029049
 * @version 1
 */

public class TestGameController extends TestCase{
	private boolean shovel;
	private boolean levelFinish;
	private boolean levelLoaded;
	private PlantName selectedPlant;
	private boolean isTileTaken;
	private int numSunCounter;
	private boolean hasPreviousState;
	private boolean isLevel;
	private boolean save;
	private boolean success;
	private boolean ser;
	private boolean file;
	private boolean fileLength;
	private boolean fileRead;
	
	protected void setUp() {
		levelLoaded = false;
		shovel = false;
		levelFinish = true;
		selectedPlant = null;
		isTileTaken = true;
		numSunCounter = 50;
		hasPreviousState = false;
		isLevel = false;
		save = false;
		success = false;
		ser = false;
		file = false;
		fileLength = false;
		fileRead = false;
	}
	
	public void testRestart() {
		assertFalse("Restart should return false since a level has not been loaded", restart());
		levelLoaded = true;
		assertTrue("Restart should return true", restart());
	}
	
	public void testEndTurn() {
		assertFalse("End turn should return false since a level has not been loaded", endTurn());
		levelLoaded = true;
		assertFalse("End turn should return false since level is finished", endTurn());
		levelFinish = false;
		assertTrue("End turn should return true", endTurn());
	}
	
	public void testBuyPlant() {
		assertFalse("Buy plant should return false since selectedPlant is null", buyPlant(1,1));
		selectedPlant = PlantName.SunFlower;
		assertFalse("Buy plant should return false since levelLoaded is false", buyPlant(1,1));
		levelLoaded = true;
		assertFalse("Buy plant should return false since levelFinish is true", buyPlant(1,1));
		levelFinish = false;
		assertFalse("Buy plant should return false since row is out of range", buyPlant(-1,1));
		assertFalse("Buy plant should return false since column is out of range", buyPlant(1,-1));
		assertFalse("Buy plant should return false since tile is taken", buyPlant(1,1));
		isTileTaken = false;
		assertFalse("Buy plant should return false since amount of sun counter is too small", buyPlant(1,1));
		numSunCounter = 100;
		assertTrue("Buy plant should return true", buyPlant(1,1));
	}
	
	public void testShovel() {
		isTileTaken = false;
		assertFalse("Shovel should return false since the level hasn't been loaded", shovel(1,1));
		levelLoaded = true;
		assertFalse("Shovel should return false since the level is finished", shovel(1,1));
		levelFinish = false;
		assertFalse("Shovel should return false since the selected row is out of range", shovel(-1,1));
		assertFalse("Shovel should return false since the selected column is out of range", shovel(1,-1));
		assertFalse("Shovel should return false since the selected tile is empty", shovel(1,1));
		isTileTaken = true;
		assertTrue("Shovel should return true", shovel(1,1));
	}
	
	public void testUndo() {
		assertFalse("Undo should return false since the level hasn't been loaded", undo());
		levelLoaded = true;
		assertFalse("Undo should return false since the level is finished", undo());
		levelFinish = false;
		assertFalse("Undo should return false since there is no previous state", undo());
		hasPreviousState = true;
		assertTrue("Undo should return true", undo());
	}
	
	public void testRedo() {
		assertFalse("redo should return false since the level hasn't been loaded", redo());
		levelLoaded = true;
		assertFalse("Undo should return false since the level is finished", redo());
		levelFinish = false;
		assertFalse("redo should return false since there is no previous state", redo());
		hasPreviousState = true;
		assertTrue("redo should return true", redo());
	}
	
	public void testLoad() {
		levelFinish = false;
		
		assertFalse("LoadLevel should return false since the level hasn't been loaded", loadLevel(1));
		levelLoaded = true;
		assertFalse("LoadLevel should return false since the level is finished", loadLevel(1));
		levelFinish = true;
		assertFalse("LoadLevel should return false since there is no previous state", loadLevel(1));
		isLevel = true;
		assertTrue("LoadLevel should return true", loadLevel(1));
	}
	
	public void testLoadNextLevel() {
		levelFinish = false;
		
		assertFalse("loadNextLevel should return false since the level hasn't been loaded", loadNextLevel());
		levelLoaded = true;
		assertFalse("loadNextLevel should return false since the level is finished", loadNextLevel());
		levelFinish = true;
		assertFalse("loadNextLevel should return false since there is no previous state", loadNextLevel());
		isLevel = true;
		assertTrue("loadNextLevel should return true", loadNextLevel());
	}
	
	public void testLoadPreviousLevel() {
		levelFinish = false;
		
		assertFalse("loadPreviousLevel should return false since the level hasn't been loaded", loadPreviousLevel());
		levelLoaded = true;
		assertFalse("loadPreviousLevel should return false since the level is finished", loadPreviousLevel());
		levelFinish = true;
		assertFalse("loadPreviousLevel should return false since there is no previous state", loadPreviousLevel());
		isLevel = true;
		assertTrue("loadPreviousLevel should return true", loadPreviousLevel());
	}
	
	public void testSave() {
		assertFalse("save should return false since it is not a valid save", save());
		save = true;
		assertFalse("save should return false since the level is not loaded", save());
		levelLoaded = true;
		assertFalse("save should return false since the level is finished", save());
		levelFinish = false;
		assertFalse("save should return false since the save was unsuccessful", save());
		success = true;
		assertTrue("save should return true", save());
	}
	
	public void testLoadSave() {
		assertFalse("load save should return false since it is not a valid save", loadSave());
		save = true;
		assertFalse("load save should return false since the file extention is not ser", loadSave());
		ser = true;
		assertFalse("load save should return false since the load was unsuccessful", loadSave());
		success = true;
		assertTrue("load save should return true", loadSave());
	}
	
	public void testCreateCustomLevel() {
		assertFalse("createCustomLevel should return false since it is not a valid file", createCustomLevel());
		file = true;
		assertFalse("createCustomLevel should return false since the creation was unsuccessful", createCustomLevel());
		success = true;
		assertTrue("createCustomLevel should return true", createCustomLevel());
	}
	
	public void testLoadCustomLevels() {
		assertFalse("loadCustomLevels should return false since it is not a valid file", loadCustomLevels());
		file = true;
		assertFalse("loadCustomLevels should return false since it has an invalid file length", loadCustomLevels());
		fileLength = true;
		assertFalse("loadCustomLevels should return false since it was unable to read the file", loadCustomLevels());
		fileRead = true;
		assertFalse("loadCustomLevels should return false since the load was unsuccessful", loadCustomLevels());
		success = true;
		assertTrue("loadCustomLevels should return true", loadCustomLevels());
	}
	
	/**
	 * Game's restart method adjusted for testing
	 * 
	 * @return true if the level has been reseted, returns false if the level has not been restarted 
	 */
	private boolean restart() {
		if(!levelLoaded) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's endTurn method adjusted for testing
	 * 
	 * @return true if the we can end turn, otherwise false 
	 */
	private boolean endTurn() {
		if(!levelLoaded) {
			return false;
		}

		if(levelFinish) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's buyPlant method adjusted for testing
	 * 
	 * @param true if the we can buy a plant turn, otherwise false 
	 */
	public boolean buyPlant(int row, int col) {
		if(selectedPlant == null) {
			return false;
		}

		if(!levelLoaded) {
			return false;
		} else if(levelFinish) {
			return false;
		} else if(row < 0 || row >= 9) {
			return false;
		} else if(col < 1|| col > 9) {
			return false;
		} else if(isTileTaken) {
			return false;
		}

		if(numSunCounter < 100) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's shovel method adjusted for testing
	 * 
	 * @param row the row of the plant that is to be removed
	 * @param col the column of the the plant that is to be removed
	 * 
	 * @return true if the we can shovel, otherwise false 
	 */
	public boolean shovel(int row, int col) {

		if(!levelLoaded) {
			return false;
		} else if(levelFinish) {
			return false;
		} else if(row < 0 || row >= 9) {
			return false;
		} else if(col < 1 || col > 9) {
			return false;
		}

		if(!isTileTaken) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's undo method adjusted for testing
	 * 
	 * @return true if the we can undo, otherwise false 
	 */
	public boolean undo() {
		if(!levelLoaded) {
			return false;
		} else if(levelFinish) {
			return false;
		}

		if(!hasPreviousState) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's redo method adjusted for testing
	 * 
	 * @return true if the we can redo, otherwise false 
	 */
	public boolean redo() {
		if(!levelLoaded) {
			return false;
		} else if(levelFinish) {
			return false;
		}

		if(!hasPreviousState) {
			return false;
		}
		return true;
	}
	
	/**
	 *  Game's load level method adjusted for testing
	 * 
	 * @param levelID the id of the level to load
	 * @return true if the we can load, otherwise false 
	 */
	public boolean loadLevel(int levelID) {
		if(levelLoaded && !levelFinish) {
			return false;
		}

		if(!isLevel) {
			return false;
		}
		return true;
	}
	
	/**
	 *  Game's load next level method adjusted for testing
	 * 
	 * @return true if the we can load next level, otherwise false 
	 */
	public boolean loadNextLevel() {
		if(levelLoaded && !levelFinish) {
			return false;
		}

		if(!isLevel) {
			return false;
		}
		return true;
	}
	
	/**
	 *  Game's load previous level method adjusted for testing
	 * 
	 * @return true if the we can load previous level, otherwise false 
	 */
	public boolean loadPreviousLevel() {
		if(levelLoaded && !levelFinish) {
			return false;
		}

		if(!isLevel) {
			return false;
		}
		return true;
	}
	
	/**
	 *  Game's save method adjusted for testing
	 * 
	 * @return true if the we can save, otherwise false 
	 */
	public boolean save() {
		if(!save) {
			return false;
		} else if(!levelLoaded) {
			return false;
		} else if(levelFinish) {
			return false;
		}

		if(!success) {
			return false;
		}
		return true;
	}
	
	/**
	 *  Game's load save method adjusted for testing
	 * 
	 * @return true if the we can load save, otherwise false 
	 */
	public boolean loadSave() {
		if(!save) {
			return false;
		} else if(!ser) {
			return false;
		}
		if(!success) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's create custom level method adjusted for testing
	 * 
	 * @return true if the we can create a custom level, otherwise false 
	 */
	public boolean createCustomLevel() {
		if(!file) {
			return false;
		}
		if(!success) {
			return false;
		}
		return true;
	}
	
	/**
	 * Game's load custom level method adjusted for testing
	 * 
	 * @return true if the we can load a custom level, otherwise false 
	 */
	public boolean loadCustomLevels() {
		if(!file || !fileLength) {
			return false;
		}
		
		if(!fileRead) {
			return false;
		}
		
		if(!success) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		junit.textui.TestRunner.run(TestGameController.class);
	}
}
