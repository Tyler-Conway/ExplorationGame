package objects;

import Entity.Entity;
import main.GamePanel;

public class Axe extends Entity{

	public static final String objectName = "Axe";

	public Axe(GamePanel gp) {
		super(gp);
		
		type = type_Axe;
		name = objectName;
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = gp.tileSize - (gp.tileSize/8);
		attackArea.height = gp.tileSize - (gp.tileSize/8);
		description = "[" + name + "]\nCut down trees!\n(and monsters)";
		price = 75;
		knockBackPower = 10;
		motion1Duration = 20;
		motion2Duration = 40;
	}
}
