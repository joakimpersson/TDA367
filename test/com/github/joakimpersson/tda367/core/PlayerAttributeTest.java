package com.github.joakimpersson.tda367.core;

import static com.github.joakimpersson.tda367.core.Attribute.BombRange;
import static com.github.joakimpersson.tda367.core.Attribute.Speed;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author joakimpersson
 *
 */
public class PlayerAttributeTest {
	//Add testing for match only upgrades
	private PlayerAttribute pa;

	@Before
	public void setUp() throws Exception {
		pa = new PlayerAttribute();
	}

	@Test
	public void testUpdateMatchAttr() {
		int speedDefault = Parameters.INSTANCE.getInitSpeed();
		pa.updateMatchAttr(Speed);
		assertEquals(pa.getAttrValue(Speed), speedDefault + 1);
	}

	@Test
	public void testUpdateRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();
		pa.updateRoundAttr(BombRange);
		assertEquals(pa.getAttrValue(BombRange), bombRangeDefault + 1);
	}

	@Test
	public void testUpdateMatchRoundAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.updateMatchAttr(BombRange);
		pa.updateRoundAttr(BombRange);

		assertEquals(pa.getAttrValue(BombRange), bombRangeDefault + 2);
	}

	@Test
	public void testResetMatchAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.updateMatchAttr(BombRange);
		pa.updateMatchAttr(BombRange);

		pa.resetMatchAttr();

		// TODO is it enough to test if only the changed value is the same?
		assertEquals(pa.getAttrValue(BombRange), bombRangeDefault);
	}

	@Test
	public void testResetTurnAttr() {
		int bombRangeDefault = Parameters.INSTANCE.getInitBombRange();

		pa.updateRoundAttr(BombRange);
		pa.updateRoundAttr(BombRange);

		pa.resetRoundAttr();

		// TODO is it enough to test if only the changed value is the same?
		assertEquals(pa.getAttrValue(BombRange), bombRangeDefault);
	}

	@After
	public void tearDown() throws Exception {
		pa = null;
	}
}
