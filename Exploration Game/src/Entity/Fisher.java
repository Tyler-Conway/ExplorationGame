package Entity;

import main.GamePanel;

public class Fisher extends Entity{

    public static final String objectName = "Fisher";
    int scale = 1;

	public Fisher(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 0;
		name = objectName;
		dialogueSet = -1;
		getImage();
		setDialogue();

        solidArea.x = (gp.tileSize/4);
        solidArea.y = (gp.tileSize/3);
        solidArea.width = gp.tileSize*scale;
        solidArea.height = gp.tileSize*scale - (gp.tileSize/3);
	}
	
	public void getImage() {
		up1 = setup("/NPC/Fisher1", gp.tileSize*scale, gp.tileSize*scale*2);
		up2 = setup("/NPC/Fisher2", gp.tileSize*scale, gp.tileSize*scale*2);
		down1 = setup("/NPC/Fisher1", gp.tileSize*scale, gp.tileSize*scale*2);
		down2 = setup("/NPC/Fisher2", gp.tileSize*scale, gp.tileSize*scale*2);
		left1 = setup("/NPC/Fisher1", gp.tileSize*scale, gp.tileSize*scale*2);
		left2 = setup("/NPC/Fisher2", gp.tileSize*scale, gp.tileSize*scale*2);
		right1 = setup("/NPC/Fisher1", gp.tileSize*scale, gp.tileSize*scale*2);
		right2 = setup("/NPC/Fisher2", gp.tileSize*scale, gp.tileSize*scale*2);
	}

	public void setDialogue() {
		dialogues[0][0] = "You approach and try to talk to the fisherman sitting on the\nedge of the dock . . .";
		dialogues[0][1] = "But he seems to be ignoring you.\nFishing requires a lot of concentration, afterall.";


	}
	
	public void speak() {
		startDialogue(this, dialogueSet);
		dialogueSet++;
		if(dialogues[dialogueSet][0] == null){
			dialogueSet = 0;
		}
	}
	
	public void setAction() {}
}
