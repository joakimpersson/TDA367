package com.github.joakimpersson.tda367.audio;


/**
 * 
 * @author Viktor Anderling
 *
 */
public interface IGameSound {
	
	public void play(float volume);
	
	public void stop();
	
	public boolean isPlaying();
	

}
