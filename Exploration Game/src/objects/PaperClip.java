package objects;

import Entity.Entity;
import main.GamePanel;

public class PaperClip extends Entity{

	public PaperClip(GamePanel gp) {
		super(gp);
		
		type = type_Weapon;
		name = "PaperClip";
		price = 2;
		down1 = setup("/objects/PaperClip", gp.tileSize, gp.tileSize);
		attackValue = 1;
		description = "[" + name + "]\nPoke 'em with\nthe sharp end!";
		attackArea.width = gp.tileSize/2;
		attackArea.height = gp.tileSize/2;
		knockBackPower = 5;
		motion1Duration = 5;
		motion2Duration = 25;
	}

}
