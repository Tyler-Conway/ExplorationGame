package objects;

import Entity.Entity;
import main.GamePanel;

public class Lantern extends Entity{

	public static final String objectName = "Lantern";
	
	public Lantern(GamePanel gp) {
		super(gp);
		
		type = type_Light;
		name = objectName;
		down1 = setup("/objects/Lantern1", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nProvides light.";
		price = 200;
		lightRadius = 576;
	}

}
