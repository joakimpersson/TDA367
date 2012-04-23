package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * This is an interface for sounds in the game.
 * 
 * @author Viktor Anderling
 *
 */
public abstract class IGameSound {
	
	private Sound sound;
	private String path;
	
	/**
	 * This is the volume that is added/subtracted from the global
	 * volume when the sound plays. This is so that volume differences
	 * in between different tracks can be evened out.
	 */
	private float initVolume;
	
	public IGameSound(String path, float initVolume) throws SlickException {
		this.path = path;
		this.initVolume = initVolume;
		this.sound = new Sound(path);
	}
	
	/**
	 * This method plays the sound at the given volume.
	 * 
	 * @param volume A float number from 0 to 1.
	 */
	public abstract void play(float volume);
	
	/**
	 * This method stops the sound from playing.
	 */
	public void stop() {
		sound.stop();
	}
	
	/**
	 * This method returns the Sound object.
	 * 
	 * @return The sound object of this class.
	 */
	protected Sound getSound() {
		return sound;
	}
	
	/**
	 * This method returns a float-value of the initial volume.
	 * 
	 * @return The initial volume of this class.
	 */
	protected float getInitVolume() {
		return initVolume;
	}
	
	/**
	 * This method returns a string of the path to the file
	 * where the sound is located.
	 * 
	 * @return The path of the sound of this class.
	 */
	protected String getPath() {
		return path;
	}
	
	/**
	 * This method checks if the sound is playing or not.
	 * 
	 * @return True if the sound is playing, else false.
	 */
	public boolean isPlaying() {
		return sound.playing();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this.getClass() != o.getClass()) {
			return false;
		} else if(this == (IGameSound) o) {
			return true;
		} else {
			return this.getPath().equals(((IGameSound) o).getPath());
		}
	}

}
