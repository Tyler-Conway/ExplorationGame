package main;

import Entity.*;
import InteractiveTiles.DamagedWall;
import InteractiveTiles.DryTree;
import InteractiveTiles.MetalPlate;
import monster.*;
import objects.*;

public class AssetSetter {

	GamePanel gp;
	public String playerClass = "";
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void placeAsset(Entity AssetArray[][], int mapNum, int index, Entity object, int x, int y){
		AssetArray[mapNum][index] = object;
		AssetArray[mapNum][index].worldX = gp.tileSize * x;
		AssetArray[mapNum][index].worldY = gp.tileSize * y;
	}

	public void setObjects() {
		int mapNum = gp.world01;
		int i = 0;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding01, 24, 23,gp.inside), 38, 44); i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding03, 24, 23,gp.inside), 31, 28); i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding02, 24, 23,gp.inside), 47, 44); i++;
		placeAsset(gp.obj, mapNum, i, new Tent(gp), 24, 39); i++;
		placeAsset(gp.obj, mapNum, i, new Lantern(gp), 12, 46); i++;
		placeAsset(gp.obj, mapNum, i, new YellowDoor(gp), 5, 4); i++;
		placeAsset(gp.obj, mapNum, i, new YellowChest(gp), 16, 35); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 16, 45); i++;
		placeAsset(gp.obj, mapNum, i, new Axe(gp), 4, 1); i++;
		placeAsset(gp.obj, mapNum, i, new MetalShield(gp), 6, 1); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 6, 3); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 35, 1); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 6, 45); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 23, 47); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 45, 1); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 4, 3); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 28, 42); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 35, 6); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 7, 37); i++;
		placeAsset(gp.obj, mapNum, i, new Heart(gp), 21, 35); i++;

		i = 0;
		mapNum = gp.world02;
		placeAsset(gp.obj, mapNum, i, new YellowChest(gp), 45, 2); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 25, 27); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 18, 8); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 10, 12); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 4, 14); i++;
		placeAsset(gp.obj, mapNum, i, new Tent(gp), 7, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 4, 39); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 5, 39); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 6, 39); i++;
		
		i = 0;
		mapNum = gp.stoneBuilding01;
		placeAsset(gp.obj, mapNum, i, new RedChest(gp), 19, 14); i++;
		placeAsset(gp.obj, mapNum, i, new BlueDoor(gp), 26, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 22, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 22, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 22, 16); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 22, 15); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 22, 18); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 30, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 16); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 15); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 30, 14); i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world01, 38, 46,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 19, 17); i++;
		
		i = 0;
		mapNum = gp.stoneBuilding02;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world01, 47, 46,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 20, 18); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 29, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 18, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 23, 14); i++;
		placeAsset(gp.obj, mapNum, i, new YellowChest(gp), 30, 14); i++;
		placeAsset(gp.obj, mapNum, i, new MetalShield(gp), 25, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 25, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 18, 17); i++;

		i = 0;
		mapNum = gp.stoneBuilding03;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world01, 31, 31,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 20, 17); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 28, 20); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 30, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 24, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 25, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 24); i++;
		placeAsset(gp.obj, mapNum, i, new Heart(gp), 20, 14); i++;
		placeAsset(gp.obj, mapNum, i, new Heart(gp), 22, 16); i++;
		placeAsset(gp.obj, mapNum, i, new Heart(gp), 18, 16); i++;
		placeAsset(gp.obj, mapNum, i, new BlueChest(gp), 27, 14); i++;
		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 24, 16); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 30, 19); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 29, 19); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 18, 24); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 19, 24); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 18, 23); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 24, 16); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 30, 19); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 29, 19); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 18, 24); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 19, 24); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 18, 23); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 24, 16); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 30, 19); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 29, 19); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 18, 24); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 19, 24); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 18, 23); i++;
				break;
		}

		i = 0;
		mapNum = gp.stoneBuilding04;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world03, 5, 5,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new BlueDoor(gp), 24, 21); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 20, 18); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 28, 18); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 18, 14); i++;
		placeAsset(gp.obj, mapNum, i, new YellowChest(gp), 30, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 25, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 25, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 30, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 27, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 18, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 21, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 30, 17); i++;
		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 23, 17); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 22, 17); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 23, 16); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 23, 17); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 22, 17); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 23, 16); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 23, 17); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 22, 17); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 23, 16); i++;
				break;
		}

		i = 0;
		mapNum = gp.stoneBuilding05;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world03, 11, 5,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 29, 17); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 20, 19); i++;
		placeAsset(gp.obj, mapNum, i, new BlueChest(gp), 29, 14); i++;	
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 28, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 18, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 25, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 25, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 18, 18); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 22, 16); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 21, 16); i++;
		
		i = 0;
		mapNum = gp.world03;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding04, 24, 24,gp.inside), 5, 3); i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding05, 24, 24,gp.inside), 11, 3); i++;
		placeAsset(gp.obj, mapNum, i, new RedDoor(gp), 7, 39); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 6, 36); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 6, 38); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 8, 38); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 8, 36); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 7, 37); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 3, 48); i++;
		placeAsset(gp.obj, mapNum, i, new RedChest(gp), 29, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 42, 46); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 9, 1); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 43, 46); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 44, 46); i++;
		placeAsset(gp.obj, mapNum, i, new BlueChest(gp), 47, 40); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 1, 5); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 8, 23); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 6, 20); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 10, 20); i++;
		placeAsset(gp.obj, mapNum, i, new Tent(gp), 4, 28); i++;
		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 1, 6); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 1, 7); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 1, 6); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 1, 7); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 1, 6); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 1, 7); i++;
				break;
		}

		i = 0;
		mapNum = gp.world04;
		placeAsset(gp.obj, mapNum, i, new ColorfulDoor(gp, gp.world05 ,19, 47,gp.outside), 19, 0); i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.keyForge, 24, 23,gp.inside), 31, 27); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 3, 22); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 3, 23); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 4, 22); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 13, 28); i++;
		placeAsset(gp.obj, mapNum, i, new BlueChest(gp), 8, 16); i++;
		placeAsset(gp.obj, mapNum, i, new Heart(gp), 11, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 2, 6); i++;
		placeAsset(gp.obj, mapNum, i, new RedChest(gp), 46, 2); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 47, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 42, 34); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 43, 34); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 44, 34); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 17, 38); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 3, 43); i++;
		placeAsset(gp.obj, mapNum, i, new CoinBronze(gp), 7, 41); i++;
		placeAsset(gp.obj, mapNum, i, new Tent(gp), 10, 46); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 23, 18); i++;
		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 13, 22); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 12, 22); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 13, 23); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 15, 13); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 28, 39); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 13, 22); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 12, 22); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 13, 23); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 15, 13); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 28, 39); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 13, 22); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 12, 22); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 13, 23); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 15, 13); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 28, 39); i++;
				break;
		}

		i = 0;
		mapNum = gp.keyForge;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.world04, 31, 29, gp.outside), 24, 25); i++;

		i = 0;
		mapNum = gp.lootCabin01;
		//Any additional objects in a loot cabin should come first:
		setupLootCabin(gp.lootCabin01, i);

		i = 0;
		mapNum = gp.lootCabin02;
		//Any additional objects in a loot cabin should come first:
		placeAsset(gp.obj, mapNum, i, new RedChest(gp), 24, 23); i++;
		setupLootCabin(gp.lootCabin02, i);

		i = 0;
		mapNum = gp.dungeon01;
		placeAsset(gp.obj, mapNum, i, new Pickaxe(gp), 19, 37); i++;
		placeAsset(gp.obj, mapNum, i, new IronDoor(gp), 21, 5); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 20, 25); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 30, 19); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 4, 17); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 47, 16); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 48, 5); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 41, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 44, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 44, 28); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 41, 28); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 48, 5); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 1, 43); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 1, 44); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 42, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 26, 38); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 28, 38); i++;
		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 46, 40); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 25, 45); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 5, 36); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 11, 46); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 6, 11); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 2, 2); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 35, 6); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 45, 13); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 46, 40); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 25, 45); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 5, 36); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 11, 46); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 6, 11); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 2, 2); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 35, 6); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 45, 13); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 46, 40); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 25, 45); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 5, 36); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 11, 46); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 6, 11); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 2, 2); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 35, 6); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 45, 13); i++;
				break;
		}

		i = 0;
		mapNum = gp.dungeon02;
		placeAsset(gp.obj, mapNum, i, new IronDoor(gp), 24, 17); gp.obj[mapNum][i].bossRoomDoor = true; i++;
		placeAsset(gp.obj, mapNum, i, new Amethyst(gp), 24, 11);i++;

		i = 0;
		mapNum = gp.beach01;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 30, 7);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 42, 10);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 30, 12);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 20, 14);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 17, 8);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 10, 3);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 20, 6);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 3, 4);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 8, 9);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 10, 10);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 37, 9);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 45, 1);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 36, 4);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 13, 11);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 40, 4);i++;
		placeAsset(gp.obj, mapNum, i, new SpiralShell(gp), 44, 2);i++;
		placeAsset(gp.obj, mapNum, i, new SpiralShell(gp), 27, 3);i++;
		placeAsset(gp.obj, mapNum, i, new SpiralShell(gp), 2, 2);i++;
		placeAsset(gp.obj, mapNum, i, new SpiralShell(gp), 40, 12);i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 45, 26);i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 45, 27);i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 44, 26);i++;

		mapNum = gp.beach02;
		i = 0;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 6, 3);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 8, 8);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 13, 5);i++;
		placeAsset(gp.obj, mapNum, i, new Seaweed(gp), 14, 10);i++;
		placeAsset(gp.obj, mapNum, i, new ClamShell(gp), 17, 2);i++;
		placeAsset(gp.obj, mapNum, i, new SpiralShell(gp), 10, 8);i++;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.stoneBuilding06, 24, 23,gp.inside), 14, 1); i++;

		mapNum = gp.stoneBuilding06;
		i = 0;
		placeAsset(gp.obj, mapNum, i, new TeleportDoor(gp, gp.beach02, 14, 3,gp.outside), 24, 25); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 20, 18); i++;
		placeAsset(gp.obj, mapNum, i, new Door(gp, mapNum, i), 29, 18); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 25, 17); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 23, 17); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 18, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 19, 14); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 18, 15); i++;

		switch(gp.player.playerClass){
			case "Fighter": 
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 30, 14); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 29, 14); i++;
				placeAsset(gp.obj, mapNum, i, new Arrow(gp), 30, 15); i++;
				break;
			case "Wizard":
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 30, 14); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 29, 14); i++;
				placeAsset(gp.obj, mapNum, i, new ManaCrystal(gp), 30, 15); i++;
				break;
			case "Peasant":
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 30, 14); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 29, 14); i++;
				placeAsset(gp.obj, mapNum, i, new Rock(gp), 30, 15); i++;
				break;
		}
	}
	
	public void setupLootCabin(int mapNum, int i) {
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 27, 26); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 21, 26); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 21, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 27, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 22, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 23, 19); i++;
		placeAsset(gp.obj, mapNum, i, new Tent(gp), 24, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 25, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 26, 19); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 21, 20); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 21, 21); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 21, 22); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 21, 23); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 21, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 21, 25); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 27, 20); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 27, 21); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 27, 22); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 27, 23); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 27, 24); i++;
		placeAsset(gp.obj, mapNum, i, new CoinSilver(gp), 27, 25); i++;
		placeAsset(gp.obj, mapNum, i, new CoinGold(gp), 24, 22); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 26, 26); i++;
		placeAsset(gp.obj, mapNum, i, new RedPotion(gp), 22, 26); i++;
	}
	
	public void setNPC() {
		int i = 0;
		placeAsset(gp.npc, gp.world01, i, new NPCWizard(gp), 12, 42); i++; i = 0;
		
		placeAsset(gp.npc, gp.world02, i, new SkeletonStatue(gp), 20, 18); i++;
		placeAsset(gp.npc, gp.world02, i, new Fisher(gp), 35, 41); i++; i = 0;
		
		placeAsset(gp.npc, gp.cabin01, i, new BlueTrader(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.cabin02, i, new RedTrader(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.cabin03, i, new YellowTrader(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.stoneBuilding02, i, new GreenNPC(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.stoneBuilding05, i, new GreenNPC(gp), 24, 20); i++; i = 0;
		placeAsset(gp.npc, gp.stoneBuilding06, i, new GreenNPC(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.keyForge, i, new KeyForger(gp), 24, 19); i++; i = 0;
		placeAsset(gp.npc, gp.beach01, i, new Fisher(gp), 40, 28); i++; i = 0;
		placeAsset(gp.npc, gp.world04, i, new Fisher(gp), 32, 6); i++; i = 0;

		//Boulders are considered NPC's for Collision and to detect
		//if their solid areas intersect with the metal plates.
		setupBoulders();
	}

	public void setupBoulders(){
		int mapNum = gp.dungeon01;
		int i = 0;
		placeAsset(gp.npc, mapNum, i, new BigRock(gp), 7, 4); i++;
		placeAsset(gp.npc, mapNum, i, new BigRock(gp), 24, 6); i++;
		placeAsset(gp.npc, mapNum, i, new BigRock(gp), 18, 17); i++;
	}
	
	public void setMonster() {
		
		int mapNum = gp.world01;
		int i = 0;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 12, 14); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 13, 15); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 11, 13); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 40, 3); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 47, 7); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 43, 5); i++;
		
		mapNum = gp.world02;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Oger(gp), 43, 11); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 25, 3); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 25, 8); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 3, 42); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 5, 42); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 7, 42); i++;

		mapNum = gp.world03;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Oger(gp), 42, 15); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 21, 29); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 24, 31); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 26, 36); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 10, 46); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 6, 46); i++;
		placeAsset(gp.monster, mapNum, i, new Oger(gp), 8, 20); i++;

		mapNum = gp.world04;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 10, 25); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 6, 25); i++;
		placeAsset(gp.monster, mapNum, i, new Oger(gp), 3, 9); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 41, 10); i++;
		placeAsset(gp.monster, mapNum, i, new Slime(gp), 41, 3); i++;
		placeAsset(gp.monster, mapNum, i, new Oger(gp), 43, 37); i++;

		mapNum = gp.dungeon01;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 10, 35); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 39, 44); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 42, 19); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 43, 3); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 1, 13); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 25, 26); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 10, 3); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 23, 36); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 28, 13); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 38, 32); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 24, 9); i++;
		placeAsset(gp.monster, mapNum, i, new Bat(gp), 13, 18); i++;

		mapNum = gp.beach01;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 25, 10);i++;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 43, 9);i++;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 7, 14);i++;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 10, 3);i++;
		
		mapNum = gp.beach02;
		i = 0;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 10, 4);i++;
		placeAsset(gp.monster, mapNum, i, new Crab(gp), 14, 7);i++;
	}

	public void setInteractiveTiles() {
		int i = 0;
		int mapNum = 0;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 28);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 28);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 24, 34);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 13);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 11);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 15, 32);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 2, 6);i++;
		
		mapNum = gp.world02;
		i = 0;
		gp.iTile[mapNum][i] = new DryTree(gp, 45, 7);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 26, 16);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 20, 6);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 14, 34);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 9, 43);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 5, 41);i++;
		
		mapNum = gp.world03;
		i = 0;
		gp.iTile[mapNum][i] = new DryTree(gp, 35, 22);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 42, 19);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 42, 12);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 17);i++;

		mapNum = gp.world04;
		i = 0;
		gp.iTile[mapNum][i] = new DryTree(gp, 5, 14);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 2, 7);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 36, 4);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 44, 6);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 44, 12);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 19, 34);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 43, 43);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 43, 36);i++;

		mapNum = gp.dungeon01;
		i = 0;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 19, 32);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 16, 36);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 22, 36);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 25, 4);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 40, 26);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 45, 26);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 14, 22);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 22, 22);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 41, 42);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 18, 6);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 35, 14);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 3, 16);i++;
		gp.iTile[mapNum][i] = new DamagedWall(gp, 38, 4);i++;
		gp.iTile[mapNum][i] = new MetalPlate(gp, 15, 2);i++;
		gp.iTile[mapNum][i] = new MetalPlate(gp, 18, 14);i++;
		gp.iTile[mapNum][i] = new MetalPlate(gp, 25, 6);i++;
	}
}
