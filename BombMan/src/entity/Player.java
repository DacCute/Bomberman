package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import object.Explode;
import object.Obj_bomb;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public long start = 0;
    public long end = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(0, 0, 20, 24);
        solidArea.x = 14;
        solidArea.y = 18;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 20;
        solidArea.height = 24;

        setDefaultvalues();
        getPlayerImage();
    }

    public void setDefaultvalues() {

        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        speed = 4;
        power = 1;
        action = "down";
        // index = 3; // Max bomb at the same time
        getPlayerImage();
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right_2.png"));
            bomb = ImageIO.read(getClass().getResourceAsStream("/object/bomb.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed || keyH.spaceBomb) {
            if (keyH.upPressed == true) {
                action = "up";
            } else if (keyH.downPressed == true) {
                action = "down";
            } else if (keyH.leftPressed == true) {
                action = "left";
            } else if (keyH.rightPressed == true) {
                action = "right";
            } else if (keyH.spaceBomb == true) {
                action = "bomb";
            }

            // Check the collision
            collisionOn = false;
            gp.Colcheck.checkTile(this);

            // CHeck Object collision
            int objectIndex = gp.Colcheck.checkObject(this, true);
            if (objectIndex < 3)
                pickItem(objectIndex);

            // Action after check
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
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickItem(int index) {
        if (index != 999) {
            gp.obj[index] = null;
            switch (index) {
                case 0:
                    this.power += 1;
                    System.out.println("Power +1");
                    break;
                case 1:
                    this.speed += 2;
                    System.out.println("Speed +1");
                    break;
                case 2:
                    this.lives += 1;
                    System.out.println("Lives +1");
            }
        }
    }

    public void PlantBomb() {
        gp.obj[3] = new Obj_bomb();
        double x = (double) (gp.player.worldX - 24) / 48;
        double y = (double) (gp.player.worldY - 24) / 48;
        gp.obj[3].worldX = (int) (Math.ceil(x) * 48);
        gp.obj[3].worldY = (int) (Math.ceil(y) * 48);

    }

    public void Explosion() {
        int x = gp.obj[3].worldX;
        int y = gp.obj[3].worldY;
        gp.obj[3] = new Explode();
        gp.obj[3].worldX = x;
        gp.obj[3].worldY = y;
    }

    public void Bomb_remove() {
        gp.obj[3] = null;
    }

    BufferedImage image = null;

    public void draw(Graphics2D g2) {

        switch (action) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                } else
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                } else
                    image = down2;
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                } else
                    image = left2;
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                } else
                    image = right2;
                break;
            case "bomb":
                if (gp.obj[3] == null) {
                    start = System.nanoTime();
                    PlantBomb();
                }
                break;
        }
        if (start > 0) {
            end = System.nanoTime();
        }
        if ((end - start) / 1e9 >= 3) {
            Explosion();
            if ((end - start) / 1e9 >= 4) {
                Bomb_remove();
                start = 0;
                end = 0;
            }
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.dispose(); // same but stronger than System.exit()

    }

}
