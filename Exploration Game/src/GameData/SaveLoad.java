package GameData;

import Entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;
import objects.*;

public class SaveLoad {

	GamePanel gp;

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		//Game does not save durring boss battle:
		if(gp.bossBattle == false){
			try {
				ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
				GameDataStorage dataStorage = new GameDataStorage();
				
				//Game State:
				dataStorage.currentArea = gp.currentArea;
				dataStorage.currentMap = gp.currentMap;
				dataStorage.skeletonGiantDefeated = gp.skeletonGiantDefeated;

				//Player's Stats && Position:
				dataStorage.life = gp.player.life;
				dataStorage.maxLife = gp.player.maxLife;
				dataStorage.coin = gp.player.coin;
				dataStorage.maxArrows = gp.player.maxArrows;
				dataStorage.arrows = gp.player.arrows;
				dataStorage.maxAmmo = gp.player.maxAmmo;
				dataStorage.ammo = gp.player.ammo;
				dataStorage.maxMana = gp.player.maxMana;
				dataStorage.mana = gp.player.mana;
				dataStorage.level = gp.player.level;
				dataStorage.strength = gp.player.strength;
				dataStorage.dexterity = gp.player.dexterity;
				dataStorage.exp = gp.player.exp;
				dataStorage.nextLevelExp = gp.player.nextLevelExp;
				dataStorage.playerClass = gp.player.playerClass;
				dataStorage.worldX = gp.player.worldX;
				dataStorage.worldY = gp.player.worldY;

				//Player's Items:
				for (int i = 0; i < gp.player.inventory.size(); i++) {
					dataStorage.itemNames.add(gp.player.inventory.get(i).name); //store the names of the items.
					dataStorage.itemAmmounts.add(gp.player.inventory.get(i).amount); //store the amount of that item a player has.
				}
				//Equiped Items:
				dataStorage.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
				dataStorage.currentShieldSlot = gp.player.getCurrentShieldSlot();
				//Map Items:
				dataStorage.savedObjectNames = new String[gp.maxMap][gp.obj[1].length];
				dataStorage.savedObjectsWorldX = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.savedObjectsWorldY = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.mapNums = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.TeleportDoorCol = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.TeleportDoorRow = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];
				dataStorage.doorObjectIndex = new int[gp.maxMap][gp.obj[1].length];
				dataStorage.TeleportDoorNewArea = new int[gp.maxMap][gp.obj[1].length];


				for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
					for(int i = 0; i < gp.obj[1].length; i++){
						if(gp.obj[mapNum][i] == null){
							dataStorage.savedObjectNames[mapNum][i] = "NA";
						}
						else{
							dataStorage.savedObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
							dataStorage.savedObjectsWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
							dataStorage.savedObjectsWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
							dataStorage.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
							if(gp.obj[mapNum][i].name.equals("Door")){
								dataStorage.mapNums[mapNum][i] = gp.obj[mapNum][i].doorMapNum;
								dataStorage.doorObjectIndex[mapNum][i] = gp.obj[mapNum][i].doorObjectIndex;
							}
							else if(gp.obj[mapNum][i].name.equals("TeleportDoor")){
								dataStorage.mapNums[mapNum][i] = gp.obj[mapNum][i].doorMapNum;
								dataStorage.TeleportDoorCol[mapNum][i] = gp.obj[mapNum][i].tpNewCol;
								dataStorage.TeleportDoorRow[mapNum][i] = gp.obj[mapNum][i].tpNewRow;
								dataStorage.TeleportDoorNewArea[mapNum][i] = gp.obj[mapNum][i].newArea;
							}
							else if(gp.obj[mapNum][i].name.equals("ColorfulDoor")){
								dataStorage.mapNums[mapNum][i] = gp.obj[mapNum][i].doorMapNum;
								dataStorage.TeleportDoorCol[mapNum][i] = gp.obj[mapNum][i].tpNewCol;
								dataStorage.TeleportDoorRow[mapNum][i] = gp.obj[mapNum][i].tpNewRow;
								dataStorage.TeleportDoorNewArea[mapNum][i] = gp.obj[mapNum][i].newArea;
								dataStorage.ColorfulDoorLocked = gp.obj[mapNum][i].locked;
							}
						}
					}
				}
				OS.writeObject(dataStorage);
			} catch (Exception e) {
				System.out.println("Save Error.");
				e.printStackTrace();
			}
		}
	}
	
	public void load() {
		
		try {
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			GameDataStorage dataStorage = (GameDataStorage)IS.readObject();

			//Game State:
			gp.currentArea = dataStorage.currentArea;
			gp.currentMap = dataStorage.currentMap;
			gp.skeletonGiantDefeated = dataStorage.skeletonGiantDefeated;
			
			//Seting player's stats to what we read from the save file:
			gp.player.life = dataStorage.life;
			gp.player.maxLife = dataStorage.maxLife;
			gp.player.coin = dataStorage.coin;
			gp.player.maxArrows = dataStorage.maxArrows;
			gp.player.arrows = dataStorage.arrows;
			gp.player.maxAmmo = dataStorage.maxAmmo;
			gp.player.ammo = dataStorage.ammo;
			gp.player.maxMana = dataStorage.maxMana;
			gp.player.mana = dataStorage.mana;
			gp.player.level = dataStorage.level;
			gp.player.strength = dataStorage.strength;
			gp.player.dexterity = dataStorage.dexterity;
			gp.player.exp = dataStorage.exp;
			gp.player.nextLevelExp = dataStorage.nextLevelExp;
			gp.player.playerClass = dataStorage.playerClass;
			gp.player.worldX = dataStorage.worldX;
			gp.player.worldY = dataStorage.worldY;

			switch(gp.player.playerClass){
				case "Fighter": gp.player.projectile = new Arrow(gp); break;
				case "Wizard": gp.player.projectile = new Fireball(gp); break;
				case "Peasant": gp.player.projectile = new Rock(gp); break;
			}

			//Inventory:
			gp.player.inventory.clear();
			for(int i = 0; i < dataStorage.itemNames.size(); i++){
				gp.player.inventory.add(gp.entityGenerator.getObject(dataStorage.itemNames.get(i)));
				gp.player.inventory.get(i).amount = dataStorage.itemAmmounts.get(i);
			}
			//Equiped Items:
			gp.player.currentWeapon = gp.player.inventory.get(dataStorage.currentWeaponSlot);
			gp.player.currentShield = gp.player.inventory.get(dataStorage.currentShieldSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getPlayerAttackImage();

			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
				for(int i = 0; i < gp.obj[1].length; i++){
					if(dataStorage.savedObjectNames[mapNum][i].equals("NA")){
						gp.obj[mapNum][i] = null;
					}
					//If Its not a door:
					else if(!dataStorage.savedObjectNames[mapNum][i].equals("Door") && 
					!dataStorage.savedObjectNames[mapNum][i].equals("TeleportDoor") && 
					!dataStorage.savedObjectNames[mapNum][i].equals("ColorfulDoor")){
						gp.obj[mapNum][i] = new Entity(gp);
						gp.obj[mapNum][i] = gp.entityGenerator.getObject(dataStorage.savedObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = dataStorage.savedObjectsWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = dataStorage.savedObjectsWorldY[mapNum][i];
						gp.obj[mapNum][i].opened = dataStorage.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened == true){
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2; //the Open Image
						}
					}
					//It is a Door:
					else{
						gp.obj[mapNum][i] = new Entity(gp);
						if(dataStorage.savedObjectNames[mapNum][i].equals("Door")){
							gp.obj[mapNum][i] = new Door(gp, dataStorage.mapNums[mapNum][i], dataStorage.doorObjectIndex[mapNum][i]);
						}
						else if(dataStorage.savedObjectNames[mapNum][i].equals("TeleportDoor")){
							gp.obj[mapNum][i] = new TeleportDoor(gp, dataStorage.mapNums[mapNum][i], 
								dataStorage.TeleportDoorCol[mapNum][i], dataStorage.TeleportDoorRow[mapNum][i],
								dataStorage.TeleportDoorNewArea[mapNum][i]);
						}
						else if(dataStorage.savedObjectNames[mapNum][i].equals("ColorfulDoor")){
							gp.obj[mapNum][i] = new ColorfulDoor(gp, dataStorage.mapNums[mapNum][i], 
								dataStorage.TeleportDoorCol[mapNum][i], dataStorage.TeleportDoorRow[mapNum][i],
								dataStorage.TeleportDoorNewArea[mapNum][i]);
							gp.obj[mapNum][i].locked = dataStorage.ColorfulDoorLocked;
						}
						gp.obj[mapNum][i].worldX = dataStorage.savedObjectsWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = dataStorage.savedObjectsWorldY[mapNum][i];
					}
				}
			}
		} catch (Exception e) {
			System.out.println("Load Error.");
			e.printStackTrace();
		}
	}
}