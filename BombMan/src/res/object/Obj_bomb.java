package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Obj_bomb extends SuperObject {
    GamePanel gp;

    public Obj_bomb(GamePanel gp) {
        name = "bomb";
        try {
            img_path = "/object/bomb.png";
            image = ImageIO.read(getClass().getResourceAsStream(img_path));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
