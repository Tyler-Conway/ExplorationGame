package Entity;

import main.GamePanel;
import objects.Axe;
import objects.Bark;
import objects.BlueKey;
import objects.Lance;
import objects.Lantern;
import objects.MetalShield;
import objects.PaperClip;
import objects.Pickaxe;
import objects.RedKey;
import objects.RedPotion;
import objects.Shield;
import objects.Staff;
import objects.Tent;
import objects.YellowKey;

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
		dialogues[0][0] = "Finally, a customer! Do you want to trade?";
		dialogues[1][0] = "Come Again, hehe!";
		dialogues[2][0] = "You need more coins to buy that!";
		dialogues[3][0] = "You have no more room in your inventory!";
		dialogues[4][0] = "You cannot sell equiped items!";
	}
	
	public void setItems() {
		inventory.add(new BlueKey(gp));
		inventory.add(new RedPotion(gp));
		inventory.add(new MetalShield(gp));
		inventory.add(new Tent(gp));
		inventory.add(new Lantern(gp));
		inventory.add(new Shield(gp));
	}
	
	public void speak() {
		gp.ui.commandNum = 2;
		gp.gameState = gp.tradeState;
		gp.ui.npc = this;
	}
}