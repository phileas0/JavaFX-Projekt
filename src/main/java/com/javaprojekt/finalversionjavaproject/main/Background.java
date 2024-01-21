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
    public BufferedImage map, map2, map3, map4, map5, map6, map7, map8, map9, map10, map11, map12, map13;
    public BufferedImage gameover, pauseScreen, expMenu, textField;
    public BufferedImage firstBatte, streetFights, theFactory, meetingRoom, kitchenRoom, finalBoss;
    private Player player;
    public int lobbycounter = 0;
    public int meetingcounter = 0;
    public int kitchencounter = 0;
    int currentMap;


    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.enemySetter = new EnemySetter(gamePanel);
        this.objectSetter = new ObjectSetter(gamePanel);
        try {
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Scene_1_bg.png")));
            textField = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/textfield.png")));
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
            map9 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/MeetingRoom.png")));
            map10 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/kitchen.png")));
            map11 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Lobby2.png")));
            map12 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/FinalBossRoom.png")));
            map13 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/waffenlager2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchLevel(int mapID) {
        switch (mapID) {
            case 0:
                gamePanel.currentMap = 1;
                map = map2;
                break;
            case 1:
                gamePanel.currentMap = 2;
                map = map3;
                break;
            case 2:
                gamePanel.currentMap = 3;
                map = map4;
                break;
            case 3:
                gamePanel.currentMap = 4;
                map = map13;
                break;
            case 4:
                gamePanel.currentMap = 5;
                map = map5;
                break;
            case 5:
                gamePanel.currentMap = 6;
                map = map6;
                break;
            case 6:
                gamePanel.currentMap = 7;
                map = map7;
                break;
            case 7:
                gamePanel.currentMap = 8;
                map = map8;
                lobbycounter++;
                break;
            case 8:
                gamePanel.currentMap = 9;
                map = map9;
                meetingcounter++;
                break;
            case 9:
                gamePanel.currentMap = 10;
                map = map10;
                kitchencounter++;
                break;
            case 10:
                gamePanel.currentMap = 11;
                map = map11 ;
                break;
            case 11:
                gamePanel.currentMap = 12;
                map = map12;
                break;
            default:
                throw new IllegalArgumentException("Invalid map ID: " + gamePanel.currentMap);
        }

        enemySetter.setEnemies(gamePanel.currentMap);
        objectSetter.setObject(gamePanel.currentMap);
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