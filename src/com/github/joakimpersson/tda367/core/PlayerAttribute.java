package com.github.joakimpersson.tda367.core;

import static com.github.joakimpersson.tda367.core.Attribute.BombRange;
import static com.github.joakimpersson.tda367.core.Attribute.Bombs;
import static com.github.joakimpersson.tda367.core.Attribute.Health;
import static com.github.joakimpersson.tda367.core.Attribute.Speed;

import java.util.EnumMap;
import java.util.Map;

/**
 * 
 * @author joakimpersson
 *
 */
public class PlayerAttribute {

	private Map<Attribute, Integer> matchAttr;
	private Map<Attribute, Integer> roundAttr;

	public PlayerAttribute() {

		matchAttr = new EnumMap<Attribute, Integer>(Attribute.class);
		roundAttr = new EnumMap<Attribute, Integer>(Attribute.class);

		this.initDefaultMatchValues();
		this.initDefaultRoundValues();
	}

	private void initDefaultMatchValues() {

		matchAttr.put(Speed, Parameters.INSTANCE.getInitSpeed());
		matchAttr.put(Bombs, Parameters.INSTANCE.getStartingBombs());
		matchAttr.put(Health, Parameters.INSTANCE.getInitHealth());
		matchAttr.put(BombRange, Parameters.INSTANCE.getInitBombRange());
		
	}

	private void initDefaultRoundValues() {

		roundAttr.put(Speed, 0);
		roundAttr.put(Bombs, 0);
		roundAttr.put(Health, 0);
		roundAttr.put(BombRange, 0);

	}

	public void updateMatchAttr(Attribute attr) {
		int tmp = matchAttr.get(attr) + 1;
		matchAttr.put(attr, tmp);
	}

	public void updateRoundAttr(Attribute attr) {
		int tmp = roundAttr.get(attr) + 1;
		roundAttr.put(attr, tmp);
	}

	public int getAttrValue(Attribute attr) {
		return matchAttr.get(attr) + roundAttr.get(attr);
	}

	public void resetAllAttr() {
		this.resetMatchAttr();
		this.resetRoundAttr();
	}

	public void resetMatchAttr() {
		this.initDefaultMatchValues();
	}

	public void resetRoundAttr() {
		this.initDefaultRoundValues();
	}
}
