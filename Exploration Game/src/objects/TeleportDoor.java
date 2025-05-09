package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class TeleportDoor extends Entity{
	
	GamePanel gp; 
	public static final String objectName = "TeleportDoor";
	public int targetMapNum, newCol, newRow;
	
	public TeleportDoor(GamePanel gp, int targetMapNum, int newCol, int newRow, int area) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		down1 = setup("/objects/Door", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		this.targetMapNum = targetMapNum;
		this.doorMapNum = targetMapNum;
		this.newCol = newCol;
		this.newRow = newRow;
		this.tpNewCol = newCol;
		this.tpNewRow = newRow;
		newArea = area;
		
		solidArea.x = 0;
		solidArea.y = -1;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		gp.playSoundEffect(gp.sound.door);
		//Causes player to enter structure:
		gp.eventHandler.changeMap(targetMapNum, newCol, newRow, newArea);
	
	}

}