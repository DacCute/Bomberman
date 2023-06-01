package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	//public static final String fileName = "/src/res/maps/map01.txt";
	

	
	
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		getTileNum();
		getTileImage();
		
		
		
	}
	public void getTileImage() {
		 
		try {
			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tile/grass.png"));
			
			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tile/rock.png"));
			tile[1].Collision =  true;
			
			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tile/wall1.png"));
			tile[2].Collision = true;
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	public void getTileNum() {
		for ( int i = 0; i < (gp.maxScreenRow ); i++) {
			for (int j = 0; j < (gp.maxScreenCol ); j++ ) {
				if (i == 0 ) {
					mapTileNum[j][i] =1;
				}else if (j == 0){
					mapTileNum[j][i] =1;
				}else if (j == gp.maxScreenCol -1){
					mapTileNum[j][i] =1;
				}else if ( i == gp.maxScreenRow -1) {
					mapTileNum[j][i] =1;
			}else {
				mapTileNum[j][i] =0;
			}
				
				if( i % 3== 0&& j %3 == 0) {
					mapTileNum[j][i] =1;
				}
			
			}
	}
				
	}
	
		
		
	
		




	public void draw(Graphics2D g2) {
		
		/*int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		while (x < gp.maxScreenCol && y < gp.maxScreenRow ) {
			g2.drawImage(tile[1].image, x, y, gp.tileSize, gp.tileSize,null);
			
			*/
			
			
		

		for ( int i = 0; i <gp.maxScreenRow; i++) {
			for (int j = 0; j <gp.maxScreenCol; j++ ) {
				if(i ==0|| j==0||i==(gp.maxScreenRow -1)||j==(gp.maxScreenCol -1 )) {
					g2.drawImage(tile[1].image, j*gp.tileSize, i*gp.tileSize, gp.tileSize, gp.tileSize,null);

					
				}else {
					
					System.out.print("  ");
					g2.drawImage(tile[0].image, j*gp.tileSize, i*gp.tileSize, gp.tileSize, gp.tileSize,null);


				}
				if( i % 3== 0&& j % 3 == 0) {
					g2.drawImage(tile[1].image, j*gp.tileSize, i*gp.tileSize, gp.tileSize, gp.tileSize,null);

					
					
				}
				
			
			}
			
		
	}

	
		
		
		
			}
		
	
		
			
		}
	 
	


