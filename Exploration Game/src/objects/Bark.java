package objects;

import Entity.Entity;
import main.GamePanel;

public class Bark extends Entity{

	public Bark(GamePanel gp) {
		super(gp);
		
		type = type_Shield;
		name = "Bark";
		price = 0;
		down1 = setup("/objects/Bark", gp.tileSize, gp.tileSize);
		defenseValue = 1;
		description = "[" + name + "]\nA peice of tree bark.\nIt looks fragile.";
		attackArea.width = 50;
		attackArea.height = 50;
	}

}
