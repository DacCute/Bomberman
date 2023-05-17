package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_lives extends SuperObject {
    public Obj_lives() {
        name = "lives";
        try {
            img_path = "/object/lives.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}