package main;

import Entity.BlueTrader;
import Entity.GreenNPC;
import Entity.NPCWizard;
import Entity.RedTrader;
import Entity.YellowTrader;
import InteractiveTiles.DryTree;
import monster.Oger;
import monster.Slime;
import objects.*;

public class AssetSetter {

	GamePanel gp;
	public String playerClass = "";
	public int a;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObjects() {
		//increment mapNum to place assets on different maps:
		
		int mapNum = gp.world01;
		a = 0;
		int i = 0;
		
		//the newMap number, newMapCol, newMapRow:
		gp.obj[mapNum][a] = new TeleportDoor(gp, gp.stoneBuilding01, 24, 23);
		gp.obj[mapNum][a].worldX = gp.tileSize * 38;
		gp.obj[mapNum][a].worldY = gp.tileSize * 44;
		a++;
		gp.obj[mapNum][a] = new TeleportDoor(gp, gp.stoneBuilding03, 24, 23);
		gp.obj[mapNum][a].worldX = gp.tileSize * 31;
		gp.obj[mapNum][a].worldY = gp.tileSize * 28;
		a++;
		gp.obj[mapNum][a] = new TeleportDoor(gp, gp.stoneBuilding02, 24, 23);
		gp.obj[mapNum][a].worldX = gp.tileSize * 47;
		gp.obj[mapNum][a].worldY = gp.tileSize * 44;
		a++;
		gp.obj[mapNum][a] = new Tent(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 24;
		gp.obj[mapNum][a].worldY = gp.tileSize * 39;
		a++;
		gp.obj[mapNum][a] = new Lantern(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 12;
		gp.obj[mapNum][a].worldY = gp.tileSize * 46;
		a++;
		gp.obj[mapNum][a] = new YellowDoor(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 5;
		gp.obj[mapNum][a].worldY = gp.tileSize * 4;
		a++;
		gp.obj[mapNum][a] = new YellowChest(gp, new YellowKey(gp));
		gp.obj[mapNum][a].worldX = gp.tileSize * 16;
		gp.obj[mapNum][a].worldY = gp.tileSize * 35;
		a++;
		gp.obj[mapNum][a] = new YellowKey(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 16;
		gp.obj[mapNum][a].worldY = gp.tileSize * 45;
		a++;
		gp.obj[mapNum][a] = new Axe(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 4;
		gp.obj[mapNum][a].worldY = gp.tileSize * 1;
		a++;
		gp.obj[mapNum][a] = new MetalShield(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 6;
		gp.obj[mapNum][a].worldY = gp.tileSize * 1;
		a++;
		gp.obj[mapNum][a] = new RedPotion(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 6;
		gp.obj[mapNum][a].worldY = gp.tileSize * 3;
		a++;
		gp.obj[mapNum][a] = new RedPotion(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 35;
		gp.obj[mapNum][a].worldY = gp.tileSize * 1;
		a++;
		gp.obj[mapNum][a] = new CoinGold(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 6;
		gp.obj[mapNum][a].worldY = gp.tileSize * 45;
		a++;
		gp.obj[mapNum][a] = new CoinBronze(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 23;
		gp.obj[mapNum][a].worldY = gp.tileSize * 47;
		a++;
		gp.obj[mapNum][a] = new CoinBronze(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 45;
		gp.obj[mapNum][a].worldY = gp.tileSize * 1;
		a++;
		gp.obj[mapNum][a] = new CoinGold(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 4;
		gp.obj[mapNum][a].worldY = gp.tileSize * 3;
		a++;
		gp.obj[mapNum][a] = new CoinSilver(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 28;
		gp.obj[mapNum][a].worldY = gp.tileSize * 42;
		a++;
		gp.obj[mapNum][a] = new CoinGold(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 35;
		gp.obj[mapNum][a].worldY = gp.tileSize * 6;
		a++;
		gp.obj[mapNum][a] = new RedPotion(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 7;
		gp.obj[mapNum][a].worldY = gp.tileSize * 37;
		a++;
		gp.obj[mapNum][a] = new Heart(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 21;
		gp.obj[mapNum][a].worldY = gp.tileSize * 35;
		a++;
		
		i = 0;
		mapNum = gp.stoneBuilding01;
		gp.obj[mapNum][i] = new RedChest(gp, new RedKey(gp));
		gp.obj[mapNum][i].worldX = gp.tileSize * 19;
		gp.obj[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		gp.obj[mapNum][i] = new BlueDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[mapNum][i] = new RedPotion(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 16;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.obj[mapNum][i] = new RedPotion(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 30;
		gp.obj[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		gp.obj[mapNum][i] = new TeleportDoor(gp, gp.world01, 38, 46);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		gp.obj[mapNum][i] = new Door(gp, mapNum, i);
		gp.obj[mapNum][i].worldX = gp.tileSize * 19;
		gp.obj[mapNum][i].worldY = gp.tileSize * 17;
		i++;
		
		
		i = 0;
		mapNum = gp.stoneBuilding02;
		gp.obj[mapNum][i] = new TeleportDoor(gp, gp.world01, 47, 46);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		gp.obj[mapNum][i] = new BlueDoor(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 5;
		gp.obj[mapNum][i].worldY = gp.tileSize * 4;
		i++;
		gp.obj[mapNum][i] = new Door(gp, mapNum, i);
		gp.obj[mapNum][i].worldX = gp.tileSize * 20;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		gp.obj[mapNum][i] = new Door(gp, mapNum, i);
		gp.obj[mapNum][i].worldX = gp.tileSize * 29;
		gp.obj[mapNum][i].worldY = gp.tileSize * 18;
		i++;
		
		
		i = 0;
		mapNum = gp.stoneBuilding03;
		gp.obj[mapNum][i] = new TeleportDoor(gp, gp.world01, 30, 31);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		
		setupLootCabin(gp.lootCabin01);

		i = 0;
		mapNum = gp.world03;
		gp.obj[mapNum][a] = new RedDoor(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 7;
		gp.obj[mapNum][a].worldY = gp.tileSize * 39;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 6;
		gp.obj[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 6;
		gp.obj[mapNum][i].worldY = gp.tileSize * 38;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 8;
		gp.obj[mapNum][i].worldY = gp.tileSize * 38;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 8;
		gp.obj[mapNum][i].worldY = gp.tileSize * 36;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 7;
		gp.obj[mapNum][i].worldY = gp.tileSize * 37;
		i++;

		setupLootCabin(gp.lootCabin02);

		//LootCabin02:
		i = 0;
		mapNum = gp.lootCabin02;

		gp.obj[mapNum][a] = new RedKey(gp);
		gp.obj[mapNum][a].worldX = gp.tileSize * 24;
		gp.obj[mapNum][a].worldY = gp.tileSize * 23;
		a++;
		
	}
	
	public void setupLootCabin(int mapNum) {
		int i = 0;
		
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 23;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new Tent(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 25;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		gp.obj[mapNum][i].worldY = gp.tileSize * 19;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 21;
		gp.obj[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 20;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 21;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 23;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 24;
		i++;
		gp.obj[mapNum][i] = new CoinSilver(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 27;
		gp.obj[mapNum][i].worldY = gp.tileSize * 25;
		i++;
		gp.obj[mapNum][i] = new CoinGold(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 24;
		gp.obj[mapNum][i].worldY = gp.tileSize * 22;
		i++;
		gp.obj[mapNum][i] = new RedPotion(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 26;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		i++;
		gp.obj[mapNum][i] = new RedPotion(gp);
		gp.obj[mapNum][i].worldX = gp.tileSize * 22;
		gp.obj[mapNum][i].worldY = gp.tileSize * 26;
		i++;
	}
	
	
	public void setClassSpecificObjects(int mapNum, int i) {

		//CLASS SPECIFIC OBJECTS (They are all placed on the same tiles, and are changed depending on playerClass): 
		if(gp.player.playerClass.equals("Knight") == false) {
			if(gp.player.playerClass.contentEquals("Fighter")) {
				gp.obj[mapNum][i] = new Arrow(gp);
				gp.obj[mapNum][i].worldX = gp.tileSize * 21;
				gp.obj[mapNum][i].worldY = gp.tileSize * 48;
				i++;
			}
			if(gp.player.playerClass.contentEquals("Wizard")) {
				gp.obj[mapNum][i] = new ManaCrystal(gp);
				gp.obj[mapNum][i].worldX = gp.tileSize * 21;
				gp.obj[mapNum][i].worldY = gp.tileSize * 48;
				i++;
			}
			if(gp.player.playerClass.contentEquals("Peasant")) {
				gp.obj[mapNum][i] = new Rock(gp);
				gp.obj[mapNum][i].worldX = gp.tileSize * 21;
				gp.obj[mapNum][i].worldY = gp.tileSize * 48;
				i++;
			}
		}
		else {
			//Erase the ClassSpecificObjects at this positon in the array:
			gp.obj[mapNum][i] = null;
			i++;
		}
	}
	
	public void setNPC() {
		
		int mapNum = gp.world01;
		int i = 0;
		
		//MAP 1 (World01):
		gp.npc[mapNum][i] = new NPCWizard(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 12;
		gp.npc[mapNum][i].worldY = gp.tileSize * 42;
		
		//Cabin01
		mapNum = gp.cabin01;
		i = 0;
		gp.npc[mapNum][i] = new BlueTrader(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 24; // Col
		gp.npc[mapNum][i].worldY = gp.tileSize * 19; // Row
		
		//Cabin02
		mapNum = gp.cabin02;
		i = 0;
		gp.npc[mapNum][i] = new RedTrader(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 24; // Col
		gp.npc[mapNum][i].worldY = gp.tileSize * 19; // Row
		
		//Cabin03
		mapNum = gp.cabin03;
		i = 0;
		gp.npc[mapNum][i] = new YellowTrader(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 24; // Col
		gp.npc[mapNum][i].worldY = gp.tileSize * 19; // Row
		
		
		//StoneBuilding02:
		mapNum = gp.stoneBuilding02;
		i = 0;
		gp.npc[mapNum][i] = new GreenNPC(gp);
		gp.npc[mapNum][i].worldX = gp.tileSize * 24; // Col
		gp.npc[mapNum][i].worldY = gp.tileSize * 19; // Row
		
		
	}
	
	public void setMonster() {
		
		//increment mapNum to place assets on different maps:
		int mapNum = gp.world01;
		int i = 0;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 12;
		gp.monster[mapNum][i].worldY = gp.tileSize * 14;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 13;
		gp.monster[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 11;
		gp.monster[mapNum][i].worldY = gp.tileSize * 13;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 40;
		gp.monster[mapNum][i].worldY = gp.tileSize * 3;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 47;
		gp.monster[mapNum][i].worldY = gp.tileSize * 7;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 43;
		gp.monster[mapNum][i].worldY = gp.tileSize * 5;
		i++;
		
		
		mapNum = gp.world02;
		i = 0;
		gp.monster[mapNum][i] = new Oger(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 43;
		gp.monster[mapNum][i].worldY = gp.tileSize * 11;
		i++;



		mapNum = gp.world03;
		i = 0;
		gp.monster[mapNum][i] = new Oger(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 42;
		gp.monster[mapNum][i].worldY = gp.tileSize * 15;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 21;
		gp.monster[mapNum][i].worldY = gp.tileSize * 29;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 24;
		gp.monster[mapNum][i].worldY = gp.tileSize * 31;
		i++;
		gp.monster[mapNum][i] = new Slime(gp);
		gp.monster[mapNum][i].worldX = gp.tileSize * 26;
		gp.monster[mapNum][i].worldY = gp.tileSize * 36;
		i++;

	}

	public void setInteractiveTiles() {
		int i = 0;
		//increment mapNum to place assets on different maps:
		int mapNum = 0;
		
		//Tree Tile DryTrees:
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 29);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 28);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 27);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 26);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 25);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 24);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 23);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 22);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 21);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 29);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 28);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 27);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 26);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 25);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 24);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 23);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 22);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 8, 21);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 24, 34);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 18, 45);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 19, 45);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 20, 45);i++;
		
		//non-tree tile dry trees:
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 13);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 12, 12);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 15, 33);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 15, 32);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 15, 31);i++;
		
		
		//world02:
		mapNum = gp.world02;
		i = 0;
		
		gp.iTile[mapNum][i] = new DryTree(gp, 45, 7);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 26, 16);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 27, 16);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 28, 16);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 29, 16);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 30, 16);i++;
		


		//world03:
		mapNum = gp.world03;
		i = 0;
		gp.iTile[mapNum][i] = new DryTree(gp, 35, 22);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 42, 19);i++;
		gp.iTile[mapNum][i] = new DryTree(gp, 42, 12);i++;

		
		
		
	}
}
