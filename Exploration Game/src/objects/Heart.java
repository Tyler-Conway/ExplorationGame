package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class Heart extends Entity{

	public static final String objectName = "Heart";
	GamePanel gp;
	public Heart(GamePanel gp) {
		super(gp);
		this.gp = gp;
		name = objectName;
		type = type_pickUpOnly;
		value = 2;
		down1 = setup("/objects/FullHeart", gp.tileSize, gp.tileSize);
		image = setup("/objects/FullHeart", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/HalfHeart", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/EmptyHeart", gp.tileSize, gp.tileSize);
	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(0);
		gp.ui.addMessage("Life +"+value);
		entity.life += value;
		return true;
	}
}
