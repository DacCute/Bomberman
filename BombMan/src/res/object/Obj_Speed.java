package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_Speed extends SuperObject {
    GamePanel gp;

    public Obj_Speed(GamePanel gp) {
        name = "Speed";
        try {
            img_path = "/object/boots.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}