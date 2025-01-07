package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import objects.Arrow;
import objects.CoinBronze;
import objects.CoinGold;
import objects.CoinSilver;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class Crab extends Entity{
	
	GamePanel gp;
    public static final String objectName = "Crab";

	public Crab(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_Monster;
		name = objectName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 5;
		life = maxLife;
		attack = 3;
		defense = 2;
		exp = 1;
		
		solidArea.x  = (gp.tileSize/8);
		solidArea.y = gp.tileSize - (gp.tileSize/2);
		solidArea.width = gp.tileSize - (gp.tileSize/8);
		solidArea.height = (gp.tileSize/3);
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		getImage();
		
	}

	public void getImage() {
		up1 = setup("/monster/Crab1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/Crab2", gp.tileSize, gp.tileSize);
		left1 = setup("/monster/Crab1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/Crab2", gp.tileSize, gp.tileSize);
		right1 = setup("/monster/Crab1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/Crab2", gp.tileSize, gp.tileSize);
		down1 = setup("/monster/Crab1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/Crab2", gp.tileSize, gp.tileSize);
	}
	
	public void setAction() {
		generateDirection(gp.FPS*2);
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
        direction = gp.player.direction; //Crab runs away
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