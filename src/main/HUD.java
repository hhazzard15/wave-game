package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	
	private int greenValue = 255; //RGB values run from 0-255
	
	private int score = 0;
	private int level = 1;
	
	public void tick() {
		
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		
		greenValue = HEALTH * 2;
		
		if(HEALTH > 0 && level != 0) score++;
		
	}
	
	public void render(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, greenValue, 0));
		g.fillRect(15, 15, (HEALTH * 2), 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		
		g.drawString("Score: " + score, 15, 64);
		g.drawString("Level: " + level, 15, 80);
		
		/*int timer = 0;
		if(timer >= 10) {
			Font fnt = new Font("arial", 1, 400);
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("1", 200, 200);
			timer++;
			System.out.println(timer);
		}*/
	}
	
	public void score(int score) {
		this.score = score;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	
}
