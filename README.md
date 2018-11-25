# Zombies vs Plants

### Repository: https://github.com/gitoutplayed/SYSC-3110-Project

### Authors: Michael Fan, Hoang Bui, Souheil Yazji, Tamer Ibrahim

# Milestone 3

## Rest of Deliverables
* Milestone 4

## Changes Made Since the Previous Deliverable
* Added new levels
* Added new plants
* Added new zombies
* Added level selecter (Added class LevelChooserPanel)
* Added new events to determine number of zombies on a tile (Added class PopupPanel)
* Added JUnit test for zombies
* Added JUnit test for plants
* Implemented Undo and Redo 

## Known Issues

## Roadmap

### Game
* Allow save and load 
* In-game Level builder

### Zombies
* There are 5 zombies, each unique in their movementspeed, damage, and health. The number of zombies per level is predetermined, but are generated randomly on the map.

### Tiles
* There are 3 tiles: road (zombie spawn), grass (where to plant new palnts and zombies travel through), and concrete (zombies' end goal). The tiles can be clicked on to determine the number of zombies on the selected tile.

### Plants
* The plants each have their unique difference between eachother base on their stats. They provide different uses to the player for a variety of gameplay.
