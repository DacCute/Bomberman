package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.Instant;

import object.Explode;
import object.Obj_Power;
import object.Obj_Speed;
import object.Obj_bomb;
import object.Obj_lives;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new Obj_Power(); // Increase bomb scale
        gp.obj[0].worldX = 11 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[1] = new Obj_Speed(); // Increase speed running
        gp.obj[1].worldX = 13 * gp.tileSize;
        gp.obj[1].worldY = 8 * gp.tileSize;

        gp.obj[2] = new Obj_lives(); // Increase lives
        gp.obj[2].worldX = 2 * gp.tileSize;
        gp.obj[2].worldY = 4 * gp.tileSize;

    }

    public void PlantBomb() {
        gp.obj[3] = new Obj_bomb();
        double x = (double) (gp.player.worldX - 24) / 48;
        double y = (double) (gp.player.worldY - 24) / 48;
        gp.obj[3].worldX = (int) (Math.ceil(x) * 48);
        gp.obj[3].worldY = (int) (Math.ceil(y) * 48);
    }

}
