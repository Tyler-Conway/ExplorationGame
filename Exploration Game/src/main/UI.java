package main;

import Entity.Entity;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import objects.Arrow;
import objects.CoinBronze;
import objects.Heart;
import objects.ManaCrystal;
import objects.Rock;

public class UI {

	GamePanel gp;
	public Font arial;
	Graphics2D g2;
	BufferedImage fullHeart, halfHeart, emptyHeart, fullMana, emptyMana, fullArrow, fullRock, coin;
	public boolean messageOn = false;
	public ArrayList<String> message = new ArrayList<>();
	public ArrayList<Integer> messageCounter = new ArrayList<>();
	//public String playerClass = "";
	
	
	public boolean gameFinished = false;
	double playTime;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	public String currentDialogue;
	public int commandNum = 0;
	public int titleScreenState = 0; // 0: first Screen, 1: 2nd Screen
	public int playerSlotCol = 0, playerSlotRow = 0, playerMaxSlotCol = 4, playerMaxSlotRow = 3;
	public int npcSlotCol = 0, npcSlotRow = 0;
	public int substate = 0;
	public int counter = 0;
	public Entity trader;
	
	
	
	
	
	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/Font/ARIAL.TTF");
			arial = Font.createFont(Font.TRUETYPE_FONT, is);
			
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();	
		}
		
		//Heart Images:
		Entity heart = new Heart(gp);
		fullHeart = heart.image;
		halfHeart = heart.image2;
		emptyHeart = heart.image3;
		
		
		//Fighter Images:
		Entity arrow = new Arrow(gp);
		fullArrow = arrow.image;
		
		//Wizard Images:
		Entity crystal = new ManaCrystal(gp);
		fullMana = crystal.image;
		emptyMana = crystal.image2;
		
		Entity rock = new Rock(gp);
		fullRock = rock.up1;
		
		Entity bronzeCoin = new CoinBronze(gp);
		coin = bronzeCoin.down1;
		
	}
	
 	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(arial);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(Color.white);
		
		
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		if(gp.gameState == gp.playState) {
			drawPlayerHUD();
			drawMonsterHealthBars();
			drawMessage();
			
		}
		if(gp.gameState == gp.pauseState) {
			drawPlayerHUD();
			drawMonsterHealthBars();
			drawPauseScreen();
		}
		if(gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		if(gp.gameState == gp.characterState) {
			drawCharacterScreen();
			drawInventory(gp.player, true);
		}
		if(gp.gameState == gp.optionsState) {
			drawOptionsScreen();
		}
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		if(gp.gameState == gp.transitionState) {
			drawTransition();
		}
		if(gp.gameState == gp.tradeState) {
			drawTradeScreen();
		}
		if(gp.gameState == gp.sleepState) {
			drawSleepScreen();
		}
		if(gp.gameState == gp.travelState) {
			drawTravelScreen();
		}
	}
 	
 	public void drawTradeScreen() {
 		switch(substate) {
 		case 0:
 			tradeSelect();
 			break;
 		case 1: 
 			tradeBuy();
 			break;
 		case 2: 
 			tradeSell();
 			break;
 		}
 		gp.keyH.enterPressed = false;
 	}
 	
 	public void tradeSelect() {
 		drawDialogueScreen();
 		
 		int x = gp.tileSize * 15;
 		int y = gp.tileSize * 4;
 		int width = gp.tileSize * 3;
 		int height = (int)(gp.tileSize * 3.5);
 		drawSubWindow(x,y,width,height);
 		
 		//Draw Texts
 		x += gp.tileSize;
 		y += gp.tileSize;
 		g2.drawString("Buy", x, y);
 		if(commandNum == 0) {
 			g2.drawString(">", x-25, y);
 			if(gp.keyH.enterPressed == true) {
 				substate = 1;
 			}
 		}
 		y += gp.tileSize;
 		g2.drawString("Sell", x, y);
 		if(commandNum == 1) {
 			g2.drawString(">", x-25, y);
 			if(gp.keyH.enterPressed == true) {
 				substate = 2;
 			}
 			
 		}
 		y += gp.tileSize;
 		g2.drawString("Leave", x, y);
 		if(commandNum == 2) {
 			g2.drawString(">", x-25, y);
 			if(gp.keyH.enterPressed == true) {
 				commandNum = 0;
 				gp.gameState = gp.dialogueState;
 				currentDialogue = "Come Again, hehe!";
 			}
 		}
 	}
 	
 	public void tradeBuy() {
 		drawInventory(trader, true);
 		drawInventory(gp.player, false);
 		
 		//Hint Window:
 		int x = gp.tileSize*2;
 		int y = gp.tileSize*9;
 		int width = gp.tileSize*6;
 		int height = gp.tileSize*2;
 		drawSubWindow(x,y,width,height);
 		g2.drawString("[ESC] Back", x+25, y+55);
 		//Player Coin window:
 		x = gp.tileSize*12;
 		y = gp.tileSize*9;
 		width = gp.tileSize*6;
 		height = gp.tileSize*2;
 		drawSubWindow(x,y,width,height);
 		g2.drawString("Your Coin: " + gp.player.coin, x+25, y + 55);
 		
 		//Draw Price Window:
 		int itemIndex = getItemIndex(npcSlotCol, npcSlotRow);
 		if(itemIndex < trader.inventory.size()) {
 			x = (int)(gp.tileSize*5.5);
 			y = (int)(gp.tileSize*5.5);
 			width = (int)(gp.tileSize*2.5);
 			height = gp.tileSize;
 			drawSubWindow(x,y,width,height);
 			g2.drawImage(coin, x+10, y+gp.tileSize/4, gp.tileSize/2, gp.tileSize/2, null);
 			
 			int price = trader.inventory.get(itemIndex).price;
 			String text = ""+price;
 			x = getXForRightText(text, gp.tileSize*8);
 			g2.drawString(text, x-15, y+40);
 			
 			
 			//Buy an Item
 			if(gp.keyH.enterPressed == true) {
 				if(trader.inventory.get(itemIndex).price > gp.player.coin) {
 					substate = 0;
 					gp.gameState = gp.dialogueState;
 					currentDialogue = ("You need more coins to buy that!");
 					drawDialogueScreen();
 				}
 				else {
 					if(gp.player.obtainItem(trader.inventory.get(itemIndex)) == true) {
 						gp.player.coin -= trader.inventory.get(itemIndex).price;
 					}
 					else {
 						substate = 0;
 	 					gp.gameState = gp.dialogueState;
 	 					currentDialogue = ("You have no more room in your inventory!");
 	 					drawDialogueScreen();
 					}
 				}
 			}
 		}
 		
 	}
 	
 	public void tradeSell() {
 		drawInventory(gp.player, true);
 		drawInventory(trader, false);
 		
 		//Hint Window:
 		int x = gp.tileSize*2;
 		int y = gp.tileSize*9;
 		int width = gp.tileSize*6;
 		int height = gp.tileSize*2;
 		drawSubWindow(x,y,width,height);
 		g2.drawString("[ESC] Back", x+25, y+55);
 		//Player Coin window:
 		x = gp.tileSize*12;
 		y = gp.tileSize*9;
 		width = gp.tileSize*6;
 		height = gp.tileSize*2;
 		drawSubWindow(x,y,width,height);
 		g2.drawString("Your Coin: " + gp.player.coin, x+25, y + 55);
 		
 		//Draw Price Window:
 		int itemIndex = getItemIndex(playerSlotCol, playerSlotRow);
 		if(itemIndex < gp.player.inventory.size()) {
 			x = (int)(gp.tileSize*15.5);
 			y = (int)(gp.tileSize*5.5);
 			width = (int)(gp.tileSize*2.5);
 			height = gp.tileSize;
 			drawSubWindow(x,y,width,height);
 			g2.drawImage(coin, x+10, y+gp.tileSize/4, gp.tileSize/2, gp.tileSize/2, null);
 			
 			int price = gp.player.inventory.get(itemIndex).price/2;
 			String text = ""+price;
 			x = getXForRightText(text, gp.tileSize*18);
 			g2.drawString(text, x-15, y+40);
 			
 			
 			//Sell an Item:
 			if(gp.keyH.enterPressed == true) {
 				if(gp.player.inventory.get(itemIndex) == gp.player.currentShield || 
 						gp.player.inventory.get(itemIndex) == gp.player.currentWeapon) {
 					
 					commandNum = 0;
 					substate = 0;
 					gp.gameState = gp.dialogueState;
 					currentDialogue = ("You cannot sell equiped items!");
 					drawDialogueScreen();
 				}
 				else {
 					if(gp.player.inventory.get(itemIndex).amount > 1) {
 						gp.player.inventory.get(itemIndex).amount--;
 					}
 					else {
 	 					gp.player.inventory.remove(itemIndex);
 	 				}
 					
 					gp.player.coin += price;
 				}
 			}
 		}
 		
 	}
 	
 	public void drawTransition() {
 		counter++;
 		g2.setColor(new Color(0,0,0,counter*5));
 		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
 		if(counter >= 50) {
 			counter = 0;
 			gp.gameState = gp.playState;
 			gp.currentMap = gp.eventHandler.tempMap;
 			gp.player.worldX = gp.tileSize * gp.eventHandler.tempCol;
 			gp.player.worldY = gp.tileSize * gp.eventHandler.tempRow;
 			gp.eventHandler.previousEventX = gp.player.worldX;
 			gp.eventHandler.previousEventY = gp.player.worldY;
			gp.changeArea();
 			
 			if(gp.currentMap == gp.beach01 && gp.eventHandler.previousMap != gp.beach02) {
 				gp.stopMusic();
 				gp.playMusic(gp.sound.beachMusic);
 			}
 			else if(gp.eventHandler.previousMap == gp.beach01 && gp.currentMap == gp.world02) {
 				gp.stopMusic();
 				gp.playMusic(gp.sound.worldMusic);
 			}
 			else if(gp.currentMap == gp.cabin01 || gp.currentMap == gp.cabin02 || gp.currentMap == gp.cabin03) {
 				gp.stopMusic();
 				gp.playMusic(gp.sound.cabinMusic);
 			}
 			else if((gp.currentMap == gp.world01 || gp.currentMap == gp.world02) && (gp.eventHandler.previousMap == gp.cabin01 || gp.eventHandler.previousMap == gp.cabin02)) {
 				gp.stopMusic();
 				gp.playMusic(gp.sound.worldMusic);
 			}
 			else if(gp.currentMap == gp.beach02 && gp.eventHandler.previousMap == gp.cabin03) {
 				gp.stopMusic();
 				gp.playMusic(gp.sound.beachMusic);
 			}
 		}
 	}
 	
	public void drawGameOverScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		int x, y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,110F));
		
		text = "Game Over";
		//Shadow
		g2.setColor(Color.black);
		x = getXForCenteredText(text);
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//Main:
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//Retry:
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXForCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40, y);
		}
		
		//Back to title:
		text = "Quit";
		x = getXForCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.drawString(">", x-40, y);
		}
		
	}
	
	public void drawSleepScreen() {
		counter++;
		gp.player.direction = "down";
		gp.player.down1 = gp.player.setup("/objects/Tent", gp.tileSize, gp.tileSize);
		gp.player.down2 = gp.player.setup("/objects/Tent", gp.tileSize, gp.tileSize);
		gp.player.attackDown1 = gp.player.setup("/objects/Tent", gp.tileSize, gp.tileSize);
		gp.player.attackDown2 = gp.player.setup("/objects/Tent", gp.tileSize, gp.tileSize);
		
		
		if(counter < 120) {
			gp.enviornmentManager.lighting.filterAlpha += 0.01f;
			if(gp.enviornmentManager.lighting.filterAlpha > 1f) {
				gp.enviornmentManager.lighting.filterAlpha = 1f;
			}
		}
		if (counter >= 120) {
			gp.enviornmentManager.lighting.filterAlpha -= 0.01f;
			if(gp.enviornmentManager.lighting.filterAlpha <= 0f) {
				gp.playSoundEffect(14);
				gp.player.getPlayerImage();
				gp.player.getPlayerAttackImage();
				gp.enviornmentManager.lighting.filterAlpha = 0f;
				counter = 0;
				gp.enviornmentManager.lighting.dayState = gp.enviornmentManager.lighting.day;
				gp.enviornmentManager.lighting.dayCounter = 0;
				gp.gameState = gp.playState;
			}
		}
	}
	
	public void drawTravelScreen() {
		switch(substate) {
 		case 0:
 			fastTravelSelect();
 			break;
 		case 1: 
 			fastTravelScreen();
 			break;
 		}
 		gp.keyH.enterPressed = false;
	}
	
	public void fastTravelScreen() {
		g2.setColor(Color.white);
 		g2.setFont(g2.getFont().deriveFont(24F));
 		
 		//Fast Travel SubWindow:
 		int frameX = gp.tileSize*2;
 		int frameY = gp.tileSize;
 		int frameWidth = gp.tileSize*16;
 		int frameHeight = gp.tileSize*9;
 		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
 		
 		//Hint Window:
 		int x = gp.tileSize*2;
 		int y = gp.tileSize*10;
 		int width = gp.tileSize*6;
 		int height = gp.tileSize*2;
 		drawSubWindow(x,y,width,height);
 		g2.drawString("[ESC] Back", x+25, y+55);
 		g2.drawString("[ENTER] Fast Travel", x+25, y+95);
	}
	
	public void fastTravelSelect() {
		drawDialogueScreen();
 		
 		int x = gp.tileSize * 15;
 		int y = gp.tileSize * 4;
 		int width = gp.tileSize * 3;
 		int height = (int)(gp.tileSize * 3.5);
 		drawSubWindow(x,y,width,height);
 		
 		//Draw Texts
 		x += gp.tileSize;
 		y += gp.tileSize;
 		g2.drawString("Yes", x, y);
 		if(commandNum == 0) {
 			g2.drawString(">", x-25, y);
 			if(gp.keyH.enterPressed == true) {
 				substate = 1;
 			}
 		}
 		y += gp.tileSize;
 		g2.drawString("Leave", x, y);
 		if(commandNum == 1) {
 			g2.drawString(">", x-25, y);
 			if(gp.keyH.enterPressed == true) {
 				commandNum = 0;
 				gp.gameState = gp.dialogueState;
 				currentDialogue = "Come Again, hehe!";
 			}
 		}
	}
	
 	public void drawOptionsScreen() {
 		g2.setColor(Color.white);
 		g2.setFont(g2.getFont().deriveFont(24F));
 		
 		//SubWindow:
 		int frameX = gp.tileSize*6;
 		int frameY = gp.tileSize;
 		int frameWidth = gp.tileSize*8;
 		int frameHeight = gp.tileSize*10;
 		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
 		
 		switch(substate) {
 		case 0: optionsTop(frameX, frameY); break;
 		case 1: optionsControl(frameX, frameY); break;
 		case 2: optionsEndGameConfirmation(frameX, frameY); break;
 		}
 		gp.keyH.enterPressed = false;
 	}

 	public void optionsTop(int frameX, int frameY) {
 		int textX;
 		int textY;
 		g2.setFont(g2.getFont().deriveFont(32f));
 		//Title:
 		String text = "Options";
 		textX = getXForCenteredText(text);
 		textY = frameY + gp.tileSize;
 		g2.drawString(text, textX, textY);
 		//Music:
 		textX = frameX + gp.tileSize;
 		textY += gp.tileSize*2;
 		g2.drawString("Music", textX, textY);
 		if(commandNum == 0) {
 			g2.drawString(">", textX-(gp.tileSize/2), textY);
 		}
 		//Sound Effects:
 		textY += gp.tileSize;
 		g2.drawString("Sounds", textX, textY);
 		if(commandNum == 1) {
 			g2.drawString(">", textX-(gp.tileSize/2), textY);
 		}
 		//Controls:
 		textY += gp.tileSize;
 		g2.drawString("Controls", textX, textY);
 		if(commandNum == 2) {
 			g2.drawString(">", textX-(gp.tileSize/2), textY);
 			if(gp.keyH.enterPressed == true) {
 				substate = 1;
 				commandNum = 0;
 			}
 		}
 		//Exit game:
 		textY += gp.tileSize;
 		g2.drawString("End Game", textX, textY);
 		if(commandNum == 3) {
 			g2.drawString(">", textX-(gp.tileSize/2), textY);
 			if(gp.keyH.enterPressed == true) {
 				substate = 2;
 				commandNum = 0;
 			}
 		}
 		//Back to game:
 		textY += gp.tileSize*2;
 		g2.drawString("Back", textX, textY);
 		if(commandNum == 4) {
 			g2.drawString(">", textX-(gp.tileSize/2), textY);
 			if(gp.keyH.enterPressed == true) {
 				gp.gameState = gp.playState;
 				commandNum = 0;
 			}
 		}
 		
 		//RESET values:
 		textX = frameX + (int)(gp.tileSize*4.5);
 		textY = frameY + gp.tileSize*2 + (gp.tileSize/2);
 		g2.setStroke(new BasicStroke(3));
 		//Music Volume:
 		g2.drawRect(textX, textY, (int)(gp.tileSize*2.5), gp.tileSize/2);
 		int volumeWidth = (gp.tileSize/2)*gp.music.volumeScale;
 		g2.fillRect(textX, textY, volumeWidth, (gp.tileSize/2));
 		
 		//Sound Volume:
 		textY += gp.tileSize;
 		g2.drawRect(textX, textY, (int)(gp.tileSize*2.5), gp.tileSize/2);
 		int soundWidth = (gp.tileSize/2)*gp.sound.volumeScale;
 		g2.fillRect(textX, textY, soundWidth, (gp.tileSize/2));
 		gp.config.saveConfig();
 	}
 	
	public void optionsControl(int frameX, int frameY) {
		int textX; 
		int textY;
		
		
		//Title:
		String text = "Controls";
		textX = getXForCenteredText(text);
		textY = frameY + gp.tileSize;
		g2.drawString(text, textX, textY);
		
		textX = frameX + gp.tileSize;
		textY += gp.tileSize/2;
		
		g2.drawString("Move", textX, textY);
		if(commandNum == 0) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("Confirm/Attack", textX, textY);
		if(commandNum == 1) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("Shoot/Cast", textX, textY);
		if(commandNum == 2) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("Inventory Screen", textX, textY);
		if(commandNum == 3) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("Pause", textX, textY);
		if(commandNum == 4) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("Options", textX, textY);
		if(commandNum == 5) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		g2.drawString("MiniMap/Map", textX, textY);
		if(commandNum == 6) {g2.drawString(">", textX-25, textY);}
		textY += gp.tileSize;
		
		textX = frameX + gp.tileSize*6;
		textY = frameY + (int)(gp.tileSize*1.5);
		g2.drawString("WASD", textX, textY); textY += gp.tileSize;
		g2.drawString("ENTER", textX, textY); textY += gp.tileSize;
		g2.drawString("F", textX, textY); textY += gp.tileSize;
		g2.drawString("C", textX, textY); textY += gp.tileSize;
		g2.drawString("P", textX, textY); textY += gp.tileSize;
		g2.drawString("ESC", textX, textY); textY += gp.tileSize;
		g2.drawString("X/M", textX, textY); textY += gp.tileSize;
		
		//BACK
		textX = frameX + gp.tileSize;
		textY = frameY + gp.tileSize*9;
		g2.drawString("Back", textX, textY);
		if(commandNum == 7) {
			g2.drawString(">", textX-25, textY);
			if(gp.keyH.enterPressed == true) {
				substate = 0;
				commandNum = 2;
			}
		}
		
	}

	public void optionsEndGameConfirmation(int frameX, int frameY) {
		int textX = frameX + gp.tileSize;
		int textY = frameY + gp.tileSize;
		
		currentDialogue = "Save & Quit the game and \nreturn to the title screen?";
		for(String line: currentDialogue.split("\n")) {
			g2.drawString(line, textX, textY);
			textY += 40;
		}
		
		//YES
		String text = "Yes";
		textX = getXForCenteredText(text);
		textY += gp.tileSize*3;
		g2.drawString(text, textX, textY);
		if(commandNum == 0) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				gp.saveLoad.save();
				substate = 0;
				gp.gameState = gp.titleState;
				titleScreenState = 0;
			}
		}
		//NO
		text = "No";
		textX = getXForCenteredText(text);
		textY += gp.tileSize;
		g2.drawString(text, textX, textY);
		if(commandNum == 1) {
			g2.drawString(">", textX - 25, textY);
			if(gp.keyH.enterPressed == true) {
				substate = 0;
				commandNum = 4;
			}
		}
		
	}
 	
	public void drawCharacterScreen() {
		//Create a Frame:
		final int frameX = gp.tileSize, frameY = gp.tileSize, frameWidth = gp.tileSize*5, frameHeight = gp.tileSize * 10;
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20, textY = frameY + gp.tileSize;
		final int lineHeight = 35;
		
		//Names:
		g2.drawString("Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Life", textX, textY);
		textY += lineHeight;
		if(gp.player.playerClass.equals("Fighter")) {
			g2.drawString("Arrows", textX, textY);
			textY += lineHeight;
		}
		else if(gp.player.playerClass.equals("Wizard")) {
			g2.drawString("Mana", textX, textY);
			textY += lineHeight;
		}
		else if(gp.player.playerClass.equals("Peasant")) {
			g2.drawString("Rocks", textX, textY);
			textY += lineHeight;
		}
		g2.drawString("Strength", textX, textY);
		textY += lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY += lineHeight;
		g2.drawString("Attack", textX, textY);
		textY += lineHeight;
		g2.drawString("Defense", textX, textY);
		textY += lineHeight;
		g2.drawString("Exp", textX, textY);
		textY += lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY += lineHeight;
		g2.drawString("Coins", textX, textY);
		textY += lineHeight;
		
		//make space between numbers and images:
		textY += gp.tileSize - 35;
		
		g2.drawString("Weapon", textX, textY);
		textY += gp.tileSize;
		g2.drawString("Shield", textX, textY);

		
		
		//Stat Values
		//reset textY for values section:
		textY = frameY + gp.tileSize;
		int tailX = (frameX + frameWidth) - 30;
		charaterValueScreenSetup(textY, tailX, String.valueOf(gp.player.level));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.life + "/" + gp.player.maxLife));
		if(gp.player.playerClass.equals("Fighter")) {
			charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.arrows + "/" + gp.player.maxArrows));
		}
		else if(gp.player.playerClass.equals("Wizard")) {
			charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.mana + "/" + gp.player.maxMana));
		}
		else if(gp.player.playerClass.equals("Peasant")) {
			charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.ammo + "/" + gp.player.maxAmmo));
		}
		
		
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.strength));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.dexterity));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.attack));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.defense));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.exp));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.nextLevelExp));
		charaterValueScreenSetup((textY += lineHeight), tailX, String.valueOf(gp.player.coin));
		
		//Equipment Images:
		charaterValueScreenSetup(textY += gp.tileSize - 35, tailX - gp.tileSize, gp.player.currentWeapon.down1);
		charaterValueScreenSetup(textY + gp.tileSize , tailX - gp.tileSize, gp.player.currentShield.down1);
		
		
	}

	public void drawInventory(Entity entity, boolean showCursor) {
		
		int frameX = 0;
		int frameY = 0;
		int frameWidth = 0;
		int frameHeight = 0;
		int slotCol = 0;
		int slotRow = 0;
		
		if(entity == gp.player) {
			frameX = gp.tileSize*12;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = playerSlotCol;
			slotRow = playerSlotRow;
		}
		else {
			frameX = gp.tileSize*2;
			frameY = gp.tileSize;
			frameWidth = gp.tileSize*6;
			frameHeight = gp.tileSize*5;
			slotCol = npcSlotCol;
			slotRow = npcSlotRow;
		}
		
		//Frame:
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		//Slot
		final int slotXStart = frameX + 20;
		final int slotYStart = frameY + 20;
		int slotX = slotXStart;
		int slotY = slotYStart;
		int slotSize = gp.tileSize + 3;
		
		//DrawPlayer's Items:
		for(int i = 0; i < entity.inventory.size(); i++) {
			
			//Equip Cursor: // this is the code that is supposed to draw the Equip background
			if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield || entity.inventory.get(i) == entity.currentLight) {
				g2.setColor(new Color(240,190,90));
				g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
			}
			g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
			
			
			
			//Display Ammount of Stackable Items: 
			if(entity == gp.player && entity.inventory.get(i).amount > 1) {
				g2.setFont(g2.getFont().deriveFont(28F));
				int amountX;
				int amountY;
				
				String a = "" + entity.inventory.get(i).amount;
				amountX = getXForRightText(a, slotX + gp.tileSize - (gp.tileSize/16));
				amountY = slotY + gp.tileSize;
				
				//Shadow
				g2.setColor(new Color(60,60,60));
				g2.drawString(a, amountX, amountY);
				//Number:
				g2.setColor(Color.white);
				g2.drawString(a, amountX-(gp.tileSize/32), amountY-(gp.tileSize/32));
			}
			
			
			slotX += slotSize;
			if(i == 4 || i == 9 || i == 14) {
				slotX = slotXStart;
				slotY += slotSize;
			}
		}
		
		//Cursor
		if(showCursor == true) {
			int cursorX = slotXStart + (slotSize*slotCol), cursorY = slotYStart + (slotSize*slotRow), cursorWidth = gp.tileSize, cursorHeight = gp.tileSize;
			//Draw Cursor
			g2.setColor(Color.white);
			g2.setStroke(new BasicStroke(3));
			g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
			
			//Description Frame:
			int dFrameX = frameX, dFrameY = frameY + frameHeight, dFrameWidth = frameWidth, dFrameHeight = gp.tileSize*3;
			//Description Text:
			int textX = dFrameX + 20, textY = dFrameY + gp.tileSize;
			g2.setFont(g2.getFont().deriveFont(28F));
			
			int itemIndex = getItemIndex(slotCol, slotRow);
			
			if(itemIndex < entity.inventory.size()) {
				
				//Description Frame only appears if itemIndex is pointing to an object in the player's inventory
				drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);
				
				for(String line: entity.inventory.get(itemIndex).description.split("\n")) {
					g2.drawString(line, textX, textY);
					textY += 32;
				}
			}
		}

		//Reset stroke:
		g2.setStroke(new BasicStroke(5));
	}
	
	public int getItemIndex(int slotCol, int slotRow) {
		return slotCol + (slotRow*5);
	}
 	
	public void charaterValueScreenSetup(int textY, int positionAlignment, String stat) {
			int textX = getXForRightText(stat, positionAlignment);
			g2.drawString(stat, textX, textY);
	}
	
	public void charaterValueScreenSetup(int textY, int tailX, BufferedImage img) {
		g2.drawImage(img, tailX, textY, null);
	}

	public void drawMonsterHealthBars(){
		//Monster HP bar:
		for(int i = 0; i < gp.monster[0].length; i++){
			if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].inView() == true){

				Entity monster = gp.monster[gp.currentMap][i];

				if(monster.hpBarOn == true && monster.dying == false && monster.boss == false) {
					double oneScale = (double)gp.tileSize/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					g2.setColor(new Color(35,35,35));
					g2.fillRect(monster.getScreenX()-1, monster.getScreenY()-16, gp.tileSize + 2, 12);
					g2.setColor(new Color(255,0,30));
					g2.fillRect(monster.getScreenX(), monster.getScreenY()-15, (int)hpBarValue, 10);
					monster.hpBarCounter++;
					if(monster.hpBarCounter > 600) {
						monster.hpBarCounter = 0;
						monster.hpBarOn = false;
					}
				}
				//Boss HP Bar:
				else if(monster.hpBarOn == true && monster.dying == false && monster.boss == true){

					double oneScale = (double)gp.tileSize*8/monster.maxLife;
					double hpBarValue = oneScale*monster.life;
					int x = gp.screenWidth/2 - gp.tileSize*4;
					int y = gp.screenHeight - (gp.tileSize*3)/2;


					g2.setColor(new Color(35,35,35));
					g2.fillRect(x-1, y-1, gp.tileSize*8 + 2, 22);
					g2.setColor(new Color(255,0,30));
					g2.fillRect(x, y, (int)hpBarValue, 20);
					g2.setFont(g2.getFont().deriveFont(Font.BOLD,24f));
					g2.setColor(Color.white);
					g2.drawString(monster.name, getXForCenteredText(monster.name), y-10);
				}
			}
		}
	}
	
	public void drawPlayerHUD() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		while(i < gp.player.maxLife/2) {
			g2.drawImage(emptyHeart, x, y, null);
			i++;
			x += gp.tileSize;
		}
		//Reset and Draw Current Life:
		x = gp.tileSize/2;
		i = 0;
		while(i < gp.player.life) {
			g2.drawImage(halfHeart, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(fullHeart, x, y, null);
			}
			i++;
			x += gp.tileSize;
		}
		
		
		switch(gp.player.playerClass) {
		case "Fighter":
			//Draw Arrow UI:
			//current arrows:
			x = (gp.tileSize/2) - 5;
			y = (int)(gp.tileSize*1.5);
			i = 0;
			while(i < gp.player.arrows) {
				g2.drawImage(fullArrow, x, y, null);
				i++;
				x += 35;
			}
			break;
		case "Knight":
			break;
		case "Wizard":
			//Draw MANA UI:
			//max mana:
			x = (gp.tileSize/2) - 5;
			y = (int)(gp.tileSize*1.5);
			i = 0;
			while(i < gp.player.maxMana){
				g2.drawImage(emptyMana, x, y, null);
				i++;
				x += 35;
				
	 		}
			//current mana:
			x = (gp.tileSize/2) - 5;
			y = (int)(gp.tileSize*1.5);
			i = 0;
			while(i < gp.player.mana) {
				g2.drawImage(fullMana, x, y, null);
				i++;
				x += 35;
			}
			break;
		case "Peasant":
			//Draw Peasant UI:
			//current Rocks:
			x = (gp.tileSize/2) - 5;
			y = (int)(gp.tileSize*1.5);
			i = 0;
			while(i < gp.player.ammo) {
				g2.drawImage(fullRock, x, y, null);
				i++;
				x += 35;
			}
			break;
		}	
	}
	
	public void drawTitleScreen() {
		
		if(titleScreenState == 0) {
			g2.setColor(Color.blue);
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			
			//Title
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 64F));
			String text = "2D Adventure Game";
			int x = getXForCenteredText(text);
			int y = gp.tileSize * 3;
			//shadow
			g2.setColor(Color.gray);
			g2.drawString(text, x+5, y+5);
			//main color:
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//MENU Player Image:
			x = gp.screenWidth / 2 - (gp.tileSize * 2)/2;
			y += gp.tileSize * 2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//Menu:
			g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
			text = "New Game";
			x = getXForCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Load Game";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			text = "Exit";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x - gp.tileSize, y);
			}
			
			g2.setFont(g2.getFont().deriveFont(16F));
			text = "(Press W, A, S, D, ESC, and ENTER to Navigate)";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			
		}
		else if(titleScreenState == 1) {
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F));
			String text = "Select your Class:";
			int x = getXForCenteredText(text);
			int y = gp.tileSize*4;
			
			//The image of the player class the cursor is on is displayed:
			g2.drawImage(gp.player.down1, gp.screenWidth / 2 - (gp.tileSize * 2)/2, (gp.tileSize), gp.tileSize*2, gp.tileSize*2, null);
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXForCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Knight";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Wizard";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Peasant";
			x = getXForCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXForCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 4) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		
		
	}

	public void drawDialogueScreen() {
		
		int x = gp.tileSize *3, y = gp.tileSize/2;
		int width = gp.screenWidth -(gp.tileSize * 6);
		int height = gp.tileSize * 4;
		drawSubWindow(x,y,width,height);
		
		x += gp.tileSize;
		y += gp.tileSize;
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 28));
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
	}
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c = new Color(0,0,0,210);
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c = new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}
	
	public void drawPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
		String text = "PAUSED";
		int x = getXForCenteredText(text), y = gp.screenHeight/2;
		g2.drawString(text, x, y);
	}
	
	public int getXForCenteredText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth/2 - length/2;
		
		return x;
	}
	
	public void addMessage(String text) {
		message.add(text);
		messageCounter.add(0);
	}
	
	public void drawMessage() {
		int messageX = gp.tileSize, messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+3, messageY+3);
				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1; //MessageCounter++
				messageCounter.set(i, counter); //set the counter to the array
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
				
			}
		}
	}

	public int getXForRightText(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
