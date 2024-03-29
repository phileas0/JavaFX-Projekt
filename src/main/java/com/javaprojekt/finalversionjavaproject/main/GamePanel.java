package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.combat.Combat;
import com.javaprojekt.finalversionjavaproject.combat.LevelUp;
import com.javaprojekt.finalversionjavaproject.combat.TextField;
import com.javaprojekt.finalversionjavaproject.design.Background;
import com.javaprojekt.finalversionjavaproject.design.HUD;
import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.object.SuperClassObject;
import com.javaprojekt.finalversionjavaproject.tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 96x96
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int maxMap = 20;
    public int currentMap = 0;
    public final int screenWidth = tileSize * maxScreenCol; // 1280
    public final int screenHeight = tileSize * maxScreenRow; // 720
    private int messageCounter = 0;
    int FPS = 120;
    private boolean gameRestarted = false;



    // Transition variables
    private float transitionOpacity = 0.0f;
    private boolean isTransitioning = false;
    private int transitionFrames = 0; // Total frames for the transition
    private int currentTransitionFrame = 0; // Current frame in the transition


    public TileManager tileManager = new TileManager(this);
    public KeyHandler keyHandler = new KeyHandler();
    public TextField textField = new TextField(this, this.keyHandler);
    public ManagerDialogue managerDialogue = new ManagerDialogue(this);
    public Thread gameThread;
    public Tutorial tutorial = new Tutorial(this);
    public EnemySetter enemySetter;
    public CollisionDetection cDetecter = new CollisionDetection(this);
    public Background background = new Background(this);
    public ObjectSetter oSetter = new ObjectSetter(this);
    public HUD hud = new HUD(this);
    public Player player = new Player(this, keyHandler);
    public SuperClassObject[][] obj = new SuperClassObject[maxMap][10];
    public ArrayList<ArrayList<Enemy>> listOfEnemies;
    public ArrayList<ArrayList<Enemy>> getListOfEnemies() {
        return listOfEnemies;
    }

    public Enemy enemy;
    private GameState currentGameState;
    private Combat combat;
    private boolean readyForCombat = false;
    private final int combatCooldownTime = 60; // Cooldown time in frames (1 second if 60 FPS)
    private int combatCooldown = 0;
    private boolean newEnemy;
    private int helpHealth;


    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        listOfEnemies = new ArrayList<>();
        for(int i = 0; i <maxMap; i++){
            listOfEnemies.add(new ArrayList<>());
        }
        enemySetter = new EnemySetter(this);
        currentGameState = GameState.PLAYING;
        player = new Player(this, keyHandler);
        this.combat = new Combat(this.player, this.enemy, this.keyHandler, this.textField);
    }

    public void setupGame() {
        oSetter.setObject(0);
        enemySetter.setEnemies(0);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 2000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void startTransition(int frames) {
        isTransitioning = true;
        transitionFrames = frames;
        currentTransitionFrame = 0;
    }

    public void startCombat(Enemy encounteredEnemy) {
        if (player != null && encounteredEnemy != null && !encounteredEnemy.isInCombat()) {
            encounteredEnemy.setInCombat(true);
            enemy = encounteredEnemy; // Set the current enemy
            combat = new Combat(player, enemy, keyHandler, textField);
            currentGameState = GameState.IN_COMBAT;
            combatCooldown = combatCooldownTime; // Set the cooldown
        }
    }

    public void update() {
        player.updateAlternateImageTimer(); // Alternate image loader
        if (isTransitioning) { // Transitions
            transitionOpacity = (float) currentTransitionFrame / transitionFrames;
            currentTransitionFrame++;
            if (currentTransitionFrame > transitionFrames) {
                isTransitioning = false;
            }
        }
        GameUtils.update(); // for sleep() funktion
        textField.update(); // displays textfields
        switch (currentGameState) { //
            case PLAYING:
                // If the tutorial is active, don't update the game state
                if(tutorial.isTutorialActive){
                    return;
                }
                if (!textField.isDisplayingMessages() || managerDialogue.ismonologue) {//update player when a message is not being displayed or when a monologue is active
                    player.update();
                    managerDialogue.ismonologue = false;
                }
                for (ArrayList<Enemy> enemies : listOfEnemies) {//enemies marked for removal are removed
                    enemies.removeIf(Enemy::isMarkedForRemoval);
                }
                if (combatCooldown > 0) { // Only checks for collision if cooldown is zero
                    combatCooldown--;
                } else {
                    for (ArrayList<Enemy> enemies : listOfEnemies) {//checks for collision with enemies
                        for (Enemy enemy : enemies) {
                            if (player.collidesWith(enemy) && !enemy.isInCombat() && !readyForCombat) {//if player collides with enemy and enemy is not in combat and player is not ready for combat
                                GameUtils.sleep(120); // Start the sleep timer
                                startTransition(120);  // Start the transition
                                textField.addMessage("Starting battle...");
                                this.enemy = enemy; // Make sure to set the current enemy
                                newEnemy = true;
                                readyForCombat = true;
                                combatCooldown = combatCooldownTime;
                            }
                        }
                    }
                }
                if (readyForCombat && !GameUtils.isSleeping() && !isTransitioning) {
                    startCombat(enemy); // Start combat after transition
                    readyForCombat = false; // Reset the flag
                    combatCooldown = combatCooldownTime; // Reset the cooldown
                }
                //System.out.println("Number of enemies: " + listOfEnemies.size());
                if (keyHandler.pauseGame) {
                    currentGameState = GameState.PAUSED;
                    keyHandler.pauseGame = false; // Reset the flag to avoid continuous toggling
                }
                break;
            case PAUSED:
                if (keyHandler.pauseGame) {
                    if(tutorial.pauseGameCounter==1){
                        tutorial.isTutorialActive1 = false;
                        tutorial.isTutorialActive2 = true;
                        tutorial.pauseGameCounter++;
                    }

                    currentGameState = GameState.PLAYING;
                    keyHandler.pauseGame = false; // Reset the flag
                }
                break;
            case IN_COMBAT:
                if (!textField.isDisplayingMessages()) {//process player turn when a message is not being displayed
                    combat.processTurn();
                }
                if (combat.enemyDead && !textField.isDisplayingMessages()) {//if enemy is dead and no message is being displayed then the enemy is removed and the player is rewarded with exp
                    combat.enemyDead = true;
                    if (player.exp >= player.expToNextLevel) {//if player has enough exp to level up then the player levels up
                        player.hasLeveledUp = true;
                        LevelUp levelUp = new LevelUp(player);
                        levelUp.applyLevelUp();
                    }
                    currentGameState = GameState.EXP_MENU;
                }
                if (combat.playerDead) {
                    textField.addMessage("You died :(");
                    startTransition(0);
                    if (!GameUtils.isSleeping()) {
                        currentGameState = GameState.GAMEOVER;
                    }
                }
                break;
            case EXP_MENU:
                if (keyHandler.interacted) {
                    player.hasLeveledUp = false;
                    currentGameState = GameState.PLAYING;
                }
                break;
            case GAMEOVER:
                if (keyHandler.escPressed && !gameRestarted) { // Press esc in gameover screen to restart the game
                    restartGame();
                    gameRestarted = true;
                }
                break;
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2 = (Graphics2D) graphics;
        switch (currentGameState) {
            case PLAYING:
                drawOverworld(g2);
                break;
            case PAUSED:
                drawPauseScreen(g2);
                break;
            case IN_COMBAT:
                drawCombatUI(g2);
                break;
            case EXP_MENU:
                drawExpMenu(g2);
                break;
            case GAMEOVER:
                drawGameover(g2);
                break;
        }
        if (isTransitioning) {
            g2.setColor(new Color(0, 0, 0, transitionOpacity));
            g2.fillRect(0, 0, getWidth(), getHeight());
        }
        g2.dispose();
    }

    private void drawExpMenu(Graphics2D g2) {
        background.drawExpMenu(g2);

        //Create EXP bar
        int barWidth = 300;
        int barHeight = 25;
        int edgeThickness = 2; // Thickness of the edge
        int barX = screenWidth / 2 - barWidth / 2;
        int barY = screenHeight / 2 - 10;

        // Draw the background/edge of the EXP bar
        g2.setColor(Color.BLACK); // Color for the edge
        g2.fillRect(barX - edgeThickness, barY - edgeThickness, barWidth + 2 * edgeThickness, barHeight + 2 * edgeThickness);

        // Draw the empty part of the EXP bar
        g2.setColor(Color.GRAY);
        g2.fillRect(barX, barY, barWidth, barHeight);

        // Draw the filled portion of the EXP bar
        float expPercentage = (float) player.exp / player.expToNextLevel;
        g2.setColor(Color.GREEN);
        g2.fillRect(barX, barY, (int) (barWidth * expPercentage), barHeight);

        // Extra text if leveled up
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Consolas", Font.PLAIN, 20));
        if (player.hasLeveledUp) {
            String lvlUp = "Level Up! New Level: " + player.getCurrentLevel();
            String health = "Health + 5: " + player.getMaxHealth();
            String energy = "Energy + 10: " + player.getEnergy();
            String damage = "Damage + 2: " + player.getDamage();
            String energyRecovery = "Speed + 3: " + player.getEnergyRecovery();
            String stimpaks = "Stimpaks + 1: " + player.getMaxStimpaks();
            String stimpakStrength = "Arcane + 2: " + player.getHealing();
            // Drawing new stats
            g2.drawString(lvlUp, screenWidth / 2 - g2.getFontMetrics().stringWidth(lvlUp) / 2, screenHeight / 2 + 10);

            g2.setColor(Color.WHITE);
            g2.drawString(health, screenWidth / 2 - g2.getFontMetrics().stringWidth(health) / 2, screenHeight / 2 + 40);
            g2.drawString(energy, screenWidth / 2 - g2.getFontMetrics().stringWidth(energy) / 2, screenHeight / 2 + 70);
            g2.drawString(damage, screenWidth / 2 - g2.getFontMetrics().stringWidth(damage) / 2, screenHeight / 2 + 100);
            g2.drawString(energyRecovery, screenWidth / 2 - g2.getFontMetrics().stringWidth(energyRecovery) / 2, screenHeight / 2 + 130);
            g2.drawString(stimpaks, screenWidth / 2 - g2.getFontMetrics().stringWidth(stimpaks) / 2, screenHeight / 2 + 160);
            g2.drawString(stimpakStrength, screenWidth / 2 - g2.getFontMetrics().stringWidth(stimpakStrength) / 2, screenHeight / 2 + 190);
        } else {
            String lvlUp = "Gained EXP: " + enemy.givesExp;
            String remaining = "EXP: " + player.getExp() + " / " + player.getExpToNextLevel();
            g2.drawString(lvlUp, screenWidth / 2 - g2.getFontMetrics().stringWidth(lvlUp) / 2, screenHeight / 2 + 10);
            g2.setColor(Color.WHITE);
            g2.drawString(remaining, screenWidth / 2 - g2.getFontMetrics().stringWidth(remaining) / 2, screenHeight / 2 + 40);
        }
    }
    private void drawGameover(Graphics2D g2) {
        background.drawGameover(g2);
    }

    private void drawOverworld(Graphics2D g2) {
        if(background.startScreen){
            background.drawStartScreen(g2);
            if(messageCounter < 2){
                textField.addMessage("Press \"E\" to start the game");
                messageCounter++;
            }textField.draw(g2);return;
        }

        //TILE
        tileManager.draw(g2);
        //Map
        background.drawMap(g2);
        //OBJECTS
        for (int i = 0; i < obj[currentMap].length; i++) {
            if (obj[currentMap][i] != null) {
                obj[currentMap][i].draw(g2, this);
            }
        }


        //PLAYER
        player.draw(g2);
        //ENEMY
        for (ArrayList<Enemy> enemies : listOfEnemies) {
            for (Enemy enemy : enemies) {
                enemy.draw(g2);
            }
        }


        //UI
        hud.draw(g2);
        if(currentMap == 0)tutorial.draw(g2);
        if(currentMap == 1)managerDialogue.drawMonologue();
        if(currentMap == 5)managerDialogue.drawDialogue();
        if(currentMap == 6)managerDialogue.drawMonologue1();
        if(currentMap == 7)managerDialogue.setIsmonologue();
        if(currentMap == 12)managerDialogue.drawManagerDialogue();
        textField.draw(g2);




        if (keyHandler.showDebugText == true) {//debugging function
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.x, x, y); y += lineHeight;
            g2.drawString("WorldY: " + player.y, x, y); y += lineHeight;
            g2.drawString("Col: " + (player.x + player.solidAreaDefaultX) / tileSize, x, y); y += lineHeight;
            g2.drawString("Row: " + (player.y + player.solidAreaDefaultY) / tileSize, x, y); y += lineHeight;

        }
        if (combat.finalBossDead) {//if final boss is dead then the credits are displayed
            background.drawCredits(g2);
            if (keyHandler.escPressed && !gameRestarted) {
                    restartGame();
                    gameRestarted = true;
            }
        }
    }
    private void drawPauseScreen(Graphics2D g2) {
        // Set the background
        background.drawPauseScreen(g2);
        // Set color of font
        g2.setColor(new Color(255, 0, 255, 255)); // Semi-transparent yellow
        // Draw a rectangle over the whole screen
        g2.setFont(new Font("Consolas", Font.PLAIN, 40));

        // Draw the "Stats:" text
        String pausedText = "Stats:";
        int textWidth = g2.getFontMetrics().stringWidth(pausedText);
        int x = getWidth() / 2 - textWidth / 2;
        int y = getHeight() / 3 - 20;
        g2.drawString(pausedText, x, y);

        g2.setFont(new Font("Consolas", Font.PLAIN, 20));  // Smaller font for details

        // Display player stats
        String levelText = "Level: " + player.getCurrentLevel();
        String expText = "EXP: " + player.getExp() + " / " + player.getExpToNextLevel();
        String healthText = "Health: " + player.currentHealth + " / " + player.getMaxHealth();
        String energy = "Energy: " + player.getEnergy() + "%";
        String damage = "Damage: " + player.getDamage();
        String energyRecovery = "Speed: " + player.getEnergyRecovery();
        String stimpaks = "Stimpaks: " + player.currentStimpaks + " / " + player.getMaxStimpaks();
        String healing = "Arcane: " + player.getHealing();

        // Calculate positions and draw the stats
        g2.drawString(levelText, getWidth() / 2 - 100, screenHeight / 3 + 30);
        g2.drawString(expText, getWidth() / 2 - 100, screenHeight / 3 + 60);
        g2.drawString(healthText, getWidth() / 2 - 100, screenHeight / 3 + 120);
        g2.drawString(energy, getWidth() / 2 - 100, screenHeight / 3 + 150);
        g2.drawString(damage, getWidth() / 2 - 100, screenHeight / 3 + 180);
        g2.drawString(energyRecovery, getWidth() / 2 - 100, screenHeight / 3 + 210);
        g2.drawString(stimpaks, getWidth() / 2 - 100, screenHeight / 3 + 240);
        g2.drawString(healing, getWidth() / 2 - 100, screenHeight / 3 + 270);
    }

    private void drawHealthBar(Graphics2D g2, int x, int y, int currentHealth, int maxHealth) {
        // Calculate health bar width based on health ratio
        int barWidth = 200; // Total width of the health bar
        int healthWidth = (int) ((currentHealth / (float) maxHealth) * barWidth);
        g2.setColor(Color.BLUE);
        g2.fillRect(x, y, healthWidth, 20);
        g2.setColor(Color.YELLOW);
        g2.drawRect(x, y, barWidth, 20);
    }
    public void drawCombatUI(Graphics2D g2) {
        switch (enemy.backgroundNr) {
            case 0:
                background.drawStreetFights(g2);
                break;
            case 1:
                background.drawStreetFights2(g2);
                break;
            case 2:
                background.drawFactoryFront(g2);
                break;
            case 3:
                background.drawTheFactory(g2);
                break;
            case 4:
                background.drawMeetingRoom(g2);
                break;
            case 5:
                background.drawKitchenRoom(g2);
                break;
            case 6:
                background.drawTheFinale(g2);
                break;
        }
        switch (enemy.backgroundNr) {
            case 1:
                player.drawPlayerPortrait1(g2);
                break;
            case 2:
                player.drawPlayerPortrait2(g2);
                break;
            case 6:
                player.drawPlayerPortrait6(g2);
                break;
            default:
                player.drawPlayerPortrait0(g2);
                break;
        }

        switch (enemy.skinNr) {
            case 0:
                enemy.drawEnemyPortrait0(g2);
                break;
            case 1:
                enemy.drawSmallGuard(g2);
                break;
            case 2:
                enemy.drawBigGuard(g2);
                break;
            case 3:
                enemy.drawDeviousGuard(g2);
                break;
            case 4:
                enemy.drawFactoryRobot(g2);
                break;
            case 5:
                enemy.drawSmallCook(g2);
                break;
            case 6:
                enemy.drawBigCook(g2);
                break;
            case 7:
                enemy.drawFinalBoss(g2);
                break;
        }

        // Draw the action menu
        g2.setColor(new Color(0, 0, 0, 190));
        g2.fillRect(5, screenHeight - 145, 200, 120); // Background for action menu
        g2.setColor(new Color(255, 255, 255, 200));
        g2.setFont(new Font("Consolas", Font.BOLD, 15));
        g2.drawString("1. Shoot: Pass Turn", 10, screenHeight - 130);
        g2.drawString("2. Shield: -65%", 10, screenHeight - 110);
        g2.drawString("3. Repair: -1 Stimpak", 10, screenHeight - 90);
        g2.drawString("4. Eagle Eyes: -40%", 10, screenHeight - 70);
        g2.drawString("5. Send Trojan: -80%", 10, screenHeight - 50);
        g2.drawString("6. Scan: -50%", 10, screenHeight - 30);


        g2.setColor(new Color(255, 255, 0, 255));
        g2.setFont(new Font("Consolas", Font.PLAIN, 20));
        // Player HP bar
        drawHealthBar(g2, 10, 5, combat.currentPlayerHealth, player.getMaxHealth());
        if (enemy != null) {
            if (newEnemy) {
                helpHealth = enemy.health;
                newEnemy = false;
            }
            if (combat.scanned && !GameUtils.isSleeping()) { // Enemy HP bar
                g2.setColor(new Color(0, 0, 0, 190)); // Background for enemy stats
                g2.fillRect(screenWidth - 210 - 5, 30, 200, 90);
                g2.setColor(new Color(255, 255, 0, 255)); // enemy stats
                drawHealthBar(g2, screenWidth - 210, 5, enemy.health, helpHealth);
                g2.drawString("Enemy HP: " + enemy.health + " / " + helpHealth, screenWidth - 210, 50);
                g2.drawString("Damage: " + enemy.damage, screenWidth - 210, 80);
                g2.drawString("Probability: " + (int) (enemy.hitPropability * 100) + "%", screenWidth - 210, 110);
            }
        } else System.out.println("Enemy null");

        // Display player and enemy stats

        g2.setColor(new Color(0, 0, 0, 190)); // Background for player stats
        g2.fillRect(5, 35, 220, 85);
        g2.setColor(new Color(255, 255, 0, 255)); // Player stats
        g2.drawString("Player HP: " + combat.currentPlayerHealth + " / " + player.maxHealth, 10, 50);
        g2.drawString("Energy: " + combat.getCurrentEnergy() + " / " + player.getEnergy() + "%", 10, 80);
        g2.drawString("Stimpaks: " + combat.getCurrentStimpaks() + " (+" + player.getHealing() + " HP)", 10, 110);

        // Textfield display
        textField.draw(g2);
    }
    public void restartGame() {
        // Dispose the current window
        Window window = SwingUtilities.getWindowAncestor(this);
        window.dispose();

        // Create a new instance of the game
        JFrame newWindow = new JFrame();
        newWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newWindow.setResizable(false);
        newWindow.setExtendedState(Frame.MAXIMIZED_BOTH);
        newWindow.setTitle("HoloHunter");

        GamePanel newGamePanel = new GamePanel();
        newWindow.add(newGamePanel);

        newWindow.pack();

        newWindow.setLocationRelativeTo(null);
        newWindow.setVisible(true);

        newGamePanel.setupGame();
        newGamePanel.startGameThread();
    }
}