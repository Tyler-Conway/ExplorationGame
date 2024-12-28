package monster;

import java.util.Random;

import Entity.Entity;
import main.GamePanel;
import objects.Arrow;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class SkeletonBoss extends Entity{
	
	GamePanel gp;
    public static final String objectName = "SkeletonBoss";
    int imageScale = 5;

	public SkeletonBoss(GamePanel gp) {
		super(gp);
		this.gp = gp;
		type = type_Monster;
		name = objectName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 50;
		life = maxLife;
		attack = 10;
		defense = 2;
		exp = 50;
		monsterHasWeapon = true;
		knockBackPower = 5;
		
		motion1Duration = gp.FPS - (gp.FPS/3);
		motion2Duration = gp.FPS + (gp.FPS/2);
		
        int size = gp.tileSize * imageScale;
		solidArea.x  = gp.tileSize;
		solidArea.y = gp.tileSize;
		solidArea.width = size - (2*gp.tileSize);
		solidArea.height = size - gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		attackArea.width = gp.tileSize*4;
		attackArea.height = gp.tileSize*4;
		
		
		getImage();
		getAttackImage();
		
	}

	public void getImage() {
		up1 = setup("/monster/BossUp1", gp.tileSize * imageScale, gp.tileSize * imageScale);
		up2 = setup("/monster/BossUp2", gp.tileSize * imageScale, gp.tileSize * imageScale);
		left1 = setup("/monster/BossLeft1", gp.tileSize * imageScale, gp.tileSize * imageScale);
		left2 = setup("/monster/BossLeft2", gp.tileSize * imageScale, gp.tileSize * imageScale);
		right1 = setup("/monster/BossRight1", gp.tileSize * imageScale, gp.tileSize * imageScale);
		right2 = setup("/monster/BossRight2", gp.tileSize * imageScale, gp.tileSize * imageScale);
		down1 = setup("/monster/BossDown1", gp.tileSize * imageScale, gp.tileSize * imageScale);
		down2 = setup("/monster/BossDown2", gp.tileSize * imageScale, gp.tileSize * imageScale);
	}
	
	public void getAttackImage() {

		attackUp1 = setup("/monster/BossAttackUp1", gp.tileSize * imageScale, gp.tileSize*2 * imageScale);
		attackUp2 = setup("/monster/BossAttackUp2", gp.tileSize * imageScale, gp.tileSize*2 * imageScale);
		attackDown1 = setup("/monster/BossAttackDown1", gp.tileSize * imageScale, gp.tileSize*2 * imageScale);
		attackDown2 = setup("/monster/BossAttackDown2", gp.tileSize * imageScale, gp.tileSize*2 * imageScale);
		attackLeft1 = setup("/monster/BossAttackLeft1", gp.tileSize*2 * imageScale, gp.tileSize * imageScale);
		attackLeft2 = setup("/monster/BossAttackLeft2", gp.tileSize*2 * imageScale, gp.tileSize * imageScale);
		attackRight1 = setup("/monster/BossAttackRight1", gp.tileSize*2 * imageScale, gp.tileSize * imageScale);
		attackRight2 = setup("/monster/BossAttackRight2", gp.tileSize*2 * imageScale, gp.tileSize * imageScale);
	}
	
	public void setAction() {
		
		if(getTileDistance(gp.player) < 10) {
            moveTowardPlayer(gp.FPS);
		}
		else {
			generateDirection(120);
		}
		
		if(attacking == false) {
			checkAttack(gp.FPS*2, gp.tileSize*5, gp.tileSize*4);
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		onPath = true;
	}
	
	public void checkDrop() {
		int i = new Random().nextInt(100)+1;

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
