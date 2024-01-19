package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class TrashbagObject extends SuperClassObject {
    public TrashbagObject() {
        name = "trashbag";
        collision = true;


        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/trashbag.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize-14, gamePanel.tileSize, null);
    }
}
