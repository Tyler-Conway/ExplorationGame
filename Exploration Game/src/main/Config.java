package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	
	GamePanel gp;
	
	public Config(GamePanel gp) {
		this.gp = gp;
		
	
	}
	
	public void saveConfig() {
		
		try {
			BufferedWriter  bw = new BufferedWriter(new FileWriter("Config.txt"));
			
			//Save Music Volume:
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			//Save Sound Volume:
			bw.write(String.valueOf(gp.sound.volumeScale));
			bw.newLine();
			
			bw.close();

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadConfig() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("Config.txt"));
			String s = br.readLine();
			
			//Music Volume:
			gp.music.volumeScale = Integer.parseInt(s);
			
			//Sound Volume
			s = br.readLine();
			gp.sound.volumeScale = Integer.parseInt(s);
			
			
			br.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
