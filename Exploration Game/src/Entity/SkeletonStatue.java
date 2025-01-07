package Entity;

import main.GamePanel;

public class SkeletonStatue extends Entity{

    public static final String objectName = "SkeletonStatue";
    int scale = 3;

	public SkeletonStatue(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 0;
		name = objectName;
		dialogueSet = -1;
		getImage();
		setDialogue();

        solidArea.x = (gp.tileSize/4);
        solidArea.y = (gp.tileSize/4);
        solidArea.width = gp.tileSize*scale - (gp.tileSize/4);
        solidArea.height = gp.tileSize*scale - (gp.tileSize/4);
	}
	
	public void getImage() {
		up1 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		up2 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		down1 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		down2 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		left1 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		left2 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		right1 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
		right2 = setup("/NPC/SkeletonStatue", gp.tileSize*scale, gp.tileSize*scale);
	}

	public void setDialogue() {
		dialogues[0][0] = "As you approach the statue, you notice an inscription.";
		dialogues[0][1] = "\"Attention travelers, beware of the Skeleton Giaint and seek\n not its famed Amethyst. For many before you have sought this\n treasure, and many have died\"";
		dialogues[0][2] = "Will you heed the statue's warning?";


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
