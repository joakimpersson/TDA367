package com.github.joakimpersson.tda367.audio;

// TODO Will be changed into abstract later class.

/**
 * This is an interface for sounds in the game.
 * 
 * @author Viktor Anderling
 *
 */
public interface IGameSound {
	
	/**
	 * This method plays the sound at the given volume.
	 * 
	 * @param volume A float number from 0 to 1.
	 */
	public void play(float volume);
	
	/**
	 * This method stops the sound from playing.
	 */
	public void stop();
	
	/**
	 * This method checks if the sound is playing or not.
	 * 
	 * @return True if the sound is playing, else false.
	 */
	public boolean isPlaying();
	

}
