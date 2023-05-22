package main;

import entity.Monster;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        // gp.obj[0] = new Obj_Power(); // Increase bomb scale
        // gp.obj[0].worldX = 11 * gp.tileSize;
        // gp.obj[0].worldY = 6 * gp.tileSize;

        // gp.obj[1] = new Obj_Speed(); // Increase speed running
        // gp.obj[1].worldX = 13 * gp.tileSize;
        // gp.obj[1].worldY = 8 * gp.tileSize;

        // gp.obj[2] = new Obj_lives(); // Increase lives
        // gp.obj[2].worldX = 2 * gp.tileSize;
        // gp.obj[2].worldY = 4 * gp.tileSize;

    }

    public void setMon() {
        gp.mons[0] = new Monster(gp);
        gp.mons[0].worldX = gp.tileSize * 3;
        gp.mons[0].worldY = gp.tileSize * 4;

        gp.mons[1] = new Monster(gp);
        gp.mons[1].worldX = gp.tileSize * 5;
        gp.mons[1].worldY = gp.tileSize * 3;

    }
}
