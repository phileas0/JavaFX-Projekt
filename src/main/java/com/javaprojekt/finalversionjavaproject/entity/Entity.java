package com.javaprojekt.finalversionjavaproject.entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int width, height;
    public int speed;
    public BufferedImage idle, up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solid;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;



    public boolean collidesWith(Entity other) {
        Rectangle thisHitbox = new Rectangle(x, y, 64, 64); // Assuming width and height are your entity's dimensions
        Rectangle otherHitbox = new Rectangle(other.x, other.y, 64, 64);
        return thisHitbox.intersects(otherHitbox);
    }

}
