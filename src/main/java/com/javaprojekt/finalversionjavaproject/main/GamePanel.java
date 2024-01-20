package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.combat.Combat;
import com.javaprojekt.finalversionjavaproject.combat.LevelUp;
import com.javaprojekt.finalversionjavaproject.combat.TextField;
import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.object.SuperClassObject;
import com.javaprojekt.finalversionjavaproject.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

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
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    TextField textField = new TextField(this, this.keyHandler);
    Thread gameThread;

    public Tutorial tutorial = new Tutorial(this);
    public EnemySetter enemySetter;
    public CollisionDetection cDetecter = new CollisionDetection(this);
    public Background background = new Background(this);
    public ObjectSetter oSetter = new ObjectSetter(this);
    public HUD hud = new HUD(this);
    public Player player;
    public SuperClassObject[][] obj = new SuperClassObject[maxMap][10];
    public ArrayList<ArrayList<Enemy>> listOfEnemies;
    public ArrayList<ArrayList<Enemy>> getListOfEnemies() {
        return listOfEnemies;
    }

    private Enemy enemy;
    private GameState currentGameState;
    private Combat combat;
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
    /*
    public void run() {
        double drawInterval = 1000000000/FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                if(remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
     */
    public void run() {
        double drawInterval = 1000000000 / FPS;
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
        textField.update();
        switch (currentGameState) {
            case PLAYING:
                // If the tutorial is active, don't update the game state
                //if(tutorial.isTutorialActive) {
                  //  return;
                //}
                if (!textField.isDisplayingMessages()) {
                    player.update();
                }
                for (ArrayList<Enemy> enemies : listOfEnemies) {
                    enemies.removeIf(Enemy::isMarkedForRemoval);
                }

                if (combatCooldown > 0) { // Only checks for collision if cooldown is zero
                    combatCooldown--;
                } else {
                    for (ArrayList<Enemy> enemies : listOfEnemies) {
                        for (Enemy enemy : enemies) {
                            if (player.collidesWith(enemy) && !enemy.isInCombat()) {
                                startCombat(enemy);
                                newEnemy = true;
                                combatCooldown = combatCooldownTime; // Reset the cooldown
                                break;
                            }
                        }
                    }

                }
                //System.out.println("Number of enemies: " + listOfEnemies.size());
                if (keyHandler.pauseGame) {
                    currentGameState = GameState.PAUSED;
                    keyHandler.pauseGame = false; // Reset the flag to avoid continuous toggling
                }
                break;
            case PAUSED:
                if (keyHandler.pauseGame) {
                    tutorial.isTutorialActive1 = false;
                    tutorial.isTutorialActive2 = true;
                    currentGameState = GameState.PLAYING;
                    keyHandler.pauseGame = false; // Reset the flag
                }
                break;
            case IN_COMBAT:
                if (!textField.isDisplayingMessages()) {
                    combat.processTurn();
                }
                if (combat.enemyDead) {
                    combat.enemyDead = true;
                    if (player.exp >= player.expToNextLevel) {
                        player.hasLeveledUp = true;
                        LevelUp levelUp = new LevelUp(player);
                        levelUp.applyLevelUp();
                    }
                    currentGameState = GameState.EXP_MENU;
                }
                if (combat.playerDead) {
                    currentGameState = GameState.GAMEOVER;
                }
                break;
            case EXP_MENU:
                if (keyHandler.interacted) {
                    player.hasLeveledUp = false;
                    currentGameState = GameState.PLAYING;
                }
                break;
            case GAMEOVER:
                break;
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;
        switch (currentGameState) {
            case PLAYING:
                drawOverworld(graphics2D);
                break;
            case PAUSED:
                drawPauseScreen(graphics2D);
                break;
            case IN_COMBAT:
                drawCombatUI(graphics2D);
                break;
            case EXP_MENU:
                drawExpMenu(graphics2D);
                break;
            case GAMEOVER:
                drawGameover(graphics2D);
                break;
        }
        graphics2D.dispose();
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
        //if(currentMap == 0)tutorial.draw(g2);



        if (keyHandler.showDebugText == true) {
            g2.setFont(new Font("Arial", Font.PLAIN, 20));
            int x = 10;
            int y = 400;
            int lineHeight = 20;

            g2.drawString("WorldX: " + player.x, x, y); y += lineHeight;
            g2.drawString("WorldY: " + player.y, x, y); y += lineHeight;
            g2.drawString("Col: " + (player.x + player.solidAreaDefaultX) / tileSize, x, y); y += lineHeight;
            g2.drawString("Row: " + (player.y + player.solidAreaDefaultY) / tileSize, x, y); y += lineHeight;

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
        String healthText = "Health: " + player.getMaxHealth();
        String energy = "Energy: " + player.getEnergy() + "%";
        String damage = "Damage: " + player.getDamage();
        String energyRecovery = "Speed: " + player.getEnergyRecovery();
        String stimpaks = "Stimpaks: " + player.getMaxStimpaks();
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
                background.drawTheFactory(g2);
                break;
            case 2:
                background.drawMeetingRoom(g2);
                break;
            case 3:
                background.drawKitchenRoom(g2);
                break;
            case 4:
                background.drawTheFinale(g2);
                break;
        }
        player.drawPlayerPortrait(g2);
        switch (enemy.skinNr) {
            case 0:
                enemy.drawEnemyPortrait0(g2);
                break;
        }

        // Draw the action menu
        combat.drawTextField(g2, getHeight() - 20, getHeight() - 20, 100, 100);
        g2.setColor(new Color(255, 255, 255, 200));
        g2.setFont(new Font("Consolas", Font.BOLD, 15));
        g2.drawString("1. Shoot: Pass Turn", 10, screenHeight - 130);
        g2.drawString("2. Shield: 75E", 10, screenHeight - 110);
        g2.drawString("3. Repair: Stimpak", 10, screenHeight - 90);
        g2.drawString("4. Eagle Eyes: 80E", 10, screenHeight - 70);
        g2.drawString("5. Send Trojan: 100E", 10, screenHeight - 50);
        g2.drawString("6. Scan: 50E", 10, screenHeight - 30);

        // Player HP bar
        drawHealthBar(g2, 10, 5, combat.currentPlayerHealth, player.getMaxHealth());
        if (enemy != null) {
            if (newEnemy) {
                helpHealth = enemy.health;
                newEnemy = false;
            }
            if (combat.scanned) { // Enemy HP bar
                drawHealthBar(g2, screenWidth - 210, 5, enemy.health, helpHealth);
            }
        } else System.out.println("Enemy null");

        // Display player and enemy stats
        g2.setColor(new Color(255, 255, 0, 255));
        g2.setFont(new Font("Consolas", Font.PLAIN, 20));
        g2.drawString("Player HP: " + combat.currentPlayerHealth + " / " + player.maxHealth, 10, 50);
        g2.drawString("Energy: " + combat.getCurrentEnergy() + " / " + player.getEnergy() + "%", 10, 80);
        g2.drawString("Stimpaks: " + combat.getCurrentStimpaks(), 10, 110);

        if (enemy != null) {
            if (combat.scanned) {
                g2.drawString("Enemy HP: " + enemy.health + " / " + helpHealth, screenWidth - 210, 50);
            }
        } else System.out.println("Enemy null");

        // Textfield display
        textField.draw(g2);
    }
}