package objects;

import Entity.Entity;
import main.GamePanel;

public class Lance extends Entity{

	public Lance(GamePanel gp) {
		super(gp);
		
		type = type_Weapon;
		name = "Lance";
		price = 20;
		down1 = setup("/objects/Lance", gp.tileSize, gp.tileSize);
		attackValue = 1;
		description = "[" + name + "]\nAn old lance.";
		attackArea.width = gp.tileSize;
		attackArea.height = gp.tileSize;
		knockBackPower = 5;
		motion1Duration = 5;
		motion2Duration = 25;
	}

}
