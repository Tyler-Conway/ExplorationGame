package Entity;

import main.GamePanel;

public class KeyForger extends Entity{

	public KeyForger(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		type = type_Trader;
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		down1 = setup("/NPC/KeyForger1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/KeyForger2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0][0] = "Ah, it's been some time since I've had a customer.\nI'm the Key Forger, welcome adventurer!\nWould you like me to forge the TriColorKey?\n(1 of each color key is required)";
		dialogues[1][0] = "Goodbye and good luck, you'll need it where you're going.";
		dialogues[2][0] = "You forged the TriColor Key!\n\nUse it to unlock the TriColorDoor in World04 to enter the\ndungeon and find the treasure.";
		dialogues[3][0] = "You are missing the materials required to forge the TriColorKey.";


	}
	
	public void speak() {
		gp.ui.commandNum = 1;
		gp.gameState = gp.forgeState;
		gp.ui.npc = this;
	}
}