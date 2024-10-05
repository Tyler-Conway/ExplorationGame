package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class Boots extends Entity{

	
	public Boots(GamePanel gp) {
		super(gp);
		name = "RedBoots";
		price = 300;
		down1 = setup("/objects/RedBoots", gp.tileSize, gp.tileSize);
		collision = true;
	}

}