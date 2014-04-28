package net.lsrp.ld29;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class WebGame extends JApplet {
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 500;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 2;
	

	public WebGame() {
		init();
	}
	
	@Override
	public void init() {
		java.awt.EventQueue.invokeLater(new Runnable() {  
            public void run() {  
                
                JFrame a = new JFrame();  
                a.getContentPane().add(new Game().frame.getContentPane());  
                  
            }  
        });  
	}
	
	public void initComponents() {
		setSize(WIDTH, HEIGHT);
		
	}
	
	public static void main(String[] args) {
		new WebGame();
	}
	
}
