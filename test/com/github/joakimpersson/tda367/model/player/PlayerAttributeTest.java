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
	private PlayerAttributes playerAttributes;

	@Before
	public void setUp() throws Exception {
		playerAttributes= new PlayerAttributes();
	}

	@Test
	public void testUpdateMatchAttr() {
		int speedDefault = Parameters.INSTANCE.getInitSpeed();
		playerAttributes.upgradeAttr(Attribute.Speed, GameModeType.Match);
		assertEquals(playerAttributes.getAttrValue(Attribute.Speed), speedDefault + 1);
	}

	@Test
	public void testUpdateRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();
		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Round);
		assertEquals(playerAttributes.getAttrValue(Attribute.BombRange), bombRangeDefault + 1);
	}

	@Test
	public void testUpdateMatchRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Match);
		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Round);

		assertEquals(playerAttributes.getAttrValue(Attribute.BombRange), bombRangeDefault + 2);
	}

	@Test
	public void testAddUpgradeAttribute() {
		Attribute attribute = Attribute.BombPower;

		assertEquals(1, playerAttributes.getAttrValue(attribute));

		playerAttributes.upgradeAttr(attribute, GameModeType.Match);

		assertEquals(2, playerAttributes.getAttrValue(attribute));

	}

	@Test
	public void testResetMatchAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Match);
		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		playerAttributes.resetAttr(GameModeType.Match);

		assertEquals(playerAttributes.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testResetTurnAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Round);
		playerAttributes.upgradeAttr(Attribute.BombRange, GameModeType.Round);

		playerAttributes.resetAttr(GameModeType.Round);

		assertEquals(playerAttributes.getAttrValue(Attribute.BombRange), bombRangeDefault);
	}

	@Test
	public void testEquals() {
		PlayerAttributes oherPlayerAttribute = new PlayerAttributes();

		// Testing equals against null
		assertFalse(oherPlayerAttribute.equals(null));

		// Testing equals against itself
		assertTrue(playerAttributes.equals(playerAttributes));

		assertTrue(playerAttributes.equals(oherPlayerAttribute));

		oherPlayerAttribute.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertFalse(playerAttributes.equals(oherPlayerAttribute));

		playerAttributes.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertTrue(playerAttributes.equals(oherPlayerAttribute));

		playerAttributes.upgradeAttr(Attribute.Speed, GameModeType.Round);
		oherPlayerAttribute.upgradeAttr(Attribute.Speed, GameModeType.Round);

		assertTrue(playerAttributes.equals(oherPlayerAttribute));
	}

	@Test
	public void testHashCode() {
		PlayerAttributes oherPlayerAttribute = new PlayerAttributes();

		assertEquals(playerAttributes.hashCode(), oherPlayerAttribute.hashCode());

		oherPlayerAttribute.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertFalse(playerAttributes.hashCode() == oherPlayerAttribute.hashCode());

		playerAttributes.upgradeAttr(Attribute.BombPower, GameModeType.Match);

		assertEquals(playerAttributes.hashCode(), oherPlayerAttribute.hashCode());

		playerAttributes.upgradeAttr(Attribute.Speed, GameModeType.Round);
		oherPlayerAttribute.upgradeAttr(Attribute.Speed, GameModeType.Round);

		assertEquals(playerAttributes.hashCode(), oherPlayerAttribute.hashCode());
	}

	@Test
	public void testGetAttributes() {
		List<Attribute> expected = Arrays.asList(Attribute.values());
		List<Attribute> actual = playerAttributes.getAttributes();

		for (Attribute attribute : expected) {
			assertTrue(actual.contains(attribute));
		}
	}

	@After
	public void tearDown() throws Exception {
		playerAttributes = null;
	}
}
