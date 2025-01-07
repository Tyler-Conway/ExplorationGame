package InteractiveTiles;

import java.awt.Graphics2D;

import Entity.Entity;
import main.GamePanel;

public class InteractiveTile extends Entity{

	GamePanel gp;
	
	public InteractiveTile(GamePanel gp, int col, int row) {
		super(gp);
		this.gp = gp;
		destructable = true;
		
		
	}
	//Overwritten in specific Interactive Tiles:
	public boolean isCorrectItem(Entity entity) {
		boolean isCorrectItem = false;
		return isCorrectItem;
	}
	
	
	public void update() {
		//Invincible Timer:
		if(invincible == true) {
			invincibleCounter++;
			if(invincibleCounter > 20) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}
	
	public void playSoundEffect() {
		
	}
	
	public InteractiveTile getDestroyedForm() {
		InteractiveTile tile = null;
		return tile;
	}

}
