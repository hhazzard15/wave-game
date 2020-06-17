package main;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			//i know how a fucking FOR loop works
			GameObject tempObject = object.get(i); //setting tempObject to each
												   //iteration 'i'
			tempObject.tick();
		}										  
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

	public void addObject(Rectangle rectangle) {
		// TODO Auto-generated method stub
		
	}
	
}
