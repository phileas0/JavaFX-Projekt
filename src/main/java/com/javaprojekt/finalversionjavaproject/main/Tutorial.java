package com.javaprojekt.finalversionjavaproject.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Tutorial {
    public int pauseGameCounter = 1;//Tutorial class
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private Font arial40;//Initialize font
    public BufferedImage KeyW;
    public BufferedImage KeyA;
    public BufferedImage KeyS;
    public BufferedImage KeyD;
    public BufferedImage KeyE;
    public BufferedImage KeyEsc;
    public BufferedImage KeyQ;
    public BufferedImage Key1;
    public BufferedImage Key2;
    public BufferedImage Key3;
    public BufferedImage Textfield;//Initialize images
    public boolean isTutorialActive = true;//Initialize boolean for tutorial display
    boolean isTutorialActive1 = false;
    boolean isTutorialActive2 = false;
    boolean isTutorialActive3 = false;


    public Tutorial(GamePanel gamePanel) {//Constructor for tutorial class to get the gamepanel and keyhandler and load the images
        this.keyHandler = gamePanel.keyHandler;
        this.gamePanel = gamePanel;
        try {
            KeyW = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/WKey.png")));
            KeyA = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/AKey.png")));
            KeyS = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/SKey.png")));
            KeyD = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/DKey.png")));
            KeyE = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/EKey.png")));
            KeyQ = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/QKey.png")));
            Key1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/1Key.png")));
            Key2 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/2Key.png")));
            Key3 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/3Key.png")));
            KeyEsc = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/keys/EscKey.png")));
            Textfield = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/Conversation/textfield.png")));

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
            g2.drawString("Press the following keys to move:", 340, 360);
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
            g2.drawString("Press the following key to pause the game:", 240, 360);
            g2.drawImage(KeyQ, 610, 400, 64, 54, null);
            g2.setComposite(ac);
        }
        if (isTutorialActive2) {
            g2.drawImage(Textfield, 100, 100, 1080, 568, null);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2.setColor(Color.GRAY);
            g2.setFont(arial40);
            g2.drawString("Press the following key to leave after fights:", 220, 360);
            g2.drawImage(KeyE, 610, 450, 64, 54, null);
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
            String paragraph = "Welcome to 'Holo Hunter'./nA cyberpunk detective RPG game./n" +
                                "You are a detective in the city/nManus Machina/n" +
                                "Try solving the mystery surrounding it!/n/n                        press 'esc'";
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
