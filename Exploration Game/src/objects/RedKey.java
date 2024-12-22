package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class RedKey extends Entity{

	GamePanel gp;
	public static final String objectName = "RedKey";
	
	public RedKey(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		type = type_Consumable;
		price = 100;
		down1 = setup("/objects/RedKey", gp.tileSize, gp.tileSize);
		collision = true;
		description = "[" + name + "]\nIt opens a door.";
		stackable = true;
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		
		int objIndex = getDetected(entity, gp.obj, "RedDoor");
		
		if(objIndex != 999) {
			gp.ui.currentDialogue = "You used the key ["+name+"] to open the door";
			gp.playSoundEffect(1);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			gp.ui.currentDialogue = "What are you trying to unlock?";
			return false;
			
		}
	}
}