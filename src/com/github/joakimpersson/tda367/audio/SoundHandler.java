package com.github.joakimpersson.tda367.audio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.lwjgl.openal.AL;
import org.newdawn.slick.SlickException;

import com.github.joakimpersson.tda367.model.constants.Parameters;

/**
 * 
 * @author Viktor Anderling
 *
 * A Singelton class for handling all the sounds in the game, witch includes
 * storing them and the volume-options.
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
	private SoundEffect bombPlaced;
	private BackgroundMusic titleTheme;
	private BackgroundMusic battleSong1;
	
	
	private BackgroundMusic playingMusic;
	
	/**
	 * The instance of this class.
	 */
	private static SoundHandler instance = null;
	
	/**
	 * This method returns the instance of this class.
	 * 
	 * @return This instance.
	 */
	public static SoundHandler getInstance() {
		if(instance == null) {
			instance = new SoundHandler();
		}
		return instance;
	}
	
	/**
	 * This constructor initiates the sounds and sets 
	 * the SFX and BGM to the initial volume.
	 */
	private SoundHandler() {
		initiateSounds();
		Parameters par = Parameters.INSTANCE;
		setSFXVolume(par.getInitSFXVolume());
		setBGMVolume(par.getInitBGMVolume());
	}
	
	/**
	 * This class initiate and saves all the sound from the resources folder.
	 */
	public void initiateSounds() {
		try {
			bombPlaced = new SoundEffect("res/sounds/PlaceBomb1.ogg", 0);
			bombExplode = new SoundEffect("res/sounds/Bomb1.ogg", -0.5f);
			titleTheme = new BackgroundMusic("res/sounds/bg1.ogg", -0.6f);
			battleSong1 = new BackgroundMusic("res/sounds/BattleSong1.ogg", -0.4f);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method plays the sound that corresponds to the given SoundType.
	 * If a BackgroundMusic is previously playing, it will stop playing
	 * before the next will start.
	 * 
	 * @param soundType The given SoundType.
	 */
	public void playSound(EventType soundType) {
		IGameSound sound = chooseSound(soundType);
		if(sound instanceof SoundEffect) {
			sound.play(sfxVolume);
		} else {
			if(playingMusic != null) {
				playingMusic.stop();
			}
			sound.play(bgmVolume);
			playingMusic = (BackgroundMusic) sound;
		}	
	}
	
	/**
	 * This method plays the sound that corresponds to the given SoundType.
	 * 
	 * @param soundType The given SoundType.
	 */
	public void stopSound(EventType soundType) {
		chooseSound(soundType).stop();
	}
	
	/**
	 * This method stops all currently playing backgroundmusic.
	 */
	public void stopCurrentlyPlayingMusic() {
		if(playingMusic != null) {
			playingMusic.stop();
			playingMusic = null;
		}
	}
	
	/**
	 * This method checks if the sound that corresponds to the given SoundType
	 * is playing.
	 * 
	 * @param soundType The given SoundType.
	 * @return True if the sound is playing, else false.
	 */
	public boolean isSoundPlaying(EventType soundType) {
		return chooseSound(soundType).isPlaying();	
	}
	
	/**
	 * This method is used internally to choose between different IGameSounds.
	 * 
	 * @param soundType
	 * @return The IGameSound that corresponds to the given SoundType.
	 */
	private IGameSound chooseSound(EventType soundType) {
		switch(soundType) {
		case BOMB_PLACED:
			return bombPlaced;
		case BOMB_EXPLODED:
			return bombExplode;
		case TITLE_SCREEN:
			return titleTheme;
		case BATTLE_SCREEN:
			return battleSong1;
		default:
			return null;
		}
	}
	
	// TODO fix input for setVolume.
	
	/**
	 * This method sets the volume for all the background music.
	 * 
	 * @param volume A float number between 0 and 1.
	 */
	public void setBGMVolume(float volume) {
		this.bgmVolume = volume;
	}
	
	/**
	 * This method sets the volume for all the sound-effect music.
	 * 
	 * @param volume A float number between 0 and 1.
	 */
	public void setSFXVolume(float volume) {
		this.sfxVolume = volume;
	}
	
	
	/**
	 * This method destroys the openAL. Use before closing the application.
	 */
	public void destroy() {
		AL.destroy();
	}
	
}
