package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperClass {
	GamePanel  gp;
	public OBJ_Heart(GamePanel gp) {
		
		
		this.gp = gp;
		name = "Heart";
	
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/OBject/heart.png"));
			image1 = ImageIO.read(getClass().getResourceAsStream("/OBject/blackheart.png"));
		
			
		
	}catch(IOException e){
		e.printStackTrace(); 
	}
		
}
}


