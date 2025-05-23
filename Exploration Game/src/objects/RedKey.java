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
		description = "[" + name + "]\nIt opens Red Doors.";
		stackable = true;
		setDialogue();
	}

	public void setDialogue(){
		dialogues[0][0] = "You used the Red Key to open the door.";
		dialogues[1][0] = "What are you trying to unlock?";
	}

	public boolean use(Entity entity) {
		
		int objIndex = getDetected(entity, gp.obj, "RedDoor");
		
		if(objIndex != 999) {
			startDialogue(this, 0);
			gp.playSoundEffect(gp.sound.door);
			gp.obj[gp.currentMap][objIndex] = null;
			return true;
		}
		else {
			startDialogue(this, 1);
			return false;
			
		}
	}
}