package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 * This class represents a one-time play sound effect track.
 * 
 * @author Viktor Anderling
 *
 */
public class SoundEffect implements IGameSound {

	private Sound sound;
	private String path;
	private float initVolume;
	
	
	public SoundEffect(String path, float initVolume) throws SlickException {
		this.path = path;
		this.initVolume = initVolume;
		this.sound = new Sound(path);
	}
	
	@Override
	public void play(float volume) {
		sound.play(1, volume + initVolume);
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
