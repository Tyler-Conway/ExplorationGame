package objects;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.management.ObjectName;

import Entity.Entity;
import main.GamePanel;

public class Boots extends Entity{

	public static final String objectName = "Boots";
	
	public Boots(GamePanel gp) {
		super(gp);
		name = objectName;
		price = 300;
		down1 = setup("/objects/RedBoots", gp.tileSize, gp.tileSize);
		collision = true;
	}

}