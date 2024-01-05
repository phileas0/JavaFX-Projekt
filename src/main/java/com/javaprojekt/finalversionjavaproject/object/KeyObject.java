package com.javaprojekt.finalversionjavaproject.object;

import javax.imageio.ImageIO;

public class KeyObject extends SuperClassObject {
    public KeyObject() {
        name = "key";
        collision = false;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
