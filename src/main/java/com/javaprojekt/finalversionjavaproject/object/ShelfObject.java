package com.javaprojekt.finalversionjavaproject.object;

import com.javaprojekt.finalversionjavaproject.entity.Entity;

import javax.imageio.ImageIO;

public class ShelfObject extends SuperClassObject {
    public ShelfObject() {
        name = "Shelf";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Regalv2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
