package objects;

import Entity.Entity;
import main.GamePanel;

public class MetalShield extends Entity{

	public MetalShield(GamePanel gp) {
		super(gp);
		
		type = type_Shield;
		name = "MetalShield";
		defenseValue= 2;
		price = 250;
		down1 = setup("/objects/metalShield", gp.tileSize, gp.tileSize);
		description = "[" + name + "]\nOffers protection\nfrom enemies.";
	}

}
