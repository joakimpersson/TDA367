package com.github.joakimpersson.tda367.audio;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import com.github.joakimpersson.tda367.model.constants.EventType;

/**
 * This singleton class handles events that controls the audio.
 * 
 * @author Viktor Anderling
 *
 */
public class AudioEventListener implements PropertyChangeListener {
	
	/**
	 * This instance.
	 */
	private static AudioEventListener INSTANCE = null;
	
	/**
	 * The instance for the SoundHandler.
	 */
	private SoundHandler sh = SoundHandler.getInstance();
	
	/**
	 * This method returns the instance of this class.
	 * 
	 * @return This instance.
	 */
	public static AudioEventListener getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AudioEventListener();
		}
		return INSTANCE;
	}
	
	/**
	 * The property name must be either play, stop, setSFXVolume, setBGMVolume.
	 *  If play or stop, it will play or stop the audio of the SoundType, witch
	 * is corresponds to newValue.
	 *  If playList, it will play the given list and thus newValue must be a list
	 * containing EventTypes.
	 *  If setSFX/BGMVolume, it will set the volume, and newValue must be a Float.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Object newValue = arg0.getNewValue();
		String propertyName = arg0.getPropertyName();
		try {
			if(propertyName.equals("play")) {
				sh.playSound((EventType)newValue);
			} else if (propertyName.equals("stop")) {
				sh.stopSound((EventType)newValue);
			} else if(propertyName.equals("playList")) {
				sh.playList((List<EventType>)newValue);
			} else if (propertyName.equals("setSFXVolume")) {
				sh.setSFXVolume((Float)newValue);
			} else if (propertyName.equals("setBGMVolume")) {
				sh.setBGMVolume((Float)newValue);
			}	
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}
}
