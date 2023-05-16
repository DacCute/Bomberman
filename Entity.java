package Entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;



import main.GamePanel;
//import main.UtilityTool;

public class Entity {
	GamePanel gp;
	public String name;
	

	public int x, y;
	public int speed = 1;
	public int solidAreaDefaultX, solidAreaDefaultY;
	
	
	public BufferedImage up1,up2,left1,left2, right1,right2, down1, down2;
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	
	public int maxLife;
	public int life;
	
	public Entity(GamePanel gp) {
		this.gp =gp;
		
	}
	public void setAction() {}
	public void update() {
		
		setAction();
		collisionOn = false;
		gp.cChecker.CheckTile(this);
		gp.cChecker.checkObject(this,false);
		
	
		
		//	if Collision is false, Player can move 
		if ( collisionOn == false ) {
			
			switch (direction) {
			case "up":
				y  -= speed;
			
				
				break;
			case "down":
				y  += speed ;
				
			
				break;
			case "left":
				x  -= speed ;
			
				
				break;
			case "right":
				x += speed ;
				
				
				break;
				
			}
		}
	
	
	spriteCounter++;
	if(spriteCounter > 20) {
		if(spriteNum ==1) {
			spriteNum = 2;
			
		}
		else if (spriteNum ==2) {
			spriteNum =1;
		}
		spriteCounter = 0;
	}	
	
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
		case "up":
			if ( spriteNum ==1) {
				image = up1;
			}
			if ( spriteNum == 2) {
				image = up2;
			}
			
			break;
		case "left":
			if( spriteNum == 1) {
				image = left1;
			}
			if ( spriteNum == 2) {
				image = left2;
			}
			break;
		case "right":
			if ( spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			break;
		case "down" :
			if ( spriteNum ==1) {
				image = down1;
			}
			if ( spriteNum == 2) {
				image = down2;
			}
			break;
		}
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
		
	}

}
