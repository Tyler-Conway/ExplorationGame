package objects;

import java.awt.Color;

import Entity.Entity;
import Entity.Projectile;
import main.GamePanel;

public class Rock extends Projectile {

	GamePanel gp;
	public static final String objectName = "Rock";
	
	public Rock(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		type = type_pickUpOnly;
		value = 1;
		speed = (gp.tileSize/8);
		maxLife = 80;
		life = maxLife;
		attack = 1;
		useCost = 1;
		alive = false;
		isProjectile = true;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.ammo >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.ammo -= useCost;
	}
	
	public boolean use(Entity entity) {
		gp.playSoundEffect(gp.sound.pickUp);
		gp.ui.addMessage("+"+value+ " Rock");
		entity.ammo += value;
		return true;
	}
	
	public Color getParticleColor() {
		Color color = new Color(40,50,0);
		return color;
	}
	public int getParticleSize() {
		 int size = 10;
		return size;
	}
	
	public int getParticleSpeed() {
		int speed = 1;
		return speed;
	}
	
	public int getMaxLife() {
		int maxLife = 20;
		return maxLife;
	}
}
