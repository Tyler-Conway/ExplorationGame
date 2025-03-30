package objects;

import Entity.Entity;
import main.GamePanel;

public class CoinGold extends Entity{

	public static final String objectName = "Gold Coin";
	GamePanel gp;
	public CoinGold(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objectName;
		down1 = setup("/objects/GoldCoin", gp.tileSize, gp.tileSize);
		value = 10;
	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(gp.sound.pickUp);
		gp.ui.addMessage("Coin +"+value);
		gp.player.coin += value;
		return true;
	}
}
