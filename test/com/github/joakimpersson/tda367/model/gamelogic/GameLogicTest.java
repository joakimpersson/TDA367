package com.github.joakimpersson.tda367.model.gamelogic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.PyromaniacRules;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.constants.GameModeType;
import com.github.joakimpersson.tda367.model.gamelogic.GameLogic;
import com.github.joakimpersson.tda367.model.gamelogic.IGameLogic;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;

/**
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class GameLogicTest {

	private List<Player> players;
	private IGameLogic gameLogic;

	@Before
	public void setUp() throws Exception {
		players = new ArrayList<Player>();
		players.add(new Player(0, "Kalle", new Position(0, 0)));
		players.add(new Player(1, "Hobbe", new Position(10, 0)));
		gameLogic = new GameLogic(players);
	}

	@Test
	public void testIsRoundOver() {
		// should be false
		assertFalse(gameLogic.isRoundOver());

		// kill one of the players
		killPlayer(players.get(0));
		// should now be true
		assertTrue(gameLogic.isRoundOver());
	}

	/**
	 * Util method used to kill a player.
	 * 
	 * @param player
	 *            A Player that will be killed if alive.
	 */
	private void killPlayer(Player player) {
		while (player.isAlive()) {
			player.playerHit();
		}
	}

	@Test
	public void testRoundOver() {
		// first kill one of the player to simulate round over
		killPlayer(players.get(0));

		// the winner should be player2
		Player winningPlayer = players.get(1);
		gameLogic.roundOver();

		int actual = winningPlayer.getRoundsWon();
		int expected = 1;
		// he should now have 1 in roundwon
		assertEquals(actual, expected);

		// his number of rounds win in the GameController object should be 1
		actual = gameLogic.getRoundsWon(winningPlayer);
		expected = 1;
		assertEquals(expected, actual);

	}

	@Test
	public void testGetRoundsWin() {
		Player losingPlayer = players.get(0);
		// the winner should be player2
		Player winningPlayer = players.get(1);

		// first kill one of the player to simulate round over
		killPlayer(losingPlayer);

		// the round is over
		gameLogic.roundOver();

		// his number of rounds win should be 1
		int actual = gameLogic.getRoundsWon(winningPlayer);
		int expected = 1;
		assertEquals(expected, actual);

		actual = gameLogic.getRoundsWon(losingPlayer);
		expected = 0;
		assertEquals(actual, expected);
	}

	@Test
	public void testGetLastRoundWinner() {
		Player losingPlayer = players.get(0);
		// the winner should be player2
		Player winningPlayer = players.get(1);

		// first kill one of the player to simulate round over
		killPlayer(losingPlayer);

		// the round is over
		gameLogic.roundOver();

		// the winning player should be player 2
		Player actual = gameLogic.getLastRoundWinner();
		assertEquals(winningPlayer, actual);
	}

	@Test
	public void testPlayersDieTogheter() {
		Player losingPlayerOne = players.get(0);
		Player losingPlayerTwo = players.get(1);

		// kill both the players
		killPlayer(losingPlayerOne);
		killPlayer(losingPlayerTwo);

		// the round should be over
		assertTrue(gameLogic.isRoundOver());

		gameLogic.roundOver();

		// the winning player should be null
		assertNull(gameLogic.getLastRoundWinner());

		losingPlayerOne.reset(GameModeType.Round);
		losingPlayerTwo.reset(GameModeType.Round);

		killPlayer(losingPlayerTwo);

		// the round should be over
		assertTrue(gameLogic.isRoundOver());
		gameLogic.roundOver();

		assertTrue(gameLogic.getLastRoundWinner().equals(losingPlayerOne));
	}

	@Test
	public void testResetRoundStats() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);

		killPlayer(losingPlayer);
		gameLogic.roundOver();

		assertEquals(0, losingPlayer.getRoundsWon());
		assertEquals(1, winningPlayer.getRoundsWon());

		gameLogic.resetRoundStats();

		assertEquals(0, losingPlayer.getRoundsWon());
		assertEquals(0, winningPlayer.getRoundsWon());
	}

	@Test
	public void testIsMatchOver() {
		Player losingPlayer = players.get(0);

		// should be false
		assertFalse(gameLogic.isMatchOver());

		simulateMatchOver(losingPlayer);

		// should be true
		assertTrue(gameLogic.isMatchOver());

	}

	/**
	 * Simulates a match ending.
	 * 
	 * @param losingPlayer
	 *            The Player that loses the match.
	 */
	private void simulateMatchOver(Player losingPlayer) {
		int maxRounds = PyromaniacRules.INSTANCE.getNumberOfRounds();

		for (int i = 0; i < maxRounds; i++) {
			killPlayer(losingPlayer);
			gameLogic.roundOver();
			losingPlayer.reset(GameModeType.Round);
		}
	}

	@Test
	public void testMatchOver() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);
		simulateMatchOver(losingPlayer);

		gameLogic.matchOver();
		// he's MatchWon in PlayerPoints should be equal to 1
		int expected = 1;
		int actual = winningPlayer.getMatchesWon();
		assertEquals(expected, actual);

	}

	@Test
	public void tesetgetMatchesWon() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);

		simulateMatchOver(losingPlayer);
		gameLogic.matchOver();

		assertEquals(0, losingPlayer.getMatchesWon());
		assertEquals(1, winningPlayer.getMatchesWon());
	}

	@Test
	public void testIsGameOver() {
		Player losingPlayer = players.get(0);

		// should be false
		assertFalse(gameLogic.isGameOver());

		simulateGameOver(losingPlayer);

		// should be true
		assertTrue(gameLogic.isGameOver());
	}

	/**
	 * Simulates a game ending.
	 * 
	 * @param losingPlayer
	 *            The Player that loses the game.
	 */
	private void simulateGameOver(Player losingPlayer) {
		int maxMatchesWon = PyromaniacRules.INSTANCE.getNumberOfMatches();
		for (int i = 0; i < maxMatchesWon; i++) {
			simulateMatchOver(losingPlayer);
			gameLogic.matchOver();
			losingPlayer.reset(GameModeType.Match);
			gameLogic.resetRoundStats();
		}
	}

	@Test
	public void testGameOver() {
		Player losingPlayer = players.get(0);
		Player winningPlayer = players.get(1);

		simulateGameOver(losingPlayer);

		gameLogic.gameOver();

		// winningPlayers GameWon in PlayerPoints should be equal to 1
		int expected = 1;
		int actual = winningPlayer.getEarnedPointGiver(PointGiver.GameWon);
		assertEquals(expected, actual);
	}

	@After
	public void tearDown() throws Exception {
		players = null;
		gameLogic = null;
	}
}
