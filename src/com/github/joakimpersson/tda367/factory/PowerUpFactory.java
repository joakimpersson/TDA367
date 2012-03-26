package com.github.joakimpersson.tda367.factory;

import com.github.joakimpersson.tda367.core.BombStackUpdateItem;
import com.github.joakimpersson.tda367.core.PowerupItem;
import com.github.joakimpersson.tda367.core.RangeUpdateItem;
import com.github.joakimpersson.tda367.core.SpeedUpdateItem;


public enum PowerUpFactory {
	INSTANCE;
	
	public static PowerUpFactory getInstance() {
		return INSTANCE;
	}
	
	public PowerupItem createObject() {
		
		double rand = Math.random();
		double underLevel = 1/3.0;
		double middleLevel = 2/3.0;

		if(Double.compare(underLevel, rand) >= 0) {
			return new SpeedUpdateItem();
		} else if(Double.compare(rand, underLevel) >= 0 && Double.compare(middleLevel, rand) >= 0) {
			return new BombStackUpdateItem();
		} else {
			return new RangeUpdateItem();
		}
	}
}
