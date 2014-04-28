package net.lsrp.ld29.level.tile;

import net.lsrp.ld29.graphics.Sprite;

public class PoisonTile extends Tile {
	
	public PoisonTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public boolean diggable() {
		return false;
	}
	
	@Override
	public boolean solid() {
		return false;
	}	
}
