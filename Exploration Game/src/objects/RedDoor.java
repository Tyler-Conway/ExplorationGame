package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class RedDoor extends Entity{
	
	public static final String objectName = "RedDoor";
	GamePanel gp; 
	public RedDoor(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		down1 = setup("/objects/RedDoor", gp.tileSize, gp.tileSize);
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
		dialogues[0][0] = gp.ui.currentDialogue = "You need to use a Red Key to open this.";
	}
	
	public void interact() {
		startDialogue(this, 0);
	}

}