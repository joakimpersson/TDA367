package com.github.joakimpersson.tda367.audio;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class AudioEventBus implements PropertyChangeListener {
	
	public enum AudioCommand {
		Play, Stop, SetSFXVolume, SetBGMVolume;
	}
	
	private SoundHandler sh = SoundHandler.getInstance();
	
	
	/**
	 * new value?
	 * old value?
	 * 
	 */
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		Object o = arg0.getNewValue();
		if(o instanceof AudioCommand) {
			AudioCommand ac = (AudioCommand) o;
			switch(ac) {
				case Play:
					break;
				case Stop:
					break;
				case SetSFXVolume:
					
					sh.setSFXVolume(1);
					break;
				case SetBGMVolume:
					break;
				default:
					break;
			}
			
			// TODO what happens here;
		}	
	}
}
