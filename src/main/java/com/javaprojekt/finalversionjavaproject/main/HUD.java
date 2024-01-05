package com.javaprojekt.finalversionjavaproject.main;

import com.javaprojekt.finalversionjavaproject.object.KeyObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HUD {
    GamePanel gamePanel;
    Font arial40;
    BufferedImage keyImage;
    public boolean messageboolean = false;
    public String messageString = "";
    String messageStringBefore = "";
    int messageTimer = 0;

    public HUD(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        arial40 = new Font("Arial", Font.BOLD, 40);
        KeyObject key = new KeyObject();
        keyImage = key.image;
    }


    public void draw(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.setFont(arial40);
        g2.drawImage(keyImage, 16, 16, 64, 64, null);
        g2.drawString(" : " + gamePanel.player.hasKey, 60, 60);

        if(messageboolean){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(messageString, 500, 720);

            if(messageStringBefore != messageString){
                messageTimer = 0;
            }

            messageStringBefore = messageString;

            messageTimer++;
            if (messageTimer > 150){
                messageboolean = false;
                messageTimer = 0;
            }


        }

    }
    public void showMessage(String message) {
        messageboolean = true;
        messageString = message;
    }
}
