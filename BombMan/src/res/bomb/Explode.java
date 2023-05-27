package bomb;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import object.SuperObject;

public class Explode extends SuperObject {
    GamePanel gp;

    public Explode(GamePanel gp) {
        name = "explode";
        try {
            img_path = "/object/explode.png";
            image1 = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image1, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
