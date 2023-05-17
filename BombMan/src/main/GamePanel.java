package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 24; // set size the screen and map
    public final int maxScreenRow = 12; // set size the screen and map
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World set up
    public final int maxWorldCol = 27;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    public CollisionCheck Colcheck = new CollisionCheck(this);

    public Player player = new Player(this, keyH);

    public SuperObject obj[] = new SuperObject[10];
    // seting object value
    public AssetSetter aSetter = new AssetSetter(this);

    // Player position default:
    int playerX = 50;
    int playerY = 50;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // this function for rendering front/ back buffer at the same time
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {
        aSetter.setObject();
        // aSetter.preObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;

            lastTime = currentTime;
            if (delta >= 1) {
                update(); // update position
                repaint(); // re-draw screen
                delta--;
            }

        }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        // Map drawing
        tileM.draw(g2);

        // Object drawing
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // Player drawing
        player.draw(g2);

        g2.dispose();

    }

}