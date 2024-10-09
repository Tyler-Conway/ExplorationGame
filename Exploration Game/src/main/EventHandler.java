package main;

import Entity.Entity;

public class EventHandler {

	GamePanel gp;
	EventRect eventRect[][][];
	int tempMap, tempCol, tempRow, previousMap;
	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		int row = 0, col = 0, map = 0;
		
		while(map < gp.maxMap && col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[map][col][row] = new EventRect();
			eventRect[map][col][row].x = gp.tileSize/4;
			eventRect[map][col][row].y = gp.tileSize/4;
			eventRect[map][col][row].width = gp.tileSize/2;
			eventRect[map][col][row].height = gp.tileSize/2;
			eventRect[map][col][row].eventRectDefaultX = eventRect[map][col][row].x;
			eventRect[map][col][row].eventRectDefaultY = eventRect[map][col][row].y;	
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
				if(row == gp.maxWorldRow) {
					row = 0;
					map++;
				}
			}
		}
		
	}
	
	public void checkEvent() {
		
		//Check if player is more than 1 tile away from last event:
		int xDistance = Math.abs(gp.player.worldX - previousEventX);
		int yDistance = Math.abs(gp.player.worldY - previousEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize*1.1) {
			canTouchEvent = true;
		}
		
		if(canTouchEvent == true) {
			
			int mapNum = gp.currentMap;
			
			
			//MAP01 EVENTS:
			switch(mapNum) {
			case 0:
				if(hit(mapNum,7,45, "left") == true) {
					damagePit(mapNum,7,45,gp.dialogueState);
				}
				else if(hit(mapNum,17,42, "any") == true) {
					damagePit(mapNum,17,42,gp.dialogueState);
				}
				else if(hit(mapNum,9,10, "left") == true) {
					healingPool(mapNum,9,10,gp.dialogueState);
				}
				else if(hit(mapNum,9,11, "left") == true) {
					healingPool(mapNum,9,11,gp.dialogueState);
				}
				else if(hit(mapNum,9,12, "left") == true) {
					healingPool(mapNum,9,12,gp.dialogueState);
				}
				else if(hit(mapNum,9,13, "left") == true) {
					healingPool(mapNum,9,13,gp.dialogueState);
				}
				else if(hit(mapNum,9,14, "left") == true) {
					healingPool(mapNum,9,14,gp.dialogueState);
				}
				else if(hit(mapNum,16, 48, "right") == true) {
					// new col, new row:
					teleport(10, 9);
				}
				else if(hit(mapNum,8, 48, "left") == true) {
					// new col, new row:
					teleport(45, 32);
				}
				//World01 Blue Trader Cabin:
				else if(isOnTheTile(mapNum, 16, 3) == true) {
					// mapNum, col, row:
					changeMap(gp.cabin01,24,26);
				}
				//road to map 02:
				else if(hit(mapNum, 49, 33, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,2,32);
				}
				//road to map 02:
				else if(hit(mapNum, 49, 32, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,2,32);
				}
				//road to map 02:
				else if(hit(mapNum, 49, 31, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,2,32);
				}
				//road to map 03:
				else if(hit(mapNum, 0, 5, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world03,47,5);
				}
				//road to map 03:
				else if(hit(mapNum, 0, 6, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world03,47,6);
				}
				//road to map 03:
				else if(hit(mapNum, 0, 7, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world03,47,7);
				}
				break;
				
			case 1:
				//CABIN01 EVNENTS:
				if(hit(mapNum, 24, 28, "any") == true) {
					// mapNum,new col, new row:
					changeMap(gp.world01,16,5);
				}
				else if(hit(mapNum,24,21,"up") == true) {
					speak(gp.npc[mapNum][0]);
				}
				break;
			//StoneBuilding02:
			case 3:
				if(hit(mapNum,24,21,"up") == true) {
					speak(gp.npc[mapNum][0]);
				}
				break;
				
				//World02 Events:
			case 5:
				//road to map 01:
				if(hit(mapNum, 0, 33, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,47,32);
				}
				//road to map 01:
				else if(hit(mapNum, 0, 32, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,47,32);
				}
				//road to map 01:
				else if(hit(mapNum, 0, 31, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,47,32);
				}
				//Enter Cabin02
				else if(isOnTheTile(mapNum, 4, 27) == true) {
					// mapNum, col, row:
					changeMap(gp.cabin02,24,26);
				}
				//Enter the loot cabin:
				else if(isOnTheTile(mapNum, 38, 15) == true) {
					// mapNum, col, row:
					changeMap(gp.lootCabin01,24,26);
				}
				//road to Beach01:
				else if(hit(mapNum, 20, 49, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,23,2);
				}
				//road to Beach01:
				else if(hit(mapNum, 21, 49, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,24,2);
				}
				//road toBeach01:
				else if(hit(mapNum, 22, 49, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,25,2);
				}
				break;
			//Cabin02 Events:
			case 6: 
				if(hit(mapNum, 24, 28, "any") == true) {
					// mapNum,new col, new row:
					changeMap(gp.world02,4,29);
				}
				else if(hit(mapNum,24,21,"up") == true) {
					speak(gp.npc[mapNum][0]);
				}
				break;
			//World03 Events:
			case 7: 
				if(hit(mapNum, 49, 5, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,2,5);
				}
				//road to map 01:
				else if(hit(mapNum, 49, 6, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,2,6);
				}
				//road to map 01:
				else if(hit(mapNum, 49, 7, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world01,2,7);
				}
				break;
			//Beach01 Events:
			case 8:
				//road to World02:
				if(hit(mapNum, 23, 0, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,20,47);
				}
				//road to World02:
				else if(hit(mapNum, 24, 0, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,21,47);
				}
				//road World02:
				else if(hit(mapNum, 25, 0, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.world02,22,47);
				}
				//road to Beach02:
				else if(hit(mapNum, 49, 5, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach02,2,5);
				}
				//road to Beach02:
				else if(hit(mapNum, 49, 6, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach02,2,6);
				}
				//road Beach02:
				else if(hit(mapNum, 49, 7, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach02,2,7);
				}
				break;
			//Beach02 Events:
			case 9:
				//road to Beach01:
				if(hit(mapNum, 0, 5, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,47,5);
				}
				//road to Beach01:
				else if(hit(mapNum, 0, 6, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,47,6);
				}
				//road to Beach01:
				else if(hit(mapNum, 0, 7, "any") == true) {
					// mapNum, col, row:
					changeMap(gp.beach01,47,7);
				}
				else if(hit(mapNum, 10, 0, "any") == true) {
					changeMap(gp.cabin03, 24, 26);
				}
				break;
			//Cabin03 Events:
			case 10:
				if(hit(mapNum, 24, 28, "any") == true) {
					// mapNum,new col, new row:
					changeMap(gp.beach02,10,2);
				}
				else if(hit(mapNum,24,21,"up") == true) {
					speak(gp.npc[mapNum][0]);
				}
				break;
			//LootCabin01:
			case 11:
				if(hit(mapNum, 24, 28, "any") == true) {
					// mapNum,new col, new row:
					changeMap(gp.world02,38,17);
				}
			}
		}
	}

	public boolean hit(int map, int col, int row, String reqDirection) {
		
		boolean hit = false;
		
		if(map == gp.currentMap) {
			//Check if player intersects eventRect:
			gp.player.solidArea.x += gp.player.worldX;
			gp.player.solidArea.y += gp.player.worldY;
			eventRect[map][col][row].x += col*gp.tileSize;
			eventRect[map][col][row].y += row*gp.tileSize;
			if(gp.player.solidArea.intersects(eventRect[map][col][row]) && (eventRect[map][col][row].eventDone) == false) {
				if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
					hit = true;
					
					previousEventX = gp.player.worldX;
					previousEventY = gp.player.worldY;
					
				}
			}
			//reset values:
			gp.player.solidArea.x = gp.player.solidAreaDefaultX;
			gp.player.solidArea.y = gp.player.solidAreaDefaultY;
			eventRect[map][col][row].x = eventRect[map][col][row].eventRectDefaultX;
			eventRect[map][col][row].y = eventRect[map][col][row].eventRectDefaultY;
		}
		
		return hit;
	}
	
	public boolean isOnTheTile(int map, int col, int row) {
		boolean result = false;
		
		if(map == gp.currentMap) {
			if(gp.player.getCol() == col && gp.player.getRow() == row) {
				result = true;
			}
		}
		
		return result;
	}
	
	public void damagePit(int map, int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentDialogue = "You fall into a Pit ";
		gp.player.life -= 1;
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int map, int col, int row, int gameState) {
		eventRect[map][col][row].width = gp.tileSize;
		eventRect[map][col][row].height = gp.tileSize;
		
		if(gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You drank the water (Health Restored)";
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.player.ammo = gp.player.maxAmmo;
			gp.player.arrows = gp.player.maxArrows;
			gp.assetSetter.setMonster();
			gp.ui.addMessage("Monsters Reset");
		}
	}
	
	public void teleport(int col, int row) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "Teleport Triggered!";
		gp.player.worldX = gp.tileSize*col;
		gp.player.worldY = gp.tileSize*row;
	}
	
	public void changeMap(int map, int col, int row) {
		previousMap = gp.currentMap;
		gp.gameState = gp.transitionState;
		tempMap = map;
		if(tempMap == gp.cabin01 || tempMap == gp.cabin02 || tempMap == gp.cabin03 || (tempMap == gp.beach02 && gp.eventHandler.previousMap == gp.cabin03) || 
				(gp.currentMap == gp.beach01 && tempMap != gp.beach02) || (gp.currentMap == gp.world02 && tempMap == gp.beach01)) {
			gp.stopMusic();
		}
		tempCol = col;
		tempRow = row;
		gp.playSoundEffect(14);
		canTouchEvent = false;
	}
	
	public void speak(Entity entity) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}

}
