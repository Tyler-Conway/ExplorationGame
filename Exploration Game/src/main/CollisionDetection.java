package main;

import Entity.Entity;

public class CollisionDetection {

	GamePanel gp;
	
	public CollisionDetection(GamePanel gp) {
		this.gp = gp;
	}

	public void checkTile(Entity e) {
		//identifying the bounds of an entity's collision rectangle:
		int entityLeftWorldX = e.worldX + e.solidArea.x;
		int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
		int entityTopWorldY = e.worldY + e.solidArea.y;
		int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		
		//Temporary collision checking when entity is being Knocked back:
		String direction = e.direction;
		if(e.knockBack == true) {
			direction = e.knockBackDirection;
		}
		
		switch(direction) {
		case "up":
			entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
				e.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
				e.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
				e.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + e.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
				e.collisionOn = true;
			}
			break;
		}
		
	}
	
	//NPC OR MONSTER COLLISION:
	public int checkEntity(Entity entity, Entity[][] target) {
		int index = 999;
		
		//Temporary collision checking when entity is being Knocked back:
				String direction = entity.direction;
				if(entity.knockBack == true) {
					direction = entity.knockBackDirection;
				}
		
		for(int i = 0; i < target[1].length; i++) {
			if(target[gp.currentMap][i] != null) {
				
				//get entity's solid area position:
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				
				//get object's solid area position:
				target[gp.currentMap][i].solidArea.x += target[gp.currentMap][i].worldX;
				target[gp.currentMap][i].solidArea.y += target[gp.currentMap][i].worldY;
				
				switch(direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {
					if(target[gp.currentMap][i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				//Non-player and Non-Projectiles entities cannot go off the map:
				if(entity != gp.player && !(entity.isProjectile == true)) {
					
					if(entity.worldX < gp.tileSize) {
						entity.worldX = gp.tileSize;
					}
					if(entity.worldX > gp.tileSize * 48) {
						entity.worldX = gp.tileSize * 48;
					}
					if(entity.worldY < gp.tileSize) {
						entity.worldY = gp.tileSize;
					}
					if(entity.worldY > gp.tileSize * 48) {
						entity.worldY = gp.tileSize * 48;
					}
				}
				
				//reset the entity and object's solid area:
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;
			}
		}
		return index;
		
	}
	
	//returns the index of the object the player has collided with:
	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		//Temporary collision checking when entity is being Knocked back:
		String direction = entity.direction;
		if(entity.knockBack == true) {
			direction = entity.knockBackDirection;
		}
		
		for(int i = 0; i < gp.obj[0].length; i++) {
			if(gp.obj[gp.currentMap][i] != null) {
				
				//get entity's solid area position:
				entity.solidArea.x += entity.worldX;
				entity.solidArea.y += entity.worldY;
				
				//get object's solid area position:
				gp.obj[gp.currentMap][i].solidArea.x += gp.obj[gp.currentMap][i].worldX;
				gp.obj[gp.currentMap][i].solidArea.y += gp.obj[gp.currentMap][i].worldY;
				
				switch(direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				}
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
					if(gp.obj[gp.currentMap][i].collision == true) {
						entity.collisionOn = true;
					}
					
					if(player == true) {
						index = i;
					}
				}
				
				//reset the entity and object's solid area:
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;
				
			}
		}
		return index;
		
	}

	public boolean checkPlayer(Entity entity) {
		boolean contactPlayer = false;
		//get entity's solid area position:
		entity.solidArea.x += entity.worldX;
		entity.solidArea.y += entity.worldY;
		
		//get object's solid area position:
		gp.player.solidArea.x += gp.player.worldX;
		gp.player.solidArea.y += gp.player.worldY;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		//reset the entity and object's solid area:
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
}
