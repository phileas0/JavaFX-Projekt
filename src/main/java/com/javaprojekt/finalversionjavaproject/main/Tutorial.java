package com.javaprojekt.finalversionjavaproject.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tutorial {//Tutorial class
    GamePanel gamePanel;
    KeyHandler keyHandler;
    Font arial40;//Initialize font
    BufferedImage KeyW, KeyA, KeyS, KeyD, KeyE, KeyEsc, KeyQ, Key1, Key2, Key3, Textfield;//Initialize images
    public boolean isTutorialActive = true;//Initialize boolean for tutorial display
    boolean isTutorialActive1 = false;
    boolean isTutorialActive2 = false;
    boolean isTutorialActive3 = false;


    public Tutorial(GamePanel gamePanel) {//Constructor for tutorial class to get the gamepanel and keyhandler and load the images
        this.keyHandler = gamePanel.keyHandler;
        this.gamePanel = gamePanel;
        try {
            KeyW = ImageIO.read(getClass().getResourceAsStream("/res/keys/WKey.png"));
            KeyA = ImageIO.read(getClass().getResourceAsStream("/res/keys/AKey.png"));
            KeyS = ImageIO.read(getClass().getResourceAsStream("/res/keys/SKey.png"));
            KeyD = ImageIO.read(getClass().getResourceAsStream("/res/keys/DKey.png"));
            KeyE = ImageIO.read(getClass().getResourceAsStream("/res/keys/EKey.png"));
            KeyQ = ImageIO.read(getClass().getResourceAsStream("/res/keys/QKey.png"));
            Key1 = ImageIO.read(getClass().getResourceAsStream("/res/keys/1Key.png"));
            Key2 = ImageIO.read(getClass().getResourceAsStream("/res/keys/2Key.png"));
            Key3 = ImageIO.read(getClass().getResourceAsStream("/res/keys/3Key.png"));
            KeyEsc = ImageIO.read(getClass().getResourceAsStream("/res/keys/EscKey.png"));
            Textfield = ImageIO.read(getClass().getResourceAsStream("/res/Conversation/textfield.png"));

            arial40 = new Font("Arial", Font.BOLD, 40);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {//draw method for the tutorial which displays the tutorial images and text
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);
        g2.setComposite(ac);
        if (isTutorialActive) {
            g2.drawImage(Textfield, 100, 100, 1080, 568, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.GRAY);
            g2.setFont(arial40);
            g2.drawString("Press the following Keys to move:", 340, 360);
            g2.drawImage(KeyW, 500, 400, 64, 54, null);
            g2.drawImage(KeyA, 600, 400, 64, 54, null);
            g2.drawImage(KeyS, 700, 400, 64, 54, null);
            g2.drawImage(KeyD, 800, 400, 64, 54, null);
            g2.setComposite(ac);
            if (keyHandler.downPressed || keyHandler.upPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
                isTutorialActive = false; // Deactivate tutorial
                isTutorialActive1 = true; // Activate next tutorial
            }
        }
        if (isTutorialActive1) {
            g2.drawImage(Textfield, 100, 100, 1080, 568, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.GRAY);
            g2.setFont(arial40);
            g2.drawString("Press the following Key to Pause the game:", 240, 360);
            g2.drawImage(KeyQ, 610, 400, 64, 54, null);
            g2.setComposite(ac);
        }
        if (isTutorialActive2) {
            g2.drawImage(Textfield, 100, 100, 1080, 568, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.GRAY);
            g2.setFont(arial40);
            g2.drawString("Press the following Key to leave after the fight Scenes", 200, 360);
            g2.drawImage(KeyE, 610, 400, 64, 54, null);
            if (keyHandler.interacted) {
                isTutorialActive2 = false;
                isTutorialActive3 = true;
            }
        }
        if (isTutorialActive3) {
            g2.setColor(Color.GRAY);
            g2.setFont(arial40);
            g2.drawImage(Textfield, 100, 100, 1080, 568, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            String paragraph = "You are the detective in this game which/nneeds to solve a" +
                                "'Cyberpunk'-Mystery,/nby fighting against enemies and/n" +
                                "solving puzzles, exploring the map./n/n                         press 'esc'";
            String[] lines = paragraph.split("/n");
            int y = 240;
            for (String line : lines) {
                g2.drawString(line, 240, y);
                y += g2.getFontMetrics().getHeight(); // Move y-coordinate down for the next line
            }
            if (keyHandler.escPressed) {
                isTutorialActive3 = false;
            }
        }
    }
}
