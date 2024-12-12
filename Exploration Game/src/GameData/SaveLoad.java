package GameData;

import Entity.Entity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import main.GamePanel;
import objects.Arrow;
import objects.Axe;
import objects.Bark;
import objects.BlueChest;
import objects.BlueKey;
import objects.Fireball;
import objects.Lance;
import objects.Lantern;
import objects.MetalShield;
import objects.PaperClip;
import objects.RedKey;
import objects.RedPotion;
import objects.Rock;
import objects.Shield;
import objects.Staff;
import objects.Tent;
import objects.YellowKey;

public class SaveLoad {

	GamePanel gp;
	
	public Entity getInventoryObject(String itemName){
		Entity object = null;
		//case "": object = new (gp); break;
		switch(itemName){

			case "Axe": object = new Axe(gp); break;
			case "Bark": object = new Bark(gp); break;
			case "BlueKey": object = new BlueKey(gp); break;
			case "Lance": object = new Lance(gp); break;
			case "Lantern": object = new Lantern(gp); break;
			case "MetalShield": object = new MetalShield(gp); break;
			case "PaperClip": object = new PaperClip(gp); break;
			case "RedKey": object = new RedKey(gp); break;
			case "RedPotion": object = new RedPotion(gp); break;
			case "Shield": object = new Shield(gp); break;
			case "Staff": object = new Staff(gp); break;
			case "Tent": object = new Tent(gp); break;
			case "YellowKey": object = new YellowKey(gp); break;
		}

		return object;
	}


	//Need to take Doors and Chests out of the Gampanel "obj" array.
	public Entity getMapObjects(int itemID){
		Entity object = null;

		switch (itemID) {
			case 0: object = new Axe(gp); break;
			case 1: object = new Bark(gp); break;
			case 2: object = new BlueKey(gp); break;
			case 3: object = new Lance(gp); break;
			case 4: object = new Lantern(gp); break;
			case 5: object = new MetalShield(gp); break;
			case 6: object = new PaperClip(gp); break;
			case 7: object = new RedKey(gp); break;
			case 8: object = new RedPotion(gp); break;
			case 9: object = new Shield(gp); break;
			case 10: object = new Staff(gp); break;
			case 11: object = new Arrow(gp); break;



			default:
				break;
		}

		return object;
	}

	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream OS = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			GameDataStorage dataStorage = new GameDataStorage();
			
			//Player's Stats
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
			// for(int i = 0; i < gp.maxMap ; i++){
			// 	for(int j = 0; j < 50; j++){
			// 	}
			// }

			//System.out.println(dataStorage.savedObjects[0][0]);

			OS.writeObject(dataStorage);
			
		} catch (Exception e) {
			System.out.println("Save Error.");
			e.printStackTrace();
		}
	}
	
	public void load() {
		
		try {
			ObjectInputStream IS = new ObjectInputStream(new FileInputStream(new File("save.dat")));
			GameDataStorage dataStorage = (GameDataStorage)IS.readObject();

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
				gp.player.inventory.add(getInventoryObject(dataStorage.itemNames.get(i)));
				gp.player.inventory.get(i).amount = dataStorage.itemAmmounts.get(i);
			}

			//Equiped Items:
			gp.player.currentWeapon = gp.player.inventory.get(dataStorage.currentWeaponSlot);
			gp.player.currentShield = gp.player.inventory.get(dataStorage.currentShieldSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getPlayerAttackImage();


			//System.out.println("first Item: " + getObject(dataStorage.savedObjects[0][3]));
			// //Map items:
			// for(int i = 0; i < gp.maxMap; i++){
			// 	for(int j = 0; j < 50; j++){
			// 		if(dataStorage.savedObjects[i][j] != null){
			// 			gp.obj[i][j] = getObject(dataStorage.savedObjects[i][j]);
			// 			gp.obj[i][j].worldX = gp.tileSize * dataStorage.savedObjectsWorldX[i][j];
			// 			gp.obj[i][j].worldY = gp.tileSize * dataStorage.savedObjectsWorldY[i][j];
			// 		}
			// 		if(gp.obj == null){
			// 			System.out.println("Load Error: GamePanel Object Array is Null");
			// 		}
			// 	}

			// }
		
			
		} catch (Exception e) {
			System.out.println("Load Error.");
			e.printStackTrace();
		}
	}

}
