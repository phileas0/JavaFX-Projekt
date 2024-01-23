# Holo Hunters - Cyberpunk RPG Game

Welcome to Holo Hunters, a cyberpunk-themed RPG game developed in Java with a pixel art style. Below are the instructions on how to work with the code and play the game.
We still have to code many important features, so there is still a lot missing!
## Setup and Execution:

1. Clone the repository to your local machine.
2. Open the project in your preferred Java development environment.
3. Run the `Main` class to start the game.

## Game Controls:

- **Movement:**
    - **W:** Move Up
    - **A:** Move Left
    - **S:** Move Down
    - **D:** Move Right

- **Debug Mode:**
    - **T:** Toggle debug text display

## Player Stats:
- **Level:** Your current level
- **EXP:** Current experience points. If you reach the goal, you level up
- **Health:** Current health points. If they go to 0, you die
- **Energy:** Current energy points. Needed for using special abilities
- **Damage:** Reduces the enemies health by the value
- **Speed:** Amount of Energy that gets replenished every round
- **Stimpaks:** Heals the player
- **Arcane:** Amount of health points that stimpaks heal

## Enemy Stats:
- **Health:** Enemy health points
- **Damage:** Amount of damage the player takes every round
- **Hit-Probability:** The probability of enemy attacks hitting

## Combat:
- **Your goal:** Getting the enemies health to 0, while players health stays up
- **Possible Actions:** Can be performed by pressing the corresponding number
  - **1. Shoot:** Enemy's health decreases by the players damage. Cost: Passing the turn to the enemy
  - **2. Shield:** Protects you from damage for the next enemy turn. Cost: 65%
  - **3. Repair:** Heals the player by using a stimpak. Cost: 1 stimpak
  - **4. Eagles Eye:** Guarantees a hit by the player for the next round. Cost: 40 Energy
  - **5. Send Trojan:** Weakens the enemy (increases damage taken by the enemy and decreases damage taken by the player) Cost: 80 Energy
  - **6. Scan:** Shows the enemies values upon use. Cost: 50 Energy

- **Other important things to know::**
  - You can use as many abilities as you want (if you have the resources)
  - Shooting passes the turn to the enemy. NO TURNING BACK!


## Important Functions:

### `GamePanel` Class:

- **Screen Settings:**
    - Adjust screen dimensions, tile size, and map settings.

- **Game Components:**
    - `tileManager`: Manages tiles and maps.
    - `keyHandler`: Handles keyboard input.
    - `background`: Manages background and map switching.
    - `mapManager`: Manages maps and level transitions.
    - `cDetecter`: Handles collision detection.
    - `oSetter`: Sets up game objects.
    - `hud`: Displays the Heads-Up Display.
    - `player`: Controls the player character.

### `Player` Class:

- **Default Position:**
    - Set the player's default position and speed.

- **Player Movement:**
    - Update player movement based on keyboard input.
    - Check tile and object collisions.

- **Object Interaction:**
    - Pick up keys and open doors.

### `CollisionDetection` Class:

- **Tile and Object Collision:**
    - Check for collisions with tiles and objects.
    - (*Detect level switches)

### `KeyHandler` Class:

- **Keyboard Input:**
    - Handle key presses for movement, interaction, and debug mode.

### `Background` Class:

- **Map Switching:**
    - Switch between different maps and backgrounds.(*not working yet)

### `ObjectSetter` Class:

- **Object Placement:**
    - Set the initial positions of key objects, doors, and shelves.

## Additional Classes:

- `Enemy` and `EnemySetter`: Basic implementation for future enemy characters.
- `TileManager` and `Tile`: Handle tile rendering and map loading.
- `HUD`: Displays player information and messages.


