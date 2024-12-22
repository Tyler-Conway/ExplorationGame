package objects;

import Entity.Entity;
import main.GamePanel;

public class MetalShield extends Entity{

	public static final String objectName = "MetalShield";
	public MetalShield(GamePanel gp) {
		super(gp);
		
		type = type_Shield;
		name = objectName;
		defenseValue= 2;
		price = 250;
		down1 = setup("/objects/metalShield", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nOffers protection\nfrom enemies.";
	}

}
