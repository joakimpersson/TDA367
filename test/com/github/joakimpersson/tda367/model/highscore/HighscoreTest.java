package com.github.joakimpersson.tda367.model.highscore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.highscore.Highscore;
import com.github.joakimpersson.tda367.model.highscore.Score;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.positions.Position;

public class HighscoreTest {

	private Highscore hs;

	@Before
	public void setUp() throws Exception {
		hs = new Highscore();
	}

	@Test
	public void testUpdate() {
		int size = 5;
		List<Player> players = createListOfPlayer(size);

		updatePlayersScore(players);

		hs.update(players);

		List<Score> expected = simulateHighScore(players);
		List<Score> actual = hs.getList();

		assertEquals(expected.size(), actual.size());

		assertEquals(expected, actual);
	}

	@Test
	public void testListTrimming() {
		int listMaxSize = Parameters.INSTANCE.getHighscoreMaxSize();
		int size = listMaxSize + 1;
		List<Player> players = createListOfPlayer(size);

		updatePlayersScore(players);

		hs.update(players);

		List<Score> expected = simulateHighScore(players);
		List<Score> actual = hs.getList();
		// Since the expected list is one score object bigger than the expected
		// the last entry should not be in the highscore list
		Score expectedMissingPlayer = expected.remove(size - 1);
		assertFalse(actual.contains(expectedMissingPlayer));

		assertEquals(expected.size(), actual.size());

		assertEquals(expected, actual);

	}

	@Test
	public void testGetList() {

		int size = 2;
		List<Player> players = createListOfPlayer(size);
		List<String> playerNames = new ArrayList<String>();

		for (Player p : players) {
			playerNames.add(p.getName());
		}

		hs.update(players);

		assertEquals(size, hs.getSize());

		for (Score score : hs.getList()) {
			String tmpPlayerName = score.getPlayerName();
			assertTrue(playerNames.contains(tmpPlayerName));
		}
		fail("Not sure about implementation");
	}

	@Test
	public void testGetSize() {
		int size = 5;
		List<Player> players = createListOfPlayer(size);

		assertTrue(hs.getSize() == 0);

		hs.update(players);

		assertEquals(size, hs.getSize());
	}

	@Test
	public void testReset() {
		int size = 5;
		List<Player> players = createListOfPlayer(size);

		hs.update(players);

		assertTrue(hs.getSize() > 0);

		hs.reset();

		assertTrue(hs.getSize() == 0);
	}

	@After
	public void tearDown() throws Exception {
		hs.reset();
		hs = null;
	}

	/**
	 * Creates a list of default instantiated players
	 * 
	 * @param size
	 *            The size of the list
	 * @return A list of players
	 */
	private List<Player> createListOfPlayer(int size) {
		List<Player> players = new ArrayList<Player>();

		Position defaultPos = new Position(0, 0);

		for (int i = 0; i < size; i++) {
			Player p = new Player(i, "Hobbe-" + i, defaultPos);
			players.add(p);
		}

		return players;
	}

	/**
	 * Updates a list of players individual scores and the player that is last
	 * in the list will have the highest score
	 * 
	 * @param players
	 *            A list of players who's score will be increased
	 */
	private void updatePlayersScore(List<Player> players) {
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			updatePlayerScore(player, i + 1);
		}
	}

	/**
	 * Updates every players score and as higher the iterations parameter is the
	 * higher the players score will be
	 * 
	 * @param player
	 *            The player to get his score increased
	 * @param iterations
	 *            How many times the players score shall be updated
	 */
	private void updatePlayerScore(Player player, int iterations) {
		List<PointGiver> pointGivers = new ArrayList<PointGiver>();
		pointGivers.add(PointGiver.PlayerHit);
		pointGivers.add(PointGiver.Pillar);
		pointGivers.add(PointGiver.RoundWon);

		for (int i = 0; i < iterations; i++) {

		}
	}

	/**
	 * Creates a simulated list that has the same score objects and is sorted in
	 * the right order as the HighScrore list is expected to look like
	 * 
	 * @param players
	 *            A list of players to be included in the list
	 * @return A list that resembles what the HighScore list should look like
	 */
	private List<Score> simulateHighScore(List<Player> players) {
		List<Score> simulatedHighScoreList = new ArrayList<Score>();

		// The updatePlayersScore modifies the players to make the last player
		// have the highest score and second to last to have the second to
		// highest
		Collections.reverse(players);

		for (Player p : players) {
			Score tmpScore = new Score(p.getName(), p.getPoints());
			simulatedHighScoreList.add(tmpScore);
		}

		return simulatedHighScoreList;
	}
}
