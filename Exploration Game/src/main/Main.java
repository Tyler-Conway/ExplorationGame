package main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main {

	public static JFrame frame;
	public static void main(String[] args) {
		frame = new JFrame();
		GamePanel gamePanel = new GamePanel();
		new Main().setIcon();

		gamePanel.config.loadConfig();
		gamePanel.setupGame();
		gamePanel.startGameThread();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("2D Exploration Game");
		frame.add(gamePanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void setIcon(){
		ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("objects/Amethyst2.png"));
		frame.setIconImage(icon.getImage());
	}
}