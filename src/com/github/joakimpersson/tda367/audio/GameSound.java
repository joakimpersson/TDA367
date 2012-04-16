package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class GameSound {
	
	private float bgmVolume;
	private float sfxVolume;
	
	private SoundEffect bombExplode;
	
	private static GameSound instance = null;
	
	public GameSound getInstance() {
		if(instance == null) {
			instance = new GameSound();
		}
		return instance;
	}
	
	private GameSound() {
		
		try {
			bombExplode = new SoundEffect("res/sounds/Bomb1.ogg");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void playSound(SoundType soundType) {
		switch(soundType) {
			case BombExplodeSFX:
				bombExplode.play(sfxVolume);
				break;
			default:
				break;
		}
	}
	
	public void setBGMVolume(float volume) {
		this.bgmVolume = volume;
	}
	
	public void setSFXVolume(float volume) {
		this.sfxVolume = volume;
	}
	
}
