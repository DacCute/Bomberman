package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Player;
import Mon_green.MON_Green;
import boom.boomit;
import objects.SuperClass;
import tile.TileManager;




public class GamePanel extends JPanel implements Runnable {
	//Screen setting
	final int originalTileSize = 16; // 16*16 size
	final int scale = 2;
	public final int tileSize = originalTileSize * scale; // 32 * 32 size
	public final int maxScreenCol = 25;
	public final int maxScreenRow = 19 ;
	public final int screenWidth = tileSize * maxScreenCol;//512 pixel
	
	public final int screenHeight = tileSize * maxScreenRow;// 384 pixel
	
	
	//PPS
	int FPS = 60;
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker (this);
	
	
	Player player = new Player(this,keyH);
	public SuperClass obj[]= new SuperClass[10];
	public MON_Green[]  mons = new MON_Green[10];
	public boomit [] boom = new boomit[1000];
	AssetSetter aSetter = new AssetSetter(this);
	public UI ui = new UI( this);
	 
	 //Set player's position
	 //int playerX = 100;
	// int playerY = 100;
	// int playerSpeed = 4;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		
	}
	public void setupGame() {
		aSetter.setObject();
		aSetter.setMon();

	}
	
	
	public void starGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}

//	@Override
/*	public void run() {
		double drawInterval = 1000000000/FPS; // 0.01666 seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		while(gameThread != null ) {
		//	long currentTime = System.nanoTime();
			//System.out.println("current time: "+ currentTime);
			
			
			
			
		//	System.out.println("It is updated");
			update();
			repaint();
			try {
				double remainingTime = nextDrawTime -System.nanoTime();
				remainingTime = remainingTime /1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	
		
	}*/
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime  = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		
		
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta +=(currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if(timer > 1000000000) {
				System.out.println("FPS: "+ drawCount);
				drawCount = 0;
				timer = 0;
				
				
			}
			
			
		}
	}
	public void update() {
		
		player.update();
		
		for (int i = 0; i < mons.length; i ++) {
			if( mons[i] != null) {
				mons[i].update();
			}
		}
		
		
	}
		
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		//TILE
		tileM.draw(g2);
		//OBJECT
	
		for (int i =0; i < this.obj.length;i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
				
			}
			
		}
		
		//MOnster
		for(int i = 0; i < mons.length; i++) {
			if(mons[i] != null) {
			mons[i].draw(g2);
			}
		}
		//BOOM
				for (int i =1; i < this.boom.length;i++) {
					if(boom[i] != null) {
						boom[i].draw(g2,this);
						
					}
					
				}
				
	
	
		//PLAYER
		player.draw(g2);
		
		
		
		// UI
		ui.draw(g2);
		ui.drawPlayerLife(g2);
		
		
		g2.dispose();

		
		
		
	}
	
	
	 
	

}
