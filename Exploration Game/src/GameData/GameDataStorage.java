package GameData;

import java.io.Serializable;
import java.util.ArrayList;


public class GameDataStorage implements Serializable{

	int gameMusic;

	//Player Info:
	int speed;
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
	int currentMap;
	String playerClass;
	String direction;

	//Item Inventory Data:
	ArrayList<String> itemNames = new ArrayList<>();
	ArrayList<Integer> itemAmmounts = new ArrayList<>();

	int currentWeaponSlot;
	int currentShieldSlot;

	//Map items:
	//TODO
	String savedObjectNames[][];
	int savedObjectsWorldX[][];
	int savedObjectsWorldY[][];
	boolean mapObjectOpened[][];
	int doorObjectIndex[][];

	int mapNums[][];
	int TeleportDoorCol[][];
	int TeleportDoorRow[][];
	int TeleportDoorNewArea[][];
	int currentArea;
	boolean ColorfulDoorLocked;
	boolean skeletonGiantDefeated;

	public GameDataStorage() {}
}