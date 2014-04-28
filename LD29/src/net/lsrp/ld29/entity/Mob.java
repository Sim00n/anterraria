package net.lsrp.ld29.entity;

import java.util.Iterator;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.level.Level;

public abstract class Mob extends Entity {
	
	public Sprite[] sprites;
	public Sprite sprite;
	public int sprite_index;
	public int gravity = 2;
	public int dir = 0;
	public boolean moving = false;
	protected final double maxHealth = 100.0;
	protected double health = maxHealth;
	public String name = "Mob";
	protected int hunger = 0;
	protected final int max_hunger = 5;
	
	public boolean freeze = false;
	
	public int anim_speed = 10;
	public int anim_counter = 0;
	public int move_cooldown = 140;
	public int move_counter = 0;
	public int click_speed = 10;
	public int click_timer = 0;
	public int digging_timer = 0;
	public int digging_cooldown = 10;
	
	public void _move(int xa, int ya) {
		if(move_counter != 0) return;
		
		move(xa, ya);
		
		if(move_counter == 0) {
			move_counter = move_cooldown;
		}
	}
	
	public void move(int xa, int ya) {
		if(freeze) return;
		
		if(xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		
		if(xa > 0) dir = 1;
		if(xa < 0) dir = 3;
		if(ya > 0) dir = 2;
		if(ya < 0) dir = 0;
		
		if(!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	@Override
	public void tick(double delta) {
		if(dy == 0)
			if(!collision(0, 1))
				move(0, gravity);
		
		if(move_counter > 0)
			move_counter--;
		
		if(click_timer > 0)
			click_timer--;
		
		if(digging_timer > 0)
			digging_timer--;
		
	}
	
	@Override
	public void render(Screen screen) {
	}
	
	private boolean collision(int dx, int dy){
		boolean solid = false;
		
		for(int c = 0; c < 4; c++) {
			int xt = ((x + dx) + c % 2 * 10 + 1) >> 4;
			int yt = ((y + dy) + c / 2 * 8 + 8) >> 4;
			if(Level.level.getTile(xt, yt).solid()) {
				solid = true;
			}
		}
		
		Iterator<Ant> i = Level.ants.iterator();
		while(i.hasNext()) {
			Ant a = i.next();
			
			if(x + dx > a.x && x + dx < a.x + a.sprite.SIZE) {
				if(y + dy > a.y && y + dy < a.y + a.sprite.SIZE) {
					solid = true;
				}
			}
		}
		
		return solid;
	}
	
}
