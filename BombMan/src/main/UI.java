package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import object.Obj_Power;
import object.Obj_Speed;
import object.Obj_lives;

public class UI {

    GamePanel gp;
    Font arial_10;
    BufferedImage speed_Image;
    BufferedImage lives_Image;
    BufferedImage power_Image;
    BufferedImage titleNameImage, playButton, quitButton, pointer;
    public int commandNum = 0;
	

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_10 = new Font("Arial", Font.PLAIN, 20);
        Obj_lives lives = new Obj_lives();
        lives_Image = lives.image;
        Obj_Speed speed = new Obj_Speed();
        speed_Image = speed.image;
        Obj_Power power = new Obj_Power();
        power_Image = power.image;
    }
    public void getTitleImage() {
		try {
			titleNameImage = ImageIO.read(getClass().getResourceAsStream("/titleScreen/gameTitle.png"));
			playButton = ImageIO.read(getClass().getResourceAsStream("/titleScreen/playButton.png"));
			quitButton = ImageIO.read(getClass().getResourceAsStream("/titleScreen/quitButton.png"));
			pointer = ImageIO.read(getClass().getResourceAsStream("/titleScreen/pointer.png"));

			
		}catch (IOException e) {
			e.printStackTrace();
			
		}
		
	}
    public void drawTileScreen(Graphics2D g2) {
		g2.setColor(new Color(0,0,0));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		//Title name 
	    g2.drawImage(titleNameImage,0, 0, gp.screenWidth, gp.tileSize * 11, null);
		g2.drawImage(playButton, 400, 515, gp.tileSize*6, gp.tileSize* 3, null );
		g2.drawImage(quitButton, 400, 525 + gp.tileSize*3 - 20, gp.tileSize*6, gp.tileSize* 3 -10, null );
		if( commandNum == 0) {
			g2.drawImage(pointer, 400 - 50, 557, gp.tileSize, gp.tileSize, null );
			
		} else if (commandNum == 1) {
			g2.drawImage(pointer, 400 - 50, 567+ gp.tileSize*3 - 20, gp.tileSize, gp.tileSize, null );
			
		}
    } 

    public void draw(Graphics2D g2) {

        g2.setFont(arial_10);
        g2.setColor(Color.white);

        g2.drawImage(lives_Image, gp.tileSize / 2, gp.tileSize / 5 * 4, gp.tileSize / 2, gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.lives, gp.tileSize, gp.tileSize / 4 * 5);

        g2.drawImage(speed_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize, gp.tileSize / 2,
                gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.speed, gp.tileSize, (gp.tileSize * 2));

        g2.drawImage(power_Image, gp.tileSize / 2, gp.tileSize / 5 * 3 + gp.tileSize * 2, gp.tileSize / 2,
                gp.tileSize / 2, null);
        g2.drawString(" x " + gp.player.power, gp.tileSize, (gp.tileSize * 3));

    }

}
