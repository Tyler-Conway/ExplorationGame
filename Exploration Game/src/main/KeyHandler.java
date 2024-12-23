package main;

import Entity.Entity;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import objects.Arrow;
import objects.Fireball;
import objects.Rock;

public class KeyHandler implements KeyListener{
	
	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, keyPressed, showDebugText = false, shotKeyPressed, spacePressed, escapePressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}

	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			escapePressed = true;
		}
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(gp.gameState == gp.titleState) {
			titleState(code);
		}
		else if(gp.gameState == gp.playState) {
			playState(code);
		}
		else if(gp.gameState == gp.pauseState) {
			pauseState(code);
		}
		else if(gp.gameState == gp.dialogueState) {
			dialogueState(code);
		}
		else if(gp.gameState == gp.characterState) {
			characterState(code);
		}
		else if(gp.gameState == gp.optionsState) {
			optionsState(code);
		}
		else if(gp.gameState == gp.gameOverState) {
			gameOverState(code);
		}
		else if(gp.gameState == gp.tradeState) {
			tradeState(code);
		}
		else if(gp.gameState == gp.mapState) {
			mapState(code);
		}
		else if(gp.gameState == gp.travelState) {
			travelState(code);
		}
	}
	
	public void titleState(int code) {
		if(gp.ui.titleScreenState == 0) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				//New Game
				if(gp.ui.commandNum == 0) {
					gp.player.down1 = gp.player.setup("/player/FighterDown1", gp.tileSize, gp.tileSize); // Set the main title screen image to always be the Fighter.
					gp.ui.titleScreenState = 1;
				}
				//Load Game
				else if(gp.ui.commandNum == 1) {
					
					//Set Player's Images and initial eqipment:
					gp.player.setDefaultValues();
					gp.player.getPlayerImage();
					gp.player.getPlayerAttackImage();
					gp.player.setItems();
					//EQUIP Initial Objects:
					gp.player.equipInitialObjects();
					//Load the player's previous stats:
					//gp.assetSetter.setObjects();
					gp.saveLoad.load();
					gp.playMusic(12);
					gp.gameState = gp.playState;
					gp.ui.commandNum = 0;
				}
				//Exit Program
				else if(gp.ui.commandNum == 2) {
					System.exit(0);
				}
			}
			if(code == KeyEvent.VK_ESCAPE) {
				System.exit(0);
			}
		}
		else if(gp.ui.titleScreenState == 1) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.titleScreenState = 0;
				gp.ui.commandNum = 0;
			}
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 4;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 4) {
					gp.ui.commandNum = 0;
				}
			}
			if(gp.ui.commandNum == 0) {
				gp.player.playerClass = "Fighter";
				gp.player.projectile = new Arrow(gp);
			}
			if(gp.ui.commandNum == 1) {
				gp.player.playerClass = "Knight";
				gp.player.projectile = null;
			}
			if(gp.ui.commandNum == 2) {
				gp.player.playerClass = "Wizard";
				gp.player.projectile = new Fireball(gp);
			}
			if(gp.ui.commandNum == 3) {
				gp.player.playerClass = "Peasant";
				gp.player.projectile = new Rock(gp);
			}
			//Get image every time the cursor moves:
			gp.player.getPlayerImage();
			
			if(code == KeyEvent.VK_ENTER) {
				gp.currentMap = gp.world01;
				gp.enviornmentManager.lighting.resetEnviornmentLighting();

				//New Character:
				if(gp.ui.commandNum != 4) {
					gp.gameState = gp.playState;
					gp.playMusic(12);
					gp.player.setDefaultValues();
					gp.player.getPlayerImage();
					gp.player.getPlayerAttackImage();
					gp.player.setItems();
					gp.assetSetter.setNPC();
					gp.player.equipInitialObjects();
					gp.assetSetter.setObjects();
					gp.ui.commandNum = 0;
				}
				//Exit to title:
				else if(gp.ui.commandNum == 4) {
					gp.ui.titleScreenState = 0;
					gp.ui.commandNum = 0;
				}	
			}
		}
	}
	
	public void playState(int code) {
		//MOVEMENTS and SPECIAL KEYS
		if(code == KeyEvent.VK_W) {upPressed = true;}
		if(code == KeyEvent.VK_S) {downPressed = true;}
		if(code == KeyEvent.VK_A) {leftPressed = true;}
		if(code == KeyEvent.VK_D) {rightPressed = true;}
		if(code == KeyEvent.VK_C || code == KeyEvent.VK_E) {gp.gameState = gp.characterState;}
		if(code == KeyEvent.VK_ENTER) {enterPressed = true;}
		if(code == KeyEvent.VK_SPACE) {spacePressed = true;}
		if(code == KeyEvent.VK_F) {shotKeyPressed = true;}
		if(code == KeyEvent.VK_P) {gp.gameState = gp.pauseState;}
		if(code == KeyEvent.VK_M) {gp.gameState = gp.mapState;}
		if(code == KeyEvent.VK_ESCAPE) {gp.gameState = gp.optionsState;}
		if(code == KeyEvent.VK_X) {
			if(gp.map.miniMapOn == false) {gp.map.miniMapOn = true;}
			else {gp.map.miniMapOn = false;}
		}
		
		//DEBUG
		if(code == KeyEvent.VK_T) {
			gp.player.debuggingMode = !gp.player.debuggingMode;
			if(showDebugText == false) {showDebugText = true;}
			else if(showDebugText == true) {showDebugText = false;}
		}
		if(code == KeyEvent.VK_R) {
			gp.assetSetter.setNPC();
			gp.assetSetter.setMonster();
			gp.ReloadCurrentMap();

			//I dont want to deal with monsters all the time:
			if(showDebugText == true){
				gp.obj = new Entity[gp.maxMap][50];
				gp.assetSetter.setObjects();
				gp.monster = new Entity[gp.maxMap][20];
				
			}
		}
	}
	
	public void mapState(int code) {
		if(code == KeyEvent.VK_M) {
			gp.gameState = gp.playState;
		}
	}
	
	public void optionsState(int code) {
		
		if(code == KeyEvent.VK_ESCAPE) {
			gp.gameState = gp.playState;
			gp.ui.commandNum = 0;
			gp.ui.substate = 0;
		}
		
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		
		int maxCommandNum = 0;
		switch(gp.ui.substate) {
		case 0:
			//Number of options (0 = Music, 1 = sound, 2 = Control, 3 = end game, 4 = back)
			maxCommandNum = 4;
			break;
		case 1: maxCommandNum = 7;
			break;
		case 2: maxCommandNum = 1;
			break;
			
		}
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			gp.ui.commandNum--;
			gp.playSoundEffect(8);
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = maxCommandNum;
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			gp.ui.commandNum++;
			gp.playSoundEffect(8);
			if(gp.ui.commandNum > maxCommandNum) {
				gp.ui.commandNum = 0;
			}
		}
		
		if(code == KeyEvent.VK_A) {
			if(gp.ui.substate == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale > 0) {
					gp.music.volumeScale--;
					gp.music.checkVolume();
					gp.playSoundEffect(8);
					
				}
				if(gp.ui.commandNum == 1 && gp.sound.volumeScale > 0) {
					gp.sound.volumeScale--;
					gp.playSoundEffect(8);
					
				}
			}
		}
		if(code == KeyEvent.VK_D) {
			if(gp.ui.substate == 0) {
				if(gp.ui.commandNum == 0 && gp.music.volumeScale < 5) {
					gp.music.volumeScale++;
					gp.music.checkVolume();
					gp.playSoundEffect(8);
				}
				if(gp.ui.commandNum == 1 && gp.sound.volumeScale < 5) {
					gp.sound.volumeScale++;
					gp.playSoundEffect(8);
					
				}
			}
		} 
		
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.substate == 2 && gp.ui.commandNum == 0) {
				gp.stopMusic();
			}
		} 
		
		
	}
	
	public void pauseState(int code) {
		if(gp.gameState == gp.pauseState) {
			gp.gameState = gp.playState;
		}
	}
	
	public void dialogueState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			gp.gameState = gp.playState;
		}
	}
	
	public void characterState(int code) {
		if(code == KeyEvent.VK_C || code == KeyEvent.VK_ESCAPE || code == KeyEvent.VK_E) {
			gp.gameState = gp.playState;
		}
		if(code == KeyEvent.VK_ENTER) {
			gp.player.selectItem();
		}
		playerInventory(code);
		
		
		
	}
	
	public void playerInventory(int code) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.playerSlotRow != 0) {
				gp.ui.playerSlotRow--;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.playerSlotCol != 0) {
				gp.ui.playerSlotCol--;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.playerSlotRow != 3) {
				gp.ui.playerSlotRow++;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.playerSlotCol != 4) {
				gp.ui.playerSlotCol++;
				gp.playSoundEffect(8);
			}
		}
	}
	
	public void npcInventory(int code) {
		if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			if(gp.ui.npcSlotRow != 0) {
				gp.ui.npcSlotRow--;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			if(gp.ui.npcSlotCol != 0) {
				gp.ui.npcSlotCol--;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			if(gp.ui.npcSlotRow != 3) {
				gp.ui.npcSlotRow++;
				gp.playSoundEffect(8);
			}
		}
		if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			if(gp.ui.npcSlotCol != 4) {
				gp.ui.npcSlotCol++;
				gp.playSoundEffect(8);
			}
		}
	}

	public void gameOverState(int code) {
		if(gp.gameState == gp.gameOverState) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					gp.currentMap = gp.world01;
					gp.player.speed = gp.player.defaultSpeed;
					gp.resetGame(false);
					gp.playMusic(12);
				}
				else if(gp.ui.commandNum == 1) {
					gp.ui.titleScreenState = 0;
					gp.gameState = gp.titleState;
					gp.resetGame(true);
				}
			}
		}
	}
	
	public void tradeState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		if(gp.ui.substate == 0) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 2;
				}
				gp.playSoundEffect(8);
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 2) {
					gp.ui.commandNum = 0;
				}
				gp.playSoundEffect(8);
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.commandNum = 0;
				gp.gameState = gp.playState;
			}
		}
		
		if(gp.ui.substate == 1) {
			npcInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.substate = 0;
			}
		}
		if(gp.ui.substate == 2) {
			playerInventory(code);
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.substate = 0;
			}
		}
	}
	
	public void travelState(int code) {
		if(code == KeyEvent.VK_ENTER) {
			enterPressed = true;
		}
		
		if(gp.ui.substate == 0) {
			if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum < 0) {
					gp.ui.commandNum = 1;
				}
				gp.playSoundEffect(8);
			}
			if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) {
					gp.ui.commandNum = 0;
				}
				gp.playSoundEffect(8);
			}
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.commandNum = 0;
				gp.gameState = gp.playState;
			}
		}
		
		if(gp.ui.substate == 1) {
			if(code == KeyEvent.VK_ESCAPE) {
				gp.ui.substate = 0;
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {upPressed = false;}
		if(code == KeyEvent.VK_S) {downPressed = false;}
		if(code == KeyEvent.VK_A) {leftPressed = false;}
		if(code == KeyEvent.VK_D) {rightPressed = false;}
		if(code == KeyEvent.VK_F) {shotKeyPressed = false;}
		if(code == KeyEvent.VK_ENTER) {enterPressed = false;}
		if(code == KeyEvent.VK_SPACE) {spacePressed = false;}
		if(code == KeyEvent.VK_ESCAPE) {escapePressed = false;}
	}
}