package com.javaprojekt.finalversionjavaproject.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Background {
    public GamePanel gamePanel;
    private BufferedImage map;

    public Background(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        try {
            map = ImageIO.read(getClass().getResourceAsStream("/res/maps/Scene_1_bg.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void drawMap(Graphics2D g2) {
        g2.drawImage(map, 0, 0, 1280, 768, null);
    }
}