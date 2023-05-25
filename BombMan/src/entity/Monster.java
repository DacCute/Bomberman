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

        setDefaultvalues();

    }

    public void setDefaultvalues() {
        action = "up";

        solidArea = new Rectangle();
        solidArea.x = 14;
        solidArea.y = 18;
        solidArea.width = 30;
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
        if (actionLockCounter == monster_direct) {

            Random random = new Random();
            int i = random.nextInt(4) + 1;// pick up a number from 1 to 100
            if (i == 1) {
                action = "up";
            }
            if (i == 2) {
                action = "down";

            }
            if (i == 3) {
                action = "left";

            }
            if (i == 4) {
                action = "right";
            }

            actionLockCounter = 0;
        }

    }

    public void draw(Graphics2D g2) {

        // g2.dispose();

    }
}
