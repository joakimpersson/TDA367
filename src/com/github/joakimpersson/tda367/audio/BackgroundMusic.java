package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;


/**
 * This class represents a looping background-track.
 * 
 * @author Viktor Anderling
 *
 */
public class BackgroundMusic extends GameSound {

	public BackgroundMusic(String path, float initVolume) throws SlickException {
		super(path, initVolume);
	}	
	@Override
	public void play(float volume) {
		getSound().loop(1, volume + getInitVolume());			
	}
	
	@Override
	public String toString() {
		String s = "BackgroundMusic: " + getPath() + ", is ";
		if(isPlaying()) {
			s = s + "playing.";
		} else {
			s = s + "not playing.";
		}
		return s;
	}
		
}
