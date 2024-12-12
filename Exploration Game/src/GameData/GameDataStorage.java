package GameData;

import java.io.Serializable;
import java.util.ArrayList;
import Entity.Entity;


public class GameDataStorage implements Serializable{

	//Player Info:
	int life;
	int maxLife;
	int level; 
	int maxMana; 
	int mana;
	int arrows;
	int maxArrows;
	int ammo;
	int maxAmmo;
	int strength;
	int dexterity;
	int nextLevelExp;
	int coin;
	int exp;
	int worldX;
	int worldY;
	String playerClass;

	//Item Inventory Data:
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmmounts = new ArrayList<>();

	int currentWeaponSlot;
	int currentShieldSlot;

	//Map items:
	//TODO
	String savedObjects[][] = new String[20][50];
	int savedObjectsWorldX[][] = new int[20][50];
	int savedObjectsWorldY[][] = new int [20][50];



	
	
	public GameDataStorage() {
		
	}

}
