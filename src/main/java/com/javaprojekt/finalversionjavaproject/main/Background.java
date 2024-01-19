package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Background {
    private ObjectSetter objectSetter;
    public GamePanel gamePanel;
    public EnemySetter enemySetter;
    public BufferedImage map, map2, map3;
    public BufferedImage gameover, pauseScreen, expMenu;
    public BufferedImage firstBatte;
    private Player player;
    int currentMap;


    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.enemySetter = new EnemySetter(gamePanel);
        this.objectSetter = new ObjectSetter(gamePanel);
        try {
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Scene_1_bg.png")));
            gameover = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Gameover.png")));
            pauseScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pauseScreen.png")));
            expMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/expMenu.png")));
            map2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Scene1part2.png")));
            map3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/waffenlagerfront.png")));
            firstBatte = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/FirstBattle.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchLevel() {
        if (gamePanel.currentMap == 0) {
            gamePanel.currentMap = 1;
            map = map2;// Change the map to the second level
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        }
        else if(gamePanel.currentMap==1) {
            gamePanel.currentMap = 2;
            map = map3;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        }

            /*else if (gamePanel.currentMap == 1) {
             //Add more conditions if you have additional levels
             //For example:
             gamePanel.currentMap = 2;
             map2 = map3;
        }*/

    }

    public void drawMap(Graphics2D g2) {
        g2.drawImage(map, 0, 0, 1280, 768, null);
    }
    public void drawGameover(Graphics2D g2) {
        g2.drawImage(gameover, 0, 0, 1280, 768, null);
    }
    public void drawPauseScreen(Graphics2D g2) {
        g2.drawImage(pauseScreen, 0, 0, 1280, 768, null);
    }
    public void drawExpMenu(Graphics2D g2) {
        g2.drawImage(expMenu, 0, 0, 1280, 768, null);
    }
    public void drawFirstBattle(Graphics2D g2) {
        g2.drawImage(firstBatte, 0, 0, 1280, 768, null);
    }
}