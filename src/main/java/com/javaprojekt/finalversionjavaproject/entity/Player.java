package com.javaprojekt.finalversionjavaproject.entity;
import com.javaprojekt.finalversionjavaproject.main.GamePanel;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;
import com.javaprojekt.finalversionjavaproject.main.Tutorial;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity{

    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    public int hasKey1 = 0;
    public int hasKey2 = 0;

    public BufferedImage idleCombat, shoot, alternateImage;
    private int alternateImageDuration = 0;
    public int currentLevel = 1;
    public int maxHealth = 20; // +5 per Level
    public int currentHealth = maxHealth; // only used in Battle
    public int damage = 4; // +2 per Level
    public int energy = 100; // + 10 per Level
    public int energyRecovery = 15; // +3 per Level
    public int maxStimpaks = 1; // +1 per Level;
    public int currentStimpaks = maxStimpaks;
    public int healing = 3; // +2 per Level
    public int exp = 0;
    public int expToNextLevel = 10; // +5 per Level
    public boolean hasLeveledUp = false;




    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        int positionX = 16;
        int positionY = 38;
        int width = 30;
        int height = 16;

        solid = new Rectangle(positionX, positionY, width, height);
        solidAreaDefaultX = 16;
        solidAreaDefaultY = 38;

        setDefaultValues();
        getPlayerImage();
        getPlayerCombatImage();
    }
    public void setDefaultValues() {
        x = 640;
        y = 450;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_0.png")));
            down2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_1.png")));
            left1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_2.png")));
            left2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_3.png")));
            right1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_4.png")));
            right2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_5.png")));
            up1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_6.png")));
            up2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/player_7.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPlayerCombatImage() {
        try {
            idleCombat = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/sprite_0.png")));
            shoot = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/sprite_4.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAlternateImage(BufferedImage image, int durationFrames) {
        this.alternateImage = image;
        this.alternateImageDuration = durationFrames;
    }
    public void updateAlternateImageTimer() {
        if (alternateImageDuration > 0) {
            alternateImageDuration--;
        }
    }


    public void drawPlayerPortrait0(Graphics2D g2) {
        BufferedImage imageToDraw = alternateImageDuration > 0 ? shoot : idleCombat;
        g2.drawImage(imageToDraw, 350, 350, 256, 256, null);
    }
    public void drawPlayerPortrait1(Graphics2D g2) {
        BufferedImage imageToDraw = alternateImageDuration > 0 ? shoot : idleCombat;
        g2.drawImage(imageToDraw, 350, 350, 256, 256, null);
    }
    public void drawPlayerPortrait2(Graphics2D g2) {
        BufferedImage imageToDraw = alternateImageDuration > 0 ? shoot : idleCombat;
        g2.drawImage(imageToDraw, 350, 350, 256, 256, null);
    }
    public void drawPlayerPortrait5(Graphics2D g2) {
        BufferedImage imageToDraw = alternateImageDuration > 0 ? shoot : idleCombat;
        g2.drawImage(imageToDraw, 450, 580, 128, 128, null);
    }
    public void drawPlayerPortrait6(Graphics2D g2) {
        BufferedImage imageToDraw = alternateImageDuration > 0 ? shoot : idleCombat;
        g2.drawImage(imageToDraw, 450, 580, 128, 128, null);
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

            //MAXHEALTH
            if (currentHealth > getMaxHealth()) {
                currentHealth = getMaxHealth();
            }

            //CHECK TILE COLLISION
            collision = false;
            gamePanel.cDetecter.checkTile(this);

            //CHECK OBJECT COLLISION
            int objIndex = gamePanel.cDetecter.checkObject(this, true);
            pickUpObject(objIndex);

            //IF COLLISION IS FALSE PLAYER CAN MOVE
            if (!collision) {
                processLeftKey();
                processRightKey();
                processUpKey();
                processDownKey();
            }
        }
    }

    // Process 'up' key
    public void processUpKey() {
        if (keyHandler.upPressed) {
            direction = "up";
            collision = false;
            gamePanel.cDetecter.checkTile(this);
            if (!collision) {
                y -= speed;
            }
        }
    }

    // Process 'down' key
    public void processDownKey() {
        if (keyHandler.downPressed) {
            direction = "down";
            collision = false;
            gamePanel.cDetecter.checkTile(this);
            if (!collision) {
                y += speed;
            }
        }
    }

    // Process 'left' key
    public void processLeftKey() {
        if (keyHandler.leftPressed) {
            direction = "left";
            collision = false;
            gamePanel.cDetecter.checkTile(this);
            if (!collision) {
                x -= speed;
            }
        }
    }

    // Process 'right' key
    public void processRightKey() {
        if (keyHandler.rightPressed) {
            direction = "right";
            collision = false;
            gamePanel.cDetecter.checkTile(this);
            if (!collision) {
                x += speed;
            }
        }
    }


    public void pickUpObject(int index) {
        if (index != 999){
            String objName = gamePanel.obj[gamePanel.currentMap][index].getClass().getSimpleName();

            switch (objName) {
                case "KeyObject":
                    hasKey1++;
                    gamePanel.obj[gamePanel.currentMap][index] = null;
                    gamePanel.hud.showMessage("You picked a key!");
                    break;
                case "KeyObject2":
                    hasKey2++;
                    gamePanel.obj[gamePanel.currentMap][index] = null;
                    gamePanel.hud.showMessage("You picked up another!");
                    break;
                case "DoorObject":
                    if (hasKey1 >= 1) {
                        hasKey1--;
                        gamePanel.obj[gamePanel.currentMap][index] = null;
                        gamePanel.hud.showMessage("You opened a door!");
                    } else if (hasKey2 >= 1){
                        gamePanel.hud.showMessage("You need another key!");
                    } else {
                        gamePanel.hud.showMessage("You need a key!");
                    }
                    break;
                case "DoorObject2":
                    if (hasKey2 >= 1) {
                        hasKey2--;
                        gamePanel.obj[gamePanel.currentMap][index] = null;
                        gamePanel.hud.showMessage("You opened the door!");
                    } else if (hasKey1 >= 1){
                        gamePanel.hud.showMessage("You need another key!");
                    } else {
                        gamePanel.hud.showMessage("You need a key!");
                    }

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
        }
        graphics2D.drawImage(image, x, y, gamePanel.tileSize * 2, gamePanel.tileSize * 2, null);
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth() {
        this.maxHealth += 5;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage() {
        this.damage += 2;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy() {
        this.energy += 10;
    }

    public int getEnergyRecovery() {
        return energyRecovery;
    }

    public void setEnergyRecovery() {
        this.energyRecovery += 3;
    }

    public int getMaxStimpaks() {
        return maxStimpaks;
    }
    public void setMaxStimpaks() { this.maxStimpaks += 1;}

    public int getHealing() {
        return healing;
    }
    public void setHealing() {
        this.healing += 2;
    }

    public void setExp(int exp) {
        this.exp += exp;
    }
    public int getExp() {return exp;}
    public int getExpToNextLevel() {return expToNextLevel;}
    public int getCurrentLevel() {return currentLevel;}
    public void setLevelUp() {
        this.expToNextLevel += 5;
        this.currentLevel++;
    }
}
