package enviornment;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {

	GamePanel gp;
	BufferedImage darknessFilter;
	boolean displayedMessage = false;
	public int dayCounter = 0;
	public float filterAlpha = 0f;
	public final int morning = 0;
	public final int day = 1;
	public final int evening = 2;
	public final int night = 3;
	public int dayState = day;
	
	float maxFilterAlpha = 0.75f;
	final int maxDayCounter = 7200; // 7200/60fps = 2 minute sub-day cycles, 8 minute days.
	final float filterAdjustmentValue = (maxFilterAlpha)/(maxDayCounter);
	
	public Lighting(GamePanel gp) {
		this.gp = gp;
		
		setPlayerLightSource();
		
	}
	public void setPlayerLightSource() {
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		if(gp.player.currentLight == null) {
			g2.setColor(new Color(0,0,0,0.75f));
		}
		else {
			//Get the center x and y of the light source (the player is the light source in this case):
			int centerX = gp.player.screenX + (gp.tileSize/2);
			int centerY = gp.player.screenY + (gp.tileSize/2);
			
			
			//Create the gradation effect within the lightCircle:
			//TODO Change some of the color values so that you can see in the dark better:
			Color color[] = new Color[12];
			float fraction[] = new float[12];
			
			color[0] = new Color(0,0,0,0.1f);
			color[1] = new Color(0,0,0,0.45f);
			color[2] = new Color(0,0,0,0.47f);
			color[3] = new Color(0,0,0,0.49f);
			color[4] = new Color(0,0,0,0.51f);
			color[5] = new Color(0,0,0,0.53f);
			color[6] = new Color(0,0,0,0.55f);
			color[7] = new Color(0,0,0,0.57f);
			color[8] = new Color(0,0,0,0.60f);
			color[9] = new Color(0,0,0,0.65f);
			color[10] = new Color(0,0,0,0.70f);
			color[11] = new Color(0,0,0,0.75f);
			
			fraction[0] = 0f;
			fraction[1] = 0.4f;
			fraction[2] = 0.5f;
			fraction[3] = 0.6f;
			fraction[4] = 0.65f;
			fraction[5] = 0.7f;
			fraction[6] = 0.75f;
			fraction[7] = 0.8f;
			fraction[8] = 0.85f;
			fraction[9] = 0.9f;
			fraction[10] = 0.95f;
			fraction[11] = 1f;
			
			
			//Original Gradation:
//			Color color[] = new Color[5];
//			float fraction[] = new float[5];
//			
//			
////			color[0] = new Color(0,0,0,0f);
////			color[1] = new Color(0,0,0,0.25f);
////			color[2] = new Color(0,0,0,0.5f);
////			color[3] = new Color(0,0,0,0.75f);
////			color[4] = new Color(0,0,0,0.98f);
////			
////			fraction[0] = 0f;
////			fraction[1] = 0.25f;
////			fraction[2] = 0.5f;
////			fraction[3] = 0.75f;
////			fraction[4] = 1f;
			
			//Create gradation paint settings for the light circle:
			RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (gp.player.currentLight.lightRadius/2), fraction, color);
			
			//Set gradient data on g2:
			g2.setPaint(gPaint);
		}
		
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		g2.dispose();
	}
	
	public void update() {
		if(gp.player.lightUpdated == true) {
			setPlayerLightSource();
			gp.player.lightUpdated = false;
		}
		
		if(gp.gameState == gp.playState && gp.keyH.showDebugText == false) {
			//Check dayState
			if(dayState == day) {
				dayCounter++;
				
				if(dayCounter > maxDayCounter) {
					dayState = evening;
					dayCounter = 0;
				}
			}
			if(dayState == evening) {
				filterAlpha += filterAdjustmentValue;
				
				if(filterAlpha > maxFilterAlpha) {
					filterAlpha = maxFilterAlpha;
					dayState = night;
				}
			}
			if(dayState == night) {
				dayCounter++;

				if(dayCounter > maxDayCounter) {
					dayState = morning;
					dayCounter = 0;
				}
			}
			if(dayState == morning) {
				filterAlpha -= filterAdjustmentValue;
				
				if(filterAlpha < 0f) {
					filterAlpha = 0f;
					dayCounter = 0;
					dayState = day;
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		if(gp.currentArea == gp.outside){
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
		}
		if(gp.currentArea == gp.outside || gp.currentArea == gp.dungeon){
			g2.drawImage(darknessFilter, 0, 0, null);
		}
		
		
		//Set alpha for the Morning/Day/Evening/Night Text:
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		
		
		if(gp.gameState != gp.sleepState) {
			//Display some debug info:
			String situation = "";
			switch(dayState) {
			case morning: 
				situation = "Morning";
				break;
			case day: situation = "Day";
				break;
			case evening: 
				situation = "Evening";
				break;
			case night:
				situation = "Night";
				g2.setColor(Color.red); 
				g2.setFont(g2.getFont().deriveFont(40f));
				break;
			}
			g2.drawString(situation, gp.tileSize/2, (gp.tileSize*11) +25);
		}
	}

	public void resetEnviornmentLighting() {
		dayState = day;
		dayCounter = 0;
		filterAlpha = 0f;
		
	}
}
