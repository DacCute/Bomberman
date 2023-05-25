package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_lives extends SuperObject {
    GamePanel gp;

    public Obj_lives(GamePanel gp) {
        name = "lives";
        try {
            img_path = "/object/lives.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}