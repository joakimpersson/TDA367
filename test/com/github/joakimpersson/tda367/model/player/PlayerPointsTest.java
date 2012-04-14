package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.PointGiver;

public class PlayerPointsTest {

	@Test
	public void testUpdate() {
		System.out.println("update test:");

		PlayerPoints pp = new PlayerPoints();
		List<PointGiver> list = new ArrayList<PointGiver>();
		
		PointGiver pillarPoint = PointGiver.Pillar;
		PointGiver boxPoint = PointGiver.Box;
		PointGiver killPoint = PointGiver.KillPlayer;
		PointGiver hitPoint = PointGiver.PlayerHit;
		
		list.add(boxPoint);
		pp.update(list);
		int scoreBox = pp.getScore();
		list.clear();
		pp.resetScore();
		
		list.add(pillarPoint);
		pp.update(list);
		int scorePillar = pp.getScore();
		list.clear();
		pp.resetScore();
		
		list.add(killPoint);
		pp.update(list);
		int scoreKill = pp.getScore();
		list.clear();
		pp.resetScore();
		
		list.add(hitPoint);
		pp.update(list);
		int scoreHit = pp.getScore();
		list.clear();
		pp.resetScore();
		
		assertEquals(5, scoreBox);
		assertEquals(20, scorePillar);
		assertEquals(30, scoreKill);
		assertEquals(15, scoreHit);
				
	}

	@Test
	public void testReduceCredits() {
				
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
		
		int tmpCredits = pp.getCredits();
		
		pp.useCredits(50);
		
		assertEquals(pp.getCredits(), (tmpCredits - 50));
		
	}

	@Test
	public void testGetScore() {
		
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

		assertEquals(70, pp.getScore());
			
	}

	@Test
	public void testGetCredits() {
		
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
		
		assertEquals(pp.getScore(), pp.getCredits());
		
	}

	@Test
	public void testGetHitPlayers() {
		
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
		
		int tmpHitPlayer = pp.getHitPlayers();
		
		list.clear();
		
		PointGiver hitPoint2 = PointGiver.PlayerHit;
		PointGiver hitPoint3 = PointGiver.PlayerHit;
		
		list.add(hitPoint2);
		list.add(hitPoint3);
		
		pp.update(list);
		
		assertEquals(pp.getHitPlayers(), tmpHitPlayer + 2);
		
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
		
		int tmpKilledPlayer = pp.getKilledPlayers();
		
		list.clear();
		
		PointGiver killPoint2 = PointGiver.KillPlayer;
		PointGiver killPoint3 = PointGiver.KillPlayer;
		
		list.add(killPoint2);
		list.add(killPoint3);
		
		pp.update(list);
		
		assertEquals(pp.getKilledPlayers(), tmpKilledPlayer + 2);
		
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
		
		int tmpDestPillarPoints = pp.getDestroyedPillars();
		
		list.clear();
		
		PointGiver destPillarPoint2 = PointGiver.Pillar;
		PointGiver destPillarPoint3 = PointGiver.Pillar;
		
		list.add(destPillarPoint2);
		list.add(destPillarPoint3);
		
		pp.update(list);
		
		assertEquals(pp.getDestroyedPillars(), tmpDestPillarPoints + 2);
		
	}

	@Test
	public void testGetDestroyedBoxes() {
		
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
		
		int tmpDestBoxPoints = pp.getDestroyedBoxes();
		
		list.clear();
		
		PointGiver destBoxPoint2 = PointGiver.Box;
		PointGiver destBoxPoint3 = PointGiver.Box;
		
		list.add(destBoxPoint2);
		list.add(destBoxPoint3);
		
		pp.update(list);
		
		assertEquals(pp.getDestroyedBoxes(), tmpDestBoxPoints + 2);
		
	}

	@Test
	public void testGetPointList() {
		System.out.println("getPointList test:");

		
		System.out.println("End test");
	}

}
