package objects;

import Entity.Entity;
import main.GamePanel;

public class RedPotion extends Entity{

	GamePanel gp;
	
	public RedPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Consumable;
		name = "RedPotion";
		price = 25;
		value = 5;
		down1 = setup("/objects/RedPotion", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nRestores +" + value + " HP.";
		stackable = true;
	}

	public boolean use(Entity entity) {
		gp.gameState = gp.dialogueState;
		gp.ui.currentDialogue = "You drink the "+name+":\n +"+value+" health.";
		entity.life += value;
		gp.playSoundEffect(9);
		return true;
	}
}
