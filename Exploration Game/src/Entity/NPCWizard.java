package Entity;

import java.util.Random;

import main.GamePanel;

public class NPCWizard extends Entity{

	public NPCWizard(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		name = "NPCWizard";
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
		dialogues[0] = "Hello, lad!";
		dialogues[1] = "You look new around these parts,\nso welcome!";
		dialogues[2] = "Have you come to seak the treasure?\nI'm looking for it myself!";
		dialogues[3] = null;
	}
	
	public void speak() {
		super.speak();
		//onPath = true;
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
