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
public class GameController implements IGameController {

	private List<Player> players;
	private int maxRoundsWon;
	private int maxMatchesWon;

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
	}

	@Override
	public boolean isRoundOver() {

		int alivePlayers = 0;
		for (Player p : players) {

			if (p.isAlive()) {
				alivePlayers++;
			}
		}
		return alivePlayers == 1;
	}

	@Override
	public void roundOver() {
		Player p = getRoundWinner();
		p.updatePlayerPoints(PointGiver.RoundWon);
		p.roundWon();
	}

	@Override
	public int getRoundsWon(Player player) {
		return player.getRoundsWon();
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

	@Override
	public boolean isMatchOver() {
		for (Player player : players) {
			int roundsWon = getRoundsWon(player);

			if (roundsWon == maxRoundsWon) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void matchOver() {
		Player p = getMatchWinner();
		p.updatePlayerPoints(PointGiver.MatchWon);
		p.matchWon();
	}

	@Override
	public void resetRoundStats() {
		for (Player player : players) {
			player.resetRoundsWon();
		}
	}

	@Override
	public int getMatchsWon(Player player) {
		return player.getMatchesWon();
	}

	/**
	 * Get the match winner from the recent match
	 * 
	 * @return The player who win the last match
	 */
	private Player getMatchWinner() {
		Player matchWinner = null;
		for (Player player : players) {
			int roundsWon = getRoundsWon(player);

			if (roundsWon == maxRoundsWon) {
				matchWinner = player;
			}
		}
		return matchWinner;
	}

	@Override
	public boolean isGameOver() {
		for (Player player : players) {
			int matchesWon = player.getMatchesWon();
			if (matchesWon == maxMatchesWon) {
				return true;
			}
		}
		return false;

	}

	@Override
	public void gameOver() {
		Player player = getGameWinner();
		player.updatePlayerPoints(PointGiver.GameWon);
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
