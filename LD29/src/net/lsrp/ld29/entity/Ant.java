package net.lsrp.ld29.entity;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.input.Keyboard;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.items.Item;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;
import net.lsrp.ld29.level.tile.Tile;
import net.lsrp.ld29.level.tile.TileCoordinate;

public abstract class Ant extends Mob {

	public boolean selected = false;
	
	public static enum ANT {
		QUEEN, WORKER, FIGHTER, BUILDER
	}
	
	protected int secureX, secureY;
	protected boolean action = false;
	
	public ANT type;
	public ArrayList<AntAction> actions = new ArrayList<AntAction>();
	public Sprite selector;
	public Sprite corpse;
	public boolean jump = false;
	public int jump_timer = 10;
	
	public Ant(int x, int y, ANT type, Sprite[] sprites, Sprite selector, Sprite corpse) {
		this.x = this.secureX = x + rand.nextInt(5);
		this.y = this.secureY = y;
		this.type = type;
		this.sprites = sprites;
		this.sprite = sprites[0];
		this.selector = selector;
		this.corpse = corpse;
		this.sprite_index = 0;
		this.anim_speed = 10;
	}
	
	public Ant(TileCoordinate tc, ANT type, Sprite[] sprites, Sprite selector, Sprite corpse) {
		this.x = this.secureX = tc.x;
		this.y = this.secureY = tc.y;
		this.type = type;
		this.sprites = sprites;
		this.sprite = sprites[0];
		this.selector = selector;
		this.corpse = corpse;
		this.sprite_index = 0;
		this.anim_speed = 10;
	}
	
	
	@Override
	public void tick(double delta) {
		
		if(selected) {
			if(Keyboard.up && !Keyboard.shift) {
				if(!jump) {
					if(Level.level.getTile(x >> 4, (y>>4)+2).solid()) {
						jump = true;
					}
				}
			}
			if(Keyboard.left && !Keyboard.shift) dx = -1;
			if(Keyboard.right && !Keyboard.shift) dx = 1;
		}
		
		if(jump) {
			jump_timer--;
			if(jump_timer == 0) {
				jump = false;
				jump_timer = 10;
			} else {
				dy = -2;
			}
		}
		
		if(dx != 0 || dy != 0) {
			move((int)dx, (int)dy);
			moving = true;
		} else {
			moving = false;
		}
		
		super.tick(delta);
		
		dy = 0;
		dx = 0;
		
		if(moving) {
			if(anim_counter % anim_speed == 0) {
				sprite_index++;
				if(sprite_index >= sprites.length) {
					sprite_index = 0;
				}
				
				sprite = sprites[sprite_index];
				anim_counter = 0;
			}
			anim_counter++;
		}
		
		
		Iterator<AntAction> i = actions.iterator();
		while(i.hasNext()) {
			AntAction aa = i.next();
			aa.tick(delta);
		}
		
		if(Mouse.getButton() == MouseEvent.BUTTON1 && !moving && Level.gui_option == GUI.HAND) {
			if(click_timer == 0) {
				int xp = (Screen.xOffset + Mouse.x()/2);
				int yp = (Screen.yOffset + Mouse.y()/2);
							
				if(xp > x && xp < x + sprite.SIZE) {
					if(yp > y && yp < y + sprite.SIZE) {
						selected = !selected;
					}
				}
				click_timer = click_speed;
			}
		}
		
		/*
		if(Mouse.getButton() == MouseEvent.BUTTON3 && selected) {
			int xp = ((Mouse.x() + Screen.xOffset + 12) >> 5) + (Screen.xOffset >> 5);
			int yp = ((Mouse.y() + Screen.yOffset + 5) >> 5) + (Screen.yOffset >> 5);
			AntAction aa = new AntAction(this, ATYPE.MOVE, new TileCoordinate[] { new TileCoordinate(xp, yp) });
			actions.add(aa);
		}*/
		
		Iterator<Ant> it = Level.ants.iterator();
		while(it.hasNext()) {
			Ant a = it.next();
			if(a != this) {
				if(a.x == x && a.y == y) {
					a.move(7, 0);
				}
			}
		}
		
		if(Mouse.getButton() == MouseEvent.BUTTON1 && click_timer == 0) {
			action();
			click_timer = click_speed;
		}
		
		Iterator<Item> iti = Level.items.iterator();
		while(iti.hasNext()) {
			Item item = iti.next();
			if(!item.isRemoved()) {
				if(item.x >> 4 == x >> 4) {
					if(Math.abs(item.y - y) < 4) {
						item.pickup(this);
					}
				}
			}
		}
		
		if(rand.nextInt(3000) == 232) {
			eat();
		}
		
		if(Level.level.getTile(x >> 4, y >> 4) == Tile.uranium) {
			die();
		}
	}
	
	private void eat() {
		if(Level.food > 0) {
			Level.food--;
			if(hunger > 0)
				hunger--;
		} else
			hunger++;
		
		if(hunger == 5) {
			if(type == ANT.QUEEN) {
				int counter = 0;
				Iterator<Ant> ai = Level.ants.iterator();
				while(ai.hasNext()) {
					if(!ai.next().isRemoved()) {
						counter++;
					}
				}
				if(counter <= 2) {
					die();
				}
			} else
				die();
		}
	}
	
	protected void die() {
		Level.add(new Corpse(x, y, corpse));
		remove();
	}
	
	public void render(Screen screen) {
		screen.renderAnt(x, y, this);
		screen.renderSelection(this);
	}
	
	protected void action() {
		
	}	
}
