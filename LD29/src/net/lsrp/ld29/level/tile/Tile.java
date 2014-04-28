package net.lsrp.ld29.level.tile;

import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;

public class Tile {
	
	public static Tile dirt = new Tile(Sprite.dirt, true);
	public static Tile dirt2 = new Tile(Sprite.dirt2, true);
	public static Tile arrowed_dirt = new Tile(Sprite.arrowed, true);
	public static Tile mossy = new Tile(Sprite.mossy, true);
	public static Tile obsidian = new ObsidianTile(Sprite.obsidian);
	public static Tile gravel = new Tile(Sprite.gravel, true);
	public static Tile voidtile = new Tile(new Sprite(16, 0xFFFF00FF));
	public static Tile earth = new Tile(Sprite.earth);
	public static Tile sky = new Tile(Sprite.sky);
	public static Tile fence = new Tile(Sprite.fence, true);
	public static Tile uranium = new PoisonTile(Sprite.uranium);
	
	public static int _dirt = 0xFFFFAAAA;
	public static int _arrowed_dirt = 0xFFFF004E;
	public static int _dirt2 = 0xFF684226;
	public static int _mossy = 0xFF686126;
	public static int _obsidian = 0xFF0D022D;
	public static int _gravel = 0xFF68614E;
	public static int _void = 0xFF7311F2;
	public static int _earth = 0xFF3F1C03;
	public static int _sky = 0xFF78D1FF;
	public static int _fence = 0xFF306806;
	public static int _uranium = 0xFFCDFF38;
	
	public int x, y;
	public Sprite sprite;
	private boolean solid = false;
	private boolean diggable = true;
	
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	public Tile(Sprite sprite, boolean solid) {
		this.sprite = sprite;
		this.solid = this.diggable = solid;
	}
	
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return solid;
	}
	
	public boolean diggable() {
		return diggable;
	}
}
