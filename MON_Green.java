package Mon_green;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class MON_Green extends Entity {

	public MON_Green(GamePanel gp) {
		super(gp);
		
		name = "Green Monster";
		speed = 1;
		direction = "down";

		solidArea = new Rectangle();
		solidArea.x = 3;
		solidArea.y = 18;
		solidArea.width = 26;
		solidArea.height = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	
		
		
		
		getMonsterImage();
		
		
		
	}
	public void getMonsterImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
	        down2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_1.png"));
	        right2 = ImageIO.read(getClass().getResourceAsStream("/monster/greenslime_down_2.png"));
		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
public void setAction() {
	actionLockCounter++;
	if(actionLockCounter == 120) {
		
	
	Random random = new Random();
	int i = random.nextInt(100) + 1;//pick up a number from 1 to 100
	if(i <=25) {
		direction = "up"; 
	}
	if(i>25 && i<=50) {
		direction = "down";
		
	}
	if(i > 50 && i <= 75) {
		direction = "left";
	
	}
	if(i > 75 && i <= 100 ) {
		direction = "right";
	}
	
	actionLockCounter = 0;
	}
	
}

}
