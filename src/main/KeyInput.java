package main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import main.Game.STATE;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler, Game game) {
		this.handler = handler;
		this.game = game;
		
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;
	}
	
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				//all key events for player1
				
				//keyboard input!!
				if(key == KeyEvent.VK_W) { 
					tempObject.setVelY(-5);  //UP
					keyDown[0] = true;
				}
				if(key == KeyEvent.VK_S) {
					tempObject.setVelY(+5);  //DOWN
					keyDown[1] = true;
				}
				if(key == KeyEvent.VK_A) {
					tempObject.setVelX(-5);  //LEFT
					keyDown[2] = true;
				}
				if(key == KeyEvent.VK_D) {
					tempObject.setVelX(5); //RIGHT
					keyDown[3] = true;
				}
				
				if(key == KeyEvent.VK_E) {
					game.gameState = STATE.Menu;
					handler.clearPlayer();
				}
				
				
			}
			/*if(tempObject.getID() == ID.Player2) {
				//key events for player2
				if(key == KeyEvent.VK_UP) tempObject.setVelY(-5);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(+5);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
			}*/
		}
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_CONTROL:
			Player.shoot(1);
		break;
		case KeyEvent.VK_UP:
			Player.shoot(2);
		break;
		case KeyEvent.VK_DOWN:
			Player.shoot(1);
		break;
		case KeyEvent.VK_LEFT:
			Player.shoot(3);
		break;
		case KeyEvent.VK_RIGHT:
			Player.shoot(4);
		break;
		}
		
		if(key == KeyEvent.VK_ESCAPE) System.exit(1);
	}

	
	
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.Player) {
				//all key events for player1
				
				//keyboard input!!
				if(key == KeyEvent.VK_W) keyDown[0]=false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_S) keyDown[1]=false;//tempObject.setVelY(0);
				if(key == KeyEvent.VK_A) keyDown[2]=false;//tempObject.setVelX(0);
				if(key == KeyEvent.VK_D) keyDown[3]=false;//tempObject.setVelX(0);
				
				//vertical movement
				if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				//horizontal movement
				if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);
			}
			if(tempObject.getID() == ID.Player2) {
				//key events for player2
				if(key == KeyEvent.VK_UP) tempObject.setVelY(0);
				if(key == KeyEvent.VK_DOWN) tempObject.setVelY(0);
				if(key == KeyEvent.VK_LEFT) tempObject.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) tempObject.setVelX(0);
			}
		}
	}

	
	public void keyTyped(KeyEvent e) {

		
	}

	
	
}
