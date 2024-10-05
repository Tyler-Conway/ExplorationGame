package tile;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Map extends TileManager{

	GamePanel gp;
	BufferedImage worldMap[];
	public boolean miniMapOn = false;
	int fullMapSize, miniMapSize, miniMapXYOffset = 5, miniMapPlayerOffset = 5;
	
	public Map(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		fullMapSize = (int)(gp.tileSize*12);
		miniMapSize = 350;
		createWorldMap();
		
		
	}

	public void createWorldMap() {
		worldMap = new BufferedImage[gp.maxMap];
		int worldMapWidth = gp.tileSize * gp.maxWorldCol;
		int worldMapHeight = gp.tileSize * gp.maxWorldRow;
		
		for(int i = 0; i < gp.maxMap; i++) {
			worldMap[i] = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = (Graphics2D)worldMap[i].createGraphics();
			
			int col = 0;
			int row = 0;
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				int tileNum = mapTileNum[i][col][row];
				int x = gp.tileSize * col;
				int y = gp.tileSize * row;
				g2.drawImage(tile[tileNum].image, x, y, null);
				
				col++;
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
				
			}
			g2.dispose();
		}
	}
	
	public void drawFullMapScreen(Graphics2D g2) {
		//Background Color:
		g2.setColor(Color.black);
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		//DrawMap:
		int width = fullMapSize;
		int height = fullMapSize;
		int x = (gp.screenWidth/2) - width/2;
		int y = (gp.screenHeight/2) - height/2;
		g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
		
		//Draw Player at current position:
		double scale = (double)(gp.tileSize*gp.maxWorldCol)/fullMapSize;
		int playerX = (int)(x+gp.player.worldX/scale);
		int playerY = (int)(y+gp.player.worldY/scale);
		int playerSize = (int)(gp.tileSize/scale);
		g2.drawImage(gp.player.down1, playerX, playerY, playerSize, playerSize, null);
		
		//Hint
		g2.setFont(gp.ui.arial.deriveFont(18F));
		g2.setColor(Color.white);
		g2.drawString("(Press M to Close)", gp.tileSize*17, gp.tileSize*12 - miniMapXYOffset);

	}
	
	public void drawMiniMap(Graphics2D g2) {
		if(miniMapOn == true) {
			int width = miniMapSize;
			int height = miniMapSize;
			int x = gp.screenWidth - width - miniMapXYOffset;
			int y = miniMapXYOffset;

			//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
			g2.drawImage(worldMap[gp.currentMap], x, y, width, height, null);
			
			//Draw Player:
			double scale = (double)(gp.tileSize*gp.maxWorldCol)/width;
			int playerX = (int)(x+gp.player.worldX/scale);
			int playerY = (int)(y+gp.player.worldY/scale);
			int playerSize = (int)(gp.tileSize/3);
			g2.drawImage(gp.player.down1, playerX - miniMapPlayerOffset, playerY - miniMapPlayerOffset, playerSize, playerSize, null);
			
			//Hint
			g2.setFont(gp.ui.arial.deriveFont(18F));
			g2.setColor(Color.white);
			g2.drawString("(Press X to Close)", x, y + height + gp.tileSize/3);
			
			//Reset Alpha:
			//g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

			
			
		}
	}
}
