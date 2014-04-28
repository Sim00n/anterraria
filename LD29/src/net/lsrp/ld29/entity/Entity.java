package net.lsrp.ld29.entity;

import java.util.Random;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.level.Level;

public abstract class Entity {

	public int x, y;
	protected double dx, dy;
	private boolean removed = false;
	protected Level level;
	
	public static Random rand = new Random();
	
	public void tick(double delta) {
		
	}
	
	public void render(Screen screen) {
		
	}
	
	public void remove() {
		removed = true;
		Level.remove(this);
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
}
