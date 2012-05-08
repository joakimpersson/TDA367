package com.github.joakimpersson.tda367.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.BombermanRules;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.constants.ResetType;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameControllerTest {

	private List<Player> players;
	private GameController gameController;

	@Before
	public void setUp() throws Exception {
		players = new ArrayList<Player>();
		players.add(new Player(0, "Kalle", new Position(0, 0)));
		players.add(new Player(1, "Hobbe", new Position(0, 0)));
		gameController = new GameController(players);
	}

	@Test
	public void testIsRoundOver() {
		// shold be false
		assertFalse(gameController.isRoundOver());

		// kill one of the players
		killPlayer(players.get(0));
		// should now be true
		assertTrue(gameController.isRoundOver());
	}

	private void killPlayer(Player p) {
		while (p.isAlive()) {
			p.playerHit();
		}
	}

	@Test
	public void testRoundOver() {
		// first kill one of the player to simulate round over
		killPlayer(players.get(0));

		// the winner should be player2
		Player winningPlayer = players.get(1);
		gameController.roundOver();

		int actual = winningPlayer.getDestroyedPointGiver(PointGiver.RoundWon);
		int expected = 1;
		// he should now have 1 in roundwon
		assertEquals(actual, expected);

		// his number of rounds win in the GameController object should be 1
		actual = gameController.getRoundsWin(winningPlayer);
		expected = 1;
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRoundsWin() {
		// first kill one of the player to simulate round over
		killPlayer(players.get(0));

		// the round is over
		gameController.roundOver();

		// the winner shold be player2
		Player winningPlayer = players.get(1);

		// his number of rounds win should be 1
		int actual = gameController.getRoundsWin(winningPlayer);
		int expected = 1;
		assertEquals(expected, actual);
	}

	@Test
	public void testIsMatchOver() {
		Player losingPlayer = players.get(0);

		// should be false
		assertFalse(gameController.isMatchOver());

		simulateMatchOver(losingPlayer);

		// should be true
		assertTrue(gameController.isMatchOver());
	}

	private void simulateMatchOver(Player losingPlayer) {
		int maxRounds = BombermanRules.INSTANCE.getNumberOfRounds();

		for (int i = 0; i < maxRounds; i++) {
			killPlayer(losingPlayer);
			gameController.roundOver();
			losingPlayer.reset(ResetType.Round);
		}
	}

	@Test
	public void testMatchOver() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);
		simulateMatchOver(losingPlayer);

		gameController.matchOver();
		// he's MatchWon in PlayerPoints should be equal to 1
		int expected = 1;
		int actual = winningPlayer.getDestroyedPointGiver(PointGiver.MatchWon);
		assertEquals(expected, actual);

		// the roundmap should also be reseted
		expected = 0;
		for (Player player : players) {
			actual = gameController.getRoundsWin(player);
			assertEquals(expected, actual);
		}
	}

	@Test
	public void testIsGameOver() {
		Player losingPlayer = players.get(0);

		// should be false
		assertFalse(gameController.isGameOver());

		simulateGameOver(losingPlayer);

		// should be true
		assertTrue(gameController.isGameOver());
	}

	private void simulateGameOver(Player losingPlayer) {
		int maxMatchesWon = BombermanRules.INSTANCE.getNumberOfMatches();
		for (int i = 0; i < maxMatchesWon; i++) {
			simulateMatchOver(losingPlayer);
			gameController.matchOver();
			losingPlayer.reset(ResetType.Match);
		}
	}

	@Test
	public void testGameOver() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);

		simulateGameOver(losingPlayer);

		gameController.gameOver();

		// winningPlayers GameWon in PlayerPoints should be equal to 1
		int expected = 1;
		int actual = winningPlayer.getDestroyedPointGiver(PointGiver.GameWon);
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		players = null;
		gameController = null;
	}
}