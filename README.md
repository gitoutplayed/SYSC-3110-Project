# Zombies vs Plants

### Repository: https://github.com/gitoutplayed/SYSC-3110-Project

### Authors: Michael Fan, Hoang Bui, Souheil Yazji, Tamer Ibrahim

# Milestone 1

## Authors

### Michael Fan
* Game.java
* GameState.java
* Level.java
* LevelManager.java
* Shop.java

### Hoang Bui
* Tile.java
* Zombie.java
* ZombieTypes.java
* ZombieFactory.java
* Walker.java
* ZombieTypes.java

### Souheil Yazji
* Plant.java
* PlantName.java
* PlantFactory.java
* PeaShooter.java
* SunFlower.java

### Tamer Ibrahim
* GameController.java
* GameView.java

## Rest of Deliverables
* Milestone 2
* Milestone 3
* Milestone 4

## Changes Made Since the Previous Deliverable
This is the first deliverable.

## Know Issues
There are no known issues.

## Roadmap

### Game
* Allow unlimited undo and redo
* Allow save and load 
* Implement a level builder

### Zombies
* There will be more ZombieTypes in the future. Each will have special stats that makes them stand out from the Walker zombie. So that would lead to adjusting ZombieFactory and adding more sub classes of Zombie
There will more status aliments caused by plants in the future, so more methods will be created to enable those debuffs in Zombie

### Tiles
* There is potentially more TileTypes that can prevent the player from planting, so methods will be included for that potential

### Plants
* More plants will be added in the future. Each will have special effects that make them stand out. This will lead to additional subclasses of Plant being made, some fields added to class Plant, and the PlantFactory class slightly modified to handle other plants.

### Controller and View
* The GUI will be implemented in the GameView class and the contoller will be implemented in the GameController class
