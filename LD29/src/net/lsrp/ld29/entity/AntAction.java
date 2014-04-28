package net.lsrp.ld29.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import net.lsrp.ld29.level.tile.TileCoordinate;

public class AntAction extends Entity {

	public static enum ATYPE {
		MOVE, DIG
	}
	
	public Ant ant;
	public ArrayList<TileCoordinate> path = new ArrayList<TileCoordinate>();
	public ATYPE type;
	
	public int action_delay = 2;
	public int action_timer = 0;
	
	public AntAction(Ant ant, ATYPE type, TileCoordinate[] path) {
		this.ant = ant;
		this.type = type;
		this.path = new ArrayList<TileCoordinate>(Arrays.asList(path));
	}
	
	@Override
	public void tick(double delta) {
		if(action_timer == action_delay) {
			if(type == ATYPE.MOVE) {
				Iterator<TileCoordinate> i = path.iterator();
				while(i.hasNext()) {
					TileCoordinate tc = i.next();
					
					if(ant.x < tc.x) {
						ant.move(1, 0);
					}
					if(ant.x > tc.x) {
						ant.move(-1, 0);
					}
					if(ant.y < tc.y) {
						ant.move(0, 1);
					}
					if(ant.y > tc.y) {
						ant.move(0, -1);
					}

					if(tc.x == ant.x) {
						ant.action = true;
						if(Math.abs(tc.y) - Math.abs(ant.y) > -3 || Math.abs(tc.y) - Math.abs(ant.y) < 3) {
							ant.action = false;
							i.remove();
						}
					}
					
					tc.timer++;
					if(tc.timer >= 20) {
						try {
							ant.action = false;
							i.remove();
						} catch(Exception e) {}
					}
					break;
				}
			}
			action_timer = 0;
		}
		action_timer++;
	}
}
