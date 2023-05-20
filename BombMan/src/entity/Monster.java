package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Monster extends Entity {
    public Monster(GamePanel gp) {
        super(gp);
        this.gp = gp;

        // name = "Green Monster";
        speed = 1;
        action = "down";

        solidArea = new Rectangle();
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 45;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getMonsterImage();

    }

    public void getMonsterImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2.png"));

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100) + 1;// pick up a number from 1 to 100
            if (i <= 25) {
                action = "up";
            }
            if (i > 25 && i <= 50) {
                action = "down";

            }
            if (i > 50 && i <= 75) {
                action = "left";

            }
            if (i > 75 && i <= 100) {
                action = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {

        // g2.dispose();

    }
}
