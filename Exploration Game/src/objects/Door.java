package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class Door extends Entity{
	
	GamePanel gp; 
	public int mapNum, objectIndex;
	
	public Door(GamePanel gp, int mapNum, int objectIndex) {
		super(gp);
		this.gp = gp;
		
		name = "Door";
		down1 = setup("/objects/Door", gp.tileSize, gp.tileSize);
		image = setup("/objects/Blank", gp.tileSize, gp.tileSize);
		collision = true;
		type = type_Obstacle;
		this.objectIndex = objectIndex;
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = gp.tileSize;
		solidArea.height = gp.tileSize;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		gp.playSoundEffect(1);
		gp.obj[mapNum][objectIndex] = null;
	}

}