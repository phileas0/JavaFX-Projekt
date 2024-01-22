package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;


public class BarricadeObject extends SuperClassObject {



    public BarricadeObject() {
        name = "burningTrashbin";
        collision = true;
        super.solid = new Rectangle(0,0,101,92);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/barricade.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, 101, 92, null);
    }
}
