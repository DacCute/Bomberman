package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Speed extends SuperObject {
    public Obj_Speed() {
        name = "Speed";
        try {
            img_path = "/object/boots.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}