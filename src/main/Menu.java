package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import main.Game.STATE;

public class Menu extends MouseAdapter{

	private Game game;
	private Handler handler;
	private HUD hud;
	public Menu(Game game, Handler handler, HUD hud) {
		this.game = game;
		this.hud = hud;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		//play button
		if(game.gameState == STATE.Menu) {
			if(mouseOver(mx, my, 210, 150, 200, 64)) {
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler));
				handler.clearEnemies();
				
			}
			//help button
			if(mouseOver(mx, my, 210, 250, 200, 64)) {
				game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
			
			//try again button
			if(mouseOver(mx, my, 210, 350, 200, 64)) {
				System.exit(1);
			}
		}
		
		//back button for help
		if(game.gameState == STATE.Help) {
			if(mouseOver(mx, my, 260, 350, 100, 60)) {
				game.gameState = STATE.Menu;

			}
		}
		
		
		

	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
				
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		if(game.gameState == STATE.Menu) {
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Hunter's Game", 150, 90);
			
			g.setFont(fnt2);
			g.drawRect(210, 150, 200, 64);
			g.drawString("Play", 275, 190);
	
			g.drawRect(210, 250, 200, 64);
			g.drawString("Help", 275, 290);
			
			g.drawRect(210, 350, 200, 64);
			g.drawString("Quit", 275, 390);
		}else if(game.gameState == STATE.Help){
			Font fnt = new Font ("arial", 1, 50);	
			Font fnt2 = new Font ("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help?", 240, 70);
			
			g.setFont(fnt2);
			g.drawRect(260, 350, 100, 60);
			g.drawString("Back", 275, 390);
		}else if(game.gameState == STATE.End){
			Font fnt = new Font ("arial", 1, 20);	
			Font fnt2 = new Font ("arial", 1, 60);
			Font fnt3 = new Font ("arial", 1, 35);
			
			g.setFont(fnt2);
			g.setColor(Color.red);
			g.drawString("GAME OVER", 130, 100);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Your score was: " + hud.getScore(), 210, 150);
			
			g.setFont(fnt3);
			g.drawRect(210, 225, 200, 64);
			g.drawString("Try Again", 230, 269);
		}
		
		
	}
	
}
