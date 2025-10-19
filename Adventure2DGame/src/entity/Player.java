package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

/*
 * Represents the controllable player character in the 2D Adventure game.
 * Player is always rendered at the center of the screen, while the world
 * scrolls around them based on movemnet.
 */
public class Player extends Entity{
	
	GamePanel gp;
	KeyHandler keyH;
	public final int screenX;
	public final int screenY;
	
	/*
	 * Player constructor.
	 * 
	 * @param gp GamePanel
	 * @param keyH KeyHandler
	 */
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		// Center the player on the screen
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2 - (gp.tileSize/2);
		
		// Define the player's collision area relative to the sprite
		solidArea = new Rectangle();
		solidArea.x = 12;
		solidArea.y = 24;
		solidArea.width = 22;
		solidArea.height = 22;
		
		setDefaultValues();
		getPlayerImage();
	}

	/*
	 * Sets the player's default position, speed, and direction.
	 */
	public void setDefaultValues() {
		
		worldX = gp.tileSize * 24;
		worldY = gp.tileSize * 24;
		speed = 4;
		direction = "down";
	}
	
	/*
	 * Loads the player's directional sprite images from resources.
	 */
	public void getPlayerImage() {
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_1-1.png.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_up_2-1.png.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_1-1.png.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_down_2-1.png.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_1-1.png.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_left_2-1.png.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_1-1.png.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/boy_right_2-1.png.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Updates the player's position and animation frame based on input.
	 * 
	 */
	public void update() {
		
		// Proceed only if a movement key is pressed
		if (keyH.upPressed || keyH.downPressend || 
				keyH.leftPressed || keyH.rightPressed) {
			
			// Update movement direction based on input
			if (keyH.upPressed) {
				direction = "up";
			} else if (keyH.downPressend) {
				direction = "down";				
			}else if (keyH.leftPressed) {
				direction = "left";
			} else if (keyH.rightPressed) {
				direction = "right";
			}
			
			// Check Tile Collision
			collisionOn = false;
			gp.collisionChecker.checkTile(this);
			
			// Move the player only if no collision was detected
			if (!collisionOn) {
				switch (direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			// Handle walking animation toggle
			spriteCounter++;
			if (spriteCounter > 7) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	/*
	 * Draw the player sprite on the screen based on direction and animation frame.
	 * 
	 * @param g2 Graphics2D
	 */
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		// Select the appropriate frame based on direction and animation state
		switch(direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}			
			break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}			
			break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}			
			break;
		}
		System.out.println(worldX / gp.tileSize + " " + worldY / gp.tileSize);
		// Draw the current sprite centered on the screen
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}

}
