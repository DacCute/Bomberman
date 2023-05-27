package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Power extends SuperObject {
    GamePanel gp;

    public Obj_Power(GamePanel gp) {
        name = "Power";
        try {
            img_path = "/object/power.png";
            image1 = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image1, gp.tileSize, gp.tileSize);

            img_path = "/object/power_potion.png";
            image2 = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image1, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
