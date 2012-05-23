package com.github.joakimpersson.tda367.model.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.GameModeType;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.positions.FPosition;
import com.github.joakimpersson.tda367.model.positions.Position;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;

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
		player = new Player(0, "Hobbe", pos);
	}

	@Test
	public void testClonePlayer() {
		assertEquals(player, new Player(player));
	}

	@Test
	public void testMove() {
		double stepSize = Parameters.INSTANCE.getBaseStepSize();
		player.move(Direction.EAST, stepSize);

		FPosition expected = new FPosition(0.5f + (float) stepSize, 0.5F);
		FPosition actual = player.getGamePosition();

		assertEquals(expected, actual);
	}

	@Test
	public void testResetRound() {
		player.upgradeAttr(Attribute.BombRange, GameModeType.Round);
		player.reset(GameModeType.Round);

		int expected = Parameters.INSTANCE.getInitBombRange();
		int actual = player.getAttribute(Attribute.BombRange);

		assertEquals(expected, actual);
	}

	@Test
	public void testMatchReset() {
		player.upgradeAttr(Attribute.BombRange, GameModeType.Match);
		player.reset(GameModeType.Match);

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
	public void testIsAlive() {
		assertTrue(player.isAlive());
		// kill the player
		while (player.getHealth() > 0) {
			player.playerHit();
		}

		assertFalse(player.isAlive());
	}

	@Test
	public void testGetScore() {
		List<PointGiver> list = new ArrayList<PointGiver>();
		list.add(new Box().getPointGiver());
		player.updatePlayerPoints(list);

		int expected = new Box().getPointGiver().getScore();
		int actual = player.getScore();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetCredits() {
		List<PointGiver> list = new ArrayList<PointGiver>();
		list.add(new Box().getPointGiver());
		player.updatePlayerPoints(list);

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
		PlayerPoints playerPoints = player.getPoints();
		List<PointGiver> l = new ArrayList<PointGiver>();
		l.add(new Box().getPointGiver());
		player.updatePlayerPoints(l);

		int expected = new Box().getPointGiver().getScore();
		int actual = playerPoints.getScore();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetDirection() {
		// Starting direction is south
		Direction expected = Direction.SOUTH;
		Direction actual = player.getDirection();

		assertTrue(expected.equals(actual));

		// change the direction to north

		expected = Direction.NORTH;
		player.move(expected, 0.2);

		actual = player.getDirection();

		assertTrue(expected.equals(actual));
	}

	@Test
	public void testIsImmortal() {
		assertFalse(player.isImmortal());
		player.playerHit();
		assertTrue(player.isImmortal());
	}

	@Test
	public void testGetEarnedPointGiver() {
		PointGiver pointGiver = PointGiver.Box;

		int expected = 0;
		int actual = player.getEarnedPointGiver(pointGiver);

		assertEquals(expected, actual);

		player.updatePlayerPoints(pointGiver);

		expected = 1;
		actual = player.getEarnedPointGiver(pointGiver);

		assertEquals(expected, actual);
	}

	@Test
	public void testGetImage() {
		int expectedPlayerIndex = 0;
		Direction expectedDirection = Direction.SOUTH;
		String expected = "player/" + expectedPlayerIndex + "/still-"
				+ expectedDirection;
		String actual = player.getImage();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetIndex() {
		int expected = 0;
		int actual = player.getIndex();

		assertEquals(expected, actual);
	}

	@Test
	public void useCredits() {
		// add credits to the player
		player.updatePlayerPoints(PointGiver.GameWon);

		// he should have 500 credits
		int expected = PointGiver.GameWon.getScore();
		int actual = player.getCredits();

		assertEquals(expected, actual);

		// buy something

		Attribute boughtAttribute = Attribute.BombStack;
		int cost = boughtAttribute.getCost();

		expected -= cost;
		player.useCredits(cost);
		actual = player.getCredits();

		assertEquals(expected, actual);

	}

	@Test
	public void testEquals() {
		int otherIndex = 1;
		String otherPlayerName = "Kalle";
		Position otherPos = new Position(5, 10);
		Player otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		// testing for self refrence and null
		assertTrue(player.equals(player));
		assertFalse(player.equals(null));

		assertFalse(player.equals(otherPlayer));

		// now slowly change other players params so they become equal
		otherIndex = 0;

		otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		assertFalse(player.equals(otherPlayer));

		otherPlayerName = "Hobbe";
		otherPos = new Position(0, 0);

		otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		assertTrue(player.equals(otherPlayer));

		// Update the player attributes and playerpoints object
		// which mean they should not be equal anymore
		player.updatePlayerPoints(PointGiver.MatchWon);
		player.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		assertFalse(player.equals(otherPlayer));

		otherPlayer.updatePlayerPoints(PointGiver.MatchWon);
		otherPlayer.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		assertTrue(player.equals(otherPlayer));

	}

	@Test
	public void testHashCode() {
		int otherIndex = 1;
		String otherPlayerName = "Kalle";
		Position otherPos = new Position(5, 10);
		Player otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		assertFalse(player.hashCode() == otherPlayer.hashCode());

		// now slowly change other players params so they become equal
		otherIndex = 0;

		otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		assertFalse(player.hashCode() == otherPlayer.hashCode());

		otherPlayerName = "Hobbe";
		otherPos = new Position(0, 0);

		otherPlayer = new Player(otherIndex, otherPlayerName, otherPos);

		assertTrue(player.hashCode() == otherPlayer.hashCode());

		// Update the player attributes and playerpoints object
		// which mean they should not be equal anymore
		player.updatePlayerPoints(PointGiver.MatchWon);
		player.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		assertFalse(player.hashCode() == otherPlayer.hashCode());

		otherPlayer.updatePlayerPoints(PointGiver.MatchWon);
		otherPlayer.upgradeAttr(Attribute.BombRange, GameModeType.Match);

		assertTrue(player.hashCode() == otherPlayer.hashCode());
	}

	@Test
	public void testRoundWon() {
		int expected = 0;
		int actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.roundWon();

		expected = 1;
		actual = player.getRoundsWon();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetRoundsWon() {
		int expected = 0;
		int actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.roundWon();
		player.roundWon();

		expected = 2;
		actual = player.getRoundsWon();

		assertEquals(expected, actual);
	}

	@Test
	public void testResetRoundsWon() {
		int expected = 0;
		int actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.roundWon();
		player.roundWon();

		expected = 2;
		actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.resetRoundsWon();

		expected = 0;
		actual = player.getRoundsWon();

		assertEquals(expected, actual);

	}

	@Test
	public void testMatchWon() {
		int expected = 0;
		int actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.matchWon();

		expected = 1;
		actual = player.getMatchesWon();

		assertEquals(expected, actual);
	}

	@Test
	public void testGetMatchesWon() {
		int expected = 0;
		int actual = player.getRoundsWon();

		assertEquals(expected, actual);

		player.matchWon();
		player.matchWon();

		expected = 2;
		actual = player.getMatchesWon();

		assertEquals(expected, actual);
	}

	@Test
	public void testIncDecBombsPlaced() {
		int bombsAvailable = Parameters.INSTANCE.getStartingBombs();
		
		for (int i = 1; i<bombsAvailable; i++) {
			player.increaseBombsPlaced();
		}
		
		player.increaseBombsPlaced();
		assertFalse(player.canPlaceBomb());
		player.decreaseBombsPlaced();
		assertTrue(player.canPlaceBomb());
	}

	@Test
	public void testAreaBombMethods() {
		while (player.getCredits() < Attribute.AreaBombs.getCost()) {
			player.getPoints().update(PointGiver.MatchWon);
		}
		player.upgradeAttr(Attribute.AreaBombs, GameModeType.Match);
		assertTrue(player.canPlaceAreaBomb());
		player.increaseAreaBombsPlaced();
		assertEquals(player.getAreaBombsAvailable(), 0);
		assertFalse(player.canPlaceAreaBomb());
	}

	@Test
	public void testGetPermanentAttributes() {
		List<Attribute> attr = player.getPermanentAttributes();
		assertEquals(player.getPermanentAttributes(), attr);
	}

	@Test
	public void testGetSpeededStepSize() {
		double stepSize = player.getSpeededStepSize();
		while (player.getCredits() < Attribute.Speed.getCost()) {
			player.getPoints().update(PointGiver.MatchWon);
		}
		player.upgradeAttr(Attribute.Speed, GameModeType.Match);
		assertFalse(stepSize == player.getSpeededStepSize());
	}
	
	@After
	public void tearDown() throws Exception {
		player = null;
	}

}
