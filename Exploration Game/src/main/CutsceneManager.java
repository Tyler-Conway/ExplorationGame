package main;

import java.awt.Graphics2D;
import java.rmi.server.Skeleton;

import Entity.Player;
import Entity.PlayerDummy;
import monster.SkeletonBoss;
import objects.IronDoor;

public class CutsceneManager{
    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int phase;

    public final int noCutScene = 0; 
    public final int skeletonGiant = 1;

    public CutsceneManager(GamePanel gp){
        this.gp = gp;

    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        switch(sceneNum){
            case skeletonGiant: skeletonGiant(); break;
        }
    }

    public void skeletonGiant(){
        if(phase == 0){
            gp.bossBattle = true;
            for(int i = 0; i < gp.obj[0].length; i++){
                if(gp.obj[gp.dungeon02][i] == null){
                    gp.assetSetter.placeAsset(gp.obj, gp.dungeon02, i, new IronDoor(gp), 24, 39);
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
                if(gp.npc[gp.dungeon02][i] == null){System.out.println(i+" null.");}
                else{ 
                    System.out.println(i+gp.npc[gp.dungeon02][i].name);
                    System.out.println(gp.npc[gp.dungeon02][i].worldX);
                    System.out.println(gp.npc[gp.dungeon02][i].worldY);
                }
            }

            for(int i = 0; i < gp.npc[1].length; i++){
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.objectName)){
                    System.out.println("Found it");
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    gp.npc[gp.currentMap][i] = null;
                    break;
                }
            }
            gp.player.drawing = true;
            sceneNum = noCutScene;
            phase = noCutScene;
            gp.gameState = gp.playState;
        }
    }
}