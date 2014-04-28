package net.lsrp.ld29.level;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;

import net.lsrp.ld29.Game;
import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.entity.Entity;
import net.lsrp.ld29.entity.ants.BuilderAnt;
import net.lsrp.ld29.entity.ants.FighterAnt;
import net.lsrp.ld29.entity.ants.QueenAnt;
import net.lsrp.ld29.entity.ants.WorkerAnt;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.graphics.Sprite;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.items.Item;
import net.lsrp.ld29.items.ItemFood;
import net.lsrp.ld29.items.ItemStick;
import net.lsrp.ld29.level.tile.Tile;
import net.lsrp.ld29.level.tile.TileCoordinate;
import net.lsrp.ld29.sound.Sound;

public class Level {

	public int width, height;
	protected String level_path;
	public int[] tiles;
	
	public static Level level;
	public static ArrayList<Entity> entities = new ArrayList<Entity>();
	public static ArrayList<Ant> ants = new ArrayList<Ant>();
	public static ArrayList<Item> items = new ArrayList<Item>();
	
	public Random rand = new Random();
	
	public static Ant ant = null;
	
	public static enum GUI {
		HAND, DIG, BUILD, FIGHT, MULTIPLY
	}
	public static GUI gui_option = GUI.HAND;
	
	public static final int skyline = 61;
	public static int food = 100;
	public static int sticks = 0;
	public static int dirt = 0;
	
	public Level(String level_path) {
		this.level_path = level_path;
		generateLevel(level_path);
		addAnts();
		Sound.rain.setVolume(-3.0f);
		Sound.rain.loop(100);
	}
	
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				if(y <= skyline)
					Tile.sky.render(x, y, screen);
				else
					Tile.earth.render(x, y, screen);
				
				getTile(x, y).render(x, y, screen);
			}
		}
		
		Iterator<Ant> i = ants.iterator();
		while(i.hasNext()) {
			Ant a = i.next();
			a.render(screen);
		}
		
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item item_ = it.next();
			item_.render(screen);
		}
		
		if(gui_option == GUI.DIG)
			screen.renderMouse(Sprite.block, TileCoordinate.align(Mouse.tx), TileCoordinate.align(Mouse.ty));
		if(gui_option == GUI.BUILD)
			screen.renderMouse(Sprite.block2, TileCoordinate.align(Mouse.tx), TileCoordinate.align(Mouse.ty));
		
		Iterator<Entity> ei = entities.iterator();
		while(ei.hasNext()) {
			Entity e = ei.next();
			e.render(screen);
		}
	}
	
	public void tick(double delta) {
		Iterator<Ant> i = ants.iterator();
		while(i.hasNext()) {
			Ant a = i.next();
			a.tick(delta);
			if(a.isRemoved())
				i.remove();
		}
		
		Iterator<Item> it = items.iterator();
		while(it.hasNext()) {
			Item item_ = it.next();
			item_.tick(delta);
		}
		
		if(ant != null) {
			ants.add(ant);
			ant = null;
		}
		
		Iterator<Entity> ei = entities.iterator();
		while(ei.hasNext()) {
			Entity e = ei.next();
			e.tick(delta);
		}
		
		Sound.rain.setVolume(-3.0f -Game.camera.y/100);
	}
	
	public Tile getTile(int x, int y) {	
		if(x < 0 || y < 0 || x >= width || y >= height) return Tile.voidtile;
		if(tiles[x + y * width] == Tile._dirt) return Tile.dirt;
		if(tiles[x + y * width] == Tile._arrowed_dirt) return Tile.arrowed_dirt;
		if(tiles[x + y * width] == Tile._dirt2) return Tile.dirt2;
		if(tiles[x + y * width] == Tile._mossy) return Tile.mossy;
		if(tiles[x + y * width] == Tile._obsidian) return Tile.obsidian;
		if(tiles[x + y * width] == Tile._sky) return Tile.sky;
		if(tiles[x + y * width] == Tile._fence) return Tile.fence;
		if(tiles[x + y * width] == Tile._gravel) return Tile.gravel;
		if(tiles[x + y * width] == Tile._uranium) return Tile.uranium;
		
		return Tile.voidtile;
	}
	
	public void destroyTile(int x, int y) {
		if(x < 0 || y < 0 || x >= width || y >= height) return;
		tiles[x + y * width] = 0;
	}
	
	private void generateLevel(String level_path) {
		try {
			BufferedImage image = ImageIO.read(Level.class.getResource(level_path));
			
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new int[width * height];
			
			image.getRGB(0, 0, width, height, tiles, 0, width);
			
			for(int y = 0; y < height; y++) {
				for(int x = 0; x < width; x++) {
					Item iti = null;
										
					if(tiles[x + y * width] == 0xFFFF0000) iti = new ItemFood(x<<4, y<<4  , "Apple", Sprite.apple);
					if(tiles[x + y * width] == 0xFF57007F) iti = new ItemFood(x<<4, y<<4  , "Berry", Sprite.berry);
					if(tiles[x + y * width] == 0xFFADFF49) iti = new ItemFood(x<<4, y<<4  , "Seeds", Sprite.seeds);
					if(tiles[x + y * width] == 0xFF3D3A08) iti = new ItemStick(x<<4, y<<4  , "Stick", Sprite.stick);
					if(tiles[x + y * width] == 0xFFEBFC00) iti = new ItemStick(x<<4, y<<4  , "Stick", Sprite.onion);
					
					if(iti != null)
						items.add(iti);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void addAnts() {
		Ant q = new QueenAnt(new TileCoordinate(rand.nextInt(5) + 105, rand.nextInt(2) + 75), new Sprite[] {Sprite.a_queen, Sprite.a_queena}, Sprite.a_queens, Sprite.a_queenc );
		ants.add(q);
		
		for(int i = 0; i < 3; i++) {
			Ant w = new WorkerAnt(new TileCoordinate(rand.nextInt(5) + 105, rand.nextInt(2) + 75), new Sprite[] {Sprite.a_worker, Sprite.a_workera}, Sprite.a_workers, Sprite.a_workerc);
			ants.add(w);
			Ant f = new FighterAnt(new TileCoordinate(rand.nextInt(5) + 105, rand.nextInt(2) + 75), new Sprite[] {Sprite.a_fighter, Sprite.a_fightera}, Sprite.a_fighters, Sprite.a_fighterc);
			ants.add(f);
			Ant b = new BuilderAnt(new TileCoordinate(rand.nextInt(5) + 105, rand.nextInt(2) + 75), new Sprite[] {Sprite.a_builder, Sprite.a_buildera}, Sprite.a_builders, Sprite.a_builderc);
			ants.add(b);
		}
	}
	
	public static void add(Entity e) {
		entities.add(e);
	}
	
	public static void remove(Entity e) {
		Iterator<Entity> i = entities.iterator();
		while(i.hasNext()) {
			try {
				if(i.next() == e) {
					i.remove();
				}
			} catch(ConcurrentModificationException ex) {
				// ex.printStackTrace();
			}
		}
	}
	
}
