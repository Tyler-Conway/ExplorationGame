package objects;

import Entity.Entity;
import main.GamePanel;

public class CoinSilver extends Entity{

	GamePanel gp;
	public CoinSilver(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		type = type_pickUpOnly;
		name = "Silver Coin";
		down1 = setup("/objects/SilverCoin", gp.tileSize, gp.tileSize);
		value = 5;
	}

	public boolean use(Entity entity) {
		gp.playSoundEffect(0);
		gp.ui.addMessage("Coin +"+value);
		gp.player.coin += value;
		return true;
	}
}
