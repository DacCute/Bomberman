package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class Entity {
    GamePanel gp;

    public int worldX, worldY;
    public int speed = 1;
    public int power;
    public int lives;
    // public int index;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, bomb, boom;
    public String action;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public Rectangle solidArea;
    // public int x, y;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int actionLockCounter = 0;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX
                && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX
                && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY
                && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

            switch (action) {

                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }

                    break;
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }

    public void setAction() {
    }

    public void update() {

        setAction();
        // Check the collision
        collisionOn = false;
        gp.Colcheck.checkTile(this);

        // CHeck Object collision
        int objectIndex = gp.Colcheck.checkObject(this, false);

        if (collisionOn == false) {

            switch (action) {
                case "up":
                    worldY -= speed;

                    break;
                case "down":
                    worldY += speed;

                    break;
                case "left":
                    worldX -= speed;

                    break;
                case "right":
                    worldX += speed;

                    break;

            }
        }

        spriteCounter++;
        if (spriteCounter > 20) {
            if (spriteNum == 1) {
                spriteNum = 2;

            } else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

}
