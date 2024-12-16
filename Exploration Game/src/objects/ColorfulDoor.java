package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class ColorfulDoor extends Entity{
	
	GamePanel gp; 
	int targetMapNum, newCol, newRow;
	
	public ColorfulDoor(GamePanel gp, int targetMapNum, int newCol, int newRow) {
		super(gp);
		this.gp = gp;
		
		name = "ColorfulDoor";
		down1 = setup("/objects/ColorfulDoor", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		this.targetMapNum = targetMapNum;
		this.newCol = newCol;
		this.newRow = newRow;
		
		solidArea.x = 0;
		solidArea.y = -1;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {

		if(gp.player.hasTriColorKey == true){
			gp.playSoundEffect(1);
			gp.eventHandler.changeMap(targetMapNum, newCol, newRow);
		}
		else{
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You must use the Tricolor Key to enter.";
		}
	}

}
