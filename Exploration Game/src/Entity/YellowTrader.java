package Entity;

import java.util.Random;

import main.GamePanel;
import objects.RedPotion;
import objects.Tent;
import objects.YellowKey;
import objects.BlueKey;
import objects.Lantern;
import objects.MetalShield;

public class YellowTrader extends Entity{

	public YellowTrader(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		type = type_Trader;
		getImage();
		setDialogue();
		setItems();
	}
	
	public void getImage() {
		down1 = setup("/NPC/YellowTrader1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/YellowTrader2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0][0] = "Finally, a customer! Do you want to trade?";
		dialogues[1][0] = "Come Again, hehe!";
		dialogues[2][0] = "You need more coins to buy that!";
		dialogues[3][0] = "You have no more room in your inventory!";
		dialogues[4][0] = "You cannot sell equiped items!";
	}
	
	public void setItems() {
		inventory.add(new YellowKey(gp));
		inventory.add(new RedPotion(gp));
		inventory.add(new Tent(gp));
		inventory.add(new Lantern(gp));
	}
	
	public void speak() {
		gp.ui.commandNum = 2;
		gp.gameState = gp.tradeState;
		gp.ui.trader = this;
		gp.ui.npc = this;
	}
}