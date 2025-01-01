package Entity;

import main.GamePanel;

public class GreenNPC extends Entity{
	
	GamePanel gp;
	
	public GreenNPC(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		direction = "down";
		speed = 1;
		type = type_Trader;
		getImage();
		setDialogue();
		
	}
	
	public void getImage() {
		down1 = setup("/NPC/GreenNPC1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/GreenNPC2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0][0] = "Hello stranger! Welcome to the Traveler's Express!\nWould you like to fast travel to annother location?";
	}
	
	public void speak() {
		gp.ui.commandNum = 1;
		gp.gameState = gp.travelState;
		gp.ui.fastTravelNPC = this;
		gp.ui.npc = this;
	}
	
}
