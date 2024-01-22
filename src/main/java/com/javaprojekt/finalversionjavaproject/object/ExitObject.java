package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;

public class ExitObject extends SuperClassObject{
    public ExitObject() {
        name = "exit";
        collision = true;
        super.solid = new Rectangle(0,0,32, 11);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/exitSign.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, worldX, worldY, 64, 32, null);
    }
}
