package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler kh;

    public Player(GamePanel gp, KeyHandler kh) {
        this.gp = gp;
        this.kh = kh;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    /**
     * updates player input 60 times per second
     */
    public void update() {
        //Todo: add diagonal movement and array key movement
        if (kh.upPressed) {
            direction = "up";
            y -= speed;
        } else if (kh.downPressed) {
            direction = "down";
            y += speed;
        } else if (kh.leftPressed) {
            direction = "left";
            x -= speed;
        } else if (kh.rightPressed) {
            direction = "right";
            x += speed;
        }

        spriteCounter++;
        if (spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            } else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    /**
     * updates drawing
     *
     * @param g2d the graphics
     */
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                } else {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 2) {
                    image = down1;
                } else {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 2) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }
        g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
    }

    /**
     * Loads the player images
     */
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Main_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Main_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Main_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Main_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Main_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Main_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Main_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Main_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
