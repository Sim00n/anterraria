package net.lsrp.ld29.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import net.lsrp.ld29.Game;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.Level.GUI;

public class Keyboard implements KeyListener {

	public static boolean[] keys = new boolean[65563];
	public static boolean up, down, left, right, shift, space, F4;
	
	public void tick() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	
		shift = keys[KeyEvent.VK_SHIFT];		
		space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_F1];
		F4 = keys[KeyEvent.VK_F4];
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_F1) {
			Game.toggleHelp();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_F4) {
			Game.toggleDebug();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == '1') Level.gui_option = GUI.HAND;
		if(e.getKeyChar() == '2') Level.gui_option = GUI.DIG;
		if(e.getKeyChar() == '3') Level.gui_option = GUI.BUILD;
		if(e.getKeyChar() == '4') Level.gui_option = GUI.FIGHT;
		if(e.getKeyChar() == '5') Level.gui_option = GUI.MULTIPLY;
	}

	
	
}
