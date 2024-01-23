package com.javaprojekt.finalversionjavaproject.object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class KeyObject extends SuperClassObject {
    public KeyObject() {
        name = "key";
        collision = false;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/key.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
