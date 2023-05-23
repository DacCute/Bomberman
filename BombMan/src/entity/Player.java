package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import bomb.Bomb;
import main.GamePanel;
import main.KeyHandler;
import object.Explode;
import object.Obj_bomb;

public class Player extends Entity {

    protected GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    // public long start = 0;
    // public long end = 0;
    int lives_minus = 0;
    // public long score = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        solidArea = new Rectangle(0, 0, 34, 30);
        solidArea.x = 10;
        solidArea.y = 14;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;

        setDefaultvalues();
        getPlayerImage();
    }

    public void setDefaultvalues() {

        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        lives = 100;
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
            // bomb = ImageIO.read(getClass().getResourceAsStream("/object/bomb.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        boolean monster_check = gp.Colcheck.check_monster(this, true);
        if (monster_check) {
            lives -= 1;
            System.out.println("your remain lives: " + lives);
        }

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
            gp.Colcheck.checkBomb(this, true);

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
                    this.lives += 100;
                    System.out.println("Lives +100");
            }
        }
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
                // if (gp.obj[3] == null) {
                // // gp.bombs[0] = new Obj_bomb();
                // gp.bombs[0].PlantBomb(0);
                // start = System.nanoTime();
                // }

                if (gp.bombs[0] == null) {
                    gp.bombs[0] = new Bomb(gp);
                    gp.bombs[0].PlantBomb(0);
                    gp.bombs[0].start = System.nanoTime();
                }

                break;
        }
        if (gp.bombs[0] != null) {
            // System.out.println("fuck");
            gp.bombs[0].Duration(0);
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // g2.dispose(); // same but stronger than System.exit()

    }

}
