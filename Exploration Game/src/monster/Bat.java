package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import objects.Arrow;
import objects.CoinGold;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class Bat extends Entity{
	
	GamePanel gp;

	public Bat(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_Monster;
		name = "Bat";
		defaultSpeed = 4;
		speed = defaultSpeed;
		maxLife = 7;
		life = maxLife;
		attack = 5;
		defense = 0;
		exp = 5;
		
		solidArea.x  = (gp.tileSize/8);
		solidArea.y = gp.tileSize - (gp.tileSize/2);
		solidArea.width = gp.tileSize - (gp.tileSize/8);
		solidArea.height = (gp.tileSize/3);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
	}

	public void getImage() {
		up1 = setup("/monster/Bat1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/Bat2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Bat1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Bat2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Bat1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Bat2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/Bat1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Bat2", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		generateDirection(10);
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;
		
		if(i < 25) {dropItem(new CoinGold(gp));}
		if(i >= 25 && i < 50) {dropItem(new Heart(gp));}
		if(i >= 50 && i < 100) {
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