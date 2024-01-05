package com.javaprojekt.finalversionjavaproject.object;

import javax.imageio.ImageIO;

public class DoorObject extends SuperClassObject{
    public DoorObject() {
        name = "Door";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
