package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import Entity.Entity;
import main.GamePanel;

public class RedChest extends Entity{

	public static final String objectName = "RedChest";
	GamePanel gp;
	Entity loot;
	
	public RedChest(GamePanel gp) {
		super(gp);
		this.gp = gp;
		this.loot = new RedKey(gp);
		
		type = type_Obstacle;
		name = objectName;
		opened = false;
		image = setup("/objects/RedChest", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/RedChestOpen", gp.tileSize, gp.tileSize);
		down1 = image;
		collision = true;
		
		solidArea.x = 4;
		solidArea.y = 16;
		solidArea.width = gp.tileSize - (gp.tileSize/4);
		solidArea.height = gp.tileSize/2;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
	}
	
	public void interact() {
		if(opened == false) {
			gp.playSoundEffect(gp.sound.chestOpen);
			
			StringBuilder sb = new StringBuilder();
			sb.append("You opened the chest and find: " + loot.name + "!");
			
			if(gp.player.obtainItem(loot) == false) {
				sb.append("\n...But you cannot carry any more!");
			}
			else {
				sb.append("\nYou obtain the " + loot.name+"!");
				down1 = image2;
				opened = true;
			}
			dialogues[0][0] = sb.toString();
			startDialogue(this, 0);
		}
		else {
			dialogues[1][0] = "It's empty";
			startDialogue(this, 1);
		}
	}

}