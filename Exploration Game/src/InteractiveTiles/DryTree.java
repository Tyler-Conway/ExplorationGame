package InteractiveTiles;

import java.awt.Color;

import Entity.Entity;
import main.GamePanel;

public class DryTree extends InteractiveTile {

	public DryTree(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = gp.tileSize* col;
		this.worldY = gp.tileSize * row;
		name = "DryTree";
		
		down1 = setup("/Interactive/DryTree", gp.tileSize, gp.tileSize);
		destructable = true;
		life = 3;
	}

	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		if(entity.currentWeapon.type == type_Axe) {
			isCorrectItem = true;
		}
		return isCorrectItem;
	}
	
	public void playSoundEffect() {
		gp.playSoundEffect(11);
	}
	
	public InteractiveTile getDestroyedForm() {
		//passes the cut down tree's coordinates into the truck's constructor:
		InteractiveTile tile = new Trunk(gp, worldX/gp.tileSize, worldY/gp.tileSize);
		return tile;
	}

	public Color getParticleColor() {
		Color color = new Color(65,50,30);
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
