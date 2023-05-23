package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import bomb.Bomb;
import bomb.Explode;
import entity.Monster;
import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16; // 16x16 pixels
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 27; // set size the screen and map
    public final int maxScreenRow = 12; // set size the screen and map
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // World set up
    public final int maxWorldCol = 27;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    int FPS = 60;

    public TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Sound sound = new Sound();
    Thread gameThread;

    public CollisionCheck Colcheck = new CollisionCheck(this);

    public Player player = new Player(this, keyH);

    public Bomb bombs[] = new Bomb[10];
    public Explode[] explodes = new Explode[40];

    public SuperObject obj[] = new SuperObject[20];
    public Monster mons[] = new Monster[10];
    // seting object value

    public AssetSetter aSetter = new AssetSetter(this);
    public Random_item gacha = new Random_item(this);

    // Player position default:
    int playerX = 1;
    int playerY = 1;
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
        // aSetter.setMon();

        PlayMusic(7);

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
        for (int i = 0; i < mons.length; ++i) {
            if (mons[i] != null)
                mons[i].update();
        }
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

        // Bombs drawing
        for (int i = 0; i < bombs.length; i++) {
            if (bombs[i] != null) {
                bombs[i].draw(g2, this);
            }
        }

        for (int i = 0; i < explodes.length; i++) {
            if (explodes[i] != null) {
                explodes[i].draw(g2, this);
            }
        }

        // Monster drawing
        for (int i = 0; i < mons.length; i++) {
            if (mons[i] != null) {
                mons[i].draw(g2, this);
            }
        }

        g2.dispose();

    }

    public void PlayMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void StopMusic() {
        sound.stop();
    }

    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }

}