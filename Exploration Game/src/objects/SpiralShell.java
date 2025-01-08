package objects;

import Entity.Entity;
import main.GamePanel;

public class SpiralShell extends Entity{

	GamePanel gp;
	public static final String objectName = "SpiralShell";
	
	public SpiralShell(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Inventory;
		name = objectName;
		price = 50; // sells for 25
		value = 1;
		down1 = setup("/objects/RedSpiralShell", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nLooks pretty.\nIt could be worth something.";
		stackable = true;
	}

	// public boolean use(Entity entity) {
	// 	startDialogue(this, 0);
	// 	entity.life += value;
	// 	gp.playSoundEffect(9);
	// 	return true;
	// }
}