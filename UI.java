package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import objects.OBJ_Heart;
import objects.OBJ_Key;
import objects.SuperClass;

public class UI {
	GamePanel gp;
	Font arial_40;
	BufferedImage keyImage;
	BufferedImage heart_red, heart_blank ;
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		
		arial_40 = new Font("Arial", Font.PLAIN, 30);
		OBJ_Key key = new OBJ_Key();
		keyImage = key.image;
		
		SuperClass heart = new OBJ_Heart(gp);
		heart_red = heart.image;
		heart_blank = heart.image1;
		
		
	}
	
	void drawPlayerLife(Graphics2D g2) {
		int x =  gp.tileSize/2;
		int y = gp.tileSize * 2;
		int i = 0;
		
		while (i < gp.player.maxLife) {
			g2.drawImage(heart_blank, x ,y,2 * gp.tileSize /3, 2 *gp.tileSize /3, null);
			
			i++;
			x += gp.tileSize;
			
			
		}
			
			// Reset
			 x =  gp.tileSize/2;
			 y = gp.tileSize * 2;
			 i = 0;
		while (i < gp.player.life) {
				g2.drawImage(heart_red, x - 3, y - 4, gp.tileSize, gp.tileSize,null);
				
				 i++;
				 x += gp.tileSize;
				
		}
		
		
		
	}

	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Arial ",Font.PLAIN, 30 ));
		g2.setColor(Color.white);
		g2.drawImage(keyImage, gp.tileSize/2 + 3,gp.tileSize/2 + 7 ,gp.tileSize, gp.tileSize, null);
		g2.drawString("x " + gp.player.hasKey, 50, 50);
		
		
	}
	

}
