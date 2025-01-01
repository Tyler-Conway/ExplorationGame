package objects;

import Entity.Entity;
import main.GamePanel;

public class Tent extends Entity{

	GamePanel gp;
	public static final String objectName = "Tent";
	
	public Tent(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Consumable;
		name = objectName;
		down1 = setup("/objects/Tent", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nYou can sleep through\nthe night.";
		price = 300;
		stackable = true;
		setDialogue();
	}
	
	public void setDialogue(){
		dialogues[0][0] = "You cannot sleep during day time";
	}

	public boolean use(Entity entity) {
		if(gp.enviornmentManager.lighting.dayState == gp.enviornmentManager.lighting.evening ||
				gp.enviornmentManager.lighting.dayState == gp.enviornmentManager.lighting.night) {
			gp.player.attackCanceled = true;
			gp.gameState = gp.sleepState;
			gp.playSoundEffect(14);
			gp.player.restoreLifeAndProjectiles();

			//Save Game: 
			gp.saveLoad.save();
			gp.ui.addMessage("Your progress has been saved");
			return true;
		}
		else {
			startDialogue(this, 0);
			return false;
		}
	}

}
