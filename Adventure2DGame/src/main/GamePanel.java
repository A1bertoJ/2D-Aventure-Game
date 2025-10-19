package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

/**
 * The main drawing surface and game loop manager for the 2D Adventure game.
 * 
 * This class handles:
 * 		Initializing screen and world dimensions
 * 		Managing the main game loop (update and render cycle)
 * 		Delegating input, collision, and drawing logic to other components
 */
public class GamePanel extends JPanel implements Runnable{
	
	// Screen Settings
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;
	public final int tileSize = originalTileSize * scale; // 48x48 tile
	final public int maxScreenCol = 16;
	final public int maxScreenRow = 12;
	final public int screenWidth = tileSize * maxScreenCol; // 48 * 16 = 768 pixels across
	final public int screenHeight = tileSize * maxScreenRow; // 48 * 12 = 576 pixels high
	
	// World Settings
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// Frames Per Second
	int fps = 60;
	
	// Game Components
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionChecker collisionChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public SuperObject obj[] = new SuperObject[10];
	
	
	/*
	 * GamePanel Constructor
	 * 
	 * Sets up the panel with its display properties and input listeners.
	 */
	public GamePanel() {

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	/*
	 * 
	 */
	public void setupGame() {
		
		aSetter.setObject();
	}
	
	/*
	 * Starts the main game thread, which runs the game loop.
	 */
	public void startGameThread() {
		
		gameThread = new Thread(this);
		gameThread.start(); // call run() method
	}

	/*
	 * Game Loop:
	 * 		Maintains a consistent frame rate
	 * 		Updates the game state 
	 * 		Repaints the screen with the updated graphics
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
			
			// Only update and repaint when one frame interval has passed 
			if (delta >= 1) {
				update();	// Update game logic
				repaint();	// Redraw screen
				delta--;
			}
			
		}
		
	}
	
	/*
	 * Updates the game logic.
	 */
	public void update() {
		
		player.update();
	}
	
	/*
	 * Draw all visual elements for the game.
	 * 
	 * @param g Graphics 
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// Tile
		tileM.draw(g2);
		
		// Object
		for (int i = 0; i < obj.length; i++) {
			if (obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		
		// Player
		player.draw(g2);
		
		g2.dispose();
	}
}
