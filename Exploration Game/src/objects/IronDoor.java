package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class IronDoor extends Entity{
	
	public static final String objectName = "IronDoor";

	GamePanel gp; 
	public IronDoor(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		down1 = setup("/objects/IronDoor", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDialogue();
	}

	public void setDialogue(){
		dialogues[0][0] = "It wont budge.\n(push the boulders onto the metal plates)";
	}
	
	public void interact() {
		gp.gameState = gp.dialogueState;
		startDialogue(this, 0);
		
	}

}