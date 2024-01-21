package com.javaprojekt.finalversionjavaproject.main;

public class GameUtils {
    private static int sleepCounter = 0;

    public static void sleep(int frames) {
        sleepCounter = frames;
    }

    public static void update() {
        if (sleepCounter > 0) {
            sleepCounter--;
        }
    }

    public static boolean isSleeping() {
        return sleepCounter > 0;
    }
}
