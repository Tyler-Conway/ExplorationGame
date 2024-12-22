package objects;

import Entity.Entity;
import main.GamePanel;

public class CoinBronze extends Entity{

	public static final String objectName = "Bronze Coin";

	GamePanel gp;
	public CoinBronze(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objectName;
		down1 = setup("/objects/BronzeCoin", gp.tileSize, gp.tileSize);
		value = 1;
	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(0);
		gp.ui.addMessage("Coin +"+value);
		gp.player.coin += value;
		return true;
	}
}
