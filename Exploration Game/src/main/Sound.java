package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[30];
	FloatControl fc;
	int volumeScale = 3;
	float volume;
	public int currentMusic;
	public final int worldMusic = 12;
	public final int beachMusic = 17;
	public final int cabinMusic = 18;
	public final int dungeonMusic = 23;
	public final int bossMusic = 21;
	public final int victoryMusic = 22;
	public final int noMusic = 29;

	public final int buySound = 24;
	public final int SellSound = 25;

	public Sound() {
		soundURL[0] = getClass().getResource("/Sound/PickUp1.wav");
		soundURL[1] = getClass().getResource("/Sound/Door.wav");
		soundURL[2] = getClass().getResource("/Sound/ColorfulDoorOpen.wav");
		soundURL[3] = getClass().getResource("/Sound/ChestOpen.wav");
		soundURL[4] = getClass().getResource("/Sound/weaponswing.wav");
		soundURL[5] = getClass().getResource("/Sound/hitmonster.wav");
		soundURL[6] = getClass().getResource("/Sound/receivedamage.wav");
		soundURL[7] = getClass().getResource("/Sound/ChestOpen.wav");
		soundURL[8] = getClass().getResource("/Sound/Cursor.wav");
		soundURL[9] = getClass().getResource("/Sound/Healing.wav");
		soundURL[10] = getClass().getResource("/Sound/Fireball.wav");
		soundURL[11] = getClass().getResource("/Sound/CutTree.wav");
		soundURL[12] = getClass().getResource("/Sound/PianoExplorationGameMusic.wav");
		soundURL[13] = getClass().getResource("/Sound/Death.wav");
		soundURL[14] = getClass().getResource("/Sound/Entered.wav");
		soundURL[15] = getClass().getResource("/Sound/Block.wav");
		soundURL[16] = getClass().getResource("/Sound/Parry.wav");
		soundURL[17] = getClass().getResource("/Sound/BeachSounds(Louder).wav");
		soundURL[18] = getClass().getResource("/Sound/TraderSong(Simple).wav");
		soundURL[19] = getClass().getResource("/Sound/DoorOpen.wav");
		soundURL[20] = getClass().getResource("/Sound/Talking.wav");
		soundURL[21] = getClass().getResource("/Sound/BossFightSong.wav");
		soundURL[22] = getClass().getResource("/Sound/VictorySong.wav");
		soundURL[23] = getClass().getResource("/Sound/DungeonMusic.wav");
		soundURL[24] = getClass().getResource("/Sound/BuySound.wav");
		soundURL[25] = getClass().getResource("/Sound/SellSound.wav");



		//No music:
		soundURL[29] = null;
	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			if(this.fc == null) {
				System.out.println(i);
			}
			checkVolume();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void play() {
		clip.start();
	}
	
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		clip.stop();
	}
	
 	public void checkVolume() {
		switch(volumeScale) {
		case 0:volume = -80f; break;
		case 1:volume = -20f; break;
		case 2: volume = -12f; break;
		case 3: volume = -5f; break;
		case 4: volume = 1f; break;
		case 5: volume = 6f; break;}
		if(this.fc != null){
			fc.setValue(volume);
		}
	}
}