package net.lsrp.ld29.level.tile;

public class TileCoordinate {

	public int x, y;
	public static final int SIZE = 16;
	public int timer = 0; // Timer for ant movement...
	
	public TileCoordinate(int x, int y) {
		this.x = x * SIZE;
		this.y = y * SIZE;
	}	
	
	public int[] coordinate() {
		int[] c = new int[2];
		
		while(x % 16 != 0)
			x--;
		while(y % 16 != 0)
			y--;
		
		c[0] = x / SIZE;
		c[1] = y / SIZE;
		return c;
	}
	
	public static int align(int i) {		
		while(i % 16 != 0)
			i--;
		
		return i;
	}
}
