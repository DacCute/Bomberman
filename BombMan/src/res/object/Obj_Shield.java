package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Shield extends SuperObject {
    public Obj_Shield() {
        name = "Shield";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/object/shield.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}