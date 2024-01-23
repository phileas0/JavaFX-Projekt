package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class TrashbagObject extends SuperClassObject {
    public TrashbagObject() {
        name = "trashbag";
        collision = true;
        super.solid = new Rectangle(0,0,48,64);


        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/trashbag.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize-14, gamePanel.tileSize, null);
    }
}
