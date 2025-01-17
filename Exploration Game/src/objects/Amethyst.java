package objects;

import Entity.Entity;
import main.GamePanel;

public class Amethyst extends Entity{
    
    GamePanel gp;
    public static final String objectName = "Amethyst";

    public Amethyst(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = objectName;
        description = "["+name+"]\nThe Skeleton Giant's\nLegendary Treasure!";
        price = 2000;
        down1 = setup("/objects/Amethyst2", gp.tileSize, gp.tileSize);

        setDialogue();
    }

    public void setDialogue(){
        // dialogues[0][0] = "";
        dialogues[0][0] = "You pick up an Amethyst.";
        dialogues[0][1] = "You found the Skeleton Giant's Legendary Treasure!";
    }

    public boolean use(Entity entity){
        gp.gameState = gp.cutsceneState;
        gp.cutsceneManager.sceneNum = gp.cutsceneManager.finalScene;

        return true;
    }
}
