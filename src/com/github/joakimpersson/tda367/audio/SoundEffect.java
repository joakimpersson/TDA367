package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 * 
 * @author Viktor Anderling
 *
 */
public class SoundEffect implements IGameSound {

	private Sound sound;
	private String path;
	
	
	public SoundEffect(String path) throws SlickException {
		this.path = path;
		this.sound = new Sound(path);
	}
	
	@Override
	public void play(float volume) {
		sound.play(1, volume);
	}
	
	@Override
	public void stop() {
		sound.stop();
	}
	
	@Override
	public String toString() {
		String s = "SoundEffect: " + path + ", is ";
		if(sound.playing()) {
			s = s + "playing.";
		} else {
			s = s + "not playing.";
		}
		return s;
	}

	@Override
	public boolean isPlaying() {
		return sound.playing();
	}

}
