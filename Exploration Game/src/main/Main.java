package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("2D Game");
		
		GamePanel gamePanel = new GamePanel();
		frame.add(gamePanel);
		
		gamePanel.config.loadConfig();
		
		gamePanel.setupGame();
		gamePanel.startGameThread();
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}