package net.lsrp.ld29.entity.ants;

import java.util.Iterator;

import net.lsrp.ld29.Game;
import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;
import net.lsrp.ld29.level.tile.TileCoordinate;

public class QueenAnt extends Ant {

	public int max_eggs = 5;
	public int eggs = 3;
	
	public double last_egg;
	
	public QueenAnt(TileCoordinate tc, Sprite[] sprites, Sprite selector, Sprite corpse) {
		super(tc, ANT.QUEEN, sprites, selector, corpse);
		last_egg = System.currentTimeMillis();
	}
	
	@Override
	public void render(Screen screen) {
		screen.renderAnt(x, y, this);
		screen.renderSelection(this);
	}	
	
	@Override
	public void tick(double delta) {
		super.tick(delta);

		if(System.currentTimeMillis() > last_egg + 60 * 1000) {
			eggs++;
			if(eggs > max_eggs)
				eggs = max_eggs;
			last_egg = System.currentTimeMillis();
		}
	}
	
	@Override
	protected void action() {
		if(eggs > 0) {
			if(Level.gui_option == GUI.MULTIPLY) {				
				Iterator<Ant> i = Level.ants.iterator();
				while(i.hasNext()) {
					Ant a = i.next();
					if(a.type != ANT.QUEEN) {
						if(Math.abs(a.x - x) < 3) {
							if(Math.abs(a.y - y) < 3) {
								if(a.type == ANT.BUILDER) {
									Level.ant = new BuilderAnt(x, y, new Sprite[] {Sprite.a_builder, Sprite.a_buildera}, Sprite.a_builders, Sprite.a_builderc);
									//eggs--;
									return;
								}
								if(a.type == ANT.FIGHTER) {
									Level.ant = new FighterAnt(x, y, new Sprite[] {Sprite.a_fighter, Sprite.a_fightera}, Sprite.a_fighters, Sprite.a_fighterc);
									eggs--;
									break;							
								}
								if(a.type == ANT.WORKER) {
									Level.ant = new WorkerAnt(x, y, new Sprite[] {Sprite.a_worker, Sprite.a_workera}, Sprite.a_workers, Sprite.a_workerc);
									eggs--;
									break;							
								}
							}
						}
					}
				}
			}
		}
	}
	
	@Override
	protected void die() {
		super.die();
		Game.end();
	}
}
