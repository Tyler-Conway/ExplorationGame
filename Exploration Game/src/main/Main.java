package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GamePanel gamePanel = new GamePanel();

		gamePanel.config.loadConfig();
		gamePanel.setupGame();
		gamePanel.startGameThread();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("2D Game");
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}