package main;

import java.io.IOException;

import javax.imageio.ImageIO;

import Mon_green.MON_Green;
import objects.OBJ_Door;
import objects.OBJ_Heart;
import objects.OBJ_Key;

public class AssetSetter {
	
	GamePanel gp;
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
		
	}
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].x = gp.tileSize * 22;
		gp.obj[0].y = gp.tileSize * 1;
		
		
		gp.obj[1]= new OBJ_Key();
		gp.obj[1].x = gp.tileSize * 2;
		gp.obj[1].y = gp.tileSize * 17;
		
			
		gp.obj[2]= new OBJ_Door();
		gp.obj[2].x = gp.tileSize * 17;
		gp.obj[2].y = gp.tileSize * 15;
		
		
	

	}
	public void setMon() {
		gp.mons[0] = new MON_Green(gp);
		gp.mons[0].x = gp.tileSize*4;
		gp.mons[0].y = gp.tileSize*6;
		
		gp.mons[1] = new MON_Green(gp);
		gp.mons[1].x = gp.tileSize*14;
		gp.mons[1].y = gp.tileSize * 10;
		
		
		gp.mons[2] = new MON_Green(gp);
		gp.mons[2].x = gp.tileSize* 20;
		gp.mons[2].y = gp.tileSize * 15;
	}
	
	

}
