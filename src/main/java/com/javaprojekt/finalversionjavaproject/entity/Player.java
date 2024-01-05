package com.javaprojekt.finalversionjavaproject.entity;
import com.javaprojekt.finalversionjavaproject.main.GamePanel;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public int hasKey = 0;
    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        solid = new Rectangle(16,38,30,16);
        solidAreaDefaultX = 16;
        solidAreaDefaultY = 38;

        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues() {
        x = 640;
        y = 100;
        speed = 3;
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

            }
            if (keyHandler.downPressed) {
                direction = "down";

            }
            if (keyHandler.leftPressed) {
                direction = "left";

            }
            if (keyHandler.rightPressed) {
                direction = "right";

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

            //CHECK TILE COLLISION
            collision = false;
            gamePanel.cDetecter.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gamePanel.cDetecter.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if (!collision) {
                if (keyHandler.upPressed) {
                    y -= speed;
                }
                if (keyHandler.downPressed) {
                    y += speed;
                }
                if (keyHandler.leftPressed) {
                    x -= speed;
                }
                if (keyHandler.rightPressed) {
                    x += speed;
                }
            }
        }
        else direction = "idle";
    }

    public void pickUpObject(int index) {
        if (index != 999){
            String objName = gamePanel.obj[index].getClass().getSimpleName();

            switch (objName) {
                case "KeyObject":
                    hasKey++;
                    gamePanel.obj[index] = null;
                    gamePanel.hud.showMessage("You picked up a key!");
                    break;
                case "DoorObject":
                    if (hasKey > 0) {
                        hasKey--;
                        gamePanel.obj[index] = null;
                        gamePanel.hud.showMessage("You opened the door!");
                    } else{
                        gamePanel.hud.showMessage("You need a key!");
                    }
                    break;
            }
        }
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
