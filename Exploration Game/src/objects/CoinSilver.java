package objects;

import Entity.Entity;
import main.GamePanel;

public class CoinSilver extends Entity{

	GamePanel gp;
	public static final String objectName = "Silver Coin";
	public CoinSilver(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = objectName;
		down1 = setup("/objects/SilverCoin", gp.tileSize, gp.tileSize);
		value = 5;
	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(gp.sound.pickUp);
		gp.ui.addMessage("Coin +"+value);
		gp.player.coin += value;
		return true;
	}
}
