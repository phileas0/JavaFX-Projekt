package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class CorpseObject extends SuperClassObject {



    public CorpseObject() {
        name = "cone";
        collision = true;

        try {
            ImageIcon icon = new ImageIcon(getClass().getResource("/res/objects/corpse.gif"));
            image = icon.getImage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
