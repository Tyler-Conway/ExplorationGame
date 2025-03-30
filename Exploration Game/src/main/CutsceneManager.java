package main;

import Entity.PlayerDummy;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import monster.SkeletonBoss;
import objects.Amethyst;
import objects.IronDoor;

public class CutsceneManager{
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int phase;
    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredits;

    public final int noCutScene = 0; 
    public final int skeletonGiant = 1;
    public final int finalScene = 2;

    boolean turnMinimapBackOn = false;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;

        endCredits = "Code/Music/Artwork\n"
                    +"      Tyler Conway"
                    +"\n\n\n\n\n\n\n\n\n"
                    +"Thank you for playing";
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        switch(sceneNum){
            case skeletonGiant: skeletonGiant(); break;
            case finalScene: finalScene(); break;
        }
    }

    public void skeletonGiant(){
        if(phase == 0){
            gp.bossBattle = true;

            if (gp.map.miniMapOn == true) {
                gp.map.miniMapOn = false;
                turnMinimapBackOn = true;
            }

            for(int i = 0; i < gp.obj[0].length; i++){
                if(gp.obj[gp.dungeon02][i] == null){
                    gp.assetSetter.placeAsset(gp.obj, gp.dungeon02, i, new IronDoor(gp), 24, 39);
                    gp.obj[gp.dungeon02][i].bossRoomDoor = true;
                    gp.obj[gp.dungeon02][i].temp = true;
                    gp.playSoundEffect(19);
                    break;
                }
            }
            for(int i = 0; i < gp.npc[0].length; i++){
                if(gp.npc[gp.currentMap][i] == null){
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }
            for(int i = 0; i < gp.monster[1].length; i++){
                if(gp.monster[gp.dungeon02][i] == null){
                    gp.assetSetter.placeAsset(gp.monster, gp.dungeon02, i, new SkeletonBoss(gp), 22, 19);
                    break;
                }
            }
            gp.player.drawing = false;
            phase++;
        }
        if(phase == 1){
            gp.player.worldY -= 2;
            if(gp.player.worldY <= gp.tileSize*20){
                phase++;
            }
        }
        if(phase == 2){
            for(int i = 0; i < gp.monster[0].length; i++){
                if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(SkeletonBoss.objectName)){
                    gp.monster[gp.currentMap][i].sleeping = false;
                    gp.monster[gp.currentMap][i].setDialogue(); //Boss dialouge depends on player level.
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    phase++;
                    break;
                }
            }
        }
        if(phase == 3){
            gp.ui.npc.startDialogue(gp.ui.npc, 0);
        }
        if(phase == 4){
            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.objectName)){
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.bossCutSceneOver = true;
            gp.player.drawing = true;
            sceneNum = noCutScene;
            phase = noCutScene;
            gp.playMusic(gp.sound.bossMusic);
            if (turnMinimapBackOn == true) {
                gp.map.miniMapOn = true;
                turnMinimapBackOn = false;
            }
            gp.gameState = gp.playState;
        }
    }

    public void finalScene(){
        if(phase == 0){
            gp.finalScene = true;
            gp.map.miniMapOn = false;
            gp.player.inventory.add(new Amethyst(gp));
            gp.saveLoad.save();
            gp.stopMusic();
            gp.ui.npc = new Amethyst(gp);
            phase++;
        }
        if(phase == 1){
            gp.ui.npc.startDialogue(gp.ui.npc, 0);
        }
        if(phase == 2){
            gp.playSoundEffect(gp.sound.chestOpen);
            phase++;
        }
        if(phase == 3){
            //wait 3 seconds for the prior sound effect:
            if(counterReached(gp.FPS*3) == true){
                phase++;
            }
        }
        if(phase == 4){
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            drawBlackScreen(alpha);
            if(alpha == 1f){
                alpha = 0;
                phase++;
            }
        }
        if(phase == 5){
            drawBlackScreen(1f);
            alpha += 0.005f;
            if(alpha > 1f){
                alpha = 1f;
            }
            String text = "After defeating the Skeleton Giant, the Adventurer\n"
                        + "found the legendary treasure: a beautiful Amethyst.\n"
                        + "    Well done Adventurer, and congratulations!";
            drawString(alpha, 38f, gp.tileSize*4, gp.tileSize*3, text, (gp.tileSize + gp.tileSize/2));
            
            if(counterReached(gp.FPS*12)){
                gp.playMusic(gp.sound.worldMusic);
                phase++;
            }
        }
        if(phase == 6){
            drawBlackScreen(1f);
            drawString(1F, 100f, gp.screenHeight/2, gp.tileSize*2 + (gp.tileSize/4), "2D Exploration Game", (gp.tileSize + gp.tileSize/2));
            if(counterReached(gp.FPS*8)){
                phase++;
            }
        }
        if(phase == 7){
            drawBlackScreen(1F);
            y = gp.screenHeight/2;
            drawCenteredString(1F, 38f, y, endCredits, (gp.tileSize + gp.tileSize/2));

            if(counterReached(gp.FPS*5)){
                phase++;
            }
        }
        if(phase == 8){
            drawBlackScreen(1F);
            y--;
            drawCenteredString(1f, 38f, y, endCredits, (gp.tileSize + gp.tileSize/2));
            if(counterReached(gp.FPS*13)){
                phase++;
            }
        }
        if(phase == 9){
            drawBlackScreen(alpha);
            drawCenteredString(1f, 38f, y, endCredits, (gp.tileSize + gp.tileSize/2));
            if(counterReached(gp.FPS*5 - (gp.FPS/2))){
                gp.stopMusic();
                sceneNum = noCutScene;
                phase = 0;
                gp.finalScene = false;
                gp.resetGame(true);
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
            }
        }
    }

    public void drawString(float alpha, float fontSize, int y, int x, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        for(String line: text.split("\n")){
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawCenteredString(float alpha, float fontSize, int y, String text, int lineHeight){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));
        for(String line: text.split("\n")){
            int x = gp.ui.getXForCenteredText(line);
            g2.drawString(line, x-gp.tileSize*3 + (gp.tileSize/2), y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    public void drawBlackScreen(float alpha){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

    public boolean counterReached(int target){
        boolean counterReached = false;
        if(counter > target){
            counterReached = true;
            counter = 0;
        }
        else{
            counter++;
        }

        return counterReached;
    }
}