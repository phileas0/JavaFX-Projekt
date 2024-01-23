package com.javaprojekt.finalversionjavaproject.design;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;
import com.javaprojekt.finalversionjavaproject.main.Tutorial;
import com.javaprojekt.finalversionjavaproject.object.KeyObject;
import com.javaprojekt.finalversionjavaproject.object.KeyObject2;

import java.awt.*;

public class HUD {
    private GamePanel gamePanel;
    private Tutorial tutorial;
    private Font arial40;
    private Image keyImage1;
    private Image keyImage2;
    public boolean messageBoolean = false;
    public String messageString = "";
    public String messageStringBefore = "";
    int messageTimer = 0;

    public HUD(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tutorial = gamePanel.tutorial;

        arial40 = new Font("Arial", Font.BOLD, 40);
        KeyObject key = new KeyObject();
        KeyObject2 key2 = new KeyObject2();
        keyImage1 = key.image;
        keyImage2 = key2.image;
    }


    public void draw(Graphics2D g2) {
        if(gamePanel.player.hasKey1==1){
            getKey(g2, 1);
        } else if (gamePanel.player.hasKey2==1) {
            getKey(g2, 2);
        }

    }

    private void getKey(Graphics2D g2, int haskey) {
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
        g2.setComposite(ac);
        g2.setFont(arial40);

        if(haskey == 1) {
            g2.setColor(Color.YELLOW);
            g2.drawImage(tutorial.Textfield, 6, 10, 140, 75, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.drawImage(keyImage1, 16, 16, 64, 64, null);
            g2.drawString(" : " + gamePanel.player.hasKey1, 60, 60);
        }
        g2.setComposite(ac);
        if(haskey == 2) {
            g2.setColor(new Color(97,42,111));
            g2.drawImage(tutorial.Textfield, 6, 10, 140, 75, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.drawImage(keyImage2, 16, 16, 64, 64, null);
            g2.drawString(" : " + gamePanel.player.hasKey2, 60, 60);
        }
        g2.setComposite(ac);
        if(messageBoolean){
            int centerX = gamePanel.getWidth() / 2;
            int variableY = 620;

            int textWidth = g2.getFontMetrics().stringWidth(messageString);
            int textHeight = g2.getFontMetrics().getHeight();

            g2.drawImage(tutorial.Textfield, centerX-(textWidth/2)-70, variableY-30, textWidth+140, 80 + textHeight, null);
            g2.drawString(messageString, centerX - textWidth / 2, variableY + textHeight);

            if(messageStringBefore != messageString){
                messageTimer = 0;
            }

            messageStringBefore = messageString;

            messageTimer++;
            if (messageTimer > 150){
                messageBoolean = false;
                messageTimer = 0;
            }
        }
    }

    public void showMessage(String message) {
        messageBoolean = true;
        messageString = message;
    }
}
