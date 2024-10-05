package GameData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import main.GamePanel;

public class SaveLoad {

	GamePanel gp;
	
	public SaveLoad(GamePanel gp) {
		this.gp = gp;
	}
	
	public void save() {
		
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
			
			GameDataStorage dataStorage = new GameDataStorage();
			
			dataStorage.life = gp.player.life;
			dataStorage.maxLife = gp.player.maxLife;
			dataStorage.coin = gp.player.coin;
			dataStorage.maxArrows = gp.player.maxArrows;
			dataStorage.arrows = gp.player.arrows;
			dataStorage.maxAmmo = gp.player.maxAmmo;
			dataStorage.ammo = gp.player.ammo;
			dataStorage.maxMana = gp.player.maxMana;
			dataStorage.mana = gp.player.mana;
			dataStorage.level = gp.player.level;
			dataStorage.strength = gp.player.strength;
			dataStorage.dexterity = gp.player.dexterity;
			dataStorage.exp = gp.player.exp;
			dataStorage.nextLevelExp = gp.player.nextLevelExp;
			
		} catch (Exception e) {
			System.out.println("Save Error.");
			e.printStackTrace();
		}
	}
	
	public void load() {
		
	}

}
