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
            up1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon_l.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2_l.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon_l.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2_l.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon_l.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2_l.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/monster/mon_r.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/monster/mon2_r.png"));

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
