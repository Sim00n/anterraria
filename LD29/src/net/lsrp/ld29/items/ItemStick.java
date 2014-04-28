package net.lsrp.ld29.items;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.sound.Sound;

public class ItemStick extends Item {

	public ItemStick(int x, int y, String name, Sprite sprite) {
		super(x, y, ITEMS.STICK, name, sprite);
	}


	@Override
	public void pickup(Ant ant) {
		super.pickup(ant);
		Level.sticks++;
		Sound.stick.start();
		remove();
	}
	
}
