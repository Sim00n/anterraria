package net.lsrp.ld29.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import net.lsrp.ld29.Game;
import net.lsrp.ld29.entity.Ant;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;
import net.lsrp.ld29.level.tile.Tile;

public class Screen {

	public int width, height;
	public int[] pixels;
	
	public static int xOffset, yOffset;
	
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0xFFFFAAFF;
		}
	}
	
	
	public void clear() {
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}


	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = tile.sprite.pixels[x + y * tile.sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderSprite(int xp, int yp, Sprite sprite, boolean skipOffset) {
		if(!skipOffset) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderAnt(int xp, int yp, Ant ant) {
		xp -= xOffset;
		yp -= yOffset;
		
		for(int y = 0; y < ant.sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < ant.sprite.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -ant.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				
				int col = ant.sprite.pixels[x + y * ant.sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void renderSelection(Ant ant) {
		if(ant.selected) {
			int xp = ant.x - xOffset;
			int yp = ant.y - yOffset			;
			
			for(int y = 0; y < ant.selector.SIZE; y++) {
				int ya = y + yp;
				for(int x = 0; x < ant.selector.SIZE; x++) {
					int xa = x + xp;
					
					if(xa < -ant.selector.SIZE || xa >= width || ya < 0 || ya >= height) break;
					if(xa < 0) xa = 0;
					
					int col = ant.selector.pixels[x + y * ant.selector.SIZE];
					if(col != 0xFFFF00FF)
						pixels[xa + ya * width] = col;
				}
			}
		}
	}
	
	public void renderMouse(Sprite sprite, int xp, int yp) {
		xp -= xOffset;
		yp -= yOffset;
			
		for(int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				
				if(xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if(xa < 0) xa = 0;
				
				int col = sprite.pixels[x + y * sprite.SIZE];
				if(col != 0xFFFF00FF)
					pixels[xa + ya * width] = col;
			}
		}
	}
	
	public void drawDebug(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("Camera x: " + Game.camera.x, 5, 15);
		g.drawString("Camera y: " + Game.camera.y, 5, 28);
		g.drawString("xOffset: " + xOffset, 5, 41);
		g.drawString("yOffset: " + yOffset, 5, 53);
		g.drawString("Frames: " + Game._frames, 5, 65);
		g.drawString("Ticks: " + Game._ticks, 5, 77);
	}
	
	public void drawGUI(Graphics g) {	
		g.setColor(new Color(196, 13, 125));
		g.setFont(new Font("Arial", Font.BOLD, 18));
		g.drawString("" + Level.food, 355, 26);
		g.drawString("" + Level.sticks, 415, 26);
		g.drawString("" + Level.ants.size(), 485, 26);
		g.drawString("" + Level.dirt, 558, 26);
		g.drawString("" + Game.time/60, 635, 26);
		g.setFont(new Font("Arial", Font.BOLD, 13));
		g.drawString("min.", 665, 26);
		
	}
	
	public void renderGUI() {
		int top = 40;
		int left = 2;
		renderSprite(left, top + 16*0, Sprite.gui_hand, true);
		renderSprite(left, top + 16*1, Sprite.gui_dig, true);
		renderSprite(left, top + 16*2, Sprite.gui_eat, true);
		renderSprite(left, top + 16*3, Sprite.gui_fight, true);
		renderSprite(left, top + 16*4, Sprite.gui_sex, true);
		
		
		int res_left = Game.WIDTH/2 - (16*11)/2;
		int res_top = 2;
		renderSprite(res_left + 16*0, res_top, Sprite.border_left, true);
		renderSprite(res_left + 16*1, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*2, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*3, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*4, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*5, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*6, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*7, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*8, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*9, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*10, res_top, Sprite.border_middle, true);
		renderSprite(res_left + 16*11, res_top, Sprite.border_right, true);
		
		renderSprite(res_left, res_top-1, Sprite.gui_food, true);
		renderSprite(res_left + 32, res_top-1, Sprite.gui_sticks, true);
		renderSprite(res_left + 64, res_top-1, Sprite.gui_ants, true);
		renderSprite(res_left + 100, res_top-1, Sprite.gui_dirt, true);
		renderSprite(res_left + 138, res_top, Sprite.gui_clock, true);
		
		int c = 0;
		if(Level.gui_option == GUI.HAND) c = 0;
		if(Level.gui_option == GUI.DIG) c = 1;
		if(Level.gui_option == GUI.BUILD) c = 2;
		if(Level.gui_option == GUI.FIGHT) c = 3;
		if(Level.gui_option == GUI.MULTIPLY) c = 4;
		
		renderSprite(left, top + 16 * c, Sprite.gui_active, true);
	}
	
	public void help() {
		renderGUI();
		
		renderSprite(2, 130 + 16 * 0, Sprite.a_queen, true);
		renderSprite(2, 130 + 16 * 1, Sprite.a_builder, true);
		renderSprite(2, 130 + 16 * 2, Sprite.a_worker, true);
		renderSprite(2, 130 + 16 * 3, Sprite.a_fighter, true);
	}
	
	public void helpG(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawString("< This allows you to pick ants.", 45, 103);
		g.drawString("< This allows you to dig holes.", 45, 133);
		g.drawString("< This allows you to create parts of the nest.", 45, 166);
		g.drawString("< This has no use for now.", 45, 198);
		g.drawString("< This allows you to mate the ants.", 45, 230);
		
		g.drawString("< Queen Ant | Action: mating.", 45, 290);
		g.drawString("< Builder Ant | Action: building.", 45, 323);
		g.drawString("< Worker Ant | Action : digging.", 45, 354);
		g.drawString("< Fighter Ant | Action: non for now.", 45, 386);
		
		g.drawString("These are respectively:", 330, 50);
		g.drawString("- Amount of food you have", 340, 50 + 20*1);
		g.drawString("- Amount of sticks you have", 340, 50 + 20*2);
		g.drawString("- Amount of ants left on the map", 340, 50 + 20*3);
		g.drawString("- Amount of dirt you have", 340, 50 + 20*4);
		g.drawString("- Time left", 340, 50 + 20*5);
		
		g.setColor(new Color(100, 133, 133, 160));
		g.fillRect(300, 200, 550, 330);
		g.setColor(new Color(150, 133, 133, 220));
		g.fillRect(600, 100, 250, 50);
		
		g.setColor(Color.WHITE);
		g.drawString("   Hello there stranger. The goal of this game is to build a nest for the group of Ants below.", 310, 220 + 20*0);
		g.drawString("The controls are fairly simple. On the left you can see 5 different actions to pick from. Navigate", 310, 220 + 20*1);
		g.drawString("through those using [1-5]. To perform an action you need the right type of ant. Pick option number 1", 310, 220 + 20*2);
		g.drawString("and [LEFT] click on an ant to select it. An indicator will show that it's chosen.", 310, 220 + 20*3);
		// Space
		g.drawString("[WSAD] to move the ants", 310, 220 + 20*5);
		g.drawString("[SHIFT + WSAD] or [RMB + WSAD] to move the camera.", 310, 220 + 20*6);
		g.drawString("[LMB] to perform an action assigned to the ant.", 310, 220 + 20*7);
		// Space
		g.drawString("> Goal", 310, 220 + 20*9);
		g.drawString("Collect sticks and dirt to build your nest in the provided area on top of this little cave.", 310, 220 + 20*10);
		g.drawString("Remember to breed your ants using the queen. If you don't have enough food, your ants will die.", 310, 220 + 20*11);
		g.drawString("When a big enough nest is built the game will end.                                          [SPACE] - toggle help", 310, 220 + 20*12);
		// Space
		g.drawString("Ludum Dare #29 - Compo - first entry.", 480, 220 + 20*14);
		g.drawString("Author: Szymon \"Sim00n\" Puzdrowski and his Twitch chat.", 420, 220 + 20*15);
		
		g.setFont(new Font("Tahoma", Font.BOLD, 36));
		g.drawString(Game.NAME, 625, 135);
		g.setFont(new Font("Tahoma", Font.BOLD, 15));
		g.drawString("®", 820, 118);
	}
	
	@SuppressWarnings("static-access")
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
	}
}
