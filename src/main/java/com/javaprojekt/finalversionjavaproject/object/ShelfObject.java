package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.entity.Entity;
import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class ShelfObject extends SuperClassObject {
    public ShelfObject() {
        name = "Shelf";
        collision = true;
        super.solid = new Rectangle(0,0,128,64);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Regalv2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize*2, gamePanel.tileSize, null);
    }

}
