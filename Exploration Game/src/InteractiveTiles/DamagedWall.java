package InteractiveTiles;

import java.awt.Color;

import Entity.Entity;
import main.GamePanel;

public class DamagedWall extends InteractiveTile{

    public DamagedWall(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = gp.tileSize* col;
		this.worldY = gp.tileSize * row;
		name = "DamagedWall";
		
		down1 = setup("/Interactive/DamagedWall", gp.tileSize, gp.tileSize);
		destructable = true;
		life = 3;
	}

	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_Pickaxe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	public void playSoundEffect() {
		gp.playSoundEffect(gp.sound.cutTree);
	}
	
	public InteractiveTile getDestroyedForm() {
		return null;
	}

	public Color getParticleColor() {
		Color color = new Color(47,47,47);
		return color;
	}
	public int getParticleSize() {
		 int size = 6;
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
