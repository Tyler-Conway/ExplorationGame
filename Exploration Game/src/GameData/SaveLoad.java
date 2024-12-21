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
import objects.BlueDoor;
import objects.BlueKey;
import objects.CoinBronze;
import objects.CoinGold;
import objects.CoinSilver;
import objects.Door;
import objects.Fireball;
import objects.Heart;
import objects.Lance;
import objects.Lantern;
import objects.ManaCrystal;
import objects.MetalShield;
import objects.PaperClip;
import objects.RedChest;
import objects.RedDoor;
import objects.RedKey;
import objects.RedPotion;
import objects.Rock;
import objects.Shield;
import objects.Staff;
import objects.Tent;
import objects.TriColorKey;
import objects.YellowChest;
import objects.YellowDoor;
import objects.YellowKey;

public class SaveLoad {

	GamePanel gp;
	
	//Cant Handle TP Doors:
	public Entity getObject(String itemName){
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
			case "BlueChest": object = new BlueChest(gp); break;
			case "RedChest": object = new RedChest(gp); break;
			case "YellowChest": object = new YellowChest(gp); break;
			case "YellowDoor": object = new YellowDoor(gp); break;
			case "TriColorKey": object = new TriColorKey(gp); break;
			case "RedDoor": object = new RedDoor(gp); break;
			case "BlueDoor": object = new BlueDoor(gp); break;
			case "Arrow": object = new Arrow(gp); break;
			case "Bronze Coin": object = new CoinBronze(gp); break;
			case "Gold Coin": object = new CoinGold(gp); break;
			case "Silver Coin": object = new CoinSilver(gp); break;
			case "Heart": object = new Heart(gp); break;
			case "ManaCrystal": object = new ManaCrystal(gp); break;
			case "Rock": object = new Rock(gp); break;
		}
		if(object == null){
			System.out.println("Returning a Null Object for: " + itemName);
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
			dataStorage.savedObjectNames = new String[gp.maxMap][gp.obj[1].length];
			dataStorage.savedObjectsWorldX = new int[gp.maxMap][gp.obj[1].length];
			dataStorage.savedObjectsWorldY = new int[gp.maxMap][gp.obj[1].length];
			dataStorage.mapObjectOpened = new boolean[gp.maxMap][gp.obj[1].length];


			for(int mapNum = 0; mapNum < gp.maxMap; mapNum++){
				for(int i = 0; i < gp.obj[1].length; i++){
					if(gp.obj[mapNum][i] == null || gp.obj[mapNum][i].name.equals("TeleportDoor") || gp.obj[mapNum][i].name.equals("Door") 
					|| gp.obj[mapNum][i].name.equals("ColorfulDoor")){
						dataStorage.savedObjectNames[mapNum][i] = "NA";
					}
					else{
						dataStorage.savedObjectNames[mapNum][i] = gp.obj[mapNum][i].name;
						dataStorage.savedObjectsWorldX[mapNum][i] = gp.obj[mapNum][i].worldX;
						dataStorage.savedObjectsWorldY[mapNum][i] = gp.obj[mapNum][i].worldY;
						dataStorage.mapObjectOpened[mapNum][i] = gp.obj[mapNum][i].opened;
					}
				}
			}

			// for(int i = 0; i < gp.obj[1].length; i++){
			// 	System.out.println("("+i+") "+dataStorage.mapObjectOpened[0][i]);
			// }

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
				gp.player.inventory.add(getObject(dataStorage.itemNames.get(i)));
				gp.player.inventory.get(i).amount = dataStorage.itemAmmounts.get(i);
			}

			//Equiped Items:
			gp.player.currentWeapon = gp.player.inventory.get(dataStorage.currentWeaponSlot);
			gp.player.currentShield = gp.player.inventory.get(dataStorage.currentShieldSlot);
			gp.player.getAttack();
			gp.player.getDefense();
			gp.player.getPlayerAttackImage();


			// for(int i = 0; i < gp.obj[1].length; i++){
			// 	System.out.println("("+i+") "+dataStorage.mapObjectOpened[0][i]);
			// }

			//Map Objects:
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
						gp.obj[mapNum][i] = getObject(dataStorage.savedObjectNames[mapNum][i]);
						gp.obj[mapNum][i].worldX = dataStorage.savedObjectsWorldX[mapNum][i];
						gp.obj[mapNum][i].worldY = dataStorage.savedObjectsWorldY[mapNum][i];
						gp.obj[mapNum][i].opened = dataStorage.mapObjectOpened[mapNum][i];
						if(gp.obj[mapNum][i].opened == true){
							gp.obj[mapNum][i].down1 = gp.obj[mapNum][i].image2; //the Open Image
						}
					}
					//It is a Door:
					else{

					}
				}
			}
		} catch (Exception e) {
			System.out.println("Load Error.");
			e.printStackTrace();
		}
	}

}
