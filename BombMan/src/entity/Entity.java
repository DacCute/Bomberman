package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

    // public int x, y;
    public int worldX, worldY;
    public int speed;
    public int power;
    public int lives;
    public int index;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, bomb, boom;
    public String action;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}
