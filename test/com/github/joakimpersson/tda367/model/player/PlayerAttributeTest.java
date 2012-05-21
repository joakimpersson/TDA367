package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.GameModeType;
import com.github.joakimpersson.tda367.model.constants.Parameters;

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
		pa.upgradeAttr(Attribute.Speed, GameModeType.Match);
		assertEquals(pa.getAttrValue(Attribute.Speed), speedDefault + 1);
	}

	@Test
	public void testUpdateRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();
		pa.upgradeAttr(Attribute.BombRange, GameModeType.Round);
		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault + 1);
	}

	@Test
	public void testUpdateMatchRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange, GameModeType.Match);
		pa.upgradeAttr(Attribute.BombRange, GameModeType.Round);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault + 2);
	}

	@Test
	public void testAddUpgradeAttribute() {
		Attribute attr = Attribute.BombPower;

		assertEquals(1, pa.getAttrValue(attr));

		pa.upgradeAttr(attr, GameModeType.Match);

		assertEquals(2, pa.getAttrValue(attr));

	}

	@Test
	public void testResetMatchAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange, GameModeType.Match);
		pa.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		pa.resetAttr(GameModeType.Match);

		assertEquals(pa.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testResetTurnAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.upgradeAttr(Attribute.BombRange, GameModeType.Round);
		pa.upgradeAttr(Attribute.BombRange, GameModeType.Round);

		pa.resetAttr(GameModeType.Round);

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

		oherPa.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertFalse(pa.equals(oherPa));

		pa.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertTrue(pa.equals(oherPa));

		pa.upgradeAttr(Attribute.Speed, GameModeType.Round);
		oherPa.upgradeAttr(Attribute.Speed, GameModeType.Round);

		assertTrue(pa.equals(oherPa));
	}

	@Test
	public void testHashCode() {
		PlayerAttributes oherPa = new PlayerAttributes();

		assertEquals(pa.hashCode(), oherPa.hashCode());

		oherPa.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertFalse(pa.hashCode() == oherPa.hashCode());

		pa.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertEquals(pa.hashCode(), oherPa.hashCode());

		pa.upgradeAttr(Attribute.Speed, GameModeType.Round);
		oherPa.upgradeAttr(Attribute.Speed, GameModeType.Round);

		assertEquals(pa.hashCode(), oherPa.hashCode());
	}

	@Test
	public void testGetAttributes() {
		List<Attribute> expected = Arrays.asList(Attribute.values());
		List<Attribute> actual = pa.getAttributes();

		for (Attribute a : expected) {
			assertTrue(actual.contains(a));
		}
	}

	@After
	public void tearDown() throws Exception {
		pa = null;
	}
}
