package boom;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;


import javax.imageio.ImageIO;
import javax.swing.Timer;

import Entity.Entity;
import main.GamePanel;

public class boomit extends Entity {
	public int x;
	public int y;	
	public BufferedImage image;
	public int elapsedTime;
	public boolean ExplosionTime =false;
	
	public boomit(GamePanel gp) {
		super(gp);
		
			
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/boom/boom 2.png"));
			
				
			
		}catch(IOException e){
			e.printStackTrace(); 
		}
	}
	
	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
		
	}

	}

	



