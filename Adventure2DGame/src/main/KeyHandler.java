package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Handles keyboard input for the 2D adventure game.
 * 
 * Class listens for key events and sets flags to 
 * indicate which movement keys are currently pressed. 
 * These flags can use the game logic to control player
 * movemnet or other actions.
 */
public class KeyHandler implements KeyListener{
	
	public boolean upPressed, downPressend, leftPressed, rightPressed;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	/*
	 * Indicates when a key is pressed, sets 
	 * corresponding flags true based on the key code.
	 * 
	 * @param e KeyEvent
	 */
	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S) {
			downPressend = true;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = true;
		}
	}

	/*
	 * Indicates when a key is released, sets
	 * corresponding flag false based on the key code.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressend = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}

}
