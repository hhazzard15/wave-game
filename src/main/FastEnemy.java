package main;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class FastEnemy extends GameObject{

	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 10;
		velY = 10;
	}
		
	public Rectangle getBounds() {
		return new Rectangle(x, y, 12, 12);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		
		if(y <= 2 || y >= Game.HEIGHT - 45) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.BLUE, 12, 12, 0.075f, handler));
		
		
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, 16, 16);
	}

}
