package Entity;

import main.GamePanel;
import objects.Bark;
import objects.BlueKey;
import objects.Lance;
import objects.Lantern;
import objects.MetalShield;
import objects.PaperClip;
import objects.RedPotion;
import objects.Shield;
import objects.Staff;
import objects.Tent;

public class BlueTrader extends Entity{

	public BlueTrader(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		type = type_Trader;
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {
		down1 = setup("/NPC/BlueTrader1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/BlueTrader2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0] = "Finally, a customer! Do you want to trade?";
	}
	
	public void setItems() {
		inventory.add(new BlueKey(gp));
		inventory.add(new RedPotion(gp));
		inventory.add(new MetalShield(gp));
		inventory.add(new Tent(gp));
		inventory.add(new Lantern(gp));
		inventory.add(new Shield(gp));
		inventory.add(new Bark(gp));
		inventory.add(new PaperClip(gp));
		inventory.add(new Staff(gp));
		inventory.add(new Lance(gp));

	}
	
	public void speak() {
		super.speak();
		gp.gameState = gp.tradeState;
		gp.ui.trader = this;
	}
}