package net.lsrp.ld29.items;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.entity.Entity;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;

public class Item extends Entity {

	public String name;
	public Sprite sprite;
	public ITEMS type;
	
	public static enum ITEMS {
		FOOD, STICK
	}
	
	public Item(int x, int y, ITEMS type, String name, Sprite sprite) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.name = name;
		this.sprite = sprite;
	}
	
	@Override
	public void render(Screen screen) {
		if(!isRemoved())
			screen.renderSprite(x, y, sprite, false);
	}
	
	@Override
	public void tick(double delta) {
		if(!isRemoved())
			super.tick(delta);
	}
	
	public void pickup(Ant ant) {
		
	}
}
