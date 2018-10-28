<p><strong>Author: </strong> Souheil Yazji</p>
<h2>How to use the Plant object</h2>
<p>Read the documentation in the source code for more detail about the public methods.</p>

<h3>Before starting a game</h3>
<p>The level has yet to load, all tiles have no plants, player can purchase plants from the shop for Sun points and specify the tiles in which they would like the plants to be placed. A PeaShooter will attack the zombies with a ranged attack down its own row. A SunFlower will slowly generate Sun points which will allow the player to purchase more plants.</p>

<h4>Current Plant types:</h4>
<ul>
  <li>PeaShooter</li>
  <li>SunFlower</li>
</ul>

<h3>Plant info</h3>
Below is a list of getters that returns information about a plant object

<ul>
  <li>getName()</li>
  <li>getPrice()</li>
  <li>getHealth()</li>
  <li>getDamage()</li>
  <li>getResrc_gen()</li>
  <li>canAttack()</li>
  <li>canResrc_gen()</li>
  <li>getAtkRange_X()</li>
  <li>getAtkRange_Y()</li>
  <li>isReadyToMove()</li>
  <li>isDead()</li>
</ul>

<h3>RoadMap</h3>
<ul>
  <li>More plants will be added in the future. Each will have special effects that make them stand out. This will lead to additional subclasses of Plant being made, some fields added to class Plant, and the PlantFactory class slightly modified to handle other plants.</li>
</ul>

<h2>Changes from previous deliverable</h2>
<p>This is the initial deliverable</p>
