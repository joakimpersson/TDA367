package com.github.joakimpersson.tda367.model;

import java.util.List;

import com.github.joakimpersson.tda367.model.constants.BombermanRules;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameController {

	private List<Player> players;
	private int maxRoundsWon;
	private int maxMatchesWon;
	private int matchID;

	/**
	 * Create a new GameController instance with this games active players
	 * 
	 * @param playersList
	 *            A list of the active players in the current game
	 */
	public GameController(List<Player> playersList) {
		this.players = playersList;
		this.maxMatchesWon = BombermanRules.INSTANCE.getNumberOfMatches();
		this.maxRoundsWon = BombermanRules.INSTANCE.getNumberOfRounds();
		this.matchID = 1;
	}

	/**
	 * Is the current round over. The round is over when all players but one has
	 * died
	 * 
	 * @return if the round is over
	 */
	public boolean isRoundOver() {

		int alivePlayers = 0;
		for (Player p : players) {

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
		p.updatePlayerPoints(PointGiver.RoundWon);
	}

	/**
	 * Get how many rounds a player has win in the current match
	 * 
	 * @param player
	 *            The player who round wins you want
	 * @return How many rounds the player has win
	 */
	public int getRoundsWin(Player player) {
		int totalRoundsWin = player.getDestroyedPointGiver(PointGiver.RoundWon);
		int modFactor = maxRoundsWon * matchID;

		if (totalRoundsWin != 0 && (totalRoundsWin % (modFactor) == 0)) {
			return maxRoundsWon;
		}

		return totalRoundsWin % modFactor;
	}

	/**
	 * A method that gets the current round winner by looking at the one who is
	 * last man standing
	 * 
	 * @return The player who wins the current round
	 */
	private Player getRoundWinner() {
		Player roundWinner = null;
		for (Player p : players) {
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
		for (Player player : players) {
			int roundsWon = getRoundsWin(player);

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
		matchID++;
	}

	/**
	 * Get how many matches a player has win in the current game
	 * 
	 * @param player
	 *            The player whose matches win you want
	 * @return How many matches a player has won
	 */
	public int getMatchsWon(Player player) {
		return player.getDestroyedPointGiver(PointGiver.MatchWon);
	}

	/**
	 * Get the match winner from the recent match
	 * 
	 * @return The player who win the last match
	 */
	private Player getMatchWinner() {
		Player matchWinner = null;
		for (Player player : players) {
			int roundsWon = getRoundsWin(player);

			if (roundsWon == maxRoundsWon) {
				matchWinner = player;
			}
		}
		return matchWinner;
	}

	/**
	 * Get the status of the game
	 * 
	 * @return If the game if over or not
	 */
	public boolean isGameOver() {
		for (Player player : players) {
			int matchesWon = player.getDestroyedPointGiver(PointGiver.MatchWon);
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
		Player player = getGameWinner();
		player.updatePlayerPoints(PointGiver.GameWon);
		matchID++;
	}

	/**
	 * Get the winning player of the game
	 * 
	 * @return The player who is the winner of the game
	 */
	private Player getGameWinner() {
		Player gameWinner = null;

		for (Player player : players) {
			int matchesWon = getMatchsWon(player);
			if (matchesWon == maxMatchesWon) {
				gameWinner = player;
			}
		}

		return gameWinner;
	}
}
