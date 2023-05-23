package bomb;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Bomb extends Entity {
    // GamePanel gp;
    public int status = 0;
    // 0 = plant bomb
    // 1 = explosion
    // 2 = remove bomb
    public long start;
    public long end;

    public Bomb(GamePanel gp) {
        super(gp);
        this.gp = gp;
        action = "up";
        getBombImage();
    }

    public void getBombImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/bomb/explode.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/bomb/explode.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void PlantBomb(int index) { // DONE
        // gp.bombs[index] = new Bomb(gp);
        double x = (double) (gp.player.worldX - gp.tileSize / 2) / gp.tileSize;
        double y = (double) (gp.player.worldY - gp.tileSize / 2) / gp.tileSize;
        gp.bombs[index].worldX = (int) (Math.ceil(x) * gp.tileSize);
        gp.bombs[index].worldY = (int) (Math.ceil(y) * gp.tileSize);
    }

    public void Duration(int index) { // DONE
        if (start > 0) {

            gp.bombs[index].status = 0;
            end = System.nanoTime();
        }
        if ((end - start) / 1e9 >= 1.5) {
            gp.playSE(4);

            gp.bombs[index].status = 2;
            Explosion(index);

            if ((end - start) / 1e9 >= 2) {
                action = "down";
                Bomb_remove(index);
                start = 0;
                end = 0;
                // gp.StopMusic();
            }
        }

    }

    public boolean bomb_inside(int obj_x, int obj_y, int bomb_x, int bomb_y) { // DONE
        // expanding(bomb_x, bomb_y, 0);
        boolean check = false;

        if (obj_x == bomb_x) {
            for (int i = bomb_y; i >= bomb_y - gp.player.power; --i) {
                if (gp.tileM.MapTileNum[bomb_x][i] != 1) {
                    expanding(bomb_x, i, 0);
                    if (gp.tileM.MapTileNum[bomb_x][i] == 2)
                        gp.tileM.MapTileNum[bomb_x][i] = 0;
                    if (obj_y == i) {
                        // System.out.println("x > y");
                        check = true;
                    }
                } else
                    break;
            }
            for (int i = bomb_y; i <= bomb_y + gp.player.power; ++i) {
                if (gp.tileM.MapTileNum[bomb_x][i] != 1) {
                    expanding(bomb_x, i, 0);
                    if (gp.tileM.MapTileNum[bomb_x][i] == 2)
                        gp.tileM.MapTileNum[bomb_x][i] = 0;
                    if (obj_y == i) {
                        // System.out.println("x < y");
                        check = true;
                    }
                } else
                    break;
            }
        }
        if (obj_y == bomb_y) {
            for (int i = bomb_x; i >= bomb_x - gp.player.power; --i) {
                if (gp.tileM.MapTileNum[i][bomb_y] != 1) {
                    expanding(i, bomb_y, 0);
                    if (gp.tileM.MapTileNum[i][bomb_y] == 2)
                        gp.tileM.MapTileNum[i][bomb_y] = 0;
                    if (obj_x == i) {
                        // System.out.println("y > x");
                        check = true;
                    }
                } else
                    break;
            }
            for (int i = bomb_x; i <= bomb_x + gp.player.power; ++i) {
                if (gp.tileM.MapTileNum[i][bomb_y] != 1) {
                    expanding(i, bomb_y, 0);
                    if (gp.tileM.MapTileNum[i][bomb_y] == 2)
                        gp.tileM.MapTileNum[i][bomb_y] = 0;
                    if (obj_x == i) {
                        // System.out.println("y < x");
                        // System.out.println(obj_x + " " + obj_y + " " + bomb_x + " " + bomb_y);
                        check = true;
                    }
                } else
                    break;
            }
        }
        // System.out.println(check);
        return check;
    }

    public void kill(int x, int y) { // DONE

        if (bomb_inside((gp.player.worldX + gp.tileSize / 2) / gp.tileSize,
                (gp.player.worldY + gp.tileSize / 2) / gp.tileSize, x, y)) {
            gp.player.lives -= 1;
            System.out.println("Your lives remain: " + gp.player.lives);
            if (gp.player.lives == 0) {
                System.out.println("Game over");
            }
        }

        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {
                if (bomb_inside((gp.mons[i].worldX + gp.mons[i].solidArea.width) / gp.tileSize,
                        (gp.mons[i].worldY + gp.mons[i].solidArea.width) / gp.tileSize, x, y)) {
                    System.out.println("You got him");
                    gp.mons[i] = null;
                    gp.player.score += 100;
                }
            }
        }
        bomb_inside(x, y, x, y);
    }

    public void Explosion(int index) {
        int x = gp.bombs[index].worldX;
        int y = gp.bombs[index].worldY;
        gp.bombs[index].status = 1;
        gp.bombs[index].worldX = x;
        gp.bombs[index].worldY = y;
        x /= gp.tileSize;
        y /= gp.tileSize;

        kill(x, y);

    }

    public void expanding(int x, int y, int index) {
        action = "down";
        // gp.bombs[index].status = 2;
        while (gp.explodes[index] != null) {
            if (gp.explodes[index].worldX / gp.tileSize == x && gp.explodes[index].worldY / gp.tileSize == y) {
                break;
            }
            index += 1;
        }
        gp.explodes[index] = new Explode();

        gp.explodes[index].worldX = x * gp.tileSize;
        gp.explodes[index].worldY = y * gp.tileSize;

        // System.out.println(gp.explodes[0].worldX + " and " + gp.explodes[0].worldY);

        // AFTER break the brick
        int gacha_result = gp.gacha.random_item(gp.tileM.MapTileNum[x][y], x, y);
        switch (gacha_result) {
            case 0:
                if (gp.player.power_count == 0) {
                    gp.player.power_count = 1;
                } else {
                    gp.obj[0] = null;
                }
                break;
            case 1:
                if (gp.player.speed_count == 0) {
                    gp.player.speed_count = 1;
                } else {
                    gp.obj[1] = null;
                }
                break;
            case 2:
                if (gp.player.lives_count == 0) {
                    gp.player.lives_count = 1;
                } else {
                    gp.obj[2] = null;
                }
                break;
        }
    }

    public void Bomb_remove(int index) {
        gp.bombs[index] = null;
        for (int i = 0; i < gp.explodes.length; i++) {
            if (gp.explodes[i] != null) {
                gp.explodes[i] = null;
            }
        }
        for (int i = 1; i <= power; ++i) {
            if (gp.bombs[index] != null) {
                gp.bombs[index] = null;
            }
        }
    }

    public void setAction() {
        System.out.println("FUCK");
        for (int i = 0; i < gp.bombs.length; ++i) {
            switch (gp.bombs[i].status) {
                case 0:
                    action = "up";
                    break;
                case 1:
                    action = "down";
                    break;
                case 2:
                    action = "down";
                    break;
            }
        }

    }

}
