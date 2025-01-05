package monster;

import Entity.Entity;
import main.GamePanel;
import objects.IronDoor;
public class SkeletonBoss extends Entity{
	
	GamePanel gp;
    public static final String objectName = "SkeletonGiant";
    int imageScale = 5;
	int dialogueIndex = 0;

	public SkeletonBoss(GamePanel gp) {
		super(gp);
		this.gp = gp;

		boss = true;
		sleeping = true;
		type = type_Monster;
		name = objectName;
		defaultSpeed = 1;
		speed = defaultSpeed;
		maxLife = 50;
		life = maxLife;
		attack = 10;
		defense = 4;
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
		setDialogue();
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

	public void setDialogue(){
		dialogues[0][0] = "No one will steal my treasure!\nWho goes there?";
		if(gp.player.level < 2){
			dialogues[0][1] = "Ha, You were foolish enough to face me\nat a meager level "+gp.player.level+"!?";
			dialogues[0][2] = "You will surely die here!";
		}
		else{
			dialogues[0][1] = "Ah, its one of the many greedy fools who\nhave disturbed my slumber over the years.";
			dialogues[0][2] = "I'll not part with a single coin,\nNot one piece of it!";
		}
	}
	public void speak(){

	}
	
	public void setAction() {
		
		if(getTileDistance(gp.player) < 12) {
            moveTowardPlayer(gp.FPS);
		}
		else {
			generateDirection(120);
		}
		
		if(attacking == false) {
			checkAttack((int)(gp.FPS*1.5), gp.tileSize*5, gp.tileSize*4);
		}
	}
	
	public void damageReaction() {
		actionLockCounter = 0;
		onPath = true;
	}
	
	public void checkDrop() {
		gp.bossBattle = false;
		gp.skeletonGiantDefeated = true;
		gp.stopMusic();
		gp.playMusic(gp.sound.victoryMusic);
		for(int i = 0; i < gp.obj[1].length; i++){
			if(gp.obj[gp.dungeon02][i] != null && gp.obj[gp.dungeon02][i].name.equals(IronDoor.objectName)){
				gp.playSoundEffect(19); //Door Open
				gp.obj[gp.dungeon02][i] = null;
			}
		}
		//Game saves as soon as you kill the final boss to be safe:
		gp.saveLoad.save();
	}
	
}
