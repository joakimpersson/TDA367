package com.github.joakimpersson.tda367.core;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerAttributeTest {
	// Add testing for match only upgrades
	private PlayerAttributes pa;

	@Before
	public void setUp() throws Exception {
		pa = new PlayerAttributes();
	}

	@Test
	public void testUpdateMatchAttr() {
		int speedDefault = Parameters.INSTANCE.getInitSpeed();
		pa.upgradeAttr(Attribute.Speed, UpgradeType.Match);
		assertEquals(pa.getAttrValue(Attribute.Speed), speedDefault + 1);
	}

	@Test
	public void testUpdateRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();
		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Round);
		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault + 1);
	}

	@Test
	public void testUpdateMatchRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Match);
		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Round);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault + 2);
	}

	@Test
	public void testResetMatchAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Match);
		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Match);

		pa.resetAttr(UpgradeType.Match);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testResetTurnAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Round);
		pa.upgradeAttr(Attribute.BombRange,UpgradeType.Round);

		pa.resetAttr();

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@After
	public void tearDown() throws Exception {
		pa = null;
	}
}
