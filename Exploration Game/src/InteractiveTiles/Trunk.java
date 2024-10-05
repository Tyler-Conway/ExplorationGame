package InteractiveTiles;

import Entity.Entity;
import main.GamePanel;

public class Trunk extends InteractiveTile {

	public Trunk(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = gp.tileSize* col;
		this.worldY = gp.tileSize * row;
		name = "Trunk";
		
		down1 = setup("/Interactive/Trunk", gp.tileSize, gp.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
}
