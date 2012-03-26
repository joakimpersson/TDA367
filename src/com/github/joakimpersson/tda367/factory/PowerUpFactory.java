package com.github.joakimpersson.tda367.factory;

import com.github.joakimpersson.tda367.core.powerupitems.BombStackUpdateItem;
import com.github.joakimpersson.tda367.core.powerupitems.PowerupItem;
import com.github.joakimpersson.tda367.core.powerupitems.RangeUpdateItem;
import com.github.joakimpersson.tda367.core.powerupitems.SpeedUpdateItem;


public class PowerUpFactory {
	
	public PowerUpFactory() {
		
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
