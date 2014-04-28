package net.lsrp.ld29.entity;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;

public class Corpse extends Mob {

	public Sprite sprite;
	
	public Corpse(int x, int y, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderSprite(x, y, sprite, false);
	}
	
	@Override
	public void tick(double delta) {
		move(0, gravity);
		//super.tick(delta);
	}
	
}
