package Entity;

import main.GamePanel;

public class PlayerDummy extends Entity{

    GamePanel gp;
    public static String objectName = "PlayerDummy";

    public PlayerDummy(GamePanel gp){
        this.gp = gp;
        super(gp);

		name = objectName;

        getImage();
    }
    public void getImage(){
		switch(gp.player.playerClass) {
		case "Fighter":
			up1 = setup("/player/FighterUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/FighterUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/FighterDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/FighterDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/FighterLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/FighterLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/FighterRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/FighterRight2", gp.tileSize, gp.tileSize);
			break;
		case "Knight":
			up1 = setup("/player/KnightUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/KnightUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/KnightDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/KnightDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/KnightLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/KnightLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/KnightRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/KnightRight2", gp.tileSize, gp.tileSize);
			break;
		case "Wizard":
			up1 = setup("/player/WizardUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/WizardUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/WizardDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/WizardDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/WizardLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/WizardLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/WizardRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/WizardRight2", gp.tileSize, gp.tileSize);
			break;
		case "Peasant":
			up1 = setup("/player/PeasantUp1", gp.tileSize, gp.tileSize);
			up2 = setup("/player/PeasantUp2", gp.tileSize, gp.tileSize);
			down1 = setup("/player/PeasantDown1", gp.tileSize, gp.tileSize);
			down2 = setup("/player/PeasantDown2", gp.tileSize, gp.tileSize);
			left1 = setup("/player/PeasantLeft1", gp.tileSize, gp.tileSize);
			left2 = setup("/player/PeasantLeft2", gp.tileSize, gp.tileSize);
			right1 = setup("/player/PeasantRight1", gp.tileSize, gp.tileSize);
			right2 = setup("/player/PeasantRight2", gp.tileSize, gp.tileSize);
			break;
		}
    }
}
