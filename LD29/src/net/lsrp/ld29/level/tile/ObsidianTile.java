package net.lsrp.ld29.level.tile;

import net.lsrp.ld29.graphics.Sprite;

public class ObsidianTile extends Tile {

	public ObsidianTile(Sprite sprite) {
		super(sprite);
	}
	
	@Override
	public boolean diggable() {
		return false;
	}
	
	@Override
	public boolean solid() {
		return true;
	}
}
