package com.github.joakimpersson.tda367.audio;

import org.lwjgl.openal.AL;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
/**
 * 
 * @author Viktor Anderling
 *
 * A Singelton class for handling all the sounds in the game, witch includes
 * storing them and the volume options.
 *
 */
public class SoundHandler {
	
	/**
	 * The volume of the background music in the game, witch is a value from 0 to 1;
	 */
	private float bgmVolume;
	
	/**
	 * The volume of the sound effects in the game, witch is a value from 0 to 1;
	 */
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
		IGameSound sound = chooseSound(soundType);
		if(sound instanceof SoundEffect) {
			sound.play(sfxVolume);
		} else {
			sound.play(bgmVolume);
		}	
	}
	
	public void stopSound(SoundType soundType) {
		
	}
	
	/**
	 * This method is used to choose between different IGameSounds.
	 * 
	 * @param soundType
	 * @return the IGameSound that corresponds to the given SoundType.
	 */
	private IGameSound chooseSound(SoundType soundType) {
		switch(soundType) {
		case BombExplodeSFX:
			return bombExplode;
		case TitleBGM:
			return titleTheme;		
		default:
			return null;
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
		return chooseSound(soundType).isPlaying();	
	}
	
}
