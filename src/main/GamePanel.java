package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

/**
 * Author: Youssef Amin
 * this class is the game panel
 */
public class GamePanel extends JPanel implements Runnable {

    //Screen Settings
    final int OGTileSize = 16; // 16x16
    final int scale = 3;

    public int tileSize = OGTileSize * scale; // 48 x 48
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;
    public int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    //set players default position
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    /**
     * Constructor for the GamePanel
     */
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // allows for better rendering performance
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }


    /**
     * starts the game thread
     */
    public void startGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * updates game data
     */
    public void update() {
        player.update();
    }

    /**
     * updates visuals
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileM.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }

    /**
     * this is the main game loop
     */
    @Override
    public void run() {

        double drawInterval = 1000000000.0 / FPS; // 60 times per second
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
