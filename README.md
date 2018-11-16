# Zombies vs Plants

### Repository: https://github.com/gitoutplayed/SYSC-3110-Project

### Authors: Michael Fan, Hoang Bui, Souheil Yazji, Tamer Ibrahim

# Milestone 2

## Rest of Deliverables
* Milestone 3
* Milestone 4

## Changes Made Since the Previous Deliverable
* Added GUI
* Added JUnit test for Game State

## Known Issues

## Roadmap
* Add more zombies
* Add more plants
* Add more levels
* Implement Undo and Redo
* Implement Save and Load
* Custom level design

### Game
* Allow unlimited undo and redo
* Allow save and load 
* In-game Level builder

### Zombies
* There will be more ZombieTypes in the future. Each will have special stats that makes them stand out from the Walker zombie. So that would lead to adjusting ZombieFactory and adding more sub classes of Zombie
There will more status aliments caused by plants in the future, so more methods will be created to enable those debuffs in Zombie

### Tiles
* There is potentially more TileTypes that can prevent the player from planting, so methods will be included for that potential

### Plants
* More plants will be added in the future. Each will have special effects that make them stand out. This will lead to additional subclasses of Plant being made, some fields added to class Plant, and the PlantFactory class slightly modified to handle other plants.

### Controller and View
* The GUI will be implemented in the GameView class and the contoller will be implemented in the GameController class
