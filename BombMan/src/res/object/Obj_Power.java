package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Power extends SuperObject {
    public Obj_Power() {
        name = "Power";
        try {
            img_path = "/object/potion_red.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
