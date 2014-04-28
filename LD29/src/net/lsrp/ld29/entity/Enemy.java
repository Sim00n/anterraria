package net.lsrp.ld29.entity;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;

public class Enemy extends Mob {
	
	public Enemy(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	@Override
	public void tick(double delta) {
		
		super.tick(delta);
	}
	
	@Override
	public void render(Screen screen) {
		
		super.render(screen);
	}
	
}
