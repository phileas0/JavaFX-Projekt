package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.object.SuperClassObject;
import com.javaprojekt.finalversionjavaproject.tile.TileManager;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS
    final int originalTileSize = 32; // 32x32
    final int scale = 2;
    public final int tileSize = originalTileSize * scale; // 96x96
    public final int maxScreenCol = 20;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; //1920
    public final int screenHeight = tileSize * maxScreenRow; // 1080
    int FPS = 60;

    TileManager tileManager = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Thread gameThread;
    Background background = new Background(this);
    public CollisionDetection cDetecter = new CollisionDetection(this);
    public ObjectSetter oSetter = new ObjectSetter(this);
    //public HUD hud = new HUD(this);
    public Player player = new Player(this, keyHandler);
    public SuperClassObject[] obj = new SuperClassObject[10];

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
    }

    public void setupGame() {
        oSetter.setObject();
    }
    public void startGameThread(){
        gameThread = new Thread (this);
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
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;

        //TILE
        tileManager.draw(graphics2D);


        //Map
        background.drawMap(graphics2D);


        //OBJECTS
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(graphics2D, this);
            }
        }

        //PLAYER
        player.draw(graphics2D);

        //UI
        //hud.draw(graphics2D);

        graphics2D.dispose();
    }


}