package com.github.joakimpersson.tda367.audio;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AudioEventBus implements PropertyChangeListener {

	private SoundHandler sh = SoundHandler.getInstance();

	/**
	 * The property name must be either play, stop, setSFXVolume, setBGMVolume.
	 * If play or stop, it will play or stop the audio of the SoundType, witch
	 * is corresponds to newValue.
	 * If setSFX/BGMVolume, it will set the volume, and newValue must be a Float.
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Object newValue = arg0.getNewValue();
		String propertyName = arg0.getPropertyName();
		try {
			if(propertyName.equals("play")) {
				sh.playSound((SoundType)newValue);
			} else if (propertyName.equals("stop")) {
				sh.stopSound((SoundType)newValue);
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
