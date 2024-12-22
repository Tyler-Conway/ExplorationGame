package objects;

import Entity.Entity;
import main.GamePanel;

public class Staff extends Entity{

	public static final String objectName = "Staff";

	public Staff(GamePanel gp) {
		super(gp);
		
		type = type_Weapon;
		name = objectName;
		price = 20;
		down1 = setup("/objects/Staff", gp.tileSize, gp.tileSize);
		attackValue = 1;
		description = "[" + name + "]\nEvery Wizard needs\n a Staff.";
		attackArea.width = gp.tileSize;
		attackArea.height = gp.tileSize;
		knockBackPower = 5;
		motion1Duration = 5;
		motion2Duration = 25;
	}

}
