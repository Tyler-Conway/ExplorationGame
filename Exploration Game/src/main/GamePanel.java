package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.Player;
import GameData.SaveLoad;
import InteractiveTiles.InteractiveTile;
import enviornment.EnviornmentManager;
import pathfinding.PathFinder;
import tile.TileManager;
import java.io.*;
import java.lang.*;
import java.util.*;
import tile.Map;

public class GamePanel extends JPanel implements Runnable{
	
	//Basic Game Settings:
	final int originalTileSize = 32; //32x32 Tiles
	final int scale = 2; //scales up the pixels to 64x64
	public final int tileSize = originalTileSize * scale; //64x64 pixel tiles
	public final int maxScreenCollumns = 20;
	public final int maxScreenRows = 12;
	public final int screenWidth = tileSize * maxScreenCollumns; //1280 pixels
	public final int screenHeight = tileSize * maxScreenRows; //960 pixels
	//FPS
	int FPS = 60;

	public KeyHandler keyH = new KeyHandler(this);
	
	//WORLD SETTINGS:
	public final int maxWorldCol = 50, maxWorldRow = 50;
	public final int maxMap = 20;
	public int currentMap = 0;
	
	//ENTITY AND OBJECTS
	public Player player = new Player(this, keyH);
	public Entity obj[][] = new Entity[maxMap][50];
	public Entity npc[][] = new Entity[maxMap][20];
	public Entity monster[][] = new Entity[maxMap][20];
	public Entity projectile[][] = new Entity[maxMap][20];
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> particalList = new ArrayList<>();
	public InteractiveTile iTile[][] = new InteractiveTile[maxMap][50];
	
	//MAP NUMBERS:
	public final int world01 = 0;
	public final int cabin01 = 1;
	public final int stoneBuilding01 = 2;
	public final int stoneBuilding02 = 3;
	public final int stoneBuilding03 = 4;
	public final int world02 = 5;
	public final int cabin02 = 6;
	public final int world03 = 7;
	public final int beach01 = 8;
	public final int beach02 = 9;
	public final int cabin03 = 10;
	public final int lootCabin01 = 11;
	
	//GAME STATES
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int dialogueState = 3;
	public final int characterState = 4;
	public final int optionsState = 5;
	public final int gameOverState = 6;
	public final int transitionState = 7;
	public final int tradeState = 8;
	public final int sleepState = 9;
	public final int mapState = 10;
	public final int travelState = 11;
	
