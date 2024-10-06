package GameData;

import java.io.Serializable;
import java.util.ArrayList;

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



	
	
	public GameDataStorage() {
		
	}

}
