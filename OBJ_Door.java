package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperClass {
	public OBJ_Door() {
		name = "Door";
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/OBject/door.png"));
		
			
		
	}catch(IOException e){
		e.printStackTrace(); 
	}
		Collision = true;
}
}
