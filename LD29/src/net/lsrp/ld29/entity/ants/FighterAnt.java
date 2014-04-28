package net.lsrp.ld29.entity.ants;

import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.level.tile.TileCoordinate;

public class FighterAnt extends Ant {

	public FighterAnt(int x, int y, Sprite[] sprites, Sprite selector, Sprite corpse) {
		super(x, y, ANT.FIGHTER, sprites, selector, corpse);
	}
	
	public FighterAnt(TileCoordinate tile, Sprite[] sprites, Sprite sprite, Sprite corpse) {
		super(tile, ANT.FIGHTER, sprites, sprite, corpse);
	}


	@Override
	public void render(Screen screen) {
		screen.renderAnt(x, y, this);
		screen.renderSelection(this);
	}
	
	@Override
	public void tick(double delta) {
		super.tick(delta);
	}
	
	@Override
	protected void action() {
		super.action();
	}
	
}
