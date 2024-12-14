package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import objects.Arrow;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class Oger extends Entity{
	
	GamePanel gp;

	public Oger(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_Monster;
		name = "Oger";
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 12;
		life = maxLife;
		attack = 8;
		defense = 2;
		exp = 10;
		monsterHasWeapon = true;
		knockBackPower = 5;
		
		motion1Duration = 40;
		motion2Duration = 85;
		
		solidArea.x  = (gp.tileSize/8);
		solidArea.y = gp.tileSize - (gp.tileSize/4);
		solidArea.width = gp.tileSize - (gp.tileSize/8);
		solidArea.height = (gp.tileSize/3);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = gp.tileSize + (gp.tileSize/8);
		attackArea.height = gp.tileSize + (gp.tileSize/8);
		
		
		getImage();
		getAttackImage();
		
	}

	public void getImage() {
		up1 = setup("/monster/OgerUp1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/OgerUp2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/OgerLeft1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/OgerLeft2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/OgerRight1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/OgerRight2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/OgerDown1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/OgerDown2", gp.tileSize, gp.tileSize);
	}
	
	public void getAttackImage() {
		attackUp1 = setup("/monster/OgerAttackUp1", gp.tileSize, gp.tileSize*2);
		attackUp2 = setup("/monster/OgerAttackUp2", gp.tileSize, gp.tileSize*2);
		attackDown1 = setup("/monster/OgerAttackDown1", gp.tileSize, gp.tileSize*2);
		attackDown2 = setup("/monster/OgerAttackDown2", gp.tileSize, gp.tileSize*2);
		attackLeft1 = setup("/monster/OgerAttackLeft1", gp.tileSize*2, gp.tileSize);
		attackLeft2 = setup("/monster/OgerAttackLeft2", gp.tileSize*2, gp.tileSize);
		attackRight1 = setup("/monster/OgerAttackRight1", gp.tileSize*2, gp.tileSize);
		attackRight2 = setup("/monster/OgerAttackRight2", gp.tileSize*2, gp.tileSize);
	}
	
	public void setAction() {
		
		if(onPath == true) {
			checkStopChasing(gp.player, 10, 1);
			searchPath(getGoalCol(gp.player),getGoalRow(gp.player));
		}
		else {
			generateDirection();
			checkStartChasing(gp.player, 7);
		}
		
		//Check if Oger should attack: 
		if(attacking == false) {
			checkAttack(25, gp.tileSize*2, gp.tileSize*2);
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		onPath = true;
	}
	
	public void checkDrop() {
		//Cast a die
		int i = new Random().nextInt(100)+1;
		
		//Set Monster Drops
		if(i < 50) {
			dropItem(new CoinBronze(gp));
		}
		if(i >= 50 && i < 75) {
			dropItem(new Heart(gp));
		}
		if(i >= 75 && i < 100) {
			if(gp.player.playerClass.equals("Fighter")) {
				dropItem(new Arrow(gp));
			}
			if(gp.player.playerClass.equals("Wizard")) {
				dropItem(new ManaCrystal(gp));
			}
			if(gp.player.playerClass.equals("Peasant")) {
				dropItem(new Rock(gp));
			}
			
		}
	}
	
}
