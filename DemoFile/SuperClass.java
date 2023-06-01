package objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperClass {
	public BufferedImage image, image1;
	public String name;
	public boolean Collision = false;
	public int x;
	public int y;
	public Rectangle solidArea = new Rectangle(0,0,32,32);
	public int solidAreaDefaultX = 0;
	public int solidAreaDeFaultY = 0;
	
	
	public void draw(Graphics2D g2, GamePanel gp) {
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
	}

}
