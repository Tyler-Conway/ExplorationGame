package Entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class Entity {

	
	GamePanel gp;
	public BufferedImage up1, up2, left1, left2, right1, right2, down1, down2, attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, 
		attackLeft2, attackRight1, attackRight2, guardUp, guardDown, guardLeft, guardRight;
	public BufferedImage image, image2, image3;
	//Solid Area Must Always be Less than the Tile Size:
	public Rectangle solidArea;
	public Rectangle attackArea;
	public String dialogues[][];
	public Entity attacker;
	public Entity linkedEntity;
	
	//Character Attributes:
	public int maxLife;
	public int life;
	public int maxMana;
	public int mana;
	public int maxAmmo;
	public int ammo;
	public int maxArrows;
	public int arrows;
	public String name;
	public int level;
	public int strength;
	public int dexterity;
	public int attack;
	public int defense;
	public int exp;
	public int nextLevelExp;
	public int coin;
	public int motion1Duration;
	public int motion2Duration;
	
	public Entity currentWeapon;
	public Entity currentShield;
	public Projectile projectile;
	public Entity currentLight;
	public int defaultSpeed;
	
	//Counters:
	public int actionLockCounter = 0;
	public int spriteCounter = 0;
	public int invincibleCounter = 0;
	public int dyingCounter = 0;
	public int hpBarCounter = 0;
	public int shotAvailableCounter = 0;
	public int knockBackCounter = 0;
	public int guardCounter = 0;
	public int offBalanceCounter = 0;
	
	//State:
	public int dialogueSet = 0;
	public int spriteNum = 1;
	public String direction = "down";
	public boolean collisionOn = false;
	public boolean collision = false, invincible = false;
	public int worldX, worldY, speed;
	public int solidAreaDefaultX, solidAreaDefaultY;
	public int dialogueIndex = 0;
	public boolean attacking = false;
	public boolean alive = true, dying = false;
	public boolean hpBarOn = false;
	public boolean destructable = false;
	public boolean onPath = false;
	public boolean knockBack = false;
	public String knockBackDirection = "";
	boolean alreadyPlayedAttackSound = false;
	public boolean guarding = false;
	public boolean transparent = false;
	public boolean offBalance = false;
	public boolean monsterHasWeapon = false;
	public boolean locked = true;
	public boolean boss = false;
	public boolean sleeping = false;
	public boolean temp = false;
	public boolean drawing = true;
	
	//Item Attributes:
	public boolean bossRoomDoor = false;
	public int attackValue;
	public int defenseValue;
	public String description = "";
	public int useCost;
	public int value;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	public int price;
	public int knockBackPower = 0;
	public boolean stackable = false;
	public int amount = 1;
	public int lightRadius;
	public boolean isProjectile = false;
	public Entity loot;
	public boolean opened = false;
	public int doorMapNum = 999;
	public int tpNewCol = 0;
	public int tpNewRow = 0;
	public int doorObjectIndex = 999;
	public int newArea;
	
	//Entity Types:
	public int type;
	public final int type_Player = 0, type_NPC = 1, type_Monster = 2, type_Weapon = 3, type_Axe = 4, type_Shield = 5, type_Consumable = 6,
			type_pickUpOnly = 7, type_Obstacle = 8, type_Light = 9, type_Trader = 10, type_Pickaxe = 11, type_Inventory = 12;
	
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		solidArea = new Rectangle(0,0,gp.tileSize - 1, gp.tileSize -1);
		attackArea = new Rectangle(0,0,0,0);
		dialogues = new String[20][20];
	}
	//Mutator Methods:
	public void changeAlpha(Graphics2D g2, float alpha) {g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));}
	//Accessor Methods:
	public String getName() {return name;}
	public int getLeftX() {return worldX + solidArea.x;}
	public int getRightX() {return worldX + solidArea.x + solidArea.width;}
	public int getTopY() {return worldY + solidArea.y;}
	public int getBottomY() {return worldY + solidArea.y + solidArea.height;}

	public int getCol() {return (worldX + solidArea.x)/gp.tileSize;}
	public int getRow() {return (worldY + solidArea.y)/gp.tileSize;}




	public int getScreenX(){return (worldX - gp.player.worldX + gp.player.screenX);}
	public int getScreenY(){return (worldY - gp.player.worldY + gp.player.screenY);}
	public int getXDistance(Entity target) {return Math.abs(getCenterX() - target.getCenterX());}
	public int getYDistance(Entity target) {return Math.abs(getCenterY() - target.getCenterY());}
	public int getCenterX(){return (worldX + left1.getWidth()/2);}
	public int getCenterY(){return (worldY + up1.getHeight()/2);}
	public int getTileDistance(Entity target) {return (getXDistance(target) + getYDistance(target))/gp.tileSize;}
	public Color getParticleColor() {Color color = null; return color;}
	public int getParticleSize() {int size = 0; return size;}
	public int getParticleSpeed() {int speed = 0; return speed;}
	public int getMaxLife() {int maxLife = 0; return maxLife;}
	//Overridden in specific Entity Classes: 
	public boolean use(Entity entity) {return false;}
	public void setAction() {}
	public void damageReaction() {}
	public void interact() {}
	public void checkDrop() {}
	public void move(String direction) {}
	public void setDialogue(){}
	
	public void resetAllCounters(){
		actionLockCounter = 0;
		spriteCounter = 0;
		invincibleCounter = 0;
		dyingCounter = 0;
		hpBarCounter = 0;
		shotAvailableCounter = 0;
		knockBackCounter = 0;
		guardCounter = 0;
		offBalanceCounter = 0;
	}

	public BufferedImage setup(String imagePath, int width, int height) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		try {
			image = ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
			image = uTool.scaleImage(image, width, height);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return image;
	}

	public void speak() {

	}

	public void facePlayer(){
		if(type != type_Trader) {
			//NPC faces player during dialogue if they are not a trader:
			switch(gp.player.direction) {
			case "up": this.direction = "down"; break;
			case "down": this.direction = "up"; break;
			case "left": this.direction = "right"; break;
			case "right": this.direction = "left"; break;
			}
		}
	}

	public void startDialogue(Entity entity, int setNum){
		gp.gameState = gp.dialogueState;
		gp.ui.npc = entity;
		dialogueSet = setNum;
	}
	
	public void checkInBounds(Entity entity) {
		entity.collisionOn = false;
		//Checks if non-player entity tries to walk outside the map it's in:
		if(entity.getCol() == 1 || entity.getCol() == 48 || entity.getRow() == 1 || entity.getRow() == 48){
			entity.collisionOn = true;
		}
	}
	
	public int getDetected(Entity user, Entity target[][], String targetName) {
		int index = 999;
		
		//Check imediately surrounding objects: 
		int nextWorldX = user.getLeftX();
		int nextWorldY = user.getTopY();

		switch(user.direction) {
		case "up": nextWorldY = user.getTopY() - (gp.tileSize/2); break;
		case "down": nextWorldY =  user.getTopY() + (gp.tileSize/2); break;
		case "left": nextWorldX = user.getLeftX() - (gp.tileSize/2); break;
		case "right": nextWorldX = user.getRightX() + (gp.tileSize/2); break;
		}
		int col = nextWorldX/gp.tileSize;
		int row = nextWorldY/gp.tileSize;
		
		
		for(int i = 0; i < target[0].length; i++) {
			if(target[gp.currentMap][i] != null && target[gp.currentMap][i].name.equals(targetName)) {
				if(target[gp.currentMap][i].getCol() == col && target[gp.currentMap][i].getRow() == row &&
						target[gp.currentMap][i].name.equals(targetName)) {
					index = i;
					break;
				}
			}
		}
		return index;
	}
	
	public void dropItem(Entity dropedItem) {
		for(int i = 0; i < gp.obj[0].length; i++) {
			if(gp.obj[gp.currentMap][i] == null) {
				gp.obj[gp.currentMap][i] = dropedItem;
				//dead monster's worldX and Y:
				gp.obj[gp.currentMap][i].worldX = worldX;
				gp.obj[gp.currentMap][i].worldY = worldY;
				break;
				
			}
		}
	}
	
	public void checkCollision() {
		collisionOn = false;
		gp.collisionDetection.checkTile(this);
		gp.collisionDetection.checkObject(this, false);
		gp.collisionDetection.checkEntity(this, gp.npc);
		gp.collisionDetection.checkEntity(this, gp.monster);
		gp.collisionDetection.checkEntity(this, gp.iTile);
		
		boolean contactPlayer = gp.collisionDetection.checkPlayer(this);
		
		if(this.type == type_Monster && contactPlayer == true) {
			damagePlayer(attack);
		}
	}
 	
	
	public void update() {
		if(sleeping == false){
			if(this != gp.player) {checkInBounds(this);}
			if(knockBack == true) {  
				checkCollision();
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
			else {
				setAction();
				checkCollision();
				if(collisionOn == false) {
					switch(direction) {
					case "up": worldY -= speed; break;
					case "down": worldY += speed; break;
					case "left": worldX -= speed; break;
					case "right": worldX += speed; break;
					}
				}
				spriteCounter++;
				if(spriteCounter > 26) {
					if(spriteNum == 1) {spriteNum = 2;}
					else if(spriteNum == 2) {spriteNum = 1;}
					spriteCounter = 0;
				}
			}
		   //Invincible Timer:
		   if(invincible == true) {
			   invincibleCounter++;
			   if(invincibleCounter > 40) {
				   invincible = false;
				   invincibleCounter = 0;
			   }
		   }
		   if(shotAvailableCounter < 120) {shotAvailableCounter++;}
		   if(offBalance == true) {
			   offBalanceCounter++;
			   if(offBalanceCounter > 60) {
				   offBalance = false;
				   offBalanceCounter = 0;
			   }
		   }
		}
	}
	
	public void checkStopChasing(Entity target, int distance, int rate) {
		if(getTileDistance(target) > distance) {
			int i = new Random().nextInt(rate);
			if(i == 0) {onPath = false;}
		}
	}
	
	public void checkStartChasing(Entity target, int distance) {
		if(getTileDistance(target) < distance) {onPath = true;}
	}
	
	public void generateDirection(int interval){
		actionLockCounter++;
		if(actionLockCounter > interval) {
			Random random = new Random();
			int i = random.nextInt(100) + 1;
			if(i <= 25) {direction = "up";}
			if(i > 25 && i <= 50) {direction = "down";}
			if(i > 50 && i <= 75) {direction = "left";}
			if(i > 75 && i <= 100) {direction = "right";}
			actionLockCounter = 0;
		}
	}

	public void moveTowardPlayer(int interval){
		actionLockCounter++;
		if(actionLockCounter > interval){
			if(getXDistance(gp.player) > getYDistance(gp.player)){
				if(gp.player.getCenterX() < getCenterX()){direction = "left";}
				else{direction = "right";}
			}
			else if(getXDistance(gp.player) < getYDistance(gp.player)){
				if(gp.player.getCenterY() < getCenterY()){direction = "up";}
				else{direction = "down";}
			}
			actionLockCounter = 0;
		}
	}
	
	public void checkShootProjectile(int rate, int shotInterval) {
		int i = new Random().nextInt(rate);
		if(i == 0 && projectile.alive == false && shotAvailableCounter >= shotInterval) {
			projectile.set(worldX, worldY, direction, true, this);
			//Check for Vacancy, and fill it:
			for(int j = 0; j < gp.projectile[0].length; j++) {
				if(gp.projectile[gp.currentMap][j] == null) {
					gp.projectile[gp.currentMap][j] = projectile;
					break;
				}
			}
			shotAvailableCounter = 0;
		}
	}
	
	public void checkAttack(int rate, int straight, int horizontal) {
		boolean inRange = false;
		int xDistance = getXDistance(gp.player);
		int yDistance = getYDistance(gp.player);
		switch(direction) {
		case "up": if(gp.player.getCenterY() < getCenterY() && yDistance < straight && xDistance < horizontal) {inRange = true;} break;
		case "down": if(gp.player.getCenterY() > getCenterY() && yDistance < straight && xDistance < horizontal) {inRange = true;} break;
		case "left": if(gp.player.getCenterX() < getCenterX() && xDistance < straight && yDistance < horizontal) {inRange = true;} break;
		case "right": if(gp.player.getCenterX() > getCenterX() && xDistance < straight && yDistance < horizontal) {inRange = true;} break;
		}
		if(inRange == true && attacking == false) {
			int i = new Random().nextInt(rate);
			if(i == 0) {
				attacking = true;
				spriteNum = 1;
				spriteCounter = 0;
				shotAvailableCounter = 0;
			}
		}
	}
	
	public void attacking() {
		spriteCounter++;
		if(spriteCounter <= motion1Duration) {spriteNum = 1;}
		if(spriteCounter > motion1Duration && spriteCounter <= motion2Duration) {
			spriteNum = 2;
			if(alreadyPlayedAttackSound == false) {
				gp.playSoundEffect(gp.sound.weaponSwing);
				alreadyPlayedAttackSound = true;
			}
			//Save Current worldx, worldy, solidArea:
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidAreaWidth = solidArea.width;
			int solidAreaHeight = solidArea.height;
			
			//Adjust player's worldx/y for attackArea:
			switch(direction) {
			case "up": worldY -= attackArea.height;break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldX += attackArea.width; break;
			}
			//attack area becomes solid area:
			solidArea.width = attackArea.width;
			solidArea.height = attackArea.height;
			
			if(type == type_Monster) {if(gp.collisionDetection.checkPlayer(this) == true) {damagePlayer(attack);}}
			else {//Player Attacks
				//Check if monsters collide with our updated attack area:
				int monsterIndex = gp.collisionDetection.checkEntity(this, gp.monster);
				gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);
				//DryTree hit detection:
				int iTileIndex = gp.collisionDetection.checkEntity(this, gp.iTile);
				gp.player.damageInteractiveTile(iTileIndex);
				int projectileIndex = gp.collisionDetection.checkEntity(this, gp.projectile);
				gp.player.damageProjectile(projectileIndex);	
			}
			//restore original data after collision checking:
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidArea.width = solidAreaWidth;
			solidArea.height = solidAreaHeight;
		}
		if(spriteCounter > motion2Duration) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
			alreadyPlayedAttackSound = false;
		}
	}
	
	public String getOppositeDirection(String direction) {
		String oppositeDirection = "";
		switch(direction) {
		case "up": oppositeDirection = "down"; break;
		case "down": oppositeDirection = "up"; break;
		case "left": oppositeDirection = "right"; break;
		case "right": oppositeDirection = "left"; break;
		}
		return oppositeDirection;
	}
	
 	public void damagePlayer(int attack) {
 		if(gp.player.invincible == false) {
 			int damage = attack - gp.player.defense;
 			//get Opposite direction of attacker:
 			String canGuardDirection = getOppositeDirection(direction);
 			if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection)){
 				//PARRY (pressed guard key less than 15 frames before attack hits you):
 				if(gp.player.guardCounter < 15 && monsterHasWeapon == true) {
 					damage = 0;
 					gp.playSoundEffect(16);
 					setKnockBack(this, gp.player, knockBackPower);
 					offBalance = true;
 					//Attacker gets put back into their "wind up phase" for 60 frames becasue they are stunned:
 					spriteCounter =-60;
 				}
 				else {
 					//Normal Guard:
 	 				gp.playSoundEffect(15);
 	 				damage /= 3;
 				}
 			}
 			else {
 				gp.playSoundEffect(6);
 				if(damage < 1) {damage  = 1;}
 			}
 			if(damage != 0) {
 				gp.player.transparent = true;
 				if(this.type != type_pickUpOnly) {setKnockBack(gp.player, this, knockBackPower);}
 			}
			gp.player.life -= damage;
			gp.player.invincible = true;
		}
 	}
 	
	public void setKnockBack(Entity target, Entity attacker, int knockBackPower) {
		this.attacker = attacker;
		target.knockBackDirection = attacker.direction;
		target.speed += knockBackPower;
		target.knockBack = true;
	}
 	
	public boolean inView(){
		boolean inView = false;
		if(worldX+gp.tileSize*5 > gp.player.worldX - gp.player.screenX &&
		   worldX-gp.tileSize < gp.player.worldX + gp.player.screenX &&
		   worldY+gp.tileSize*5 > gp.player.worldY - gp.player.screenY &&
		   worldY-gp.tileSize < gp.player.worldY + gp.player.screenY) {
				inView = true;
		   }
		return inView;
	}


	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		//save resources by only drawing tiles the player can see 
		//(+1 to avoid seeing un-drawn tiles):
		if(inView() == true) {
			int tempScreenX = getScreenX();
			int tempScreenY = getScreenY();
			
			switch(direction) {
			case "up":
				if(attacking == false) {
					if(spriteNum == 1) {image = up1;}
					if(spriteNum == 2) {image = up2;}
				}
				if(attacking == true) {
					tempScreenY = getScreenY() - up1.getHeight();
					if(spriteNum == 1) {image = attackUp1;}
					if(spriteNum == 2) {image = attackUp2;}
				}
				break;
			case "down":
				if(attacking == false) {
					if(spriteNum == 1) {image = down1;}
					if(spriteNum == 2) {image = down2;}
				}
				if(attacking == true) {
					if(spriteNum == 1) {image = attackDown1;}
					if(spriteNum == 2) {image = attackDown2;}
				}
				break;
			case "left":
				if(attacking == false) {
					if(spriteNum == 1) {image = left1;}
					if(spriteNum == 2) {image = left2;}
				}
				if(attacking == true) {
					tempScreenX = getScreenX() - left1.getWidth();
					if(spriteNum == 1) {image = attackLeft1;}
					if(spriteNum == 2) {image = attackLeft2;}
				}
				break;
			case "right":
				if(attacking == false) {
					if(spriteNum == 1) {image = right1;}
					if(spriteNum == 2) {image = right2;}
				}
				if(attacking == true) {
					if(spriteNum == 1) {image = attackRight1;}
					if(spriteNum == 2) {image = attackRight2;}
				}
				break;
			}
			if(invincible == true && destructable == false && dying == false) {
				hpBarOn = true;
				hpBarCounter = 0;
				changeAlpha(g2, 0.4f);
			}
			if(dying == true) {dyingAnimation(g2);}
			g2.drawImage(image, tempScreenX, tempScreenY, null);
			changeAlpha(g2, 1f);
		}
	}
	
	public void dyingAnimation(Graphics2D g2) {
		dyingCounter++;
		hpBarOn = false;
		int i = 5;
		if(dyingCounter <= i) {changeAlpha(g2, 0f);}
		if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2, 0f);}
		if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2, 1f);}
		if(dyingCounter > i*8) {dying = false; alive = false;}
	}
	
	public void generateParticle(Entity generator, Entity target) {
		Color color = generator.getParticleColor();
		int size = generator.getParticleSize();
		int speed = generator.getParticleSpeed();
		int maxLife = generator.getMaxLife();
		
		//Call the particle construtor and add it to the array list for active particles:
		Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -1,-1); gp.particalList.add(p1);
		Particle p2 = new Particle(gp, target, color, size, speed, maxLife, -1,1); gp.particalList.add(p2);
		Particle p3 = new Particle(gp, target, color, size, speed, maxLife, 1,-1); gp.particalList.add(p3);
		Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 1,1); gp.particalList.add(p4);
	}

	public void searchPath(int goalCol, int goalRow) {
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/gp.tileSize;
		gp.pathFinder.setNodes(startCol, startRow, goalCol, goalRow);
		//If it has found a path:
		if(gp.pathFinder.search() == true) {
			//Get Next WorldX and WorldY
			int nextX = gp.pathFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pathFinder.pathList.get(0).row * gp.tileSize;
			
			//Get Entity's current solidArea positions:
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			//Entity's solid area has to be less than tile size (48x48) for this to work:
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {direction = "up";}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {direction = "down";}
			else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				//left or right:
				if(enLeftX > nextX) {direction = "left";}
				if(enLeftX < nextX) {direction = "right";}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				//Up or Left:
				direction = "up";
				checkCollision();
				if(collisionOn == true) {direction = "left";}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				//Up or Right:
				direction = "up";
				checkCollision();
				if(collisionOn == true) {direction = "right";}
			}
			else if(enTopY < nextY && enLeftX > nextX) {
				//Down or Left:
				direction = "down";
				checkCollision();
				if(collisionOn == true) {direction = "left";}
			}
			else if(enTopY < nextY && enLeftX < nextX) {
				//Down or Right:
				direction = "down";
				checkCollision();
				if(collisionOn == true) {direction = "right";}
			}
			int nextCol = gp.pathFinder.pathList.get(0).col;
			int nextRow = gp.pathFinder.pathList.get(0).row;
			if(nextCol == goalCol && nextRow == goalRow) {
				//onPath = false;
			}	
		}	
	}	
}
