package main;

import java.util.ArrayList;

public class Projectile {

	private double speedX, speedY;
	private int x, y;
	private boolean visible;
	private Player player;
		
	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		//speedX = 1;
		//speedY = 1;
		visible = true;
	}
	
	public void tick() {
		x += speedX;
		y += speedY;
		if(x > 1000) {
			visible = false;
		}
		if(y > 1000) {
			visible = false;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public double getSpeedX() {
		return speedX;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
}
