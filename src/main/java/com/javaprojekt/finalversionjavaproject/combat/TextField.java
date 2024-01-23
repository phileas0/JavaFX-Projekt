package com.javaprojekt.finalversionjavaproject.combat;

import com.javaprojekt.finalversionjavaproject.main.GamePanel;
import com.javaprojekt.finalversionjavaproject.main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class TextField {
    private GamePanel gamePanel;
    private KeyHandler keyHandler;
    private BufferedImage textboxBackground;
    private final int x, y, width, height;
    public boolean isVisible;
    private Queue<String> messages;
    private String currentMessage;
    private long messageFrameCount;
    private final long messageDuration; // Duration to display each message (in frames)

    public TextField(GamePanel gamePanel, KeyHandler keyHandler) {//Constructor for the textfield class to get the gamepanel and keyhandler and load the image
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.width = 767; // Width of the textbox image
        this.height = 100; // Height of the textbox image
        this.x = (gamePanel.screenWidth - width) / 2; // Center horizontally
        this.y = gamePanel.screenHeight - height - 10; // Positioned at the bottom with a small margin
        this.messages = new LinkedList<>();
        this.messageFrameCount = 0;
        this.messageDuration = 120;
        try {
            textboxBackground = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/combat/textfield.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isDisplayingMessages() {
        return isVisible && !messages.isEmpty();
    }// Method to check if the textbox is currently displaying messages


    public void addMessage(String message) { // Add a message to the queue
        messages.add(message);
        if (!isVisible) {
            isVisible = true;
            messageFrameCount = 0; // Reset frame count when a new message is added
        }
    }

    public void update() { // Update the textbox while it is visible and not empty
        if (isVisible && !messages.isEmpty()) {
            messageFrameCount++;
            if (messageFrameCount >= messageDuration) {
                messages.poll(); // Remove and display the next message
                messageFrameCount = 0;
                if (messages.isEmpty()) {
                    isVisible = false; // No more messages to display
                }
            }
        }
    }

    public void draw(Graphics2D g2) {//draw method for the textbox which displays the textbox image and text
        if (isVisible && !messages.isEmpty()) {
            String currentMessage = messages.peek(); // Get the current message without removing it

            // Draw the textbox background image
            g2.drawImage(textboxBackground, x, y, width, height, null);

            // Draw the text over the image
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial", Font.BOLD, 20));
            FontMetrics metrics = g2.getFontMetrics();
            int textX = x + (width - metrics.stringWidth(currentMessage)) / 2;
            int textY = y + ((height - metrics.getHeight()) / 2) + metrics.getAscent();
            g2.drawString(currentMessage, textX, textY);
        }
    }
}