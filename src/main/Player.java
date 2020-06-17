package main;

import java.awt.Color;

import java.awt.Graphics;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

public class Player extends GameObject{

	Random r = new Random();
	Handler handler;
	private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
	private double speedX;
	
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;

	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		x = Game.clamp((int)x, 0, Game.WIDTH - 40);
		y = Game.clamp((int)y, 2, Game.HEIGHT - 63);
		
		collision();
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, Color.white, 32, 32, 0.045f, handler));
		
		if(HUD.HEALTH == 0) { 
			//System.exit(1); //KILLS THE GAME WINDOW WHEN HEALTH IS ZERO
			handler.removeObject(this);
		}
		//System.out.println("x: " + x + "y: " + y);
	}

	private void collision() {
		for(int i= 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getID() == ID.BasicEnemy || tempObject.getID() == ID.FastEnemy || tempObject.getID() == ID.SmartEnemy) { // TEMP OBJECT IS NOW BASIC ENEMY
				if(getBounds().intersects(tempObject.getBounds())) {
					//collision code
					HUD.HEALTH -= 2;
					
				}
			}
			
		}
	}
	
	
	
	@Override
	public void render(Graphics g) {
	
		
		if(id == ID.Player) g.setColor(Color.white); //set the color of player 1
		else if(id == ID.Player2) g.setColor(Color.blue); //set the color of player 2
		g.fillRect((int)x, (int)y, 32, 32);
		
		
	}

	public static void shoot(int direction) {
		Projectile p = new Projectile(0, 0);
		
		if(direction == 1) { //SHOOTING UP
			p.setSpeedY(1);
		}else if(direction == 2) { //SHOOTING DOWN 
			p.setSpeedY(-1);
		}else if(direction == 3) { //SHOOTING LEFT
			p.setSpeedX(-1);
		}else if(direction == 4) { //SHOOTING RIGHT
			p.setSpeedX(1);
		}
		
		projectiles.add(p);
		
	}
	
	public ArrayList getProjectiles() {
		return projectiles;
	}
	
	public float getPX() {
		return x;
	}
	
	public float getPY() {
		return y;
	}
	
	public void setPX(float x) {
		this.x = x;
	}
	
	public void setPY(float y) {
		this.y = y;
	}
	
}




