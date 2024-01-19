package com.javaprojekt.finalversionjavaproject.object;

import javax.imageio.ImageIO;

public class DoorObject2 extends SuperClassObject{
    public DoorObject2() {
        name = "Door2";
        collision = true;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
