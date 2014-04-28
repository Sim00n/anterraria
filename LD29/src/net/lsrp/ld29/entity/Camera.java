package net.lsrp.ld29.entity;

import java.awt.event.MouseEvent;

import net.lsrp.ld29.Game;
import net.lsrp.ld29.input.Keyboard;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.level.Level;


public class Camera extends Entity {
	
	public Camera(int x, int y) {
		this.x = x;
		this.y = y;
		dx = 5;
		dy = 5;
	}

	
	@Override
	public void tick(double delta) {
		if(Keyboard.up && (Keyboard.shift || Mouse.getButton() == MouseEvent.BUTTON3)) y -= dy;
		if(Keyboard.down && (Keyboard.shift || Mouse.getButton() == MouseEvent.BUTTON3)) y += dy;
		if(Keyboard.left && (Keyboard.shift || Mouse.getButton() == MouseEvent.BUTTON3)) x -= dx;
		if(Keyboard.right && (Keyboard.shift || Mouse.getButton() == MouseEvent.BUTTON3)) x += dx;
		
		if(x - Game.WIDTH/2 < 0) 
			x = 251;
		if(x > Level.level.width * 16 - Game.WIDTH/2)
			x = 2949;
		if(y - Game.WIDTH/2 < 0)
			y = 251;
		
	}
	
}
