package entity;

import java.awt.image.BufferedImage;

/**
 * Author: Youssef Amin
 * this is the parent class to all entities (Player, monsters, ect)
 */
public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage left1, left2, up1, up2, right1, right2, down1, down2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
}
