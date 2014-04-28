package net.lsrp.ld29.items;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.sound.Sound;

public class ItemFood extends Item {

	public ItemFood(int x, int y, String name, Sprite sprite) {
		super(x, y, ITEMS.FOOD, name, sprite);
	}
	
	@Override
	public void pickup(Ant ant) {
		super.pickup(ant);
		
		if(sprite == Sprite.apple)
			Level.food++;
		else if(sprite == Sprite.berry)
			Level.food += 2;
		else if(sprite == Sprite.seeds)
			Level.food += 5;
		else if(sprite == Sprite.onion) { 
			Level.food += 50;
		}
		
		Sound.apple.start();
		remove();
	}
}
