# How to use the Game object
Read the documentation in the source code for class Game for more detail about the public methods.

## Before starting a game
After creating a new Game object, call game.loadNextLevel() to load a level first. The game will not work if no level is loaded. Do not call this method again until the current level is finished. Call game.isLevelFinished() to check whether the current level is finished.

## Level loaded
After a level is loaded, call game.start() to start the current level. Calling other gameplay related function before this method is called will cause exceptions. Do not call this method again until the current level is finished. Always call this method after game.loadNextLevel() or any other level loading related methods.

## Game started
* Call game.endTurn() to finish the current turn
* Call game.buyPlant() to buy plants

## Game info
Below is a list of getters that returns information about the current level
* getGrid()
* getTurnNumber() 
* isLevelFinished()
* getAvailableZombies()
* getNumberOfZombiesPending()
* getSunCounter()
* getNumberOfZombiesLeft()
* getTotalNumberOfZombies()
* isLevelLoaded() 
* getShopItems()

    
