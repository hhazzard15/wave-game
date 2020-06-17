package main;

import java.util.Random;

public class Spawner {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	
	private int scoreKeep = 0;
	private int scoreKeepF = 0;
	
	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick() {
		scoreKeep++;
		scoreKeepF++;
		
		if(scoreKeep >= 100) {
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			/*
			if(hud.getLevel() == 10) {
				handler.addObject(new BasicEnemy(r.nextInt((Game.WIDTH-20)), r.nextInt((Game.HEIGHT-20)), ID.BasicEnemy, handler));
			}else if(hud.getLevel() == 2) {
				handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-20), ID.FastEnemy, handler));
			}else if(hud.getLevel() == 3) {
				handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-20), ID.SmartEnemy, handler));
			}*/
		}
		
		//ADDING 3 FAST ENEMIES EVERY 500 TICKS
		/*if(scoreKeepF >= 500) {
			scoreKeepF = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-20), ID.FastEnemy, handler));
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-20), ID.FastEnemy, handler));
			handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH-20), r.nextInt(Game.HEIGHT-20), ID.FastEnemy, handler));
		}*/
	}
	
}
