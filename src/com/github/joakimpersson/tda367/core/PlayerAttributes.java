package com.github.joakimpersson.tda367.core;

import static com.github.joakimpersson.tda367.core.Attribute.BombRange;
import static com.github.joakimpersson.tda367.core.Attribute.BombStack;
import static com.github.joakimpersson.tda367.core.Attribute.Health;
import static com.github.joakimpersson.tda367.core.Attribute.Speed;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerAttributes {

	/**
	 * A simple Enum that is representing the two different types of available
	 * upgrades. You use of of the two the specify what kind of update you want
	 * either match duration upgrade or round base upgrade
	 */
	public enum UpgradeType {
		Round, Match;
	}

	/** Every Attribute is associated with an integer or level */
	private Map<Attribute, Integer> matchAttr;
	private Map<Attribute, Integer> roundAttr;

	/**
	 * Create a new player attribute object and instantiate the default values
	 * for the match and round enummap
	 */
	public PlayerAttributes() {

		matchAttr = new EnumMap<Attribute, Integer>(Attribute.class);
		roundAttr = new EnumMap<Attribute, Integer>(Attribute.class);

		this.initDefaultMatchValues();
		this.initDefaultRoundValues();
	}

	/**
	 * Upgrade either an round or match attribute with one level
	 * 
	 * @param attr
	 *            The attribute to be upgraded
	 * @param type
	 *            The type of the upgrade
	 */
	public void upgradeAttr(Attribute attr, UpgradeType type) {
		switch (type) {
		case Round:
			updateRoundAttr(attr);
			break;
		case Match:
			updateMatchAttr(attr);
			break;
		default:
			// Should not happen!
			break;
		}
	}

	/**
	 * Upgraded an match attribute with one level
	 * 
	 * @param attr
	 *            The attribute to be upgraded
	 */
	private void updateMatchAttr(Attribute attr) {
		if (matchAttr.containsKey(attr)) {
			int tmp = matchAttr.get(attr) + 1;
			matchAttr.put(attr, tmp);

		} else {
			matchAttr.put(attr, 1);
		}
	}

	/**
	 * Upgraded an round attribute with one level
	 * 
	 * @param attr
	 *            The attribute to be upgraded
	 */
	private void updateRoundAttr(Attribute attr) {
		int tmp = roundAttr.get(attr) + 1;
		roundAttr.put(attr, tmp);
	}

	/**
	 * Get an attributes level, higher is better
	 * 
	 * @param attr
	 *            The attribute which value you want
	 * @return The attributes level
	 */
	public int getAttrValue(Attribute attr) {

		int rAttr = roundAttr.get(attr) == null ? 0 : roundAttr.get(attr);
		int mAttr = matchAttr.get(attr) == null ? 0 : matchAttr.get(attr);

		return mAttr + rAttr;
	}

	/**
	 * Reset both match and round attributes
	 */
	public void resetAttr() {
		this.resetMatchAttr();
		this.resetRoundAttr();
	}

	/**
	 * 
	 * After either a round or a match reset the players attribute to its
	 * standrad values
	 * 
	 * @param type
	 *            What kind of reset of the attribute is it
	 */
	public void resetAttr(UpgradeType type) {
		switch (type) {
		case Match:
			resetMatchAttr();
			break;
		case Round:
			resetRoundAttr();
			break;
		default:
			// Should not happen!
			break;
		}
	}

	/**
	 * Reset the match map to its standard values
	 */
	private void resetMatchAttr() {
		this.initDefaultMatchValues();
	}

	/**
	 * Reset the round map to its standard values
	 */
	private void resetRoundAttr() {
		this.initDefaultRoundValues();
	}

	/**
	 * Set the match map values to the standard values from the Parameter class
	 */
	private void initDefaultMatchValues() {

		matchAttr.put(Speed, Parameters.INSTANCE.getInitSpeed());
		matchAttr.put(BombStack, Parameters.INSTANCE.getStartingBombs());
		matchAttr.put(Health, Parameters.INSTANCE.getInitHealth());
		matchAttr.put(BombRange, Parameters.INSTANCE.getInitBombRange());

	}

	/**
	 * Set the round map values to the standard values which is zero
	 */
	private void initDefaultRoundValues() {

		roundAttr.put(Speed, 0);
		roundAttr.put(BombStack, 0);
		roundAttr.put(BombRange, 0);

	}
}
