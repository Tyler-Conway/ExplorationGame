package Entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;
import monster.SkeletonBoss;
import objects.Bark;
import objects.Lance;
import objects.MetalShield;
import objects.PaperClip;
import objects.Shield;
import objects.Staff;

public class Player extends Entity{

	public final int screenX, screenY;
	public String playerClass = "Fighter";
	public boolean attackCanceled = false;
	public boolean lightUpdated = false;
	public boolean debuggingMode = false;
	int originalDexterity;
	int maxLevel = 4;
	
	public KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		//sets the collision area to a smaller rectangle within the player's tile:
		//(x = 8, y = 16, width and height of the rectangle is 32 pixels)
		//solidArea = new Rectangle(8, 16, 32, 32);
		solidArea = new Rectangle(gp.tileSize/6, gp.tileSize/3, gp.tileSize - (gp.tileSize/3), gp.tileSize - (gp.tileSize/3)-1);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
	}

	public void setDefaultValues() {
		setDefaultPositions();
		//Player Status:
		maxMana = 4;
		mana = maxMana;
		maxArrows = 5;
		arrows = maxArrows;
		maxAmmo = 6;
		ammo = 6;
		level = 1;
		exp = 0;
		nextLevelExp = 5;
		coin = 500;
		alreadyPlayedAttackSound = false;
		currentLight = null;

		//Default Player Stats and Items:
		switch(playerClass) {
		case "Fighter":
			defaultSpeed = 5;
			maxLife = 8;
			strength = 1; //More Strength = more attack damage.
			dexterity = 1; //More Dexterity = less damage taken.
			currentWeapon = new Lance(gp);
			currentShield = new Shield(gp);
			break;
		case "Knight":
			defaultSpeed = 4;
			maxLife = 10;
			strength = 1; //More Strength = more attack damage.
			dexterity = 2; //More Dexterity = less damage taken.
			currentWeapon = new Lance(gp);
			currentShield = new MetalShield(gp);
			break;
		case "Wizard":
			defaultSpeed = 5;
			maxLife = 6;
			strength = 1; //More Strength = more attack damage.
			dexterity = 1; //More Dexterity = less damage taken.
			currentWeapon = new Staff(gp);
			currentShield = new Shield(gp);
			break;
		case "Peasant":
			defaultSpeed = 5;
			maxLife = 6;
			strength = 1; //More Strength = more attack damage.
			dexterity = 1; //More Dexterity = less damage taken.
			currentWeapon = new PaperClip(gp);
			currentShield = new Bark(gp);
			break;
		}
		speed = defaultSpeed;
		life = maxLife;
		attack = getAttack(); // total attack = strength * weapon.
		defense = getDefense(); //total defense = shield * dexterity.
		getPlayerImage();
		setDialogue();
		getPlayerAttackImage();
		getPlayerGuardImage();
		setItems();
	}
	
	public void setDefaultPositions() {
		gp.currentMap = 0;		
		worldX = gp.tileSize * 12;
		worldY = gp.tileSize * 44;
		// gp.currentMap = gp.dungeon02;
		// worldX = gp.tileSize * 24;
		// worldY = gp.tileSize * 13;
		direction = "down";
	}
	
	public void restoreLifeAndProjectiles() {
		if(playerClass.equals("Fighter")) {arrows = maxArrows;}
		if(playerClass.equals("Wizard")) {mana = maxMana;}
		if(playerClass.equals("Peasant")) {ammo = maxAmmo;}
		
		invincible = false;
		transparent = false;
		life = maxLife;
		attacking = false;
		guarding = false;
		knockBack = false;
		lightUpdated = true;
	}
	
	public void setItems() {
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		//inventory.add(new TriColorKey(gp));
	}

	public int getCurrentWeaponSlot(){
		int slot = 0;
		for(int i = 0; i < inventory.size(); i++){
			if(inventory.get(i) == currentWeapon){slot = i;}
		}
		return slot;
	}

	public int getCurrentShieldSlot(){
		int slot = 0;
		for(int i = 0; i < inventory.size(); i++){
			if(inventory.get(i) == currentShield){slot = i;}
		}
		return slot;
	}
	 
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		motion1Duration = currentWeapon.motion1Duration;
		motion2Duration = currentWeapon.motion2Duration;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {return defense = dexterity * currentShield.defenseValue;}
	
	public void getPlayerImage() {
		
		//Default Image for Title Screen:
		down1 = setup("/player/FighterDown1", gp.tileSize, gp.tileSize);
		//Get the movement images for the Player:
		switch(playerClass) {
		case "Fighter":
			up1 = setup("/player/FighterUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/FighterUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/FighterDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/FighterDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/FighterLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/FighterLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/FighterRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/FighterRight2", gp.tileSize, gp.tileSize);
			break;
		case "Knight":
			up1 = setup("/player/KnightUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/KnightUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/KnightDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/KnightDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/KnightLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/KnightLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/KnightRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/KnightRight2", gp.tileSize, gp.tileSize);
			break;
		case "Wizard":
			up1 = setup("/player/WizardUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/WizardUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/WizardDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/WizardDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/WizardLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/WizardLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/WizardRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/WizardRight2", gp.tileSize, gp.tileSize);
			break;
		case "Peasant":
			up1 = setup("/player/PeasantUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/PeasantUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/PeasantDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/PeasantDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/PeasantLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/PeasantLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/PeasantRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/PeasantRight2", gp.tileSize, gp.tileSize);
			break;
		}
	}
	
	public void getPlayerAttackImage() {
		switch(playerClass) {
		case "Fighter":
			if(currentWeapon.type == type_Weapon) {
				if(currentWeapon.name.equals("Lance")) {
					attackUp1 = setup("/player/FighterLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/FighterLanceAttackUp2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/FighterLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/FighterLanceAttackDown2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/FighterLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/FighterLanceAttackLeft2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/FighterLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/FighterLanceAttackRight2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("PaperClip")) {
					attackUp1 = setup("/player/FighterLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/FighterPaperClipAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/FighterLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/FighterPaperClipAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/FighterLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/FighterPaperClipAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/FighterLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/FighterPaperClipAttackRight", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Staff")) {
					attackUp1 = setup("/player/FighterLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/FighterStaffAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/FighterLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/FighterStaffAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/FighterLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/FighterStaffAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/FighterLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/FighterStaffAttackRight", gp.tileSize*2, gp.tileSize);
				}
			}
			if(currentWeapon.type == type_Axe) {
				attackUp1 = setup("/player/FighterAxeAttackUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/FighterAxeAttackUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/FighterAxeAttackDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/FighterAxeAttackDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/FighterAxeAttackLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/FighterAxeAttackLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/FighterAxeAttackRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/FighterAxeAttackRight2", gp.tileSize*2, gp.tileSize);
			}
			if(currentWeapon.type == type_Pickaxe){
				attackUp1 = setup("/player/FighterPickaxeUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/FighterPickaxeUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/FighterPickaxeDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/FighterPickaxeDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/FighterPickaxeLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/FighterPickaxeLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/FighterPickaxeRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/FighterPickaxeRight2", gp.tileSize*2, gp.tileSize);
			}
			break;
		case "Knight":
			if(currentWeapon.type == type_Weapon) {
				if(currentWeapon.name.equals("Lance")) {
					attackUp1 = setup("/player/KnightLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/KnightLanceAttackUp2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/KnightLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/KnightLanceAttackDown2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/KnightLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/KnightLanceAttackLeft2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/KnightLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/KnightLanceAttackRight2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("PaperClip")) {
					attackUp1 = setup("/player/KnightLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/KnightPaperClipAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/KnightLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/KnightPaperClipAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/KnightLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/KnightPaperClipAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/KnightLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/KnightPaperClipAttackRight", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Staff")) {
					attackUp1 = setup("/player/KnightLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/KnightStaffAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/KnightLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/KnightStaffAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/KnightLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/KnightStaffAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/KnightLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/KnightStaffAttackRight", gp.tileSize*2, gp.tileSize);
				}
			}
			if(currentWeapon.type == type_Axe) {
				attackUp1 = setup("/player/KnightAxeAttackUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/KnightAxeAttackUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/KnightAxeAttackDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/KnightAxeAttackDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/KnightAxeAttackLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/KnightAxeAttackLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/KnightAxeAttackRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/KnightAxeAttackRight2", gp.tileSize*2, gp.tileSize);
			}
			if(currentWeapon.type == type_Pickaxe){
				attackUp1 = setup("/player/KnightPickaxeUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/KnightPickaxeUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/KnightPickaxeDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/KnightPickaxeDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/KnightPickaxeLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/KnightPickaxeLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/KnightPickaxeRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/KnightPickaxeRight2", gp.tileSize*2, gp.tileSize);
			}
			break;
		case "Wizard":
			if(currentWeapon.type == type_Weapon) {
				if(currentWeapon.name.equals("Staff")) {
					attackUp1 = setup("/player/WizardStaffAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/WizardStaffAttackUp2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/WizardStaffAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/WizardStaffAttackDown2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/WizardStaffAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/WizardStaffAttackLeft2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/WizardStaffAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/WizardStaffAttackRight2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("PaperClip")) {
					attackUp1 = setup("/player/WizardStaffAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/WizardPaperClipAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/WizardStaffAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/WizardPaperClipAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/WizardStaffAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/WizardPaperClipAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/WizardStaffAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/WizardPaperClipAttackRight", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Lance")) {
					attackUp1 = setup("/player/WizardStaffAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/WizardLanceAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/WizardStaffAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/WizardLanceAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/WizardStaffAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/WizardLanceAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/WizardStaffAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/WizardLanceAttackRight", gp.tileSize*2, gp.tileSize);
				}
			}
			if(currentWeapon.type == type_Axe) {
				attackUp1 = setup("/player/WizardAxeAttackUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/WizardAxeAttackUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/WizardAxeAttackDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/WizardAxeAttackDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/WizardAxeAttackLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/WizardAxeAttackLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/WizardAxeAttackRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/WizardAxeAttackRight2", gp.tileSize*2, gp.tileSize);
			}
			if(currentWeapon.type == type_Pickaxe){
				attackUp1 = setup("/player/WizardPickaxeUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/WizardPickaxeUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/WizardPickaxeDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/WizardPickaxeDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/WizardPickaxeLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/WizardPickaxeLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/WizardPickaxeRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/WizardPickaxeRight2", gp.tileSize*2, gp.tileSize);
			}
			break;
		case "Peasant":
			if(currentWeapon.type == type_Weapon) {
				if(currentWeapon.name.equals("Lance")) {
					attackUp1 = setup("/player/PeasantLanceAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/PeasantLanceAttackUp2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/PeasantLanceAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/PeasantLanceAttackDown2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/PeasantLanceAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/PeasantLanceAttackLeft2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/PeasantLanceAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/PeasantLanceAttackRight2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("PaperClip")) {
					attackUp1 = setup("/player/PeasantPaperClipAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/PeasantPaperClipAttackUp2", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/PeasantPaperClipAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/PeasantPaperClipAttackDown2", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/PeasantPaperClipAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/PeasantPaperClipAttackLeft2", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/PeasantPaperClipAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/PeasantPaperClipAttackRight2", gp.tileSize*2, gp.tileSize);
				}
				else if(currentWeapon.name.equals("Staff")) {
					attackUp1 = setup("/player/PeasantPaperClipAttackUp1", gp.tileSize, gp.tileSize*2);
					attackUp2 = setup("/player/PeasantStaffAttackUp", gp.tileSize, gp.tileSize*2);
					attackDown1 = setup("/player/PeasantPaperClipAttackDown1", gp.tileSize, gp.tileSize*2);
					attackDown2 = setup("/player/PeasantStaffAttackDown", gp.tileSize, gp.tileSize*2);
					attackLeft1 = setup("/player/PeasantPaperClipAttackLeft1", gp.tileSize*2, gp.tileSize);
					attackLeft2 = setup("/player/PeasantStaffAttackLeft", gp.tileSize*2, gp.tileSize);
					attackRight1 = setup("/player/PeasantPaperClipAttackRight1", gp.tileSize*2, gp.tileSize);
					attackRight2 = setup("/player/PeasantStaffAttackRight", gp.tileSize*2, gp.tileSize);
				}
			}
			if(currentWeapon.type == type_Axe) {
				attackUp1 = setup("/player/PeasantAxeAttackUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/PeasantAxeAttackUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/PeasantAxeAttackDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/PeasantAxeAttackDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/PeasantAxeAttackLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/PeasantAxeAttackLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/PeasantAxeAttackRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/PeasantAxeAttackRight2", gp.tileSize*2, gp.tileSize);	
			}
			if(currentWeapon.type == type_Pickaxe){
				attackUp1 = setup("/player/PeasantPickaxeUp1", gp.tileSize, gp.tileSize*2);
				attackUp2 = setup("/player/PeasantPickaxeUp2", gp.tileSize, gp.tileSize*2);
				attackDown1 = setup("/player/PeasantPickaxeDown1", gp.tileSize, gp.tileSize*2);
				attackDown2 = setup("/player/PeasantPickaxeDown2", gp.tileSize, gp.tileSize*2);
				attackLeft1 = setup("/player/PeasantPickaxeLeft1", gp.tileSize*2, gp.tileSize);
				attackLeft2 = setup("/player/PeasantPickaxeLeft2", gp.tileSize*2, gp.tileSize);
				attackRight1 = setup("/player/PeasantPickaxeRight1", gp.tileSize*2, gp.tileSize);
				attackRight2 = setup("/player/PeasantPickaxeRight2", gp.tileSize*2, gp.tileSize);
			}
			break;
		}
	}
	
	public void getPlayerGuardImage() {
		switch(playerClass) {
		case "Fighter":
			if(currentShield.name.equals("Shield")){
				guardUp = setup("/player/FighterGuardUp", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/FighterGuardDown", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/FighterGuardLeft", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/FighterGuardRight", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("MetalShield")){
				guardUp = setup("/player/FighterGuardUpMetal", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/FighterGuardDownMetal", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/FighterGuardLeftMetal", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/FighterGuardRightMetal", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Bark")){
				guardUp = setup("/player/FighterGuardUpBark", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/FighterGuardDownBark", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/FighterGuardLeftBark", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/FighterGuardRightBark", gp.tileSize, gp.tileSize);
			}
			else{displayErrorGuardImages();}
			break;
		case "Knight":
			if(currentShield.name.equals("Shield")){
				guardUp = setup("/player/KnightGuardUp", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/KnightGuardDown", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/KnightGuardLeft", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/KnightGuardRight", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("MetalShield")){
				guardUp = setup("/player/KnightGuardUpMetal", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/KnightGuardDownMetal", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/KnightGuardLeftMetal", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/KnightGuardRightMetal", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Bark")){
				guardUp = setup("/player/KnightGuardUpBark", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/KnightGuardDownBark", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/KnightGuardLeftBark", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/KnightGuardRightBark", gp.tileSize, gp.tileSize);
			}
			else{displayErrorGuardImages();}
			break;
		case "Wizard":
			if(currentShield.name.equals("Shield")){
				guardUp = setup("/player/WizardGuardUp", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/WizardGuardDown", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/WizardGuardLeft", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/WizardGuardRight", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("MetalShield")){
				guardUp = setup("/player/WizardGuardUpMetal", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/WizardGuardDownMetal", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/WizardGuardLeftMetal", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/WizardGuardRightMetal", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Bark")){
				guardUp = setup("/player/WizardGuardUpBark", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/WizardGuardDownBark", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/WizardGuardLeftBark", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/WizardGuardRightBark", gp.tileSize, gp.tileSize);
			}
			else{displayErrorGuardImages();}
			break;
		case "Peasant":
			if(currentShield.name.equals("Shield")){
				guardUp = setup("/player/PeasantGuardUp", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/PeasantGuardDown", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/PeasantGuardLeft", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/PeasantGuardRight", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("MetalShield")){
				guardUp = setup("/player/PeasantGuardUpMetal", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/PeasantGuardDownMetal", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/PeasantGuardLeftMetal", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/PeasantGuardRightMetal", gp.tileSize, gp.tileSize);
			}
			else if(currentShield.name.equals("Bark")){
				guardUp = setup("/player/PeasantGuardUpBark", gp.tileSize, gp.tileSize);
				guardDown = setup("/player/PeasantGuardDownBark", gp.tileSize, gp.tileSize);
				guardLeft = setup("/player/PeasantGuardLeftBark", gp.tileSize, gp.tileSize);
				guardRight = setup("/player/PeasantGuardRightBark", gp.tileSize, gp.tileSize);
			}
			else{displayErrorGuardImages();}
			break;
		}
	}

	private void displayErrorGuardImages(){
		guardUp = setup("/player/PlayerUp", gp.tileSize, gp.tileSize);
		guardDown = setup("/player/PlayerDown", gp.tileSize, gp.tileSize);
		guardLeft = setup("/player/PlayerLeft", gp.tileSize, gp.tileSize);
		guardRight = setup("/player/PlayerRight", gp.tileSize, gp.tileSize);
	}
	
	//Movement, Collision, attacking, etc:
	public void update() {
		if(knockBack == true) { 
			//collision checking:
			collisionOn = false;
			gp.collisionDetection.checkTile(this);
			gp.collisionDetection.checkObject(this, true);
			gp.collisionDetection.checkEntity(this, gp.npc);
			gp.collisionDetection.checkEntity(this, gp.monster);
			gp.collisionDetection.checkEntity(this, gp.iTile);
 			if(collisionOn == true) {
 				knockBackCounter = 0;
 				knockBack = false;
 				speed = defaultSpeed;
 			}
 			else if(collisionOn == false) {
 				switch(knockBackDirection) {
 				case "up": worldY -= speed; break;
 				case "down": worldY += speed; break;
 				case "left": worldX -= speed; break;
 				case "right": worldX += speed; break;
 				}
 			}
 			
 			knockBackCounter++;
 			if(knockBackCounter >= 10) {
 				knockBackCounter = 0;
 				knockBack = false;
 				speed = defaultSpeed;
 			}
 		}
		else if(attacking == true) {attacking();}
		else if(keyH.spacePressed == true) {
			guarding = true;
			guardCounter++;
		}
		else if(keyH.upPressed == true || keyH.downPressed == true ||
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			if(keyH.upPressed == true) {direction = "up";}
			else if(keyH.downPressed == true) {direction = "down";}
			else if(keyH.leftPressed == true) {direction = "left";}
			else if(keyH.rightPressed == true) {direction = "right";}
			//collision checking:
			collisionOn = false;
			gp.collisionDetection.checkTile(this);
			//object collision checking:
			int objIndex = gp.collisionDetection.checkObject(this, true);
			pickUpObject(objIndex);
			//npc collision checking:
			int npcIndex = gp.collisionDetection.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			//Monster Collision Checking:
			int monsterIndex = gp.collisionDetection.checkEntity(this, gp.monster);
			contactMonster(monsterIndex);
			//Interactive Tile Collision Checking:
			int iTileIndex = gp.collisionDetection.checkEntity(this, gp.iTile);
			//Event Collision Checking: 
			gp.eventHandler.checkEvent();
			
			//if collision is false, we can move:
			//the enter key cannot move the player:
			if(collisionOn == false && keyH.enterPressed == false || debuggingMode == true) {
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
			}
		
			if(keyH.enterPressed == true && attackCanceled == false) {
				attacking = true;
				spriteCounter = 0;
			}
			//reset for next attacks:
			attackCanceled = false;			
			gp.keyH.enterPressed = false;
			guarding = false;
			guardCounter = 0;
				
			// change walking animation every 10 frames:
			spriteCounter++;
			if(spriteCounter > 10) {
				if(spriteNum == 1) {
					spriteNum = 2;
				}
				else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
				guarding = false;
			}
		}

		if(playerClass.equals("Knight") == false) {
			if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
					&& shotAvailableCounter == 30 && projectile.haveResource(this) == true) {
				//Setup projectile:
				projectile.set(worldX, worldY, direction, true, this);
				//Subtract Mana Cost:
				projectile.subtractResource(this);	
				//Check Vacancy & add to projectile array:
				for(int i = 0; i < gp.projectile[0].length; i++) {
					if(gp.projectile[gp.currentMap][i] == null) {gp.projectile[gp.currentMap][i] = projectile;break;}
				}
				gp.playSoundEffect(10);
				//reset Counter for next time:
				shotAvailableCounter = 0;
			}
		}
		//Invincible Timer:
		if(invincible == true) {
			invincibleCounter ++;
			if(invincibleCounter > 60) {
				invincible = false;
				transparent = false;
				invincibleCounter = 0;
			}
		}
		//fix the 'ghost shot' effect:
		if(shotAvailableCounter < 30) {shotAvailableCounter++;}
		if(life > maxLife) {life = maxLife;}
		if(mana > maxMana) {mana = maxMana;}
		if(ammo > maxAmmo) {ammo = maxAmmo;}
		if(arrows > maxArrows) {arrows = maxArrows;}
		if(life <= 0 && gp.keyH.showDebugText == false) {
			gp.gameState = gp.gameOverState;
			gp.stopMusic();
			//Smashing enter as you die would have caused you to instantly retry:
			gp.ui.commandNum = -1;
			gp.playSoundEffect(13);	
		}
		if(guarding == true && keyH.spacePressed == false) {
			guarding = false;
			guardCounter = 0;
		}
	}
	
	public void interactNPC(int i) {
		if(gp.keyH.enterPressed == true) {
			if(i != 999) {
				attackCanceled = true;
				gp.npc[gp.currentMap][i].speak();	
			}
		}
		if(i != 999){
			gp.npc[gp.currentMap][i].move(direction);
		}
	}
	
	public void contactMonster(int i) {
		if(i != 999) {
			if(invincible == false && gp.monster[gp.currentMap][i].dying == false && gp.monster[gp.currentMap][i].alive == true) {
				gp.playSoundEffect(6);
				int damage = gp.monster[gp.currentMap][i].attack - defense;
				if(damage < 1) {damage  = 1;}
				life -= damage;
				invincible = true;
				transparent = true;
			}
		}
	}
	
	public void damageMonster(int i, Entity attacker, int attack, int knockBackPower) {
		if(i != 999) {
			if(gp.monster[gp.currentMap][i].invincible == false) {
				gp.playSoundEffect(5);
				if(knockBackPower > 0) {setKnockBack(gp.monster[gp.currentMap][i], attacker, gp.player.knockBackPower);}
				if(gp.monster[gp.currentMap][i].offBalance == true) {attack *= 3;}

				int damage = attack - gp.monster[gp.currentMap][i].defense;
				if(damage <= 0){
					damage  = 0;
					gp.ui.addMessage("Hit Blocked.");
				}
				gp.monster[gp.currentMap][i].life -= damage;
				gp.monster[gp.currentMap][i].invincible = true;
				gp.monster[gp.currentMap][i].damageReaction();

				if(gp.monster[gp.currentMap][i].life  <= 0) {
					gp.monster[gp.currentMap][i].dying = true;
					gp.ui.addMessage(gp.monster[gp.currentMap][i].name + " Killed!" + " (+"+gp.monster[gp.currentMap][i].exp+" Exp)");
					exp += gp.monster[gp.currentMap][i].exp;
					if(gp.monster[gp.currentMap][i].name.equals(SkeletonBoss.objectName)){
						gp.stopMusic();
					}
					checkLevelUp();
				}
			}
		}
	}
	
	public void damageProjectile(int i) {
		if(i != 999) {
			gp.projectile[gp.currentMap][i].alive = false;
			generateParticle(gp.projectile[gp.currentMap][i], gp.projectile[gp.currentMap][i]);
		}
	}
	
	public void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[gp.currentMap][i].destructable == true && gp.iTile[gp.currentMap][i].isCorrectItem(this) == true && gp.iTile[gp.currentMap][i].invincible == false) {
			gp.iTile[gp.currentMap][i].playSoundEffect();
			gp.iTile[gp.currentMap][i].life--;
			gp.iTile[gp.currentMap][i].invincible = true;
			//Generate Particles:
			generateParticle(gp.iTile[gp.currentMap][i], gp.iTile[gp.currentMap][i]);
			if(gp.iTile[gp.currentMap][i].life == 0) {gp.iTile[gp.currentMap][i] = gp.iTile[gp.currentMap][i].getDestroyedForm();}
		}
	}
	
	public void checkLevelUp() {
		if(exp >= nextLevelExp && level < maxLevel) {
			level++;
			if(level != maxLevel){
				nextLevelExp = nextLevelExp * 3;
			}
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			gp.playSoundEffect(7);
			gp.gameState = gp.dialogueState;
			setDialogue();
			startDialogue(this, 0);
			gp.ui.addMessage("LevelUp: Level"+ gp.player.level);
		}
	}

	public void setDialogue(){
		dialogues[0][0] ="Level Up: Level " + level + "!\nYou feel stronger!";
	}
	
	public void selectItem() {
		int itemIndex = gp.ui.getItemIndex(gp.ui.playerSlotCol, gp.ui.playerSlotRow);
		if(itemIndex < inventory.size()) {
			Entity selectedItem = inventory.get(itemIndex);
			if(selectedItem.type == type_Weapon || selectedItem.type == type_Axe || selectedItem.type == type_Pickaxe) {
				currentWeapon = selectedItem;
				attack = getAttack();
				getPlayerAttackImage();
			}
			if(selectedItem.type == type_Shield) {
				currentShield = selectedItem;
				defense = getDefense();
				getPlayerGuardImage();
			}
			if(selectedItem.type == type_Light) {
				if(currentLight == selectedItem) {currentLight = null;}
				else {currentLight = selectedItem;}
				lightUpdated = true;
			}
			if(selectedItem.type == type_Consumable) {
				gp.ui.npc = selectedItem;
				if(selectedItem.use(this) == true) {
					if(selectedItem.amount > 1) {selectedItem.amount--;}
					else {inventory.remove(itemIndex);}
				}
			}
		}
	}
	
	public int searchItemInInventory(String itemName) {
		int itemIndex = 999;	
		for(int i = 0; i < inventory.size(); i++) {
			if(inventory.get(i).name.equals(itemName)) {
				itemIndex = i;
				break;
			}
		}
		return itemIndex;
	}
	
	public boolean obtainItem(Entity item) {
		boolean obtainable = false;
		Entity newItem = gp.entityGenerator.getObject(item.name);

		//Check if stackable:
		if(newItem.stackable == true) {
			int index = searchItemInInventory(newItem.name);
			if(index != 999) {
				inventory.get(index).amount++;
				obtainable = true;
			}
			//New Item (check for a vacancy): 
			else {
				if(inventory.size() != maxInventorySize) {
					inventory.add(newItem);
					obtainable = true;
				}
			}
		}
		//NON Stackable items:
		else {
			if(inventory.size() != maxInventorySize) {
				inventory.add(newItem);
				obtainable = true;
			}
		}
		return obtainable;
	}
	
	public void equipInitialObjects() {
		for(int itemIndex = 0; itemIndex < inventory.size(); itemIndex++) {
			if(inventory.get(itemIndex).type == type_Weapon || inventory.get(itemIndex).type == type_Axe) {currentWeapon = inventory.get(itemIndex);}
			if(inventory.get(itemIndex).type == type_Shield) {
				currentShield = inventory.get(itemIndex);
				defense = getDefense();
			}
		}
	}
	
 	public void pickUpObject(int i) {
		String text;
		if(i != 999) {
			//PICKUPONLY Items:
			if(gp.obj[gp.currentMap][i].type == type_pickUpOnly){
				gp.obj[gp.currentMap][i].use(this);
				gp.obj[gp.currentMap][i] = null;
			}
			//Obstacle Items:
			else if(gp.obj[gp.currentMap][i].type == type_Obstacle) {
				if(keyH.enterPressed == true) {
					attackCanceled = true;
					gp.obj[gp.currentMap][i].interact();
				}
			}
			//Inventory Items:
			else {
				if(obtainItem(gp.obj[gp.currentMap][i]) == true) {
					gp.playSoundEffect(gp.sound.pickUp);
					text =  "You got a " + gp.obj[gp.currentMap][i].name + "!";
				}
				else {text = "You cannot carry any more!";}
				gp.ui.addMessage(text);
				gp.obj[gp.currentMap][i] = null;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
			}
			if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = attackUp1;}
				if(spriteNum == 2) {image = attackUp2;}
			}
			if(guarding == true) {image = guardUp;} break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackDown1;}
				if(spriteNum == 2) {image = attackDown2;}
			}
			if(guarding == true) {image = guardDown;} break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
			}
			if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = attackLeft1;}
				if(spriteNum == 2) {image = attackLeft2;}
			}
			if(guarding == true) {image = guardLeft;} break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
			}
			if(attacking == true) {
				if(spriteNum == 1) {image = attackRight1;}
				if(spriteNum == 2) {image = attackRight2;}
			}
			if(guarding == true) {image = guardRight;} break;
		}
		if(transparent == true) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));}
		if(drawing == true){g2.drawImage(image, tempScreenX, tempScreenY, null);}
		//Reset Alpha (Opacity)
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
	}
}
