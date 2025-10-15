package main;

import javax.swing.JFrame;

/**
 * 	 * Entry point for the 2D Adventure Game application.
	 * 
	 * This class initializes the main game window and starts the game loop.
	 * It sets up a JFrame, attaches the GamePanel (handles rendering and game logic),
	 * and makes the window visible to the user.
	 * 
	 * Author: Alberto Jimenez Jr
 */
public class Main {

	/**
	 * Main method.
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args) {

		// Creat application window and settings
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("2D Aventure");
		
		// Add GamePanel (game logic and drawing)
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		window.pack();
		
		// Center the window on the screen
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		// Start the game loop thread
		gamePanel.startGameThread();
	}

}
