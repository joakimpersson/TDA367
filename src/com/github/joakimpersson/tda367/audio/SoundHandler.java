package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
/**
 * 
 * @author Viktor
 *
 */
public class SoundHandler {
	
	private float bgmVolume;
	private float sfxVolume;
	
	private SoundEffect bombExplode;
	private BackgroundMusic titleTheme;
	
	private static SoundHandler instance = null;
	
	public static SoundHandler getInstance() {
		if(instance == null) {
			instance = new SoundHandler();
		}
		return instance;
	}
	
	private void initiateSounds() {
		try {
			bombExplode = new SoundEffect("res/sounds/Bomb1.ogg");
			titleTheme = new BackgroundMusic("res/sounds/titleTheme1.ogg");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SoundHandler() {
		initiateSounds();
		setBGMVolume(1);
		setBGMVolume(1);
	}
		
	
	public void playSound(SoundType soundType) {
		switch(soundType) {
			case BombExplodeSFX:
				bombExplode.play(sfxVolume);
				break;
			case TitleBGM:
				titleTheme.play(bgmVolume);
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
