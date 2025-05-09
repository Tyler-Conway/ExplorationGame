package main;

import Entity.Entity;
import objects.*;

public class EntityGenerator {
    GamePanel gp;
    
    public EntityGenerator(GamePanel gp){
        this.gp = gp;
    }
	public Entity getObject(String itemName){
		Entity object = null;
		switch(itemName){
			case Axe.objectName: object = new Axe(gp); break;
			case Bark.objectName: object = new Bark(gp); break;
			case BlueKey.objectName: object = new BlueKey(gp); break;
			case Lance.objectName: object = new Lance(gp); break;
			case Lantern.objectName: object = new Lantern(gp); break;
			case MetalShield.objectName: object = new MetalShield(gp); break;
			case PaperClip.objectName: object = new PaperClip(gp); break;
			case RedKey.objectName: object = new RedKey(gp); break;
			case RedPotion.objectName: object = new RedPotion(gp); break;
			case Shield.objectName: object = new Shield(gp); break;
			case Staff.objectName: object = new Staff(gp); break;
			case Tent.objectName: object = new Tent(gp); break;
			case YellowKey.objectName: object = new YellowKey(gp); break;
			case BlueChest.objectName: object = new BlueChest(gp); break;
			case RedChest.objectName: object = new RedChest(gp); break;
			case YellowChest.objectName: object = new YellowChest(gp); break;
			case YellowDoor.objectName: object = new YellowDoor(gp); break;
			case TriColorKey.objectName: object = new TriColorKey(gp); break;
			case RedDoor.objectName: object = new RedDoor(gp); break;
			case BlueDoor.objectName: object = new BlueDoor(gp); break;
			case Arrow.objectName: object = new Arrow(gp); break;
			case CoinBronze.objectName: object = new CoinBronze(gp); break;
			case CoinGold.objectName: object = new CoinGold(gp); break;
			case CoinSilver.objectName: object = new CoinSilver(gp); break;
			case Heart.objectName: object = new Heart(gp); break;
			case ManaCrystal.objectName: object = new ManaCrystal(gp); break;
			case Rock.objectName: object = new Rock(gp); break;
			case Pickaxe.objectName: object = new Pickaxe(gp); break;
			case IronDoor.objectName: object = new IronDoor(gp); break;
			case Amethyst.objectName: object = new Amethyst(gp); break;
			case Seaweed.objectName: object = new Seaweed(gp); break;
			case ClamShell.objectName: object = new ClamShell(gp); break;
			case SpiralShell.objectName: object = new SpiralShell(gp); break;
		}
		return object;
	}
}