package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;


public class BurningTrashbin extends SuperClassObject {



    public BurningTrashbin() {
        name = "burningTrashbin";
        collision = true;
        super.solid = new Rectangle(0,0,45,103);

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/burningTrashbin.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, gamePanel.tileSize, gamePanel.tileSize*2, null);
    }
}
