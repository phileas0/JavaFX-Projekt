package com.javaprojekt.finalversionjavaproject.entity;

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
    public int health;
    public int damage;
    public boolean isMarkedForRemoval() {
        return markForRemoval;
    }

    public Enemy(int health, int damage) {
        this.health = health;
        this.damage = damage;
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
        } catch (IOException e) {
            e.printStackTrace();
        }
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
}
