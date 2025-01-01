package main;

import Entity.Entity;

public class EventHandler{

	GamePanel gp;
	EventRect eventRect[][][];
	Entity eventDialogueHandler;
	int tempMap, tempCol, tempRow, previousMap;	
	int previousEventX, previousEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GamePanel gp) {
		this.gp = gp;
		eventRect = new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		eventDialogueHandler = new Entity(gp);
		setDialouge();
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

	public void setDialouge(){
		eventDialogueHandler.dialogues[0][0] = "You fall into a Pit.";

		eventDialogueHandler.dialogues[1][0] = "You drank the water (Health Restored)";	

		eventDialogueHandler.dialogues[2][0] = "Teleport Triggered!";
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
			
			
			switch(mapNum) {
		//MAP01 EVENTS:
			case 0:
				if(hit(mapNum,7,45, "left") == true) {damagePit(mapNum,7,45,gp.dialogueState);}
				else if(hit(mapNum,17,42, "any") == true) {damagePit(mapNum,17,42,gp.dialogueState);}
				else if(hit(mapNum,9,10, "left") == true) {healingPool(mapNum,9,10,gp.dialogueState);}
				else if(hit(mapNum,9,11, "left") == true) {healingPool(mapNum,9,11,gp.dialogueState);}
				else if(hit(mapNum,9,12, "left") == true) {healingPool(mapNum,9,12,gp.dialogueState);}
				else if(hit(mapNum,9,13, "left") == true) {healingPool(mapNum,9,13,gp.dialogueState);}
				else if(hit(mapNum,9,14, "left") == true) {healingPool(mapNum,9,14,gp.dialogueState);}
				else if(hit(mapNum,16, 48, "right") == true) {teleport(10, 9);}
				else if(hit(mapNum,8, 48, "left") == true) {teleport(45, 32);}
				//World01 Blue Trader Cabin:
				else if(isOnTheTile(mapNum, 16, 3) == true) {changeMap(gp.cabin01,24,26,gp.inside);}
				//road to map 02:
				else if(hit(mapNum, 49, 33, "any") == true) {changeMap(gp.world02,2,32,gp.outside);}
				else if(hit(mapNum, 49, 32, "any") == true) {changeMap(gp.world02,2,32,gp.outside);}
				else if(hit(mapNum, 49, 31, "any") == true) {changeMap(gp.world02,2,32,gp.outside);}
				//^^^road to map 02:
				//road to map 03:
				else if(hit(mapNum, 0, 5, "any") == true) {changeMap(gp.world03,47,5,gp.outside);}
				else if(hit(mapNum, 0, 6, "any") == true) {changeMap(gp.world03,47,6,gp.outside);}
				else if(hit(mapNum, 0, 7, "any") == true) {changeMap(gp.world03,47,7,gp.outside);}
				//^^^road to map 03:
				break;
		//CABIN01 EVNENTS:	
			case 1:
				if(hit(mapNum, 24, 28, "any") == true) {changeMap(gp.world01,16,5, gp.outside);}
				else if(hit(mapNum,24,21,"up") == true) {speak(gp.npc[mapNum][0]);}
				break;
		//StoneBuilding02:
			case 3:
				if(hit(mapNum,24,21,"up") == true) {speak(gp.npc[mapNum][0]);}
				break;
		//World02 Events:
			case 5:
				//road to map 01:
				if(hit(mapNum, 0, 33, "any") == true) {changeMap(gp.world01,47,32,gp.outside);}
				else if(hit(mapNum, 0, 32, "any") == true) {changeMap(gp.world01,47,32,gp.outside);}
				else if(hit(mapNum, 0, 31, "any") == true) {changeMap(gp.world01,47,32,gp.outside);}
				//^^^road to map 01:
				//Enter Cabin02
				else if(isOnTheTile(mapNum, 4, 27) == true) {changeMap(gp.cabin02,24,26,gp.inside);}
				//Enter the loot cabin:
				else if(isOnTheTile(mapNum, 38, 15) == true) {changeMap(gp.lootCabin01,24,26,gp.inside);}
				//road to Beach01:
				else if(hit(mapNum, 20, 49, "any") == true) {changeMap(gp.beach01,23,2,gp.outside);}
				else if(hit(mapNum, 21, 49, "any") == true) {changeMap(gp.beach01,24,2,gp.outside);}
				else if(hit(mapNum, 22, 49, "any") == true) {changeMap(gp.beach01,25,2,gp.outside);}
				//^^^road toBeach01:
				//Road to World04: 
				else if(hit(mapNum, 49, 33, "any") == true) {changeMap(gp.world04,3,33,gp.outside);}
				else if(hit(mapNum, 49, 32, "any") == true) {changeMap(gp.world04,3,32,gp.outside);}
				else if(hit(mapNum, 49, 31, "any") == true) {changeMap(gp.world04,3,31,gp.outside);}
				break;
		//Cabin02 Events:
			case 6: 
				if(hit(mapNum, 24, 28, "any") == true) {changeMap(gp.world02,4,29,gp.outside);}
				else if(hit(mapNum,24,21,"up") == true) {speak(gp.npc[mapNum][0]);}
				break;
		//World03 Events:
			case 7: 
				//road to map01:
				if(hit(mapNum, 49, 5, "any") == true) {changeMap(gp.world01,3,5,gp.outside);}
				else if(hit(mapNum, 49, 6, "any") == true) {changeMap(gp.world01,3,6,gp.outside);}
				else if(hit(mapNum, 49, 7, "any") == true) {changeMap(gp.world01,3,7,gp.outside);}
				//^^^Road to World01
				else if(isOnTheTile(mapNum, 42, 9) == true) {changeMap(gp.lootCabin02,24,26,gp.inside);}
				break;
		//Beach01 Events:
			case 8:
				//road to World02:
				if(hit(mapNum, 23, 0, "any") == true) {changeMap(gp.world02,20,47,gp.outside);}
				else if(hit(mapNum, 24, 0, "any") == true) {changeMap(gp.world02,21,47,gp.outside);}
				else if(hit(mapNum, 25, 0, "any") == true) {changeMap(gp.world02,22,47,gp.outside);}
				//^^^Road to World02
				//road to Beach02:
				else if(hit(mapNum, 49, 5, "any") == true) {changeMap(gp.beach02,2,5,gp.outside);}
				else if(hit(mapNum, 49, 6, "any") == true) {changeMap(gp.beach02,2,6,gp.outside);}
				else if(hit(mapNum, 49, 7, "any") == true) {changeMap(gp.beach02,2,7,gp.outside);}break;
				//^^^Road to Beach02
		//Beach02 Events:
			case 9:
				//road to Beach01:
				if(hit(mapNum, 0, 5, "any") == true) {changeMap(gp.beach01,47,5,gp.outside);}
				else if(hit(mapNum, 0, 6, "any") == true) {changeMap(gp.beach01,47,6,gp.outside);}
				else if(hit(mapNum, 0, 7, "any") == true) {changeMap(gp.beach01,47,7,gp.outside);}
				//^^^ Road to Beach01:
				else if(hit(mapNum, 10, 0, "any") == true) {changeMap(gp.cabin03, 24, 26,gp.inside);} break;
		//Cabin03 Events:
			case 10:
				if(hit(mapNum, 24, 28, "any") == true) {changeMap(gp.beach02,10,2,gp.outside);}
				else if(hit(mapNum,24,21,"up") == true) {speak(gp.npc[mapNum][0]);} break;
		//LootCabin01:
			case 11: if(hit(mapNum, 24, 28, "any") == true) {changeMap(gp.world02,38,17,gp.outside);} break;
		//LootCabin02:
			case 12: if(hit(mapNum, 24, 28, "any") == true) {changeMap(gp.world03,42,11,gp.outside);} break;
			//StoneBuilding05:
			case 14: if(hit(mapNum,24,22,"up") == true) {speak(gp.npc[mapNum][0]);} break;
		//World04
			case 15:
				if(hit(mapNum, 0, 33, "any") == true) {changeMap(gp.world02,46,33,gp.outside);}
				else if(hit(mapNum, 0, 32, "any") == true) {changeMap(gp.world02,46,32,gp.outside);}
				else if(hit(mapNum, 0, 31, "any") == true) {changeMap(gp.world02,46,31,gp.outside);}
				break;
		//World05
			case 16:
				if(hit(mapNum, 19, 48, "any") == true) {changeMap(gp.world04,19,3,gp.outside);}
				else if(hit(mapNum, 18, 48, "any") == true) {changeMap(gp.world04,18,3,gp.outside);}
				else if(hit(mapNum, 20, 48, "any") == true) {changeMap(gp.world04,20,3,gp.outside);}
				else if(hit(mapNum, 19, 42, "any") == true) {changeMap(gp.dungeon01, 19, 40,gp.dungeon);}
				break;
		//Dungeon01
			case 17:
				if(hit(mapNum, 19, 42, "any") == true) {changeMap(gp.world05, 19, 44,gp.outside);}
				else if(hit(mapNum, 30, 1, "any") == true) {changeMap(gp.dungeon02, 24, 46,gp.dungeon);}
				break;
		//Dungeon02
			case 18:
				if(hit(mapNum, 24, 48, "any") == true) {changeMap(gp.dungeon01, 30, 3,gp.dungeon);}
				else if(hit(mapNum, 24, 37, "any") == true) {skeletonGiantCutScene();}
				else if(hit(mapNum, 24, 41, "any") == true) {saveGame(gp.dungeon02, 24, 41);}
				break;
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
		eventDialogueHandler.startDialogue(eventDialogueHandler, 0);
		gp.player.life -= 1;
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void healingPool(int map, int col, int row, int gameState) {
		eventRect[map][col][row].width = gp.tileSize;
		eventRect[map][col][row].height = gp.tileSize;
		
		if(gp.keyH.enterPressed == true) {
			gp.player.attackCanceled = true;
			eventDialogueHandler.startDialogue(eventDialogueHandler, 1);
			gp.player.life = gp.player.maxLife;
			gp.player.mana = gp.player.maxMana;
			gp.player.ammo = gp.player.maxAmmo;
			gp.player.arrows = gp.player.maxArrows;
			gp.assetSetter.setMonster();
			gp.ui.addMessage("Monsters Reset");
		}
	}
	
	public void teleport(int col, int row) {
		eventDialogueHandler.startDialogue(eventDialogueHandler, 2);
		gp.player.worldX = gp.tileSize*col;
		gp.player.worldY = gp.tileSize*row;
	}
	
	public void changeMap(int map, int col, int row, int area) {
		previousMap = gp.currentMap;
		gp.gameState = gp.transitionState;
		gp.nextArea = area;
		tempMap = map;
		if(tempMap == gp.cabin01 || tempMap == gp.cabin02 || tempMap == gp.cabin03 || (tempMap == gp.beach02 && gp.eventHandler.previousMap == gp.cabin03) || 
				(gp.currentMap == gp.beach01 && tempMap != gp.beach02) || (gp.currentMap == gp.world02 && tempMap == gp.beach01)) {
			gp.stopMusic();
		}
		tempCol = col;
		tempRow = row;
		gp.playSoundEffect(14);
		canTouchEvent = false;

		String text = "";
		text = gp.getWorldname(map);
		gp.ui.addMessage(text);
	}
	
	public void speak(Entity entity) {
		if(gp.keyH.enterPressed == true) {
			gp.gameState = gp.dialogueState;
			gp.player.attackCanceled = true;
			entity.speak();
		}
	}

	public void skeletonGiantCutScene(){
		if(gp.bossBattle == false && gp.skeletonGiantDefeated == false && gp.bossCutSceneOver == false){
			gp.bossBattle = true;
			gp.gameState = gp.cutsceneState;
			gp.cutsceneManager.sceneNum = gp.cutsceneManager.skeletonGiant;
		}
	}

	public void saveGame(int map, int col, int row){
		gp.saveLoad.save();
		gp.ui.addMessage("Game Saved (no saving during boss fight)");
		eventRect[map][col][row].eventDone = true;
		canTouchEvent = false;
	}
}
