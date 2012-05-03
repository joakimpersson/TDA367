package com.github.joakimpersson.tda367.audio;

import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 * This is an abstract class for sounds in the game.
 * 
 * @author Viktor Anderling
 * @modified joakimpersson
 * 
 */
public abstract class GameSound {

	private Sound sound;
	private String path;
	private float duration;

	/**
	 * This is the volume that is added/subtracted from the global volume when
	 * the sound plays. This is so that volume differences in between different
	 * tracks can be evened out.
	 */
	private float initVolume;

	public GameSound(String path, float initVolume) throws SlickException {
		this.path = path;
		this.initVolume = initVolume;
		this.sound = new Sound(path);
		this.duration = calculateDuration();
		System.out.println(duration);
	}

	/**
	 * This method plays the sound at the given volume.
	 * 
	 * @param volume
	 *            A float number from 0 to 1.
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
	 * @return
	 * 			The duration of this sound in milliseconds.
	 */
	protected float getDuration() {
		return duration;
	}
	
	/**
	 * Calculates the duration of this GameSound.
	 * 
	 * @return
	 * 			The duration in milliseconds.
	 */
	private float calculateDuration() {
		File file = new File(path);
		AudioInputStream audioInputStream;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(file);
		} catch (Exception e) {
			e.printStackTrace();
			return -1f;
			}
	    AudioFormat format = audioInputStream.getFormat();
	    return (file.length() * 1000 / (format.getFrameSize() * format.getFrameRate()));		
	}
	
	/**
	 * This method returns a string of the path to the file where the sound is
	 * located.
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
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		GameSound other = (GameSound) obj;
		return this.getPath().equals(other.getPath())
				&& this.sound.equals(other.sound);
	}

	@Override
	public int hashCode() {
		int sum = 5;
		sum += sound.hashCode() * 7;

		return sum;
	}
}
