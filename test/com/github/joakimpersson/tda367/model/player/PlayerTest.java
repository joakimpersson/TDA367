package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.constants.ResetType;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.utils.FPosition;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		Position pos = new Position(0, 0);
		player = new Player(1,"Hobbe", pos);
	}

	@Test
	public void testMove() {
		player.move(Direction.EAST);
		
		FPosition expected = new FPosition(0.7F,0.5F);
		FPosition actual = player.getGamePosition();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testResetRound() {
		player.upgradeAttr(Attribute.BombRange, UpgradeType.Round);
		player.reset(ResetType.Round);
		
		int expected = Parameters.INSTANCE.getInitBombRange();
		int actual = player.getAttribute(Attribute.BombRange);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testMatchReset() {
		player.upgradeAttr(Attribute.BombRange, UpgradeType.Match);
		player.reset(ResetType.Match);
		
		int expected = Parameters.INSTANCE.getInitBombRange();
		int actual = player.getAttribute(Attribute.BombRange);
		
		assertEquals(expected, actual);
	}

	@Test
	public void testPlayerHit() {
		int expected = player.getHealth() - 1;
		player.playerHit();
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void testKillPlayer() {
		while(player.getHealth() > 0) {
			player.playerHit();
		}
		
		assertFalse(player.isAlive());
	}

	@Test
	public void testGetScore() {
		List<PointGiver> l = new ArrayList<PointGiver>();
		l.add(new Box().getPointGiver());
		player.updatePlayerPoints(l);
		
		int expected = new Box().getPointGiver().getScore();
		int actual = player.getScore();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetCredits() {
		List<PointGiver> l = new ArrayList<PointGiver>();
		l.add(new Box().getPointGiver());
		player.updatePlayerPoints(l);
		
		int expected = new Box().getPointGiver().getScore();
		int actual = player.getCredits();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testGetName() {
		String expected = "Hobbe";
		String actual = player.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetHealth() {
		int expected = Parameters.INSTANCE.getInitHealth();
		int actual = player.getHealth();
		assertEquals(expected, actual);
	}

	@Test
	public void getTilePosition() {
		Position expected = new Position(0, 0);
		Position actual = player.getTilePosition();
		assertEquals(expected, actual);
	}

	@Test
	public void getPlayerPoints() {
		PlayerPoints pp = player.getPoints();
		List<PointGiver> l = new ArrayList<PointGiver>();
		l.add(new Box().getPointGiver());
		player.updatePlayerPoints(l);
		
		int expected = new Box().getPointGiver().getScore();
		int actual = pp.getScore();
		
		assertEquals(expected, actual);
	}
	
	@After
	public void tearDown() throws Exception {
		player = null;
	}

}
