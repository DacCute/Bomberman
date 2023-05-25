package bomb;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GamePanel;

public class Bomb extends Entity {
    public long start, end;
    int clock_counter = 0;
    long wait = 0;
    // KeyHandler keyH;

    public Bomb(GamePanel gp) {
        super(gp);
        this.gp = gp;
        action = "up";
        getBombImage();
    }

    public void getBombImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/bomb/bomb.png"));
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

    // public void Duration(int index) {
    // clock_counter++;
    // System.out.println(clock_counter);
    // if (clock_counter > 90) {
    // gp.playSE(4);
    // Explosion(index);
    // }
    // if (clock_counter > 150) {
    // action = "down";
    // Bomb_remove(index);
    // clock_counter = 0;
    // }
    // }

    public void Duration1(int index) { // DONE
        end = System.nanoTime();
        if (gp.gameState == gp.pauseState) {
            wait = (end - start);
        } else {
            if ((end - wait - start) / 1e9 >= 1.5) {
                gp.playSE(4);
                Explosion(index);
                if ((end - wait - start) / 1e9 >= 2) {
                    action = "down";
                    Bomb_remove(index);
                    start = 0;
                    end = 0;
                }
            }
        }
    }

    public boolean bomb_inside(int obj_x, int obj_y, int bomb_x, int bomb_y) { // DONE
        boolean check = false;

        if (obj_x == bomb_x) {
            for (int i = bomb_y; i >= bomb_y - gp.player.power; --i) {
                if (gp.tileM.MapTileNum[bomb_x][i] != 1) {
                    expanding(bomb_x, i, 0);
                    if (gp.tileM.MapTileNum[bomb_x][i] == 2)
                        gp.tileM.MapTileNum[bomb_x][i] = 0;
                    if (obj_y == i) {
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
        // KILL player
        if (bomb_inside((gp.player.worldX + gp.tileSize / 2) / gp.tileSize,
                (gp.player.worldY + gp.tileSize / 2) / gp.tileSize, x, y)) {
            gp.player.hp -= lives_minus;
            if (gp.player.hp <= 0) {
                gp.player.hp = 0;
                gp.ui.GameOver = true;
            }
        }
        // KILL monster
        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {
                if (bomb_inside((gp.mons[i].worldX + gp.mons[i].solidArea.width) / gp.tileSize,
                        (gp.mons[i].worldY + gp.mons[i].solidArea.width) / gp.tileSize, x, y)) {
                    // gp.ui.showMessage(gp.mons[i].hp + "", i);
                    gp.mons[i].hp -= 1;
                    System.out.println("MONSTER HP: " + gp.mons[i].hp);
                    if (gp.mons[i].hp <= 0) {
                        gp.mons[i] = null;
                    }
                    // gp.player.score += 100;
                }
            }
        }

        boolean monster_count = false;
        for (int i = 0; i < gp.mons.length; ++i) {
            if (gp.mons[i] != null) {
                monster_count = true;
            }
        }
        if (monster_count == false) {
            // GAME FINISHED
            gp.ui.GameFinish = true;
            gp.StopMusic();
            // gp.playSE(); // SOUND CONGRAT
        }

        // Desytroy block
        bomb_inside(x, y, x, y);
    }

    public void Explosion(int index) {
        int x = gp.bombs[index].worldX;
        int y = gp.bombs[index].worldY;
        gp.bombs[index].worldX = x;
        gp.bombs[index].worldY = y;
        x /= gp.tileSize;
        y /= gp.tileSize;

        kill(x, y);

    }

    public void expanding(int x, int y, int index) {

        while (gp.explodes[index] != null) {
            if (gp.explodes[index].worldX / gp.tileSize == x && gp.explodes[index].worldY / gp.tileSize == y) {
                break;
            }
            index += 1;
        }
        gp.explodes[index] = new Explode(gp);

        gp.explodes[index].worldX = x * gp.tileSize;
        gp.explodes[index].worldY = y * gp.tileSize;

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

}
