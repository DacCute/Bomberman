package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import bomb.Bomb;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    protected GamePanel gp;
    public KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int lives_minus = 0;
    // public long score = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

        setDefaultvalues();
    }

    public void setDefaultvalues() {

        solidArea = new Rectangle(0, 0, 34, 30);
        solidArea.x = 10;
        solidArea.y = 14;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 28;
        solidArea.height = 28;

        worldX = gp.tileSize * 1;
        worldY = gp.tileSize * 1;
        hp = 100;
        lives_minus = 1;
        speed = 4;
        power = 1;
        action = "down";
        bomb_count = 1; // Max bomb at the same time

        lives_count = 0;
        speed_count = 0;
        power_count = 0;

        getPlayerImage();

    }

    public void getPlayerImage() {
        up1 = setup(character + "up_1");
        up2 = setup(character + "up_2");
        down1 = setup(character + "down_1");
        down2 = setup(character + "down_2");
        left1 = setup(character + "left_1");
        left2 = setup(character + "left_2");
        right1 = setup(character + "right_1");
        right2 = setup(character + "right_2");

    }

    public void update() {
        // gp.playSE(1);
        boolean monster_check = gp.Colcheck.check_monster(this, true);
        if (monster_check) {
            hp -= monster_atk;
            if (gp.player.hp <= 0) {
                gp.player.hp = 0;
                gp.ui.GameOver = true;
            }
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

            if (objectIndex < 3) {
                pickItem(objectIndex);
                gp.playSE(8);
            }

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
                    break;
                case 1:
                    this.speed += 2;
                    break;
                case 2:
                    this.hp += 40;
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
                switch (bomb_count) {
                    case 1:
                        if (gp.bombs[0] == null) {
                            gp.bombs[0] = new Bomb(gp);
                            gp.bombs[0].PlantBomb(0);
                            gp.bombs[0].start = System.nanoTime();
                        }
                        break;

                    case 2:
                        if (gp.bombs[0] == null) {
                            gp.bombs[0] = new Bomb(gp);
                            gp.bombs[0].PlantBomb(0);
                            gp.bombs[0].start = System.nanoTime();
                        } else {
                            if (gp.bombs[1] == null) {
                                gp.bombs[1] = new Bomb(gp);
                                gp.bombs[1].PlantBomb(1);
                                if (gp.bombs[1].worldX == gp.bombs[0].worldX
                                        && gp.bombs[1].worldY == gp.bombs[0].worldY) {
                                    gp.bombs[1] = null;
                                } else
                                    gp.bombs[1].start = System.nanoTime();
                            }
                        }
                        break;
                }
                action = "";
                break;
        }
        for (int i = 0; i < bomb_count; ++i) {
            if (gp.bombs[i] != null) {
                gp.bombs[i].Duration1(i);
            }
        }

        int x = screenX;
        int y = screenY;

        // if (screenX > worldX) {
        // x = worldX;
        // }
        // if (screenY > worldY) {
        // y = worldY;
        // }

        // int RightOffset = gp.screenWidth - screenX;
        // if (RightOffset > gp.worldWidth - worldX) {
        // x = gp.screenWidth - (gp.worldWidth - worldX);
        // }
        // int BottomOffset = gp.screenHeight - screenY;
        // if (BottomOffset > gp.worldHeight - worldY) {
        // y = gp.screenHeight - (gp.worldHeight - worldY);
        // }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

        // g2.dispose(); // same but stronger than System.exit()

    }

}
