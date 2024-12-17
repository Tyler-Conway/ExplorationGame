package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class TriColorKey extends Entity{

	GamePanel gp;
	
	public TriColorKey(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = "TriColorKey";
		type = type_Consumable;
		price = 100;
		down1 = setup("/objects/TriColorKey", gp.tileSize, gp.tileSize);
		collision = true;
		description = "[" + name + "]\nIt opens the TriColorDoor door.";
		stackable = true;
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(entity, gp.obj, "ColorfulDoor");
		
		if(objIndex != 999) {
			gp.ui.currentDialogue = "You used the "+name+" to unlock the door";
			gp.playSoundEffect(1);
			gp.obj[gp.currentMap][objIndex].locked = false;
			return true;
		}
		else {
			gp.ui.currentDialogue = "What are you trying to unlock?";
			return false;
		}
	}
}