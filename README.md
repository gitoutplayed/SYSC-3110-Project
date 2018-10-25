<p><strong>Author: </strong> Hoang Bui</p>
<h2>How to use the Zombie object</h2>
<p>Read the documentation in the source code for classes Zombie, Walker, and ZombieFactory for more detail about the public methods.</p>

<h3>Before starting a game</h3>
<p>The level has yet to load, so the zombies don't exist yet. For each level, the zombies are predetermined.</p>

<h3>Level loaded</h3>
<p>After the level has loaded, a zombieFactory object will be created. The ZombieFactory will generate different types of zombie objects. These zombie objects are sub class of Zombie, which have differet stats depending on their ZombieTypes.</p>

<h4>Current ZombieTypes:</h4>
<ul>
  <li>WALKER</li>
</ul>

<h3>Zombie info</h3>
Below is a list of getters that returns information about a zombie object

<ul>
  <li>getDamage()</li>
  <li>getHealth()</li>
  <li>getAtkRange()</li>
  <li>getCurrentMovementSpeed()</li>
  <li>getMovementCounter()</li>
  <li>getZombieType()</li>
  <li>isDead()</li>
  <li>isDoneTile()</li>
  <li>isSlowed()</li>
  <li>getZombieProgress()</li>
  <li>isReadyToMove()</li>
</ul>

<h3>RoadMap</h3>
<ul>
  <li>There will be more ZombieTypes in the future. Each will have special stats that makes them stand out from the Walker zombie. So that would lead to adjusting ZombieFactory and adding more sub classes of Zombie</li>
  <li>There will more status aliments caused by plants in the future, so more methods will be created to enable those debuffs in Zombie</li>
</ul>

<h2>How to use the Tile object</h2>
<p>Read the documentation in the source code for class Tile for more detail about the public methods.</p>
<h3>Before starting a game</h3>
<p>The level has yet to load, so tiles don't exist yet. For each level, the tiles are predetermined.</p>
<h3>Level loaded</h3>
<p>After the level has loaded, tiles objects will be created. The tile objects can store a Plant object, list of Zombie objects and what type of tile it is.</p>

<h3>Tile info</h3>
Below is a list of getters that returns information about a zombie object

<ul>
  <li>getResidingPlant()</li>
  <li>getTileType()</li>
  <li>getResidingZombie()</li>
  <li>getFirstZombie()</li>
  <li>hasPlant()</li>
  <li>hasZombie()</li>
  <li>isEmpty()</li>
</ul>

<h3>RoadMap</h3>
<ul>
  <li>There is potentially more TileTypes that can prevent the player from planting, so methods will be incldued for that potential</li>
</ul>

<h2>Changes from previous deliverable</h2>
<p>This is the initial deliverable</p>
