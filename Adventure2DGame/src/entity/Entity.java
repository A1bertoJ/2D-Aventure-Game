package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/*
 * Represents a general entity within the game world.
 */
public class Entity {

	// World Coordinates, Speed
	public int worldX, worldY;
	public int speed;
	
	// Sprite Images
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	
	// Sprite Animation Control
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	// Collision Handling
	public Rectangle solidArea;
	public boolean collisionOn = false;
}
