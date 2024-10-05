package objects;

import java.awt.Color;

import Entity.Entity;
import Entity.Projectile;
import main.GamePanel;

public class Arrow extends Projectile {

	GamePanel gp;
	
	public Arrow(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "Arrow";
		type = type_pickUpOnly;
		value = 1;
		image = setup("/projectile/ArrowDown", gp.tileSize, gp.tileSize);
		speed = (gp.tileSize/8);
		maxLife = 80;
		life = maxLife;
		attack = 2;
		useCost = 1;
		alive = false;
		isProjectile = true;
		getImage();
	}
	
	public void getImage() {
		up1 = setup("/projectile/ArrowUp", gp.tileSize, gp.tileSize);
		up2 = setup("/projectile/ArrowUp", gp.tileSize, gp.tileSize);
		down1 = setup("/projectile/ArrowDown", gp.tileSize, gp.tileSize);
		down2 = setup("/projectile/ArrowDown", gp.tileSize, gp.tileSize);
		left1 = setup("/projectile/ArrowLeft", gp.tileSize, gp.tileSize);
		left2 = setup("/projectile/ArrowLeft", gp.tileSize, gp.tileSize);
		right1 = setup("/projectile/ArrowRight", gp.tileSize, gp.tileSize);
		right2 = setup("/projectile/ArrowRight", gp.tileSize, gp.tileSize);
	}
	
	public boolean haveResource(Entity user) {
		boolean haveResource = false;
		if(user.arrows >= useCost) {
			haveResource = true;
		}
		return haveResource;
	}
	
	public void subtractResource(Entity user) {
		user.arrows -= useCost;
	}
	
	public boolean use(Entity entity) {
		gp.playSoundEffect(0);
		gp.ui.addMessage("Arrows +"+value);
		entity.arrows += value;
		return true;
	}
	
	public Color getParticleColor() {
		Color color = new Color(255,255,255);
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
