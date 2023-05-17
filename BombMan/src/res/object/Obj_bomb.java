package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_bomb extends SuperObject {
    public Obj_bomb() {
        solidAreaDefaultX = 48;
        solidAreaDefaultY = 48;
        name = "bomb";
        collision = true;
        try {
            img_path = "/object/bomb.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
