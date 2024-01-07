package com.javaprojekt.finalversionjavaproject.entity;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Enemy extends Entity {
    public Enemy() {
        int health = 10;
        int damage = 2;
        getEnemyImage();
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
