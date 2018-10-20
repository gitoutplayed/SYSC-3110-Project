import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the LevelManager.
 * 
 * @author Michael Fan 101029934
 */

public class LevelManager {
    private List<Level> levels;
    private int currentLevel;

    public LevelManager() {
	levels = new LinkedList<Level>();
	currentLevel = -1;
	createLevels();
    }

    public void addLevel(Level level) {
	levels.add(level);
    }

    public Level getNextLevel() {
	if (currentLevel == levels.size()) {
	    return null;
	}

	return levels.get(++currentLevel);
    }

    public Level getPreviousLevel() {
	if (currentLevel == 0) {
	    return null;
	}

	return levels.get(--currentLevel);
    }

    public Level getLevel(int index) {
	if (index < 0 || index >= levels.size()) {
	    return null;
	}

	currentLevel = index;
	return levels.get(index);
    }

    public int size() {
	return levels.size();
    }

    public int getCurrentLevel() {
	return currentLevel;
    }

    public void createLevels() {
	// Create the levels here
	Level level = new Level(1);
	level.addPlant(PlantName.PeaShooter);
	level.addPlant(PlantName.SunFlower);
	level.addZombie(ZombieTypes.WALKER, 10);
	level.setBaseSunCounterGain(25);
	level.setSpawnRate(2);
	level.setSpawnAmount(1);

	levels.add(level);
    }
}