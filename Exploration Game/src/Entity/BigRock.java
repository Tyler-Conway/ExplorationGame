package Entity;

import java.util.ArrayList;
import java.util.Random;

import InteractiveTiles.InteractiveTile;
import InteractiveTiles.MetalPlate;
import main.GamePanel;
import objects.IronDoor;

public class BigRock extends Entity{

	Random random;
    public static final String objectName = "BigRock";
    int count = 0;

	public BigRock(GamePanel gp) {
		super(gp);
		
		direction = "down";
		speed = 4;
		name = objectName;
		random = new Random();
		getImage();
		setDialogue();

        solidArea.x = 2;
        solidArea.y = 6; 
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;
	}
	
	public void getImage() {
		up1 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		up2 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		down1 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		down2 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		left1 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		left2 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		right1 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
		right2 = setup("/NPC/RockObstacle", gp.tileSize, gp.tileSize);
	}

	public void setDialogue() {
		dialogues[0] = "It's a big rock.\nWalk into it to push it around.";
		dialogues[1] = null;
	}

    //disables the update method
    public void update(){

    }
	
	public void speak() {
		super.speak();
	}

    public void move(String d){
        this.direction = d;
        checkCollision();
        if(collisionOn == false){
            switch(direction){
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        detectMetalPlate();
    }

    public void detectMetalPlate(){
        ArrayList<InteractiveTile> plateList = new ArrayList<>();
        ArrayList<Entity> rockList = new ArrayList<>();

        for(int i = 0; i < gp.iTile[1].length; i++){
            if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name.equals(MetalPlate.objectName)){
                plateList.add(gp.iTile[gp.currentMap][i]);
            }
        }
        for(int i = 0; i < gp.npc[1].length; i++){
            if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(BigRock.objectName)){
                rockList.add(gp.npc[gp.currentMap][i]);
            }
        }

        int count = 0;

        for(int i = 0; i < plateList.size(); i++){
            int xDistance = Math.abs(worldX - plateList.get(i).worldX);
            int yDistance = Math.abs(worldY - plateList.get(i).worldY);
            int distance = Math.max(xDistance, yDistance);

            if(distance < gp.tileSize/2){

                if(linkedEntity == null){
                    linkedEntity = plateList.get(i);
                    gp.playSoundEffect(1);
                }
            }
            else{
                if(linkedEntity == plateList.get(i)) {
                    linkedEntity = null;
                }
            }
        }
        for(int i = 0; i < rockList.size(); i++){
            if(rockList.get(i).linkedEntity != null){
                count++;
            }
        }
        if(count == rockList.size()){
            for(int i = 0; i < gp.obj[1].length; i++){
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(IronDoor.objectName)){
                    gp.obj[gp.currentMap][i] = null;
                    gp.playSoundEffect(19);
                }
            }
        }
    }
}