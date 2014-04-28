package net.lsrp.ld29.graphics;

public class Sprite {

	public static Sprite dirt = new Sprite(16, 0, 0, SpriteSheet.tiles);
	public static Sprite dirt2 = new Sprite(16, 0, 1, SpriteSheet.tiles);
	public static Sprite mossy = new Sprite(16, 0, 2, SpriteSheet.tiles);
	public static Sprite obsidian = new Sprite(16, 0, 3, SpriteSheet.tiles);
	public static Sprite fence = new Sprite(16, 0, 4, SpriteSheet.tiles);
	public static Sprite gravel = new Sprite(16, 0, 5, SpriteSheet.tiles);
	public static Sprite earth = new Sprite(16, 1, 0, SpriteSheet.tiles);
	public static Sprite sky = new Sprite(16, 2, 0, SpriteSheet.tiles);
	public static Sprite uranium = new Sprite(16, 1, 1, SpriteSheet.tiles);
	public static Sprite arrowed = new Sprite(16, 1, 2, SpriteSheet.tiles);
	
	
	public static Sprite a_queen = new Sprite(16, 3, 0, SpriteSheet.tiles);
	public static Sprite a_queena = new Sprite(16, 3, 1, SpriteSheet.tiles);
	public static Sprite a_queens = new Sprite(16, 4, 0, SpriteSheet.tiles);
	public static Sprite a_queenc = new Sprite(16, 4, 1, SpriteSheet.tiles);
	
	public static Sprite a_worker = new Sprite(16, 3, 2, SpriteSheet.tiles);
	public static Sprite a_workera = new Sprite(16, 3, 3, SpriteSheet.tiles);
	public static Sprite a_workers = new Sprite(16, 4, 2, SpriteSheet.tiles);
	public static Sprite a_workerc = new Sprite(16, 4, 3, SpriteSheet.tiles);
	
	public static Sprite a_fighter = new Sprite(16, 3, 4, SpriteSheet.tiles);
	public static Sprite a_fightera = new Sprite(16, 3, 5, SpriteSheet.tiles);
	public static Sprite a_fighters = new Sprite(16, 4, 4, SpriteSheet.tiles);
	public static Sprite a_fighterc = new Sprite(16, 4, 5, SpriteSheet.tiles);
	
	public static Sprite a_builder = new Sprite(16, 3, 6, SpriteSheet.tiles);
	public static Sprite a_buildera = new Sprite(16, 3, 7, SpriteSheet.tiles);
	public static Sprite a_builders = new Sprite(16, 4, 6, SpriteSheet.tiles);
	public static Sprite a_builderc = new Sprite(16, 4, 7, SpriteSheet.tiles);
	
	public static Sprite block = new Sprite(16, 5, 0, SpriteSheet.tiles);
	public static Sprite block2 = new Sprite(16, 5, 10, SpriteSheet.tiles);
	
	public static Sprite gui_dig = new Sprite(16, 5, 1, SpriteSheet.tiles);
	public static Sprite gui_sex = new Sprite(16, 5, 2, SpriteSheet.tiles);
	public static Sprite gui_fight = new Sprite(16, 5, 3, SpriteSheet.tiles);
	public static Sprite gui_eat = new Sprite(16, 5, 4, SpriteSheet.tiles);
	public static Sprite gui_active = new Sprite(16, 5, 5, SpriteSheet.tiles);
	public static Sprite gui_hand = new Sprite(16, 5, 6, SpriteSheet.tiles);
	
	public static Sprite border_left = new Sprite(16, 5, 7, SpriteSheet.tiles);
	public static Sprite border_middle = new Sprite(16, 5, 8, SpriteSheet.tiles);
	public static Sprite border_right = new Sprite(16, 5, 9, SpriteSheet.tiles);
	
	public static Sprite apple = new Sprite(16, 6, 0, SpriteSheet.tiles);
	public static Sprite berry = new Sprite(16, 6, 1, SpriteSheet.tiles);
	public static Sprite seeds = new Sprite(16, 6, 2, SpriteSheet.tiles);
	public static Sprite stick = new Sprite(16, 6, 3, SpriteSheet.tiles);
	public static Sprite onion = new Sprite(16, 6, 4, SpriteSheet.tiles);
	
	public static Sprite gui_food = new Sprite(16, 7, 0, SpriteSheet.tiles);
	public static Sprite gui_sticks = new Sprite(16, 7, 1, SpriteSheet.tiles);
	public static Sprite gui_ants = new Sprite(16, 7, 2, SpriteSheet.tiles);
	public static Sprite gui_dirt = new Sprite(16, 7, 3, SpriteSheet.tiles);
	public static Sprite gui_clock = new Sprite(16, 7, 4, SpriteSheet.tiles);
	
	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public Sprite(int size, int y, int x, SpriteSheet sheet) {
		this.SIZE = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		
		load();
	}
	
	public Sprite(int size, int color) {
		this.SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}
	
	public void setColor(int color) {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = color;
			}
		}
	}
	
	private void load() {
		for(int y = 0; y < SIZE; y++) {
			for(int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x+this.x) + (y+this.y) * sheet.SIZE];
			}
		}
	}
	
}
