package net.lsrp.ld29.entity.ants;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;
import net.lsrp.ld29.level.tile.TileCoordinate;
import net.lsrp.ld29.sound.Sound;

public class WorkerAnt extends Ant {

	public WorkerAnt(TileCoordinate tc, Sprite[] sprites, Sprite selector, Sprite corpse) {
		super(tc, ANT.WORKER, sprites, selector, corpse);
	}
	
	public WorkerAnt(int x, int y, Sprite[] sprites, Sprite sprite, Sprite corpse) {
		super(x, y, ANT.WORKER, sprites, sprite, corpse);
	}
	
	@Override
	protected void action() {
		if(selected)
			digAtMouse();
	}
	
	public void digAtMouse() {
		if(Level.gui_option == GUI.DIG) {
			if(digging_timer == 0) {
				int xp = ((Mouse.x() + Screen.xOffset + 24) >> 5) + (Screen.xOffset >> 5);
				int yp = ((Mouse.y() + Screen.yOffset + 2) >> 5) + (Screen.yOffset >> 5);
				
				int px = x >> 4;
				int py = y >> 4;
				
				if(Math.abs(px - xp) < 4 ) {
					if(Math.abs(py - yp) < 4 ) {
						dig(xp, yp);
					}
				}
			}
		}
	}
	
	public void dig(int x, int y) {
		if(Level.level.getTile(x, y).diggable()) {
			Level.level.destroyTile(x, y);
			digging_timer = digging_cooldown;
			Level.dirt++;
			Sound.dig.start();
		}
	}
}
