package com.github.joakimpersson.tda367.model.player;

import static com.github.joakimpersson.tda367.model.constants.Attribute.BombPower;
import static com.github.joakimpersson.tda367.model.constants.Attribute.BombRange;
import static com.github.joakimpersson.tda367.model.constants.Attribute.BombStack;
import static com.github.joakimpersson.tda367.model.constants.Attribute.Health;
import static com.github.joakimpersson.tda367.model.constants.Attribute.Speed;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Parameters;

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
	 * @return The attributes level or 0 if the specified attribute is not
	 *         activated
	 */
	public int getAttrValue(Attribute attr) {

		int rAttr = roundAttr.get(attr) == null ? 0 : roundAttr.get(attr);
		int mAttr = matchAttr.get(attr) == null ? 0 : matchAttr.get(attr);

		return mAttr + rAttr;
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
			resetRoundAttr();
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
		matchAttr.put(BombPower, Parameters.INSTANCE.getInitBombPower());

	}

	/**
	 * Set the round map values to the standard values which is zero
	 */
	private void initDefaultRoundValues() {

		roundAttr.put(Speed, 0);
		roundAttr.put(BombStack, 0);
		roundAttr.put(BombRange, 0);

	}

	/**
	 * Get the Map that is representing the players match attributes and its
	 * corresponding values
	 * 
	 * @return The players Match Attributs Map
	 */
	public Map<Attribute, Integer> getMatchAttrs() {
		return matchAttr;
	}

	/**
	 * Get a list of all the players attribute
	 * 
	 * @return List of the players match attributes
	 */
	public List<Attribute> getAttributes() {
		ArrayList<Attribute> tmp = new ArrayList<Attribute>();
		for (Attribute a : matchAttr.keySet()) {
			tmp.add(a);
		}
		return tmp;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		PlayerAttributes other = (PlayerAttributes) obj;
		return this.matchAttr.equals(other.matchAttr)
				&& this.roundAttr.equals(other.roundAttr);
	}

	@Override
	public int hashCode() {
		int sum = 0;

		for (Attribute a : matchAttr.keySet()) {
			sum += matchAttr.get(a) * 5;
		}

		for (Attribute a : roundAttr.keySet()) {
			sum += roundAttr.get(a) * 7;
		}
		return sum;
	}
}
