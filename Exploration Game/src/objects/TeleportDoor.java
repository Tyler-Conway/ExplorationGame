package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class TeleportDoor extends Entity{
	
	GamePanel gp; 
	public int targetMapNum, newCol, newRow;
	
	public TeleportDoor(GamePanel gp, int targetMapNum, int newCol, int newRow) {
		super(gp);
		this.gp = gp;
		
		name = "TeleportDoor";
		down1 = setup("/objects/Door", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		this.targetMapNum = targetMapNum;
		this.doorMapNum = targetMapNum;
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
		gp.playSoundEffect(1);
		//Causes player to enter structure:
		gp.eventHandler.changeMap(targetMapNum, newCol, newRow);
	
	}

}