package InteractiveTiles;

import Entity.Entity;
import main.GamePanel;

public class MetalPlate extends InteractiveTile {
    
    public static final String objectName = "MetalPlate";

	public MetalPlate(GamePanel gp, int col, int row) {
		super(gp, col, row);
		this.gp = gp;
		this.worldX = gp.tileSize* col;
		this.worldY = gp.tileSize * row;
		name = objectName;
		
		down1 = setup("/Interactive/MetalPlate", gp.tileSize, gp.tileSize);
		
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 0;
		solidArea.height = 0;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
	}
}
