package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerAttributeTest {
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

		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Match);
		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Round);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault + 2);
	}

	@Test
	public void testAddUpgradeAttribute() {
		Attribute attr = Attribute.BombPower;

		assertEquals(1, pa.getAttrValue(attr));

		pa.upgradeAttr(attr, UpgradeType.Match);

		assertEquals(2, pa.getAttrValue(attr));

	}

	@Test
	public void testResetMatchAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Match);
		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Match);

		pa.resetAttr(UpgradeType.Match);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testResetTurnAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Round);
		pa.upgradeAttr(Attribute.BombRange, UpgradeType.Round);

		pa.resetAttr(UpgradeType.Round);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testEquals() {
		PlayerAttributes oherPa = new PlayerAttributes();

		// Testing equals against null
		assertFalse(oherPa.equals(null));

		// Testing equals against itself
		assertTrue(pa.equals(pa));

		assertTrue(pa.equals(oherPa));

		oherPa.upgradeAttr(Attribute.BombPower, UpgradeType.Match);

		assertFalse(pa.equals(oherPa));

		pa.upgradeAttr(Attribute.BombPower, UpgradeType.Match);

		assertTrue(pa.equals(oherPa));

		pa.upgradeAttr(Attribute.Speed, UpgradeType.Round);
		oherPa.upgradeAttr(Attribute.Speed, UpgradeType.Round);

		assertTrue(pa.equals(oherPa));
	}

	@Test
	public void testHashCode() {
		PlayerAttributes oherPa = new PlayerAttributes();

		assertEquals(pa.hashCode(), oherPa.hashCode());

		oherPa.upgradeAttr(Attribute.BombPower, UpgradeType.Match);

		assertFalse(pa.hashCode() == oherPa.hashCode());

		pa.upgradeAttr(Attribute.BombPower, UpgradeType.Match);

		assertEquals(pa.hashCode(), oherPa.hashCode());

		pa.upgradeAttr(Attribute.Speed, UpgradeType.Round);
		oherPa.upgradeAttr(Attribute.Speed, UpgradeType.Round);

		assertEquals(pa.hashCode(), oherPa.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		pa = null;
	}
}
