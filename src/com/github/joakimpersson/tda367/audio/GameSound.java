package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.Sound;

public class GameSound {
	
	private SoundEffect BombExplodeSFXSound = new SoundEffect("res/sounds/Bomb1.ogg");
	
	private static GameSound instance = null;
	
	public GameSound getInstance() {
		if(instance == null) {
			instance = new GameSound();
		}
		return instance;
	}
	
	private GameSound() {
		
	}
	
	public void playSound(SoundType soundType) {
		switch(soundType) {
			case BombExplodeSFX:
				break;
			default:
				break;
		}
	}
	
}
