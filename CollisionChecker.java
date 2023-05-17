package main;

import Entity.Entity;
import Mon_green.MON_Green;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
		
		
	}
	public void CheckTile(Entity entity) {
		
		
	int entityLeft = entity.x + entity.solidArea.x;
	int entityRight = entity.x + entity.solidArea.x + gp.player.solidArea.width;
	int entityBottom = entity.y + entity.solidArea.y + gp.player.solidArea.height;
	int entityTop = entity.y + entity.solidArea.y;
		
		
	int entityLeftCol = entityLeft /gp.tileSize;
	int entityRightCol = entityRight /gp.tileSize;
	int entityTopRow =   entityBottom /gp.tileSize;
	int entityBottomRow = entityTop /gp.tileSize;
	
	int TileNum1, TileNum2;
	
	
	switch(entity.direction) {
	case "up":
		entityTopRow = ( entityTop - entity.speed)/ gp.tileSize;
		TileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
		TileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
	

		if (gp.tileM.tile[TileNum1].Collision == true || gp.tileM.tile[TileNum2].Collision == true  ) {
			entity.collisionOn = true;
		}
		break;
		
		
	case "down":
		entityBottomRow = ( entityBottom + entity.speed)/ gp.tileSize;
		TileNum1 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
		TileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
	

		if (gp.tileM.tile[TileNum1].Collision == true || gp.tileM.tile[TileNum2].Collision == true  ) {
			entity.collisionOn = true;
		}
			break;
		
		
	case "left":
		

		entityLeftCol = ( entityLeft - entity.speed)/ gp.tileSize;
		TileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
		TileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

		if (gp.tileM.tile[TileNum1].Collision == true || gp.tileM.tile[TileNum2].Collision == true  ) {
			entity.collisionOn = true;
		}
			break;
		
		
	case "right":
		entityRightCol = (entityRight + entity.speed)/ gp.tileSize;
		TileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
		TileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

		if (gp.tileM.tile[TileNum1].Collision == true || gp.tileM.tile[TileNum2].Collision == true  ) {
			entity.collisionOn = true;
		} 
		break;
		
	
	
	}
	
		
	}

	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for( int i = 0; i < gp.obj.length; i++) {
			if ( gp.obj[i] != null) {
				// Get entity's solid area position
				entity.solidArea.x = gp.player.x +entity.solidArea.x;
				entity.solidArea.y = gp.player.y +entity.solidArea.y;
				// get the object's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].x + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].y + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						System.out.println("up collision"); 
						if(gp.obj[i].Collision == true) {
							entity.collisionOn =true;
						}
						if(player == true) {
							index = i;
						}
							
							
					
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						System.out.println("down collision");
						if(gp.obj[i].Collision == true) {
							//entity.collisionOn =true;
						}
						if(player == true) {
							index = i;
							
							
						}
					}
					break;
					
				case "left":
					entity.solidArea.x -= entity.speed;
				
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						System.out.println("left collision");
					
						if(gp.obj[i].Collision == true) {
							entity.collisionOn =true;
						}
						if(player == true) {
							index = i;
						
						}
					}
					break;
					
				case "right":
					entity.solidArea.x += entity.speed;
					
					if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
						System.out.println("right collision");
						
						if(gp.obj[i].Collision == true) {
							entity.collisionOn =true;
						}
					if(player == true) {
						index = i;
							
						} 
					
					break;
					
					}
					

				
			}
				

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDeFaultY;
				
		}
		
	}
        return index;
	}
	//NPC Or Monster
	public  int checkEntity(Entity entity, Entity[]target) {
		
		
	int index = 999;
		
		for( int i = 0; i < target.length; i++) {
			if ( target[i] != null) {
				// Get entity's solid area position
				entity.solidArea.x = gp.player.x +entity.solidArea.x;
				entity.solidArea.y = gp.player.y +entity.solidArea.y;
				// get the object's solid area position
				target[i].solidArea.x = target[i].x + target[i].solidArea.x;
				target[i].solidArea.y = target[i].y + target[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					
					if(entity.solidArea.intersects(target[i].solidArea)) {
						System.out.println("up collision"); 
							entity.collisionOn =true;
	
							index = i;
						
							
		
					
					}
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					
					if(entity.solidArea.intersects(target[i].solidArea)) {
						System.out.println("down collision");
							entity.collisionOn =true;
						
							index = i;
							
							
						
					}
					break;
					
				case "left":
					entity.solidArea.x -= entity.speed;
				
					if(entity.solidArea.intersects(target[i].solidArea)) {
						System.out.println("left collision");
					
					
							entity.collisionOn =true;
						
							index = i;
						
						
					}
					break;
					
				case "right":
					entity.solidArea.x += entity.speed;
					
					if(entity.solidArea.intersects(target[i].solidArea)) {
						System.out.println("right collision");
						
		
							entity.collisionOn =true;
					
						index = i;
							
						
					
					break;
					
					}
					

				
			}
				

				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
				
		}
		
	}
        return index;
	}				
	
	public int checkPlayer(MON_Green [] mons) {
		
		int index = 999;
		for( int i = 0; i < gp.mons.length; i++) {
			if ( gp.mons[i] != null) {
		
		// Get entity's solid area position
		gp.mons[i].solidArea.x = gp.mons[i].x + gp.mons[i].solidArea.x;
		gp.mons[i].solidArea.y = gp.mons[i].y + gp.mons[i].solidArea.y;
		// get the object's solid area position
		gp.player.solidArea.x = gp.player.x + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.y + gp.player.solidArea.y;
		
		switch(gp.mons[i].direction) {
		case "up":
			gp.mons[i].solidArea.y -= gp.mons[i].speed;
			
			if(gp.player.solidArea.intersects(gp.mons[i].solidArea)) {
				System.out.println("up collision"); 
				gp.mons[i].collisionOn = true;

			
					index = 1;

			
			}
			break;
		case "down":
			gp.mons[i].solidArea.y += gp.mons[i].speed;
			
			if(gp.player.solidArea.intersects(gp.mons[i].solidArea)) {
				System.out.println("down collision");
				gp.mons[i].collisionOn = true;
				
					
				index = 1;

					
				
			}
			break;
			
		case "left":
			gp.mons[i].solidArea.x -= gp.mons[i].speed;
		
			if(gp.player.solidArea.intersects(gp.mons[i].solidArea)) {
				System.out.println("left collision");
			
			
				gp.mons[i].collisionOn = true;
				
				index = 1;

				
			}
			break;
			
		case "right":
			gp.mons[i].solidArea.x += gp.mons[i].speed;
			
			if(gp.player.solidArea.intersects(gp.mons[i].solidArea)) {
				System.out.println("right collision");
				

				gp.mons[i].collisionOn =true;
			
				index = 1;

					
				
			
			break;
			
			}
			

		
	}
		

		gp.mons[i].solidArea.x = gp.mons[i].solidAreaDefaultX;
		gp.mons[i].solidArea.y = gp.mons[i].solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
	}

}
		return index;
	}
}
