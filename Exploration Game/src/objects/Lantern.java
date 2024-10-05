package objects;

import Entity.Entity;
import main.GamePanel;

public class Lantern extends Entity{

	
	public Lantern(GamePanel gp) {
		super(gp);
		
		type = type_Light;
		name = "Lantern";
		down1 = setup("/objects/Lantern1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nProvides light";
		price = 200;
		lightRadius = 576;
	}

}
