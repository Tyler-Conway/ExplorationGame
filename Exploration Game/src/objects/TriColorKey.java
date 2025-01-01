package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class TriColorKey extends Entity{

	GamePanel gp;
	public static final String objectName = "TriColorKey";
	
	public TriColorKey(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		type = type_Consumable;
		price = 100;
		down1 = setup("/objects/TriColorKey", gp.tileSize, gp.tileSize);
		collision = true;
		description = "[" + name + "]\nIt opens the TriColorDoor.";
		stackable = true;
		setDialogue();
	}

	public void setDialogue(){
		dialogues[0][0] = "You used the key ["+name+"] to open the door";
		dialogues[1][0] = "What are you trying to unlock?";
	}

	public boolean use(Entity entity) {
		
		int objIndex = getDetected(entity, gp.obj, "ColorfulDoor");
		
		if(objIndex != 999) {
			startDialogue(this, 0);
			gp.playSoundEffect(1);
			gp.obj[gp.currentMap][objIndex].locked = false;
			return true;
		}
		else {
			startDialogue(this, 1);
			return false;
		}
	}
}