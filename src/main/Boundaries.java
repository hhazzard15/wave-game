package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Boundaries extends GameObject{

	public Boundaries(int x, int y, ID id) {
		super(x, y, id);
		
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 800, 30);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
