package com.javaprojekt.finalversionjavaproject.entity;
import com.javaprojekt.finalversionjavaproject.main.GamePanel;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 350;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            idle = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_0.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_3.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_4.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_5.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_6.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_7.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/playerTest_8.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {

        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            }
            if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            }
            if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            }
            if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if (spriteCounter > 10) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else direction = "idle";
    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if (spriteNum == 1) {image = up1;}
                if (spriteNum == 2) {image = up2;}
                break;
            case "down":
                if (spriteNum == 1) {image = down1;}
                if (spriteNum == 2) {image = down2;}
                break;
            case "left":
                if (spriteNum == 1) {image = left1;}
                if (spriteNum == 2) {image = left2;}
                break;
            case "right":
                if (spriteNum == 1) {image = right1;}
                if (spriteNum == 2) {image = right2;}
                break;
            case "idle":
                image = idle;
        }
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}