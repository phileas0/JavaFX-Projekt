package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.entity.Entity;
import com.javaprojekt.finalversionjavaproject.entity.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    public GamePanel gamePanel;
    private Image map;
    private Image gameover;
    public Image map2;
    public Image map3;
    private Player player;
    int currentMap;


    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            map = ImageIO.read(getClass().getResourceAsStream("/res/maps/Scene_1_bg.png"));
            gameover = ImageIO.read(getClass().getResourceAsStream("/res/player/Gameover.png"));
            map2 = ImageIO.read(getClass().getResourceAsStream("/res/maps/Scene1part2.png"));
            ImageIcon icon = new ImageIcon(getClass().getResource("/res/maps/StreetbackgroundSzene4.gif"));
            map3 = icon.getImage();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void switchLevel() {
        if (gamePanel.currentMap == 0) {
            gamePanel.currentMap = 1;
            map = map2; // Change the map to the second level
        }
        else if(gamePanel.currentMap==1) {
            gamePanel.currentMap = 2;
            map = map3;
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
}