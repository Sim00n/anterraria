package net.lsrp.ld29.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import net.lsrp.ld29.Game;

public class Mouse implements MouseListener, MouseMotionListener {
	
	public static int tx, ty;
	
	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseB = -1;

	public static int x() {
		return mouseX;
	}
	
	public static int y() {
		return mouseY;
	}
	
	public static int getButton() {
		return mouseB;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		tx = Game.camera.x + x()/2 - Game.WIDTH/2;
		ty = Game.camera.y + y()/2 - Game.HEIGHT/2;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		
		tx = Game.camera.x + x()/2 - Game.WIDTH/2;
		ty = Game.camera.y + y()/2 - Game.HEIGHT/2;

	}

}
