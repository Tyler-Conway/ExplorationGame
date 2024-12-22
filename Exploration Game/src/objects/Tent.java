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
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You cannot sleep when the sun is out.";
			return false;
		}
	}

}
