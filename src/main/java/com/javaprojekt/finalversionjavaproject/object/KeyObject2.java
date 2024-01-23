package com.javaprojekt.finalversionjavaproject.object;

import javax.imageio.ImageIO;
import java.util.Objects;

public class KeyObject2 extends SuperClassObject {
    public KeyObject2() {
        name = "key2";
        collision = false;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/objects/key2.png")));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
