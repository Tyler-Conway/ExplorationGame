package objects;

import Entity.Entity;
import main.GamePanel;

public class ManaCrystal extends Entity{

	GamePanel gp;
	public static final String objectName = "ManaCrystal";

	public ManaCrystal(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		name = objectName;
		type = type_pickUpOnly;
		value = 1;
		down1 = setup("/objects/FullMana", gp.tileSize, gp.tileSize);
		image = setup("/objects/FullMana", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/EmptyMana", gp.tileSize, gp.tileSize);

	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(gp.sound.pickUp);
		gp.ui.addMessage("Mana +"+value);
		entity.mana += value;
		return true;
	}
}
