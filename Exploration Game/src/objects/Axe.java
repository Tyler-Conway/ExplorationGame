package objects;

import Entity.Entity;
import main.GamePanel;

public class Axe extends Entity{

	public Axe(GamePanel gp) {
		super(gp);
		
		type = type_Axe;
		name = "Axe";
		down1 = setup("/objects/axe", gp.tileSize, gp.tileSize);
		attackValue = 2;
		attackArea.width = gp.tileSize - (gp.tileSize/2);
		attackArea.height = gp.tileSize - (gp.tileSize/2);
		description = "[" + name + "]\nCut down trees!\n(and monsters)";
		price = 75;
		knockBackPower = 10;
		motion1Duration = 20;
		motion2Duration = 40;
	}

}
