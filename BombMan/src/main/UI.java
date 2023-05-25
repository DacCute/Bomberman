package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import object.Obj_Power;
import object.Obj_Speed;
import object.Obj_lives;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_80;
    Font arial_40;
    Font arial_20;
    Font arial_15;
    BufferedImage speed_Image;
    BufferedImage lives_Image;
    BufferedImage power_Image;
    // int message_counter = 0;
    int clock = 0;
    // int index;
    // String message;
    // boolean messageOn = false;
    double playTime = 0;
    public boolean GameFinish = false;
    public boolean GameOver = false;

    BufferedImage titleNameImage, playButton, quitButton, pointer;
    public int commandNum = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_80 = new Font("Arial", Font.PLAIN, 80);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        arial_15 = new Font("Arial", Font.PLAIN, 15);

        Obj_lives lives = new Obj_lives(gp);
        lives_Image = lives.image;
        Obj_Speed speed = new Obj_Speed(gp);
        speed_Image = speed.image;
        Obj_Power power = new Obj_Power(gp);
        power_Image = power.image;
    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaleImage = null;

        try {
            scaleImage = ImageIO.read(getClass().getResourceAsStream("/titleScreen/" + imageName + ".png"));
            scaleImage = uTool.scaleImage(scaleImage, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return scaleImage;
    }

    public void getTitleImage() {
        titleNameImage = setup("gameTitle");
        playButton = setup("playButton");
        quitButton = setup("quitButton");
        pointer = setup("pointer");
    }

    // public void showMessage(String text, int index) {
    // this.index = index;
    // message = text;
    // messageOn = true;

    // }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_20);
        g2.setColor(Color.white);

        switch (gp.gameState) {
            case 0:
                drawTileScreen(g2);
                break;
            case 1:
                g2.setFont(arial_20);
                g2.setColor(Color.white);

                g2.drawImage(lives_Image, gp.tileSize / 2, gp.tileSize / 5 * 4, gp.tileSize / 2, gp.tileSize / 2, null);
                g2.drawString(" x " + gp.player.hp, gp.tileSize, gp.tileSize / 4 * 5);

                g2.drawImage(speed_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize, gp.tileSize / 2,
                        gp.tileSize / 2, null);
                g2.drawString(" x " + gp.player.speed, gp.tileSize, (gp.tileSize * 2));

                g2.drawImage(power_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize * 2, gp.tileSize / 2,
                        gp.tileSize / 2, null);
                g2.drawString(" x " + gp.player.power, gp.tileSize, (gp.tileSize * 3));

                // if (messageOn == true) {
                // g2.setFont(arial_15);
                // g2.drawString(message, gp.player.screenX, gp.player.screenY);

                // message_counter++;
                // if (message_counter > 60) {
                // message_counter = 0;
                // messageOn = false;
                // }
                // }

                break;
            case 2:
                drawPauseScreen(g2);
                break;
            case 3:
                drawDifficult(g2);
                break;
            case 4:
                afterFinish(g2);
                break;
            case 5:
                // TODO
                break;
        }

        if (GameFinish || GameOver) {
            gp.gameState = gp.ENDGAME;
        } else {
            playTime += (double) 1 / 60;
        }

    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        String text = "PAUSED";
        int x = getXcenter(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public void drawTileScreen(Graphics2D g2) {

        // Background setup
        // MAYBE SETTING IMAGE
        g2.setColor(new Color(3, 75, 34));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(arial_80);
        int x = getXcenter("BOMBERMAN");
        int y = gp.tileSize * 3;

        g2.setColor(Color.GRAY);
        g2.drawString("BOMBERMAN", x + 5, y + 5);

        g2.setColor(Color.WHITE);
        g2.drawString("BOMBERMAN", x, y);

        // IMAGE CHARACTER
        x = gp.screenWidth / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x - gp.tileSize, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // OPTIONS
        g2.setFont(arial_40);
        String text;
        text = "NEW GAME";
        x = getXcenter(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "DIFFICULT";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "EXIT";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }

    }

    public void drawDifficult(Graphics2D g2) {

        // Background setup
        // MAYBE SETTING IMAGE
        g2.setColor(new Color(3, 75, 34));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(arial_80);
        int x = getXcenter("BOMBERMAN");
        int y = gp.tileSize * 3;

        g2.setColor(Color.WHITE);
        g2.drawString("BOMBERMAN", x, y);

        g2.setColor(Color.GRAY);
        g2.drawString("BOMBERMAN", x + 5, y + 5);

        // IMAGE CHARACTER
        x = gp.screenWidth / 2;
        y += gp.tileSize * 2;
        g2.drawImage(gp.player.down1, x - gp.tileSize, y, gp.tileSize * 2, gp.tileSize * 2, null);

        // OPTIONS
        g2.setFont(arial_40);
        String text;
        text = "EASY";
        x = getXcenter(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "NORMAL";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "HARD";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public void afterFinish(Graphics2D g2) {
        gp.StopMusic();
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(arial_80);
        g2.setColor(Color.YELLOW);
        String text;
        int textLength;
        int x, y;
        if (GameFinish) {
            text = "You WIN!";
        } else {
            text = "You LOSE!";
        }

        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

        x = getXcenter(text);
        y = gp.tileSize * 2;
        g2.drawString(text, x, y);

        playTime = Math.round(playTime * 100.0) / 100.0;
        text = "Your time is: " + playTime + " seconds";
        textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);

        // ASKING
        g2.setFont(arial_80);
        text = "You want to play easy mode?";
        x = getXcenter(text);
        y += gp.tileSize * 4;

        g2.setColor(Color.GRAY);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // OPTIONS
        g2.setFont(arial_40);
        text = "YES";
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "NO";
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }

        text = "QUIT";
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x - gp.tileSize, y);
        }
    }

    public int getXcenter(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
