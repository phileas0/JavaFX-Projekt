package com.javaprojekt.finalversionjavaproject.entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Enemy extends Entity {
    public boolean markForRemoval = false;
    public boolean isInCombat = false;
    public BufferedImage enemy0, manager, smallGuard, bigGuard, deviousGuard, factoryRobot, smallCook, bigCook, finalBoss, enemy0Sprite, smallGuardSprite, bigGuardSprite, deviousGuardSprite, factoryRobotSprite, smallCookSprite, bigCookSprite;
    public int health;
    public int damage;
    public double hitPropability;
    public int skinNr;
    public int backgroundNr;

    public int givesExp;
    public int getGivesExp() {
        return givesExp;
    }

    public boolean isMarkedForRemoval() {
        return markForRemoval;
    }

    public Enemy(int health, int damage, double hitPropability, int givesExp, int skinNr, int backgroundNr) {
        this.health = health;
        this.damage = damage;
        this.hitPropability = hitPropability;
        this.skinNr = skinNr;
        this.backgroundNr = backgroundNr;
        this.givesExp = givesExp;
        getEnemyImage();
    }
    public void setInCombat(boolean inCombat) {
        this.isInCombat = inCombat;
    }//
    public boolean isInCombat() {
        return isInCombat;
    }
    public void takeDamage(int amount) {//damage taken
        health -= amount;
        if (health < 0) health = 0;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }
    public void getEnemyImage() {
        try {
            enemy0 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/enemy0.png")));
            smallGuard = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/smallGuard.png")));
            bigGuard = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/bigGuard.png")));
            deviousGuard = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/deviousGuard.png")));
            factoryRobot = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/factoryRobot.png")));
            smallCook = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/smallCook.png")));
            bigCook = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/bigCook.png")));
            manager = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Conversation/manager.png")));
            finalBoss = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/finalBoss.png")));

            enemy0Sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/enemy0Sprite.png")));
            smallGuardSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/smallGuardSprite.png")));
            bigGuardSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/bigGuardSprite.png")));
            deviousGuardSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/deviousGuardSprite.png")));
            factoryRobotSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/factoryRobotSprite.png")));
            smallCookSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/smallCookSprite.png")));
            bigCookSprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/enemies/bigCookSprite.png")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawEnemyPortrait0(Graphics2D g2) {
        g2.drawImage(enemy0, 650, 350, 256, 256, null);
    }
    public void drawSmallGuard(Graphics2D g2) {
        g2.drawImage(smallGuard, 650, 350, 256, 256, null);
    }
    public void drawBigGuard(Graphics2D g2) {
        g2.drawImage(bigGuard, 650, 350, 256, 256, null);
    }
    public void drawFactoryRobot(Graphics2D g2) {
        g2.drawImage(factoryRobot, 650, 350, 256, 256, null);
    }
    public void drawSmallCook(Graphics2D g2) {
        g2.drawImage(smallCook, 650, 350, 256, 256, null);
    }
    public void drawBigCook(Graphics2D g2) {
        g2.drawImage(bigCook, 650, 350, 256 / 2, 256, null);
    }
    public void drawDeviousGuard(Graphics2D g2) {
        g2.drawImage(deviousGuard, 650, 350, 256, 256, null);
    }
    public void drawFinalBoss(Graphics2D g2) {
        g2.drawImage(finalBoss, 650, 200, 512, 512, null);
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage enemyImage;
        switch (skinNr) {
            case 1:
                enemyImage = smallGuardSprite;
                break;
            case 2:
                enemyImage = bigGuardSprite;
                break;
            case 3:
                enemyImage = deviousGuardSprite;
                break;
            case 4:
                enemyImage = factoryRobotSprite;
                break;
            case 5:
                enemyImage = smallCookSprite;
                break;
            case 6:
                enemyImage = bigCookSprite;
                break;
            case 7:
                enemyImage = manager;
                break;
            default:
                enemyImage = enemy0Sprite;
                break;
        }

        if (enemyImage != null) {
            graphics2D.drawImage(enemyImage, x, y, 64, 135, null);
        } else {
            // Draw a placeholder rectangle if sprite is null
            graphics2D.setColor(Color.RED);
            graphics2D.fillRect(20, 20, 20, 20); // Placeholder for enemy
        }
    }

    public boolean isOnMap() {
        return !isMarkedForRemoval() && !isInCombat();
    }
}
