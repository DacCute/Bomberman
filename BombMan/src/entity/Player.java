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

    protected GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public long start = 0;
    public long end = 0;
    int lives_minus = 0;
    public long score = 0;

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

    public void PlantBomb() {
        gp.obj[3] = new Obj_bomb();
        double x = (double) (gp.player.worldX - gp.tileSize / 2) / gp.tileSize;
        double y = (double) (gp.player.worldY - gp.tileSize / 2) / gp.tileSize;
        gp.obj[3].worldX = (int) (Math.ceil(x) * gp.tileSize);
        gp.obj[3].worldY = (int) (Math.ceil(y) * gp.tileSize);
    }

    public void Duration() {
        if (start > 0) {
            end = System.nanoTime();
        }
        if ((end - start) / 1e9 >= 1.5) {
            Explosion();
            if ((end - start) / 1e9 >= 2) {
                Bomb_remove();
                start = 0;
                end = 0;
            }
        }

    }

    public boolean bomb_inside(int obj_x, int obj_y, int bomb_x, int bomb_y) {
        boolean check = false;

        if (obj_x == bomb_x) {
            for (int i = bomb_y; i >= bomb_y - power; --i) {
                if (gp.tileM.MapTileNum[bomb_x][i] != 1) {
                    if (obj_y == i) {
                        check = true;
                    }
                } else
                    break;
            }
            for (int i = bomb_y; i <= bomb_y + power; ++i) {
                if (gp.tileM.MapTileNum[bomb_x][i] != 1) {
                    if (obj_y == i) {
                        check = true;
                    }
                } else
                    break;
            }
        } else if (obj_y == bomb_y) {
            for (int i = bomb_x; i >= bomb_x - power; --i) {
                if (gp.tileM.MapTileNum[i][bomb_y] != 1) {
                    if (obj_x == i) {
                        check = true;
                    }
                } else
                    break;
            }
            for (int i = bomb_x; i <= bomb_x + power; ++i) {
                if (gp.tileM.MapTileNum[i][bomb_y] != 1) {
                    if (obj_x == i) {
                        check = true;
                    }
                } else
                    break;
            }
        }

        return check;
    }

    public void kill(int x, int y) {

        if (bomb_inside(gp.player.worldX / gp.tileSize, (gp.player.worldY + 30) / gp.tileSize, x, y)) {
            gp.player.lives -= 1;
            System.out.println("Your lives remain: " + gp.player.lives);
            if (gp.player.lives == 0) {
                System.out.println("Game over");
            }
        }

        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {
                if (bomb_inside(gp.mons[i].worldX / gp.tileSize, gp.mons[i].worldY / gp.tileSize, x, y)) {
                    System.out.println("You got him");
                    gp.mons[i] = null;
                    score += 100;
                }
            }
        }
    }

    public void Explosion_test() {
        int x = gp.obj[3].worldX;
        int y = gp.obj[3].worldY;
        gp.obj[3] = new Explode();
        gp.obj[3].worldX = x;
        gp.obj[3].worldY = y;
        x /= gp.tileSize;
        y /= gp.tileSize;

        for (int i = 1; i <= power; ++i) {
            // Explosion Right
            if (x + i < gp.maxWorldCol)
                if (gp.tileM.MapTileNum[x + i][y] != 1 && gp.tileM.MapTileNum[x + i - 1][y] != 1) {
                    gp.tileM.MapTileNum[x + i][y] = 0;

                    expanding(x, y, i, i, 0);
                }
            // Explosion Left
            if (x - i > 0)
                if (gp.tileM.MapTileNum[x - i][y] != 1 && gp.tileM.MapTileNum[x - i + 1][y] != 1) {
                    gp.tileM.MapTileNum[x - i][y] = 0;

                    expanding(x, y, i + 2, -i, 0);
                }
            // Explosion Down
            if (y + i < gp.maxWorldRow)
                if (gp.tileM.MapTileNum[x][y + i] != 1 && gp.tileM.MapTileNum[x][y + i - 1] != 1) {
                    gp.tileM.MapTileNum[x][y + i] = 0;

                    expanding(x, y, i + 4, 0, i);
                }
            // Explosion Up
            if (y - i > 0)
                if (gp.tileM.MapTileNum[x][y - i] != 1 && gp.tileM.MapTileNum[x][y - i + 1] != 1) {
                    gp.tileM.MapTileNum[x][y - i] = 0;

                    expanding(x, y, i + 6, 0, -i);
                }
        }

        kill(x, y);
    }

    public void Explosion() {
        int x = gp.obj[3].worldX;
        int y = gp.obj[3].worldY;
        gp.obj[3] = new Explode();
        gp.obj[3].worldX = x;
        gp.obj[3].worldY = y;
        x /= gp.tileSize;
        y /= gp.tileSize;

        kill(x, y);

        for (int i = 1; i <= power; ++i) {

            // Explosion Right
            if (x + i < gp.maxWorldCol)
                if (gp.tileM.MapTileNum[x + i][y] != 1 && gp.tileM.MapTileNum[x + i - 1][y] != 1) {
                    gp.tileM.MapTileNum[x + i][y] = 0;

                    expanding(x, y, i, i, 0);
                }
            // Explosion Left
            if (x - i > 0)
                if (gp.tileM.MapTileNum[x - i][y] != 1 && gp.tileM.MapTileNum[x - i + 1][y] != 1) {
                    gp.tileM.MapTileNum[x - i][y] = 0;

                    expanding(x, y, i + 2, -i, 0);
                }
            // Explosion Down
            if (y + i < gp.maxWorldRow)
                if (gp.tileM.MapTileNum[x][y + i] != 1 && gp.tileM.MapTileNum[x][y + i - 1] != 1) {
                    gp.tileM.MapTileNum[x][y + i] = 0;

                    expanding(x, y, i + 4, 0, i);
                }
            // Explosion Up
            if (y - i > 0)
                if (gp.tileM.MapTileNum[x][y - i] != 1 && gp.tileM.MapTileNum[x][y - i + 1] != 1) {
                    gp.tileM.MapTileNum[x][y - i] = 0;

                    expanding(x, y, i + 6, 0, -i);
                }
        }
    }

    public void expanding(int x, int y, int i, int xi, int yi) {
        gp.obj[3 + i] = new Explode();
        gp.obj[3 + i].worldX = (x + xi) * gp.tileSize;
        gp.obj[3 + i].worldY = (y + yi) * gp.tileSize;
    }

    public void Bomb_remove() {
        gp.obj[3] = null;
        for (int i = 1; i <= power; ++i) {
            if (gp.obj[3 + i] != null) {
                gp.obj[3 + i] = null;
            }
            if (gp.obj[3 + i + 2] != null) {
                gp.obj[3 + i + 2] = null;
            }
            if (gp.obj[3 + i + 4] != null) {
                gp.obj[3 + i + 4] = null;
            }
            if (gp.obj[3 + i + 6] != null) {
                gp.obj[3 + i + 6] = null;
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
                if (gp.obj[3] == null) {
                    PlantBomb();
                    start = System.nanoTime();
                }
                break;
        }
        Duration();

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        // g2.dispose(); // same but stronger than System.exit()

    }

}
