package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;

public class DoorObject2 extends SuperClassObject{
    public DoorObject2() {
        name = "Door2";
        collision = true;
        super.solid = new Rectangle(0,0,320,70);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/door2.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize*5, gamePanel.tileSize, null);
    }
}