	//SYSTEM
	public TileManager tileM = new TileManager(this);
	Thread gameThread;
	Config config = new Config(this);
	public CollisionDetection  collisionDetection = new CollisionDetection(this);
	public AssetSetter assetSetter= new AssetSetter(this);
	public EventHandler eventHandler = new EventHandler(this);
	public Sound music = new Sound();
	public Sound sound = new Sound();
	public UI ui = new UI(this);
	public PathFinder pathFinder = new PathFinder(this);
	public EnviornmentManager enviornmentManager = new EnviornmentManager(this);
	public Map map = new Map(this);
	public SaveLoad saveLoad = new SaveLoad(this);
	
	
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		assetSetter.setObjects();
		assetSetter.setNPC();
		assetSetter.setMonster();
		assetSetter.setInteractiveTiles();
		enviornmentManager.setup();
		gameState = titleState;
	}
	
	public void retry() {
		currentMap = world01;
		player.setDefaultPositions();
		player.restoreLifeAndProjectiles();
		assetSetter.setNPC();
		assetSetter.setMonster();
		update();
	}
	
	public void restart() {
		currentMap = world01;
		player.setDefaultValues();
		player.inventory.clear();
		player.currentShield = null;
		player.currentWeapon = null;
		player.setDefaultPositions();
		player.restoreLifeAndProjectiles();
		player.setItems();
		assetSetter.setObjects();
		assetSetter.setNPC();
		assetSetter.setMonster();
		assetSetter.setInteractiveTiles();
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
		//uncomment to turn on music:d
		//playMusic(0);
	}

	//This is the game loop:
	public void run() {
		
		double drawInterval = 1000000000/FPS; //1 billion nanoseconds/60FPS = 1/60th of a second updates(or frames).
		double nextDrawTime = System.nanoTime() + drawInterval;
		//while the game thread exists, repeat the game loop:
		while(gameThread != null) {
			//Update Game Information:
			update();
			
			//Draw the Newly Updated Screen (invokes the paintComponent method):
			//"repaint()" is the proper name for the paintComponent method instead of "paintComponent()"
			repaint();
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000; //convert nano into milliseconds
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				//Sleep uses milliseconds instead of nanoseconds, which is why we needed to convert the units.
				Thread.sleep((long)remainingTime);
				
				nextDrawTime += drawInterval;
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
	}
	
	public void update() {
		if(gameState == playState) {

			player.update();
			
			for(int i = 0; i < npc[0].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
				}
			}
			//MONSTERS
			for(int i = 0; i < monster[0].length; i++) {
				if(monster[currentMap][i] != null) {
					if(monster[currentMap][i].alive == true && monster[currentMap][i].dying == false) {
						monster[currentMap][i].update();
					}
					if(monster[currentMap][i].alive == false) {
						monster[currentMap][i].checkDrop();
						monster[currentMap][i] = null;
					}
				}
			}
			//Projectiles
			for(int i = 0; i < projectile[0].length; i++) {
				if(projectile[currentMap][i] != null) {
					if(projectile[currentMap][i].alive == true) {
						projectile[currentMap][i].update();
					}
					if(projectile[currentMap][i].alive == false) {
						projectile[currentMap][i] = null;
					}
				}
			}
			//PARTICLES:
			for(int i = 0; i < particalList.size(); i++) {
				if(particalList.get(i) != null) {
					if(particalList.get(i).alive == true) {
						particalList.get(i).update();
					}
					if(particalList.get(i).alive == false) {
						particalList.remove(i);
					}
				}
			}
			for(int i = 0; i < iTile[0].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].update();
				}
			}
		}
		enviornmentManager.update();
	}
	
	//drawToTempScreen:
	public void paintComponent(Graphics g) {
		super.paintComponent(g); //invokes the paintComponent method in JPanel
		
		Graphics2D g2 = (Graphics2D)g;
		//DEBUG
		long drawStart = 0;
		if(keyH.showDebugText == true) {
			drawStart = System.nanoTime();
		}
		
		//Title Screen:
		if(gameState == titleState) {
			ui.draw(g2);
		}
		else if(gameState == mapState) {
			map.drawFullMapScreen(g2);
		}
		//Other Screens:
		else {
			tileM.draw(g2);
			
			for(int i = 0; i < iTile[0].length; i++) {
				if(iTile[currentMap][i] != null) {
					iTile[currentMap][i].draw(g2);
				}
			}
			
			//Add entities to list:
			//NPC
			for(int i = 0; i < npc[0].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			//OBJECTS
			for(int i = 0; i < obj[0].length; i++) {
				if(obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
				}
			}
			//MONSTERS
			for(int i = 0; i < monster[0].length; i++) {
				if(monster[currentMap][i] != null) {
					entityList.add(monster[currentMap][i]);
				}
			}
			//PROJECTILES: 
			for(int i = 0; i < projectile[0].length; i++) {
				if(projectile[currentMap][i] != null) {
					entityList.add(projectile[currentMap][i]);
				}
			}
			for(int i = 0; i < particalList.size(); i++) {
				if(particalList.get(i) != null) {
					entityList.add(particalList.get(i));
				}
			}
			entityList.add(player);
			
			//Draw Entities:
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			
			entityList.clear(); //Reset Entity List
			enviornmentManager.draw(g2); // needs to be before UI, so UI is above enviornmental effects.
			map.drawMiniMap(g2); // draws minimap above the Enviornmental effects.
			ui.draw(g2); 
			
			if(keyH.showDebugText == true) {
				long drawEnd = System.nanoTime();
				long passed = drawEnd - drawStart;
				g2.setFont(ui.arial.deriveFont(20F));
				g2.setColor(Color.white);
				
				int x = 10, y = 400, lineHeight = 20;
				g2.drawString("WorldX: " + player.worldX, x, y);
				g2.drawString("WorldY: " + player.worldY, x, y += lineHeight);
				g2.drawString("Col: " + (player.worldX + player.solidArea.x) / tileSize, x, y += lineHeight);
				g2.drawString("Row: " + (player.worldY + player.solidArea.y) / tileSize, x, y += lineHeight);
				g2.drawString("Draw Time: " + passed + " ns", x, y += lineHeight);
			}
			
			g2.dispose();
		}
	}
	
	public void getCurrentMap(){
		//Maps also need to be setup in tileManager:
		switch(currentMap) {
		case world01: tileM.loadMap("/maps/World01.txt", world01); break;
		case cabin01: tileM.loadMap("/maps/Cabin01.txt", cabin01); break;
		case stoneBuilding01: tileM.loadMap("/maps/StoneBuilding01.txt", stoneBuilding01); break;
		case stoneBuilding02: tileM.loadMap("/maps/StoneBuilding02.txt", stoneBuilding02); break;
		case stoneBuilding03: tileM.loadMap("/maps/StoneBuilding03.txt", stoneBuilding03); break;
		case world02: tileM.loadMap("/maps/World02.txt", world02); break;
		case cabin02: tileM.loadMap("/maps/Cabin02.txt", cabin02); break;
		case world03: tileM.loadMap("/maps/World03.txt", world03); break;
		case beach01: tileM.loadMap("/maps/Beach01.txt", beach01); break;
		case beach02: tileM.loadMap("/maps/Beach02.txt", beach02); break;
		case cabin03: tileM.loadMap("/maps/Cabin03.txt", cabin03); break;
		case lootCabin01: tileM.loadMap("/maps/lootCabin01.txt", lootCabin01); break;
		}
	}
	
	public void playMusic(int i) {
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		music.stop();
	}
	
	public void playSoundEffect(int i) {
		sound.setFile(i);
		sound.play();
	}
	
}
