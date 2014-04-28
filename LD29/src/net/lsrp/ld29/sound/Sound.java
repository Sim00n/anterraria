package net.lsrp.ld29.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

	public static final Sound dig = new Sound(File.separator + "res" + File.separator + "sound" + File.separator + "sounds" + File.separator + "dig.wav");
	public static final Sound build = new Sound(File.separator + "res" + File.separator + "sound" + File.separator + "sounds" + File.separator + "build.wav");
	public static final Sound apple = new Sound(File.separator + "res" + File.separator + "sound" + File.separator + "sounds" + File.separator + "apple.wav");
	public static final Sound stick = new Sound(File.separator + "res" + File.separator + "sound" + File.separator + "sounds" + File.separator + "stick.wav");
	
	public static final Sound rain = new Sound(File.separator + "res" + File.separator + "sound" + File.separator + "music" + File.separator + "shortrain.wav");
	
	private final String file_name;
	private File file;
	private Clip clip;
	private FloatControl volume;
	
	public Sound(String file_name) {
		this.file_name = file_name;
		load();
	}
	
	private void load() {
		try {
			file = new File("");
			System.out.println(file.getAbsolutePath());
			file = new File(file.getAbsolutePath() + file_name); 
			AudioInputStream audio = AudioSystem.getAudioInputStream(file);
			clip = AudioSystem.getClip();
			clip.open(audio);
			
			volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setVolume(float vol) {
		volume.setValue(vol);
	}
	
	public void start() {
		volume.setValue(-13.0f);
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}
	
	public void stop() {
		clip.stop();
	}
	
	public void loop(int times) {
		clip.loop(times);
	}
	
	
	
}