package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperClass {
	public OBJ_Key() {
		name = "Key";
	
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/OBject/key.png"));
		
			
		
	}catch(IOException e){
		e.printStackTrace(); 
	}
		
}
}
