package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.Objects;


public class ManagerObject extends SuperClassObject {



    public ManagerObject() {
        name = "manager";

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Conversation/manager.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics2D g2, GamePanel gamePanel) {
        g2.drawImage(image, 280, 475, 64, 135, null);
    }
}
