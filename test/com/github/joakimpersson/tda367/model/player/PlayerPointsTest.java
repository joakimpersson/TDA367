package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

public class PlayerPointsTest {

	private PlayerPoints pp;

	@Before
	public void setUp() throws Exception {
		pp = new PlayerPoints();
	}

	@Test
	public void testUpdate() {

		List<PointGiver> list = new ArrayList<PointGiver>();

		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;

		list.add(boxPoint);
		pp.update(list);
		int scoreBox = pp.getScore();
		list.clear();
		pp.reset();

		list.add(pillarPoint);
		pp.update(list);
		int scorePillar = pp.getScore();
		list.clear();
		pp.reset();

		list.add(killPoint);
		pp.update(list);
		int scoreKill = pp.getScore();
		list.clear();
		pp.reset();

		list.add(hitPoint);
		pp.update(list);
		int scoreHit = pp.getScore();
		list.clear();
		pp.reset();

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

		pp.update(list);

		int tmpCredits = pp.getCredits();

		pp.useCredits(50);

		assertEquals(pp.getCredits(), (tmpCredits - 50));

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

		pp.update(list);

		assertEquals(expected, pp.getScore());

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

		pp.update(list);

		assertEquals(pp.getScore(), pp.getCredits());

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

		pp.update(list);

		int tmpHitPlayer = pp.getDestroyedPointGiver(PointGiver.PlayerHit);

		list.clear();

		PointGiver hitPoint2 = PointGiver.PlayerHit;
		PointGiver hitPoint3 = PointGiver.PlayerHit;

		list.add(hitPoint2);
		list.add(hitPoint3);

		pp.update(list);

		assertEquals(pp.getDestroyedPointGiver(PointGiver.PlayerHit),
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

		pp.update(list);

		int tmpDestBoxPoints = pp.getDestroyedPointGiver(PointGiver.Box);

		list.clear();

		PointGiver destBoxPoint2 = PointGiver.Box;
		PointGiver destBoxPoint3 = PointGiver.Box;

		list.add(destBoxPoint2);
		list.add(destBoxPoint3);

		pp.update(list);
		// TODO do not follow the correct syntax rolen!
		assertEquals(pp.getDestroyedPointGiver(PointGiver.Box),
				tmpDestBoxPoints + 2);

	}

	@Test
	public void testReset() {
		List<PointGiver> list = new ArrayList<PointGiver>();
		list.add(PointGiver.PlayerHit);
		list.add(PointGiver.PlayerHit);
		list.add(PointGiver.PlayerHit);

		// The Score and credits should be greater than zero
		pp.update(list);
		int actualScore = pp.getScore();
		int actualCredits = pp.getCredits();

		assertTrue(actualScore > 0);
		assertTrue(actualCredits > 0);

		// Reset the players PlayerPoints object object
		pp.reset();

		actualScore = pp.getScore();
		actualCredits = pp.getCredits();
		assertTrue(actualScore == 0);
		assertTrue(actualCredits == 0);

	}

	@After
	public void tearDown() throws Exception {
		pp = null;
	}
}
