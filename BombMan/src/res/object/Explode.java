package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Explode extends SuperObject {
    public Explode() {
        name = "explode";
        try {
            img_path = "/object/explode.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
