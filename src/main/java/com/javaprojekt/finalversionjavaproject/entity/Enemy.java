package com.javaprojekt.finalversionjavaproject.entity;

import com.javaprojekt.finalversionjavaproject.main.Background;
import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Enemy extends Entity {
    public boolean markForRemoval = false;
    public boolean isInCombat = false;
    public BufferedImage idleCombat;
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
    }

    public boolean isInCombat() {
        return isInCombat;
    }
    public void takeDamage(int amount) {
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
            idle = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/player/playerTest_0.png")));
            idleCombat = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/firstEnemy.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void drawEnemyPortrait0(Graphics2D g2) {
        g2.drawImage(idleCombat, 650, 350, 256, 256, null);
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage image = idle;
        if (image != null) {
            graphics2D.drawImage(image, x, y, 64, 64, null);
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
