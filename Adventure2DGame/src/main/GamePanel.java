package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	final public int maxScreenCol = 16;
	final public int maxScreenRow = 12;
	final public int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768 pixels across
	final public int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 pixels high
	
	// Frames per second
	int fps = 60;
	
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start(); // call run() method
		
	}

	/*
	 * Game Loop
	 */
	@Override
	public void run() {
		
		double drawInterval = 1000000000/fps; // 0.0166 seconds = 60fps
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			
			if (delta >= 1) {
				// Update: update information such as character position
				update();
				// Draw: draw the screen with updated information  
				repaint();
				delta--;
			}
			
		}
		
	}
	
	/*
	 * 
	 */
	public void update() {
		
		player.update();
	}
	
	/*
	 * 
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
	
		Graphics2D g2 = (Graphics2D)g;
		
		tileM.draw(g2);
		
		
		player.draw(g2);
		
		g2.dispose();
	}
}
