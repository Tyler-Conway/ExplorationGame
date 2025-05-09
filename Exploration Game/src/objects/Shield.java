package objects;

import Entity.Entity;
import main.GamePanel;

public class Shield extends Entity{

	public static final String objectName = "Shield";
	public Shield(GamePanel gp) {
		super(gp);
		
		type = type_Shield;
		name = objectName;
		price = 35;
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nMade of wood.";
	}

	
	
}
