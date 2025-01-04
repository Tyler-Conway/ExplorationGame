package objects;

import Entity.Entity;
import main.GamePanel;

public class Pickaxe extends Entity{

	public static final String objectName = "Pickaxe";

	public Pickaxe(GamePanel gp) {
		super(gp);
		
		type = type_Pickaxe;
		name = objectName;
		down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize);
		attackValue = 1;
		attackArea.width = (gp.tileSize/2) - 1;
		attackArea.height = (gp.tileSize/2) - 1;
		description = "[" + name + "]\nBreak Down Damaged Walls";
		price = 75;
		knockBackPower = 0;
		motion1Duration = 20;
		motion2Duration = 40;
	}
}