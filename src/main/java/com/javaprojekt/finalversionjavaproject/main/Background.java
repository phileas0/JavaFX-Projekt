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
    public BufferedImage map, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11, map12;
    public BufferedImage gameover, pauseScreen, expMenu;
    public BufferedImage firstBatte, streetFights, theFactory, meetingRoom, kitchenRoom, finalBoss;
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
            streetFights = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/streetFights.png")));
            meetingRoom = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/meetingRoom.png")));
            kitchenRoom = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/kitchenRoom.png")));
            finalBoss = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/finalBoss.png")));
            map4 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/waffenlager.png")));
            map5 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/weaponOffice.gif")));
            map6 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/StreetbackgroundSzene4.gif")));
            map7 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/hqFront.png")));
            map8 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Lobby.png")));
            map11 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Lobby2.png")));
            map12 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/FinalBossRoom.png")));
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
        } else if(gamePanel.currentMap == 1) {
            gamePanel.currentMap = 2;
            map = map3;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 2) {
            gamePanel.currentMap = 3;
            map = map4;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 3) {
            gamePanel.currentMap = 4;
            map = map5;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 4) {
            gamePanel.currentMap = 5;
            map = map6;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 5) {
            gamePanel.currentMap = 6;
            map = map7;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 6) {
            gamePanel.currentMap = 7;
            map = map8;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 7) {
            gamePanel.currentMap = 10;
            map = map11;
            enemySetter.setEnemies(gamePanel.currentMap);
            objectSetter.setObject(gamePanel.currentMap);
        } else if (gamePanel.currentMap == 10) {
            gamePanel.currentMap = 11;
            map = map12;
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
    public void drawStreetFights(Graphics2D g2) {
        g2.drawImage(streetFights, 0, 0, 1280, 768, null);
    }
    public void drawTheFactory(Graphics2D g2) {
        g2.drawImage(theFactory, 0, 0, 1280, 768, null);
    }
    public void drawMeetingRoom(Graphics2D g2) {
        g2.drawImage(meetingRoom, 0, 0, 1280, 768, null);
    }
    public void drawKitchenRoom(Graphics2D g2) {
        g2.drawImage(kitchenRoom, 0, 0, 1280, 768, null);
    }
    public void drawTheFinale(Graphics2D g2) {
        g2.drawImage(finalBoss, 0, 0, 1280, 768, null);
    }
}