package main;

import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

public class SmartEnemy extends GameObject{

	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getID() == ID.Player) player = handler.object.get(i); 
		}
		

	}
		
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		float diffX = x - player.getX() - 8;
		float diffY = y - player.getY() - 8;
		float distance = (float) Math.hypot((double)(x-player.getX()),(double)(y-player.getY()));
		
		velX = (int) ((-0.5f / distance) * diffX * 3);
		velY = (int) ((-0.5f / distance) * diffY * 3);
		
		
		if(y <= 2 || y >= Game.HEIGHT - 45) {
			velY *= -1;
		}
		if(x <= 0 || x >= Game.WIDTH - 16) {
			velX *= -1;
		}
		
		handler.addObject(new Trail(x, y, ID.Trail, Color.green, 32, 32, 0.075f, handler));
		
		
		
	}
	
	public void render(Graphics g) {
		
		//Graphics2D g2d = (Graphics2D) g;
		
		/*g.setColor(Color.red);
		g2d.draw(getBounds());*/
		
		g.setColor(Color.green);
		g.fillRect(x, y, 32, 32);
	}

}
