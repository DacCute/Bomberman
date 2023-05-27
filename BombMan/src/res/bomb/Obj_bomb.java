package bomb;

import java.io.IOException;

import javax.imageio.ImageIO;

import object.SuperObject;

public class Obj_bomb extends SuperObject {
    public Obj_bomb() {
        name = "bomb";
        try {
            img_path = "/object/bomb.png";
            image1 = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
