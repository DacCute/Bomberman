package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.Obj_Power;
import object.Obj_Speed;
import object.Obj_lives;

public class UI {

    GamePanel gp;
    Font arial_10;
    BufferedImage speed_Image;
    BufferedImage lives_Image;
    BufferedImage power_Image;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_10 = new Font("Arial", Font.PLAIN, 20);
        Obj_lives lives = new Obj_lives();
        lives_Image = lives.image;
        Obj_Speed speed = new Obj_Speed();
        speed_Image = speed.image;
        Obj_Power power = new Obj_Power();
        power_Image = power.image;
    }

    public void draw(Graphics2D g2) {

        g2.setFont(arial_10);
        g2.setColor(Color.white);

        g2.drawImage(lives_Image, gp.tileSize / 2, gp.tileSize / 5 * 4, gp.tileSize / 2, gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.lives, gp.tileSize, gp.tileSize / 4 * 5);

        g2.drawImage(speed_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize, gp.tileSize / 2,
                gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.speed, gp.tileSize, (gp.tileSize * 2));

        g2.drawImage(power_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize * 2, gp.tileSize / 2,
                gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.power, gp.tileSize, (gp.tileSize * 3));

    }

}
