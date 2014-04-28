package net.lsrp.ld29;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.lsrp.ld29.entity.Camera;
import net.lsrp.ld29.graphics.Screen;
import net.lsrp.ld29.input.Keyboard;
import net.lsrp.ld29.input.Mouse;
import net.lsrp.ld29.level.Level;
import net.lsrp.ld29.level.tile.Tile;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	// Constants
	public static final String NAME = "Anterraria";
	public static final int WIDTH = 500;
	public static final int HEIGHT = WIDTH / 16 * 9;
	public static final int SCALE = 2;
	public static final int TICKS = 60;
	
	// Components
	public JFrame frame;
	private static Thread thread;	
	public static Screen screen;
	public static Keyboard key;
	public static Mouse mouse;
	public static Camera camera;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
	// Runtime
	private static boolean running = false;
	private static boolean winmsg = false;
	public static boolean help = true;
	public static boolean debug = false;
	public static int _frames = 0;
	public static int _ticks = 0;
	public static int time = 20*60;
	
	
	public Game() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		
		frame = new JFrame(NAME);
		
		screen = new Screen(WIDTH, HEIGHT);
		key = new Keyboard();
		mouse = new Mouse();
		camera = new Camera(105 << 4, (85 << 4));
		
		Level.level = new Level("/textures/sheets/tiles/levelmap.png");
		//Level.level = new Level("/textures/sheets/tiles/testmap.png");
		
		addKeyListener(key);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}
	
	public synchronized void start() {
		thread = new Thread(this, "Thread");
		thread.start();
		running = true;
		
		frame.setBackground(new Color(0xFF00FF));
		frame.setForeground(Color.BLACK);
	}
	
	public synchronized static void stop() {
		running = false;
		try {
			thread.join(); 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void end() {
		JOptionPane.showMessageDialog(null, "Your queen has died due to widespread hunger in your nest. Sorry mate.");
		stop();
	}
	
	public static void win() {
		JOptionPane.showMessageDialog(null, "Your nest is huge!! Congrats, you've won the game in " + (20 - Game.time/60) + " minutes. You can still keep playing and remember to post your results to Ludum Dare Website!");
	}
	
	public static void toggleHelp() {
		help = !help;
	}
	
	public static void toggleDebug() {
		debug = !debug;
	}
	
	@Override
	public void run() {
		
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / TICKS;
		double delta = 0;
		int frames = 0;
		int ticks = 0;
		
		requestFocus();
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1) {
				tick(delta);
				ticks++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				_frames = frames;
				_ticks = ticks;
				frames = 0;
				ticks = 0;
				Game.time--;
			}
		}
	}
	
	private void tick(double delta) {
		key.tick();
		Level.level.tick(delta);
		camera.tick(delta);
		
		int c = 0;
		
		for(int y = 0; y < 62; y++) {
			for(int x = 87; x < 124; x++) {
				if(Level.level.getTile(x, y) == Tile.fence) {
					c++;
				}
			}
		}
		
		if(c > 50 && !winmsg) {
			win();
			winmsg = true;
		}
	}
	
	private void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			bs = getBufferStrategy();
		}
		
		screen.clear();
		
		int xScroll = camera.x - screen.width/2;
		int yScroll = camera.y - screen.height/2;
		Level.level.render(xScroll, yScroll, screen);
		
		if(!help) {
			camera.render(screen);
			screen.renderGUI();
		}
		
		if(help) {
			screen.help();
		}
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	
		if(!help) {
			if(debug)
				screen.drawDebug(g);
			screen.drawGUI(g);
		}
		
		if(help) {
			screen.helpG(g);
		}
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
			}
		});
		
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(NAME);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setLocationRelativeTo(null);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setVisible(true);
		game.start();
	}

}
