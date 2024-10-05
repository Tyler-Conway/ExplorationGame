package Entity;

import java.util.Random;

import main.GamePanel;
import objects.RedPotion;
import objects.Tent;
import objects.BlueKey;
import objects.Lantern;
import objects.MetalShield;
import objects.RedKey;

public class RedTrader extends Entity{

	public RedTrader(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		type = type_Trader;
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {
		down1 = setup("/NPC/RedTrader1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/RedTrader2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0] = "Finally, a customer! Do you want to trade?";
	}
	
	public void setItems() {
		inventory.add(new RedKey(gp));
		inventory.add(new RedPotion(gp));
		inventory.add(new Tent(gp));
		inventory.add(new Lantern(gp));
	}
	
	public void speak() {
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.trader = this;
		
	}
}