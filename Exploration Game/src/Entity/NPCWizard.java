package Entity;

import java.util.Random;

import main.GamePanel;

public class NPCWizard extends Entity{

	Random random;

	public NPCWizard(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		name = "NPCWizard";
		random = new Random();
		dialogueSet = -1;
		getImage();
		setDialogue();
	}
	
	public void getImage() {
		up1 = setup("/NPC/WizardUp1", gp.tileSize, gp.tileSize);
		up2 = setup("/NPC/WizardUp2", gp.tileSize, gp.tileSize);
		down1 = setup("/NPC/WizardDown1", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/WizardDown2", gp.tileSize, gp.tileSize);
		left1 = setup("/NPC/WizardLeft1", gp.tileSize, gp.tileSize);
		left2 = setup("/NPC/WizardLeft2", gp.tileSize, gp.tileSize);
		right1 = setup("/NPC/WizardRight1", gp.tileSize, gp.tileSize);
		right2 = setup("/NPC/WizardRight2", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0][0] = "Hello, lad!";
		dialogues[0][1] = "You look new around these parts, so welcome!";
		dialogues[0][2] = "Have you come to seak the treasure? I'm looking for it myself!";

		dialogues[1][0] = "Beware of slimes up ahead, they can be quite dangerous.";
		dialogues[1][1] = "Although for an adventurer like yourself, they shouldn't be\nmuch of a challenge.";

		dialogues[2][0] = "I wonder if there is an axe somewhere around here?";
		dialogues[2][1] = "There are some trees up ahead that block the paths";

	}
	
	public void speak() {
		facePlayer();
		startDialogue(this, dialogueSet);
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null){
			dialogueSet = 0;
		}
	}
	
	public void setAction() {
		if(onPath == true) {
			//int goalCol = 5;
			//int goalRow = 2;
			int goalCol = (gp.player.worldX+gp.player.solidArea.x)/gp.tileSize;
			int goalRow = (gp.player.worldY+gp.player.solidArea.y)/gp.tileSize;
			
			searchPath(goalCol,goalRow);
		}
		else {
			actionLockCounter++;
			
			if(actionLockCounter == 120) {
				Random random = new Random();
				int i = random.nextInt(100) + 1;
				
				if(i <= 25) {
					direction = "up";
				}
				if(i > 25 && i <= 50) {
					direction = "down";
				}
				if(i > 50 && i <= 75) {
					direction = "left";
				}
				if(i > 75 && i <= 100) {
					direction = "right";
				}
				actionLockCounter = 0;
			}	
		}
	}
}
