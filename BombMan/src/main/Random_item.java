package main;

import java.util.Random;

import object.Obj_Power;
import object.Obj_Speed;
import object.Obj_lives;

public class Random_item {
    GamePanel gp;
    public int rate = 10;
    public int order;

    public Random_item(GamePanel gp) {
        this.gp = gp;
    }

    public int random_item(int index, int x, int y) {
        if (index == 2) {
            Random random = new Random();
            order = random.nextInt(Math.abs(rate));
            switch (order) {
                case 0: {
                    gp.obj[0] = new Obj_Power(gp); // Increase bomb scale
                    gp.obj[0].worldX = x * gp.tileSize;
                    gp.obj[0].worldY = y * gp.tileSize;
                }
                    break;
                case 1: {
                    gp.obj[1] = new Obj_Speed(gp); // Increase speed running
                    gp.obj[1].worldX = x * gp.tileSize;
                    gp.obj[1].worldY = y * gp.tileSize;
                }
                    break;
                case 2: {
                    gp.obj[2] = new Obj_lives(gp); // Increase lives
                    gp.obj[2].worldX = x * gp.tileSize;
                    gp.obj[2].worldY = y * gp.tileSize;
                }
                    break;
            }
        } else
            order = 999;
        return order;
    }

}
