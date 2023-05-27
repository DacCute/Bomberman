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
    private Font atari_200;
    private Font arial_80;
    private Font arial_60;
    private Font arial_40;
    private Font arial_20;
    // private Font arial_15;
    private BufferedImage boy;
    private BufferedImage girl;
    private BufferedImage adudu;
    private BufferedImage speed_Image;
    private BufferedImage lives_Image;
    private BufferedImage power_Image;
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

        atari_200 = new Font("SansSerif", Font.BOLD, 160);
        arial_80 = new Font("Arial", Font.PLAIN, 80);
        arial_60 = new Font("Arial", Font.PLAIN, 60);
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_20 = new Font("Arial", Font.BOLD, 20);
        // arial_15 = new Font("Arial", Font.PLAIN, 15);

        Obj_lives lives = new Obj_lives(gp);
        lives_Image = lives.image1;
        Obj_Speed speed = new Obj_Speed(gp);
        speed_Image = speed.image1;
        Obj_Power power = new Obj_Power(gp);
        power_Image = power.image1;
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
                drawStatus(g2);
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
                selectSkin(g2);
                break;
        }

        if (GameFinish || GameOver) {
            gp.gameState = gp.ENDGAME;
        } else {
            playTime += (double) 1 / 60;
        }

    }

    public void selectSkin(Graphics2D g2) {
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.white);
        g2.setFont(arial_40);
        int x = getXcenter("CHOOSING CHARACTER");
        int y = gp.tileSize * 3;
        g2.drawString("CHOOSING CHARACTER", x, y);
        getTitleImage();

        String text;
        text = "BOY";
        g2.setFont(arial_20);
        x = gp.tileSize + 24;
        y += 3 * gp.tileSize;
        int scale = 4 * gp.tileSize;
        g2.drawImage(boy, x, y, scale, scale, null);
        g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);

        if (commandNum == 0) {
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
            g2.drawImage(pointer, x, y + scale + 20, gp.tileSize - 10, gp.tileSize - 10, null);

        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
        }

        text = "GIRL";
        x += 4 * gp.tileSize + scale;
        g2.drawImage(girl, x, y, scale, scale, null);
        g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
        if (commandNum == 1) {
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
            g2.drawImage(pointer, x, y + scale + 20, gp.tileSize - 10, gp.tileSize - 10, null);

        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
        }

        text = "ADUDU";
        x += 4 * gp.tileSize + scale;
        g2.drawImage(adudu, x, y, scale, scale, null);
        g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
        if (commandNum == 2) {
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
            g2.drawImage(pointer, x, y + scale + 20, gp.tileSize - 10, gp.tileSize - 10, null);

        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x + gp.tileSize, y + scale + gp.tileSize);
        }
    }

    public void drawStatus(Graphics2D g2) {
        g2.setColor(new Color(94, 138, 113));
        g2.fillRect(0, 0, gp.screenWidth, gp.tileSize * 4);

        g2.setColor(new Color(254, 105, 63));
        g2.fillRect(15, 24, 180, gp.tileSize * 3);

        g2.fillRect(210, 24, 280, gp.tileSize * 3);
        g2.fillRect(505, 24, 280, gp.tileSize * 3);
        g2.fillRect(800, 24, 280, gp.tileSize * 3);

        g2.setFont(arial_20);
        g2.setColor(Color.white);

        int x = gp.tileSize / 2;
        int y = gp.tileSize / 5 * 4;
        int size = gp.tileSize / 2;

        g2.drawImage(lives_Image, x, y, size, size, null);
        g2.drawString(" x " + gp.player.hp, gp.tileSize, gp.tileSize / 4 * 5);

        int x_mons = x + gp.tileSize * 8;
        int y_mons = y;
        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {
                g2.drawImage(gp.mons[i].up1, x_mons - 3 * gp.tileSize, y + 12, gp.tileSize * 2, gp.tileSize * 2, null);
                g2.drawImage(lives_Image, x_mons, y_mons, size, size, null);
                g2.drawString(" x " + gp.mons[i].hp, x_mons + 24, gp.tileSize / 4 * 5);
                x_mons += 295;
            }
        }

        y = gp.tileSize / 5 * 3 + gp.tileSize + 8;
        g2.drawImage(speed_Image, x, y, size, size, null);
        g2.drawString(" x " + gp.player.speed, gp.tileSize, (gp.tileSize * 2) + 5);

        y += gp.tileSize - 5;
        g2.drawImage(power_Image, x, y, size, size, null);
        g2.drawString(" x " + gp.player.power, gp.tileSize, (gp.tileSize * 3));

    }

    public void drawPauseScreen(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        String text;
        int x = gp.screenWidth / 2 - 3 * gp.tileSize;
        int y = gp.screenHeight / 2 - 4 * gp.tileSize;

        g2.setColor(new Color(255, 255, 255, 150));
        g2.fillRect(x - 5, y - 5, 6 * gp.tileSize + 10, 8 * gp.tileSize + 10);
        g2.setColor(new Color(20, 21, 23, 150));
        g2.fillRect(x, y, 6 * gp.tileSize, 8 * gp.tileSize);

        text = "PAUSE";
        g2.setFont(arial_60);
        g2.setColor(Color.white);
        x = getXcenter(text);
        y = y + 2 * gp.tileSize;
        g2.drawString(text, x, y);

        g2.setColor(new Color(255, 255, 255, 150));
        x = gp.screenWidth / 2 - 3 * gp.tileSize + 10;
        y += gp.tileSize;
        g2.fillRect(x, y, 6 * gp.tileSize - 20, 4 * gp.tileSize + 34);
        x += 10;
        y += 10;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(x, y, 6 * gp.tileSize - 40, 4 * gp.tileSize + 14);

        text = "Retry";
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        x = getXcenter(text);
        y += 2 * gp.tileSize - 50;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize - 10, gp.tileSize - 10, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "Go back";
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        x = getXcenter(text);
        y += 1 * gp.tileSize + 15;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize - 10, gp.tileSize - 10, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "Quit";
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        x = getXcenter(text);
        y += 1 * gp.tileSize + 15;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

    }

    public void drawTileScreen(Graphics2D g2) {

        // Background setup
        // MAYBE SETTING IMAGE
        getTitleImage();

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(atari_200);
        int x = getXcenter("BOMBERMAN");
        int y = gp.tileSize * 5;

        g2.setColor(Color.GRAY);
        g2.drawString("BOMBERMAN", x + 10, y + 10);
        g2.setColor(Color.GRAY);
        g2.drawString("BOMBERMAN", x + 5, y + 5);

        g2.setColor(Color.YELLOW);
        g2.drawString("BOMBERMAN", x, y);

        // OPTIONS
        g2.setColor(Color.white);
        g2.setFont(arial_40);
        String text;
        text = "NEW GAME";
        x = getXcenter(text);
        y += gp.tileSize * 4;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "CHARACTER";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "EXIT";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

    }

    public void getTitleImage() {
        titleNameImage = setup("gameTitle");
        playButton = setup("playButton");
        quitButton = setup("quitButton");
        pointer = setup("select");

        boy = gp.player.setup("boy/down_1");
        girl = gp.player.setup("girl/down_1");
        adudu = gp.player.setup("adudu/down_1");

    }

    public void drawDifficult(Graphics2D g2) {

        // Background setup
        getTitleImage();
        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        // Title name
        g2.setFont(arial_80);
        int x = getXcenter("BOMBERMAN");
        int y = gp.tileSize * 3;

        g2.setColor(Color.GRAY);
        g2.drawString("BOMBERMAN", x + 5, y + 5);

        g2.setColor(Color.YELLOW);
        g2.drawString("BOMBERMAN", x, y);

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
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "NORMAL";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "HARD";
        x = getXcenter(text);
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }
    }

    public void afterFinish(Graphics2D g2) {
        gp.StopMusic();

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setFont(arial_80);
        g2.setColor(Color.YELLOW);
        String text;
        int x, y;
        if (GameFinish) {
            text = "You WIN!";

        } else {
            text = "You LOSE!";
        }

        x = getXcenter(text);
        y = gp.tileSize * 2;
        g2.drawString(text, x, y);

        playTime = Math.round(playTime * 100.0) / 100.0;
        text = "Your time is: " + playTime + " seconds";

        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);

        // ASKING
        g2.setFont(arial_80);
        text = "You want to play again?";
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
        y += gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "NO";
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }

        text = "QUIT";
        x = getXcenter(text);
        y += gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.setColor(Color.gray);
            g2.drawString(text, x + 2, y + 2);
            g2.setColor(new Color(255, 89, 64));
            g2.drawString(text, x, y);

            g2.drawImage(pointer, x - gp.tileSize * 2, y - gp.tileSize + 10, gp.tileSize, gp.tileSize, null);
        } else {
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
        }
    }

    public int getXcenter(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
}
