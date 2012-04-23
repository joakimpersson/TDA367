package com.github.joakimpersson.tda367.audio;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;


/**
 * This class represents a looping background-track.
 * 
 * @author Viktor Anderling
 *
 */
public class BackgroundMusic implements IGameSound {

		private Sound sound;
		private String path;
		
		/**
		 * This is the volume that is added/subtracted from the global
		 * volume when the sound plays. This is so that volume differences
		 * in between different tracks can be evened out.
		 */
		private float initVolume;
		
		public BackgroundMusic(String path, float initVolume) throws SlickException {
			this.path = path;
			this.initVolume = initVolume;
			this.sound = new Sound(path);
		}
		
		@Override
		public void play(float volume) {
			sound.loop(1, volume + initVolume);			
		}

		@Override
		public void stop() {
			sound.stop();			
		}
		
		@Override
		public String toString() {
			String s = "BackgroundMusic: " + path + ", is ";
			if(sound.playing()) {
				s = s + "playing.";
			} else {
				s = s + "not playing.";
			}
			return s;
		}
		
		@Override
		public boolean isPlaying() {
			return sound.playing();
		}
}
