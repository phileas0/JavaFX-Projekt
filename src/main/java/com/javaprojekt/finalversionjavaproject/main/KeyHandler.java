package com.javaprojekt.finalversionjavaproject.main;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed, interacted, pauseGame, pressed1, pressed2, pressed3, space;
    boolean showDebugText = false;


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W){
            upPressed = true;
        }
        if (code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if (code == KeyEvent.VK_S){
            downPressed = true;
        }
        if (code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if (code == KeyEvent.VK_E){
            interacted = true;
        }
        if (code == KeyEvent.VK_Q){
            pauseGame = true;
        }

        //DEBUG
        if (code == KeyEvent.VK_T) {
            if (showDebugText == false) {
                showDebugText = true;
            } else if (showDebugText == true) {
                showDebugText = false;
            }
        }
        if (code == KeyEvent.VK_1){
            pressed1 = true;
        }
        if (code == KeyEvent.VK_2){
            pressed2 = true;
        }
        if (code == KeyEvent.VK_3){
            pressed3 = true;
        }
        if (code == KeyEvent.VK_SPACE){
            space = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_E) {
            interacted = false;
        }
        if (code == KeyEvent.VK_Q) {
            pauseGame = false;
        }
        if (code == KeyEvent.VK_1){
            pressed1 = false;
        }
        if (code == KeyEvent.VK_2){
            pressed2 = false;
        }
        if (code == KeyEvent.VK_3){
            pressed3 = false;
        }
        if (code == KeyEvent.VK_SPACE){
            space = false;
        }
    }
}
