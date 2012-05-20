package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

public class PlayerPointsTest {

	private PlayerPoints playerPoint;

	@Before
	public void setUp() throws Exception {
		playerPoint = new PlayerPoints();
	}

	@Test
	public void testUpdate() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(boxPoint);
		playerPoint.update(list);
		int scoreBox = playerPoint.getScore();
		list.clear();
		playerPoint.reset();

		list.add(pillarPoint);
		playerPoint.update(list);
		int scorePillar = playerPoint.getScore();
		list.clear();
		playerPoint.reset();

		list.add(killPoint);
		playerPoint.update(list);
		int scoreKill = playerPoint.getScore();
		list.clear();
		playerPoint.reset();

		list.add(hitPoint);
		playerPoint.update(list);
		int scoreHit = playerPoint.getScore();
		list.clear();
		playerPoint.reset();

		assertEquals(PointGiver.Box.getScore(), scoreBox);
		assertEquals(PointGiver.Pillar.getScore(), scorePillar);
		assertEquals(PointGiver.KillPlayer.getScore(), scoreKill);
		assertEquals(PointGiver.PlayerHit.getScore(), scoreHit);

	}

	@Test
	public void testReduceCredits() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);
		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		playerPoint.update(list);

		int tmpCredits = playerPoint.getCredits();

		playerPoint.useCredits(50);

		assertEquals(playerPoint.getCredits(), (tmpCredits - 50));

	}

	@Test
	public void testGetScore() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		int expected = 0;

		list.add(pillarPoint);
		expected += pillarPoint.getScore();
		list.add(boxPoint);
		expected += boxPoint.getScore();
		list.add(killPoint);
		expected += killPoint.getScore();
		list.add(hitPoint);
		expected += hitPoint.getScore();

		playerPoint.update(list);

		assertEquals(expected, playerPoint.getScore());

	}

	@Test
	public void testGetCredits() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);
		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		playerPoint.update(list);

		assertEquals(playerPoint.getScore(), playerPoint.getCredits());

	}

	@Test
	public void testGetHitPlayers() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);
		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		playerPoint.update(list);

		int tmpHitPlayer = playerPoint
				.getDestroyedPointGiver(PointGiver.PlayerHit);

		list.clear();

		PointGiver hitPoint2 = PointGiver.PlayerHit;
		PointGiver hitPoint3 = PointGiver.PlayerHit;

		list.add(hitPoint2);
		list.add(hitPoint3);

		playerPoint.update(list);

		assertEquals(playerPoint.getDestroyedPointGiver(PointGiver.PlayerHit),
				tmpHitPlayer + 2);

	}

	@Test
	public void testGetKilledPlayers() {

		PlayerPoints pp = new PlayerPoints();
		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);

		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		pp.update(list);

		int tmpKilledPlayer = pp.getDestroyedPointGiver(PointGiver.KillPlayer);

		list.clear();

		PointGiver killPoint2 = PointGiver.KillPlayer;
		PointGiver killPoint3 = PointGiver.KillPlayer;

		list.add(killPoint2);
		list.add(killPoint3);

		pp.update(list);

		assertEquals(pp.getDestroyedPointGiver(PointGiver.KillPlayer),
				tmpKilledPlayer + 2);

	}

	@Test
	public void testGetDestroyedPillars() {

		PlayerPoints pp = new PlayerPoints();
		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);
		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		pp.update(list);

		int tmpDestPillarPoints = pp.getDestroyedPointGiver(PointGiver.Pillar);

		list.clear();

		PointGiver destPillarPoint2 = PointGiver.Pillar;
		PointGiver destPillarPoint3 = PointGiver.Pillar;

		list.add(destPillarPoint2);
		list.add(destPillarPoint3);

		pp.update(list);

		assertEquals(pp.getDestroyedPointGiver(PointGiver.Pillar),
				tmpDestPillarPoints + 2);

	}

	@Test
	public void testGetDestroyedBoxes() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(pillarPoint);
		list.add(boxPoint);
		list.add(killPoint);
		list.add(hitPoint);

		playerPoint.update(list);

		int tmpDestBoxPoints = playerPoint
				.getDestroyedPointGiver(PointGiver.Box);

		list.clear();

		PointGiver destBoxPoint2 = PointGiver.Box;
		PointGiver destBoxPoint3 = PointGiver.Box;

		list.add(destBoxPoint2);
		list.add(destBoxPoint3);

		playerPoint.update(list);
		// TODO do not follow the correct syntax rolen!
		assertEquals(tmpDestBoxPoints + 2,
				playerPoint.getDestroyedPointGiver(PointGiver.Box));

	}

	@Test
	public void testReset() {
		List<PointGiver> list = new ArrayList<PointGiver>();
		list.add(PointGiver.PlayerHit);
		list.add(PointGiver.PlayerHit);
		list.add(PointGiver.PlayerHit);

		// The Score and credits should be greater than zero
		playerPoint.update(list);
		int actualScore = playerPoint.getScore();
		int actualCredits = playerPoint.getCredits();

		assertTrue(actualScore > 0);
		assertTrue(actualCredits > 0);

		// Reset the players PlayerPoints object object
		playerPoint.reset();

		actualScore = playerPoint.getScore();
		actualCredits = playerPoint.getCredits();
		assertTrue(actualScore == 0);
		assertTrue(actualCredits == 0);

	}

	@Test
	public void compareToTest() {
		PlayerPoints ppDummy = new PlayerPoints();

		List<PointGiver> list = new ArrayList<PointGiver>();
		list.add(PointGiver.Bomb);
		list.add(PointGiver.Pillar);
		list.add(PointGiver.KillPlayer);

		ppDummy.update(list);
		playerPoint.update(list);

		assertEquals(0, playerPoint.compareTo(ppDummy));

		list.add(PointGiver.PlayerHit);

		playerPoint.update(list);

		assertTrue(playerPoint.compareTo(ppDummy) > 0);

	}

	@Test
	public void testHashCode() {
		PlayerPoints otherPlayerPoints = new PlayerPoints();

		// They should be equal when created
		assertTrue(playerPoint.hashCode() == otherPlayerPoints.hashCode());

		// changes to the playerPoint object
		playerPoint.update(PointGiver.Bomb);

		// They should not be equal anymore
		assertFalse(playerPoint.hashCode() == otherPlayerPoints.hashCode());

		// same changes to the other playerPoint object
		otherPlayerPoints.update(PointGiver.Bomb);

		// they should now be equal again
		assertTrue(playerPoint.hashCode() == otherPlayerPoints.hashCode());

		// this time changes to the other playerPoint object
		otherPlayerPoints.update(PointGiver.Bomb);

		// no equal anymore
		assertFalse(playerPoint.hashCode() == otherPlayerPoints.hashCode());
	}

	@Test
	public void testEquals() {
		PlayerPoints otherPlayerPoints = new PlayerPoints();

		// testing against itself and null
		assertTrue(playerPoint.equals(playerPoint));

		assertFalse(playerPoint.equals(null));

		// They should be equal when created
		assertTrue(playerPoint.equals(otherPlayerPoints));

		// changes to the playerPoint object
		playerPoint.update(PointGiver.Bomb);

		// They should not be equal anymore
		assertFalse(playerPoint.equals(otherPlayerPoints));

		// same changes to the other playerPoint object
		otherPlayerPoints.update(PointGiver.Bomb);

		// they should now be equal again
		assertTrue(playerPoint.equals(otherPlayerPoints));

		// this time changes to the other playerPoint object
		otherPlayerPoints.update(PointGiver.Bomb);

		// no equal anymore
		assertFalse(playerPoint.equals(otherPlayerPoints));

	}

	@Test
	public void testCopyConstructor() {
		PlayerPoints playerPointsCopy = new PlayerPoints(playerPoint);

		// They should be equal and have same hashCode
		assertTrue(playerPoint.equals(playerPointsCopy));
		assertTrue(playerPoint.hashCode() == playerPointsCopy.hashCode());

		// testing the copy class when the playerpoint has been upgraded
		playerPoint.update(PointGiver.Bomb);

		playerPointsCopy = new PlayerPoints(playerPoint);

		// They should still be equal and have same hashCode
		assertTrue(playerPoint.equals(playerPointsCopy));
		assertTrue(playerPoint.hashCode() == playerPointsCopy.hashCode());
	}

	@After
	public void tearDown() throws Exception {
		playerPoint = null;
	}
}
