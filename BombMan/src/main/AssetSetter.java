package main;

import java.util.Random;

import entity.Monster;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        // SETIING BEFORE START GAME
    }

    public int Random_x() {
        Random random = new Random();
        int x;
        x = random.nextInt(27);
        return x;
    }

    public int Random_y() {
        Random random = new Random();
        int y;
        y = random.nextInt(12);
        return y;
    }

    public void setMon() {
        int x;
        int y;

        for (int i = 0; i < 3; ++i) {
            x = Random_x();
            y = Random_y();
            while (gp.tileM.MapTileNum[x][y] != 0) {
                x = Random_x();
                y = Random_y();
            }
            gp.mons[i] = new Monster(gp);
            gp.mons[i].worldX = gp.tileSize * x;
            gp.mons[i].worldY = gp.tileSize * y;
        }

    }
}
