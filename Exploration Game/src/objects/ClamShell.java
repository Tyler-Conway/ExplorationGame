package objects;

import Entity.Entity;
import main.GamePanel;

public class ClamShell extends Entity{

	GamePanel gp;
	public static final String objectName = "ClamShell";
	
	public ClamShell(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Inventory;
		name = objectName;
		price = 500; //sells for 250.
		value = 1;
		down1 = setup("/objects/ClamShell", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nValuable and beautiful.";
		stackable = true;
	}

	// public boolean use(Entity entity) {
	// 	startDialogue(this, 0);
	// 	entity.life += value;
	// 	gp.playSoundEffect(9);
	// 	return true;
	// }
}