package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;


public class ConeObject extends SuperClassObject {



    public ConeObject() {
        name = "cone";
        collision = true;
        super.solid = new Rectangle(0,0,75,82);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/cone.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
