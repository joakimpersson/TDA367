package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;


/**
 * This class represents a one-time play sound effect track.
 * 
 * @author Viktor Anderling
 *
 */
public class SoundEffect extends GameSound {

	public SoundEffect(String path, float initVolume) throws SlickException {
		super(path, initVolume);
	}

	@Override
	public void play(float volume) {
		getSound().play(1, volume * getInitVolume());
	}
	
	@Override
	public String toString() {
		String s = "SoundEffect: " + getPath() + ", is ";
		if(isPlaying()) {
			s = s + "playing.";
		} else {
			s = s + "not playing.";
		}
		return s;
	}

}
