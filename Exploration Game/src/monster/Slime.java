package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import objects.Arrow;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class Slime extends Entity{
	
	GamePanel gp;

	public Slime(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_Monster;
		name = "Slime";
		defaultSpeed = 2;
		speed = defaultSpeed;
		maxLife = 4;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 2;
		projectile = new Rock(gp);
		
		//solidArea.x = 3;
		//solidArea.y = 18;
		solidArea.x  = (gp.tileSize/8);
		solidArea.y = gp.tileSize - (gp.tileSize/2);
		solidArea.width = gp.tileSize - (gp.tileSize/8);
		solidArea.height = (gp.tileSize/3);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		
	}

	public void getImage() {
		up1 = setup("/monster/SlimeDown1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/SlimeDown2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/SlimeDown1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/SlimeDown2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/SlimeDown1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/SlimeDown2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/SlimeDown1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/SlimeDown2", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		
		if(onPath == true) {
			checkStopChasing(gp.player, 15, 100);
			searchPath(getGoalCol(gp.player),getGoalRow(gp.player));
			checkShootProjectile(200, 120);
		}
		else {
			generateDirection();
			checkStartChasing(gp.player, 5);
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		//direction = gp.player.direction;
		//It becomes Agressive and charges at player:
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
