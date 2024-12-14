package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

public class TileManager {

	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][][];
	boolean drawPath = true;
	
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[50];
		mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
		getTileImage();
		setUpMaps();
	}

	public void setUpMaps(){
		//This Creates the Full and Mini maps:
		loadMap("/maps/World01.txt", gp.world01);
		loadMap("/maps/Cabin01.txt", gp.cabin01);
		loadMap("/maps/StoneBuilding01.txt", gp.stoneBuilding01);
		loadMap("/maps/StoneBuilding02.txt", gp.stoneBuilding02);
		loadMap("/maps/StoneBuilding03.txt", gp.stoneBuilding03);
		loadMap("/maps/StoneBuilding04.txt", gp.stoneBuilding04);
		loadMap("/maps/StoneBuilding05.txt", gp.stoneBuilding05);
		loadMap("/maps/World02.txt", gp.world02);
		loadMap("/maps/Cabin02.txt", gp.cabin02);
		loadMap("/maps/World03.txt", gp.world03);
		loadMap("/maps/Beach01.txt", gp.beach01);
		loadMap("/maps/Beach02.txt", gp.beach02);
		loadMap("/maps/Cabin03.txt", gp.cabin03);
		loadMap("/maps/lootCabin01.txt", gp.lootCabin01);
		loadMap("/maps/lootCabin01.txt", gp.lootCabin02);
		loadMap("/maps/World04.txt", gp.world04);
	}
	
	public void setup(int index, String imageName, boolean collision) {
		
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imageName + ".png"));
			tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collision = collision;
			
		}catch(Exception e) {
			
		}
	}
	
	public void loadMap(String filePath, int mapNum) {
		try {
			
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader( new InputStreamReader(is));
			
			int row = 0;
			int col = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[mapNum][col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getTileImage() {
		
			setup(0, "NeoGrass", false);
			setup(1, "Wall", true);
			setup(2, "NeoWater", true);
			setup(3, "Tree4", true);
			setup(4, "Dirt2", false);
			setup(5, "Sand2", false);
			setup(6, "SmallWindow", true);
			setup(7, "WaterBorderTop", true);
			setup(8, "WaterBorderLeft", true);
			setup(9, "WaterBorderRight", true);
			setup(10, "WaterBorderBottom", true);
			setup(11, "Water2", true);
			setup(12, "WaterBorderTopLeft", true);
			setup(13, "WaterBorderTopRight", true);
			setup(14, "WaterBorderBottomLeft", true);
			setup(15, "Cabin", false);
			setup(16, "CabinFloor", false);
			setup(17, "WoodTable", true);
			setup(18, "WaterBorderBottomRight", true);
			setup(19, "Door", false);
			setup(20, "YellowDoor", false);
			setup(21, "BlueDoor", false);
			setup(22, "RedDoor", false);
			setup(23, "ColorfulDoor", false);
			setup(24, "WaterCornerTopLeft", true);
			setup(25, "WaterCornerTopRight", true);
			setup(26, "WaterCornerBottomLeft", true);
			setup(27, "WaterCornerBottomRight", true);
			setup(28, "Sand1", false);
			setup(29, "Sand2", false);
			setup(30, "WoodFloor", false);
			setup(31, "DockPostLeft", true);
			setup(32, "DockPostRight", true);
			setup(33, "DockSupportBeam", true);
			setup(34, "DockFloorHole", false);
			setup(35, "VerticalWoodFloor", false);
			
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0, worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			//save resources by only drawing tiles the player can see 
			//(+1 to avoid seeing un-drawn tiles):
			if(worldX+gp.tileSize > gp.player.worldX - gp.player.screenX &&
			   worldX-gp.tileSize < gp.player.worldX + gp.player.screenX &&
			   worldY+gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY-gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);

			}
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
			
		}
		//draw the pathfinding traces:
		
		// if(drawPath == true) {
		// 	g2.setColor(new Color(255, 0, 0, 70));
		// 	for(int i = 0; i < gp.pathFinder.pathList.size(); i++) {
				
		// 		int worldX = gp.pathFinder.pathList.get(i).col * gp.tileSize;
		// 		int worldY = gp.pathFinder.pathList.get(i).row * gp.tileSize;
		// 		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		// 		int screenY = worldY - gp.player.worldY + gp.player.screenY;
				
		// 		g2.fillRect(screenX, screenY, gp.tileSize, gp.tileSize);
				
		// 	}
			
		// }
	}
	
	
}
