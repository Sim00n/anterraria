package net.lsrp.ld29.entity.ants;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;
import net.lsrp.ld29.level.tile.Tile;
import net.lsrp.ld29.level.tile.TileCoordinate;
import net.lsrp.ld29.sound.Sound;

public class BuilderAnt extends Ant {

	public BuilderAnt(int x, int y, Sprite[] sprites, Sprite selector, Sprite corpse) {
		super(x, y, ANT.BUILDER, sprites, selector, corpse);
	}
	
	public BuilderAnt(TileCoordinate tile, Sprite[] sprites, Sprite selector, Sprite corpse) {
		super(tile, ANT.BUILDER, sprites, selector, corpse);
	}

	
	@Override
	protected void action() {
		if(Level.dirt > 0 && Level.sticks > 0) {
			fenceAtMouse();
		}
	}
	
	public void fenceAtMouse() {
		if(Level.gui_option == GUI.BUILD) {
			if(digging_timer == 0) {
				int xp = ((Mouse.x() + Screen.xOffset + 24) >> 5) + (Screen.xOffset >> 5);
				int yp = ((Mouse.y() + Screen.yOffset + 24) >> 5) + (Screen.yOffset >> 5);
				
				int px = x >> 4;
				int py = y >> 4;
				
				if(Math.abs(px - xp) < 3 || Math.abs(xp - px) < 3) {
					if(Math.abs(py - yp) < 3 || Math.abs(yp - py) < 3 ) {
						fence(xp, yp);
					}
				}
			}
		}
	}
	
	public void fence(int x, int y) {
		if(Level.level.getTile(x, y).diggable() && Level.level.getTile(x, y).sprite != Sprite.fence) {
			Level.level.destroyTile(x, y);
			Level.level.tiles[x + y * Level.level.width] = Tile._fence;
			
			digging_timer = digging_cooldown;
			
			Level.dirt--;
			Level.sticks--;
			
			Sound.build.start();
		}
	}
}
