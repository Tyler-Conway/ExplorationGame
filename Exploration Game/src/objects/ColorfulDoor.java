package objects;

import Entity.Entity;
import main.GamePanel;

public class ColorfulDoor extends Entity{
	
	GamePanel gp; 
	public static final String objectName = "ColorfulDoor";
	int targetMapNum, newCol, newRow;
	
	public ColorfulDoor(GamePanel gp, int targetMapNum, int newCol, int newRow, int area) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		down1 = setup("/objects/ColorfulDoor", gp.tileSize, gp.tileSize);
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
		dialogues[0][0] = "You must use the TriColorKey to unlock this door.";
	}
	
	public void interact() {

		if(locked == false){
			gp.playSoundEffect(1);
			gp.eventHandler.changeMap(targetMapNum, newCol, newRow, newArea);
		}
		else{
			startDialogue(this, 0);
		}
	}

}
