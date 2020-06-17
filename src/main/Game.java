package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Random;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9; //size of the game window
	private Thread thread;
	private boolean running = false;
	
	private Random r;
	private Handler handler;
	private Player player;
	private HUD hud;
	private Spawner spawner;
	private Menu menu;
	
	public enum STATE{
		Menu,
		Help,
		Game,
		End
	};
	
	public STATE gameState = STATE.Menu; //SETS THE STATE OF THE GAME WINDOW (GAME, MENU, ETC...)
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		spawner = new Spawner(handler, hud);
		menu = new Menu(this, handler, hud);
		this.addKeyListener(new KeyInput(handler, this)); //TELLS THE GAME TO PREPARE FOR KEY INPUT
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "go go power rangers", this);//HERE WE SET THE SIZE OF THE GAME WINDOW AND THE TITLE
		
		
		
		menu = new Menu(this, handler, hud);
		
		
		r = new Random();
		
		if(gameState == STATE.Game) {
			handler.addObject(new Player(HEIGHT/2, WIDTH/2 -100, ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt((Game.WIDTH-200)), r.nextInt((Game.HEIGHT-200)), ID.BasicEnemy, handler));
		}else {
			for(int i = 0; i < 20; i++) {
				handler.addObject(new MenuParticle(r.nextInt(WIDTH-200), r.nextInt(HEIGHT-200), ID.MenuParticle, handler));
			}
		}

		

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//GAME LOOP, BASICALLY THE HEARTBEAT OF THE GAME
	public void run() {
		//the guy i stole this code from also stole it from someone else. i think it was notch
		player = new Player(200, 200, ID.Player, handler);
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//prfloat out the current FPS the game is running at
				//System.out.prfloatln("FPS: " + frames);
				frames = 0;
			}
			
			ArrayList projectiles = player.getProjectiles();
			for(int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				if(p.isVisible() == true) {
					p.tick();
				}else {
					projectiles.remove(i);
				}
			}
			
		}
		stop();
	}
	
	private void tick() {
		handler.tick();
		
		if(gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			
			if(HUD.HEALTH <= 0) {
				HUD.HEALTH = 100;
				handler.clearEnemies();
				gameState = STATE.End;
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End) {
			menu.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3); //HOW MANY BUFFERS TO CREATE
				//IT CAN'T BE 2 BUFFERS, AND MORE THAN 3 IS NOT EFFICIENT
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0,  0, WIDTH, HEIGHT);
		
		
		
		//PROJECTILES ***************************************************
		ArrayList projectiles = player.getProjectiles();
		for(int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.setColor(Color.orange);
			g.fillRect(p.getX(),p.getY(), 16, 16);
		}
			
		handler.render(g);
		
		if(gameState == STATE.Game) {
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			g.setColor(Color.white);
			menu.render(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(float var, float min, float max) {
		if(var >= max)
			return (int) (var = max);
		else if(var <= min)
			return (int) (var = min);
		else
			return (int) var;
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
