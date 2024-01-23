package com.javaprojekt.finalversionjavaproject.design;

import com.javaprojekt.finalversionjavaproject.combat.TextField;
import com.javaprojekt.finalversionjavaproject.entity.Player;
import com.javaprojekt.finalversionjavaproject.main.*;

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
    public BufferedImage gameover, pauseScreen, expMenu, textField, credits, startImage;
    public BufferedImage streetFights, streetFights2, factoryFront, theFactory, meetingRoom, kitchenRoom, finalBoss;
    public Player player;
    public KeyHandler keyhandler;
    private Tutorial tutorial;
    public int lobbycounter = 0;
    public int meetingcounter = 0;
    public int kitchencounter = 0;
    public int startcounter = 0;
    private int creditcounter;
    public boolean startScreen = true;


    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.keyhandler = gamePanel.keyHandler;
        this.enemySetter = new EnemySetter(gamePanel);
        this.objectSetter = new ObjectSetter(gamePanel);
        this.player = new Player(gamePanel, gamePanel.keyHandler);
        this.tutorial = gamePanel.tutorial;
        try {
            map = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Scene_1_bg.png")));
            textField = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/textfield.png")));
            gameover = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/Gameover.png")));
            credits = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Conversation/credits.png")));
            pauseScreen = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/pauseScreen.png")));
            expMenu = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/expMenu.png")));
            map2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/Scene1part2.png")));
            map3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/maps/waffenlagerfront.png")));
            streetFights = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/streetFights.png")));
            streetFights2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/streetFights2.png")));
            factoryFront = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/factoryFront.png")));
            theFactory = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/theFactory.png")));
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
            startImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/res/maps/startscreen.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchLevel(int mapID) {
        switch (mapID) {
            case 0:
                gamePanel.currentMap = 1;
                map = map2;
                player.currentHealth = player.maxHealth;
                break;
            case 1:
                gamePanel.currentMap = 2;
                map = map3;
                player.currentHealth = player.maxHealth;
                break;
            case 2:
                gamePanel.currentMap = 3;
                map = map4;
                player.currentHealth = player.maxHealth;
                break;
            case 3:
                gamePanel.currentMap = 4;
                map = map13;
                player.currentHealth = player.maxHealth;
                break;
            case 4:
                gamePanel.currentMap = 5;
                map = map5;
                player.currentHealth = player.maxHealth;
                break;
            case 5:
                gamePanel.currentMap = 6;
                map = map6;
                player.currentHealth = player.maxHealth;
                break;
            case 6:
                gamePanel.currentMap = 7;
                map = map7;
                player.currentHealth = player.maxHealth;
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
                player.currentHealth = player.maxHealth;
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
    public void drawGameover(Graphics2D g2) {g2.drawImage(gameover, 0, 0, 1280, 768, null);}
    public void drawPauseScreen(Graphics2D g2) {
        g2.drawImage(pauseScreen, 0, 0, 1280, 768, null);
    }
    public void drawExpMenu(Graphics2D g2) {
        g2.drawImage(expMenu, 0, 0, 1280, 768, null);
    }
    public void drawCredits(Graphics2D g2) {
        Font arial40 = new Font("Arial", Font.BOLD, 40);
        g2.setFont(arial40);
        g2.setColor(Color.PINK);
        String message = "You won. Press \"esc\" to restart the game";
        int centerX = gamePanel.getWidth() / 2;
        int variableY = 620;

        int textWidth = g2.getFontMetrics().stringWidth(message);
        int textHeight = g2.getFontMetrics().getHeight();
        g2.drawImage(credits, 0, 0, 1280, 768, null);
        creditcounter++;
        if(creditcounter>180){
            g2.drawImage(tutorial.Textfield, centerX-(textWidth/2)-150, variableY-30, textWidth+300, 80 + textHeight, null);
            g2.drawString("You won. Press \"esc\" to restart the game", centerX - textWidth / 2, variableY + textHeight);
        }

    }
    public void drawStartScreen(Graphics2D g2) {
        g2.drawImage(startImage, 0, 0, 1280, 768, null);
        if(keyhandler.interacted){
            startScreen = false;
        }
    }

    public void drawStreetFights(Graphics2D g2) {
        g2.drawImage(streetFights, 0, 0, 1280, 768, null);
    }
    public void drawStreetFights2(Graphics2D g2) {
        g2.drawImage(streetFights2, 0, 0, 1280, 768, null);
    }
    public void drawFactoryFront(Graphics2D g2) {
        g2.drawImage(factoryFront, 0, 0, 1280, 768, null);
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