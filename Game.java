import java.util.List;
import java.util.Random;

/**
 * This class represents the Game.
 */

public class Game {
    private Shop shop;
    private LevelManager levelManager;
    private GameState gameState;
    private Level currentLevel;
    public static Random random = new Random();

    private boolean levelLoaded;

    public Game() {
	shop = new Shop();
	levelManager = new LevelManager();

	levelLoaded = false;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Gameplay

    public void start() {
	gameState = new GameState();
	gameState.addPendingZombies(currentLevel.getZombies());
	shop.addPlants(currentLevel.getPlants());
    }

    /**
     * When a turns end all the actions will take place.
     */
    public void endTurn() {
	if (isLevelDone()) {
	    gameState.levelDone();
	    levelLoaded = false;
	    return;
	}

	// Spawn new zombies
	spawnZombies();

	// Gain base sun counter
	gainBaseSunCounter();

	action();

	gameState.nextTurn();

    }

    /**
     * 
     */
    public boolean buyPlant(PlantName plant, int row, int col) {
	Plant newPlant = shop.purchase(plant, gameState.getSunCounter());

	if (newPlant == null) {
	    return false;
	} else if ((row < GameState.BEGINNING || row >= GameState.ZOMBIE_SPAWN)
		|| (col < GameState.BEGINNING || col >= GameState.ZOMBIE_SPAWN)) {
	    return false;
	} else if (gameState.isRowDisabled(row)) {
	    return false;
	} else if (!gameState.getGrid()[row][col].isEmpty()) {
	    return false;
	}

	gameState.addPlant(newPlant, row, col);
	gameState.spendSunCounter(newPlant.getPrice());

	return true;
    }

    private boolean isLevelDone() {
	return gameState.getNumberOfDisabledRows() == GameState.ROW || gameState.getNumberOfZombiesLeft() == 0;
    }

    private void action() {
	Tile[][] grid = gameState.getGrid();
	for (int row = 0; row < GameState.ROW; row++) {
	    for (int col = 0; col < GameState.COL; col++) {
		Tile tile = grid[row][col];
		// The tile holds a plant
		if (tile.hasPlant()) {
		    plantAction(tile.getResidingPlant(), grid, row, col);
		}
		// The tile holds a zombie
		else if (tile.hasZombie()) {
		    zombieAction(tile.getResidingZombie(), grid, row, col);
		}
	    }
	}
    }

    private void gainBaseSunCounter() {
	gameState.gainSunCounter(currentLevel.getBaseSunCounterGain());
    }

    private void plantAction(Plant plant, Tile[][] grid, int row, int col) {
	// Get resource if the plant generates resource
	if (plant.canResrc_gen()) {
	    gameState.gainSunCounter(plant.getResrc_gen());
	} // Attack if if can attack
	else if (plant.canAttack()) {
	    // Attacking horizontally
	    for (int x = 1; x <= plant.getAtkRange_X(); x++) {
		if (x + col >= GameState.COL) {
		    break;
		}

		Tile tile = grid[row][col + x];

		// If the tile contains a zombie then the zombie will take damage
		if (tile.hasZombie()) {
		    Zombie firstZombie = tile.getFirstZombie();
		    firstZombie.takeDamage(plant.getDamage());

		    if (firstZombie.isDead()) {
			tile.removeZombie(firstZombie);
			gameState.zombieDied();
		    }

		    break;
		}
	    }
	}
    }

    private void zombieAction(List<Zombie> zombies, Tile[][] grid, int row, int col) {
	for (int i = 0; i < zombies.size(); i++) {
	    Zombie zombie = zombies.get(i);
	    // If the zombie is ready to move that it will move
	    if (zombie.isReadyToMove()) {
		Tile currentTile = grid[row][col];
		Tile nextTile = (col - 1 > GameState.BEGINNING) ? grid[row][col - 1] : null;

		// If the next tile is the beginning then this row is disabled
		if (nextTile == null) {
		    gameState.addDisabledRow(row);
		    for (Tile t : grid[row]) {
			t.clearResidingZombie();
		    }
		} else {
		    // If the next tile contains a plant attack it
		    if (nextTile.hasPlant()) {
			Plant plant = nextTile.getResidingPlant();

			plant.takeDmg(zombie.getDamage());

			if (plant.isDead()) {
			    nextTile.removePlant();
			}
		    }
		    // If the next tile is empty then move to it
		    else {
			currentTile.removeZombie(zombie);
			nextTile.addZombie(zombie);
			zombie.resetMovementCounter();
			i--;
		    }
		}
	    } else {
		zombie.incrementMovementCounter();
	    }
	}
    }

    private void spawnZombies() {
	if (gameState.getTurnNumber() % currentLevel.getSpawnRate() != 0) {
	    return;
	} else if (gameState.getNumberOfZombiesPending() == 0) {
	    return;
	}

	for (int i = 0; i < currentLevel.getSpawnAmount(); i++) {
	    int row = random.nextInt(GameState.ROW);
	    while (gameState.isRowDisabled(row)) {
		row = random.nextInt(GameState.ROW);
	    }
	    gameState.addZombie(row);
	}
    }

    // End Gameplay
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Game info
    
    public String getGrid() {
	Tile[][] grid = gameState.getGrid();
	StringBuilder sb = new StringBuilder();

	for (int row = 0; row < GameState.ROW; row++) {
	    for (int col = 0; col < GameState.COL; col++) {
		Tile tile = grid[row][col];

		if (tile.hasPlant()) {
		    switch (tile.getResidingPlant().getName()) {
		    case PeaShooter:
			sb.append("|P|");
			break;
		    case SunFlower:
			sb.append("|S|");
			break;
		    }
		} else if (tile.hasZombie()) {
		    sb.append("|" + "Z" + tile.getResidingZombie().size() + "|");
		} else {
		    sb.append("| |");
		}
	    }
	    sb.append("\n");
	}

	return sb.toString();
    }

    public int getTurnNumber() {
	return gameState.getTurnNumber();
    }

    public boolean isLevelFinished() {
	return gameState.isLevelFinished();
    }

    public String getAvailableZombies() {
	StringBuilder sb = new StringBuilder();

	for (ZombieTypes z : currentLevel.getAvailableZombies()) {
	    sb.append(z.name() + " ");
	}

	return sb.toString();
    }

    public int getNumberOfZombiesPending() {
	return gameState.getNumberOfZombiesPending();
    }

    public int getSunCounter() {
	return gameState.getSunCounter();
    }

    public int getNumberOfZombiesLeft() {
	return gameState.getNumberOfZombiesLeft();
    }

    public int getTotalNumberOfZombies() {
	return gameState.getTotalNumberOfZombies();
    }
    
    public boolean isLevelLoaded() {
	return isLevelLoaded();
    }
    
    // End Game info
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Level loading

    /**
     * Loads the specified level
     * 
     * @param levelID
     *            the id of the level to load
     */
    public boolean loadLevel(int levelID) {
	currentLevel = levelManager.getLevel(levelID - 1);

	if (currentLevel == null) {
	    return false;
	}

	levelLoaded = true;
	return true;
    }

    public boolean loadNextLevel() {
	currentLevel = levelManager.getNextLevel();

	if (currentLevel == null) {
	    return false;
	}

	levelLoaded = true;
	return true;
    }

    public boolean loadPreviousLevel() {
	currentLevel = levelManager.getPreviousLevel();

	if (currentLevel == null) {
	    return false;
	}

	levelLoaded = true;
	return true;
    }

    // End Level loading
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}