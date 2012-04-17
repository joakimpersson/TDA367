package com.github.joakimpersson.tda367.audio;

import org.lwjgl.openal.AL;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
/**
 * 
 * @author Viktor Anderling
 *
 * A Class for handling all the sounds in the game, witch includes
 * storing them and the volume options. It is important to 
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
	
	public void initiateSounds() {
		try {
			bombExplode = new SoundEffect("res/sounds/Bomb1.ogg");
			titleTheme = new BackgroundMusic("res/sounds/bg1.ogg");
			
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SoundHandler() {
		initiateSounds();
		setSFXVolume(1);
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
	
	/**
	 * This method sets the volume for the background-music.
	 * 
	 * @param volume A float number between 0 and 1.
	 */
	public void setBGMVolume(float volume) {
		this.bgmVolume = volume;
	}
	
	public void setSFXVolume(float volume) {
		this.sfxVolume = volume;
	}
	
	public void destroy() {
		AL.destroy();
	}
	
	public boolean isSoundPlaying(SoundType soundType) {
		switch(soundType) {
		case BombExplodeSFX:
			return bombExplode.isPlaying();
		case TitleBGM:
			return titleTheme.isPlaying();		
		default:
			return false;
		}
	
	}
	
}
