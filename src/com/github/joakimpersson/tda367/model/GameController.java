package com.github.joakimpersson.tda367.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.joakimpersson.tda367.model.constants.BombermanRules;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameController {

	private Map<Player, Integer> players;
	private int maxRoundsWon;
	private int maxMatchesWon;

	/**
	 * Create a new GameController instance with this games active players
	 * 
	 * @param players
	 *            A list of the active players in the current game
	 */
	public GameController(List<Player> players) {
		this.players = new HashMap<Player, Integer>();
		int roundsWon = 0;
		for (Player p : players) {
			this.players.put(p, roundsWon);
		}
		maxMatchesWon = BombermanRules.INSTANCE.getNumberOfMatches();
		maxRoundsWon = BombermanRules.INSTANCE.getNumberOfRounds();
	}

	/**
	 * Is the current round over. The round is over when all players but one has
	 * died
	 * 
	 * @return Of the round is over
	 */
	public boolean isRoundOver() {

		int alivePlayers = 0;
		for (Player p : players.keySet()) {

			if (p.isAlive()) {
				alivePlayers++;
			}
		}
		return alivePlayers == 1;
	}

	/**
	 * Increment the winning players number of rounds win and update his
	 * PlayerPoints object
	 */
	public void roundOver() {
		Player p = getRoundWinner();
		int roundsWon = players.get(p);
		players.put(p, roundsWon + 1);
		p.updatePlayerPoints(PointGiver.RoundWon);
	}

	/**
	 * Get how many rounds one player has win
	 * 
	 * @param player
	 *            The player who round wins you want
	 * @return How many rounds the player has win
	 */
	public int getRoundsWin(Player player) {
		return players.get(player);
	}

	/**
	 * A method that gets the current round winner by looking at the one who is
	 * last man standing
	 * 
	 * @return The player who wins the current round
	 */
	private Player getRoundWinner() {
		Player roundWinner = null;
		for (Player p : players.keySet()) {
			if (p.isAlive()) {
				roundWinner = p;
			}
		}
		return roundWinner;
	}

	/**
	 * Gets the current match status if it is over or not. The match is over
	 * when one player reaches the maxRoundWin limit
	 * 
	 * @return If the Match is over or not
	 */
	public boolean isMatchOver() {
		for (Player p : players.keySet()) {
			int roundsWon = players.get(p);

			if (roundsWon == maxRoundsWon) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Handling the actions for when the match is over and makes sure that the
	 * game is properly updated
	 */
	public void matchOver() {
		Player p = getMatchWinner();
		p.updatePlayerPoints(PointGiver.MatchWon);
		resetRoundMap();
	}

	/**
	 * Get the match winner from the recent match
	 * 
	 * @return The player who win the last match
	 */
	private Player getMatchWinner() {
		Player matchWinner = null;
		for (Player p : players.keySet()) {
			int roundsWon = players.get(p);

			if (roundsWon == maxRoundsWon) {
				matchWinner = p;
			}
		}
		return matchWinner;
	}

	/**
	 * Resets the Map responsible for storing how many rounds every player has
	 * win
	 */
	private void resetRoundMap() {
		int newValue = 0;
		for (Player p : players.keySet()) {
			players.put(p, newValue);
		}

	}

	/**
	 * Get the status of the game
	 * 
	 * @return If the game if over or not
	 */
	public boolean isGameOver() {
		for (Player p : players.keySet()) {
			int matchesWon = p.getDestroyedPointGiver(PointGiver.MatchWon);
			if (matchesWon == maxMatchesWon) {
				return true;
			}
		}
		return false;

	}

	/**
	 * Perform actions corresponding to that the game is over
	 */
	public void gameOver() {
		Player p = getGameWinner();
		p.updatePlayerPoints(PointGiver.GameWon);
	}

	/**
	 * Get the winning player of the game
	 * 
	 * @return The player who is the winner of the game
	 */
	private Player getGameWinner() {
		Player gameWinner = null;

		for (Player p : players.keySet()) {
			int matchesWon = p.getDestroyedPointGiver(PointGiver.MatchWon);
			if (matchesWon == maxMatchesWon) {
				gameWinner = p;
			}
		}

		return gameWinner;
	}
}
