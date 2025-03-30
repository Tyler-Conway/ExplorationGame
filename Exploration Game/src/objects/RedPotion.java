package objects;

import Entity.Entity;
import main.GamePanel;

public class RedPotion extends Entity{

	GamePanel gp;
	public static final String objectName = "RedPotion";
	
	public RedPotion(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_Consumable;
		name = objectName;
		price = 25;
		value = 5;
		down1 = setup("/objects/RedPotion", gp.tileSize, gp.tileSize);
		description = "["+name+"]\nRestores +" + value + " HP.";
		stackable = true;
		setDialogue();
	}

	public void setDialogue(){
		dialogues[0][0] = "You drink the "+name+":\n +"+value+" health.";
	}

	public boolean use(Entity entity) {
		startDialogue(this, 0);
		entity.life += value;
		gp.playSoundEffect(gp.sound.healing);
		return true;
	}
}
