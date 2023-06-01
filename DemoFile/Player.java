package Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import boom.boomit;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	//GamePanel gp;
	KeyHandler keyH;
	public int hasKey =0;
	public int setBoomcount = 0;

	
	int counter2 = 0;
	
	
	
	
	public Player( GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyH = keyH;

		solidArea = new Rectangle();
		solidArea.x = 4;
		solidArea.y = 8;
		solidArea.width = 24;
		solidArea.height = 24;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	
	
		
	
		
		setDefaultValue();
		getPlayerImage();
	}
	public void setDefaultValue() {
		x = 100;
		y = 100;
				
		speed = 2;
		direction = "down";
		
		maxLife = 3;
		life = maxLife;
		
	}
	
	

	public void getPlayerImage() {
		try {

			up1 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_up_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_up_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_left_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_left_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_right_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/walkingboy/boy_right_2.png"));
			
		}catch (IOException e) {
			e.printStackTrace();
			
		}
	}
	
	
	public void update() {
		if(keyH.upPressed == true || keyH.downPressed == true || 
				keyH.rightPressed == true || keyH.leftPressed == true || keyH.boom == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			
				
				
				
			}else if(keyH.downPressed == true ) {
				direction = "down";
			
		
				
				
			}else if (keyH.leftPressed == true) {
				direction = "left";
			
			
				
				
				
			
			}else if (keyH.rightPressed == true) {
				direction = "right" ;
				
				
			}else if (keyH.boom == true) {
				setBoomcount++;
				gp.boom[setBoomcount] = new boomit(gp);
				gp.boom[setBoomcount].x = ((int)(this.solidArea.x + this.x)/gp.tileSize)*gp.tileSize;
				gp.boom[setBoomcount].y = ((int)(this.solidArea.y+ this.y)/gp.tileSize)*gp.tileSize;
				
			}
			
			
			
			//Check Tile Collision
				collisionOn = false;
				gp.cChecker.CheckTile(this);
				//Check object collision
			int objIndex =	gp.cChecker.checkObject(this, true);
			System.out.print(objIndex);
			
			
			
			
			pickUpObject(objIndex);		
					
			//Check Monster collision
			int monsIndex = gp.cChecker.checkEntity(this, gp.mons);
			contactMons(monsIndex);
			
			int PlayerCheck = gp.cChecker.checkPlayer(gp.mons);
			System.out.println(PlayerCheck);
				
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
	}
			
public void contactMons(int i) {
		if( i != 999) {
			life -=1;
			
			
		}
		
	}
public void pickUpObject(int i) {
	if(i != 999) {
		String nameObj = gp.obj[i].name;
		switch(nameObj){
		case "Key":
			hasKey++;
			gp.obj[i] = null;
			break;
		case "Door":
			if( hasKey ==2) {
			gp.obj[i] = null;
			}
			break;
			
		
		}


	}
	
	
}
		
	
	public void draw(Graphics2D g2) {
	//g2.setColor(Color.white);
	//g2.fillRect(x, y, gp.tileSize, gp.tileSize);
	
	

		
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
	g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
	}
}
