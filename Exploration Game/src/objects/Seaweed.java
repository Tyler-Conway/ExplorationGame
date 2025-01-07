package objects;

import Entity.Entity;
import main.GamePanel;

public class Seaweed extends Entity{

	GamePanel gp;
	public static final String objectName = "Seaweed";
	
	public Seaweed(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Consumable;
		name = objectName;
		price = 5;
		value = 1;
		down1 = setup("/objects/Seaweed", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nRestores +" + value + " HP.";
		stackable = true;
		setDialogue();
	}

	public void setDialogue(){
		dialogues[0][0] = "You eat the "+name+":\n +"+value+" health.";
	}

	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.life += value;
		gp.playSoundEffect(9);
		return true;
	}
}
