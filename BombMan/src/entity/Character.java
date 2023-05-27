package entity;

import main.GamePanel;

public class Character extends Entity {
    GamePanel gp;

    public Character(GamePanel gp) {
        super(gp);
        this.gp = gp;
    }

    public void getPlayerImage() {

        up1 = setup("up_1");
        up2 = setup("up_2");
        down1 = setup("down_1");
        down2 = setup("down_2");
        left1 = setup("left_1");
        left2 = setup("left_2");
        right1 = setup("right_1");
        right2 = setup("right_2");

    }
}
