package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Enemy;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.object.SuperClassObject;
import com.javaprojekt.finalversionjavaproject.tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    //SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 96x96
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 1280
    public final int screenHeight = tileSize * maxScreenRow; // 720
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Background background = new Background(this);
    public CollisionDetection cDetecter = new CollisionDetection(this);
    public ObjectSetter oSetter = new ObjectSetter(this);
    public HUD hud = new HUD(this);
    public Player player = new Player(this, keyHandler);
    public SuperClassObject[] obj = new SuperClassObject[10];
    public ArrayList<Enemy> listOfEnemies;
    private EnemySetter enemySetter;
    private GameState currentGameState;

    // Set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        listOfEnemies = new ArrayList<>();
        enemySetter = new EnemySetter(this);
        currentGameState = GameState.PLAYING;
    }

    public void setupGame() {
        oSetter.setObject();
        enemySetter.setEnemies();

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

    public void update() {
        switch (currentGameState) {
            case PLAYING:
                player.update();
                for (Enemy enemy : listOfEnemies) {
                    if (player.collidesWith(enemy)) {
                        System.out.println("Collision between Player and Entity!");
                        currentGameState = GameState.IN_COMBAT;
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
                    currentGameState = GameState.PLAYING;
                    keyHandler.pauseGame = false; // Reset the flag
                }
                break;
            case IN_COMBAT:

                break;
        }
    }


    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        switch (currentGameState) {
            case PLAYING:
                //TILE
                tileManager.draw(graphics2D);


                //Map
                background.drawMap(graphics2D);


                //OBJECTS
                for (int i = 0; i < obj.length; i++) {
                    if (obj[i] != null) {
                        obj[i].draw(graphics2D, this);
                    }
                }
                //PLAYER
                player.draw(graphics2D);
                //ENEMY
                for (Enemy enemy : listOfEnemies) {
                    enemy.draw(graphics2D);
                }
                //UI
                hud.draw(graphics2D);
                break;

            case PAUSED:
                drawPauseScreen(graphics2D);
        }

        graphics2D.dispose();


    }
    private void drawPauseScreen(Graphics2D g2) {
        // Set the color for the pause screen
        g2.setColor(new Color(0, 0, 0, 123)); // Semi-transparent black

        // Draw a rectangle over the whole screen
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Set the color and font for the text
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Arial", Font.BOLD, 40));

        // Draw the paused text
        String pausedText = "Game Paused";
        int textWidth = g2.getFontMetrics().stringWidth(pausedText);
        int x = getWidth() / 2 - textWidth / 2;
        int y = getHeight() / 2;

        g2.drawString(pausedText, x, y);
    }
}