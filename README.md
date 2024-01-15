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


