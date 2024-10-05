package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class ColorfulDoor extends Entity{

	
	public ColorfulDoor(GamePanel gp) {
		super(gp);
		name = "ColorfulDoor";
		down1 = setup("/objects/ColorfulDoor", gp.tileSize, gp.tileSize);
		collision = true;
		
	}

}