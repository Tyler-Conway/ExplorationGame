package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class YellowDoor extends Entity{
	
	GamePanel gp; 
	public static final String objectName = "YellowDoor";
	public YellowDoor(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		down1 = setup("/objects/YellowDoor", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You need to use a Yellow Key to open this";
		
	}

}