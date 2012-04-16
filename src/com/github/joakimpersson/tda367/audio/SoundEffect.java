package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class SoundEffect {

	private Sound sound;
	
	public SoundEffect(String path) throws SlickException {
			this.sound = new Sound(path);
	}
	
	public void play(float volume) {
		sound.play(0, volume);
	}

}
