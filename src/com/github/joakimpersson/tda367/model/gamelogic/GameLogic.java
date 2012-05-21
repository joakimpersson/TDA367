package com.github.joakimpersson.tda367.model.gamelogic;

import java.util.List;

import com.github.joakimpersson.tda367.model.constants.BombermanRules;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * A class that handle all logic about when a round, match or game is over and
 * not.
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class GameLogic implements IGameLogic {

	private List<Player> players;
	private Player lastRoundWinner;
	private int maxRoundsWon;
	private int maxMatchesWon;
	private boolean noWinner;

	/**
	 * Create a new GameController instance with this games active players
	 * 
	 * @param playersList
	 *            A list of the active players in the current game
	 */
	public GameLogic(List<Player> playersList) {
		this.players = playersList;
		this.maxMatchesWon = BombermanRules.INSTANCE.getNumberOfMatches();
		this.maxRoundsWon = BombermanRules.INSTANCE.getNumberOfRounds();
	}

	/**
	 * The round is over when all players but one has died.
	 */
	@Override
	public boolean isRoundOver() {

		int alivePlayers = 0;
		for (Player player : players) {

			if (player.isAlive()) {
				alivePlayers++;
			}
		}

		if (alivePlayers == 0) {
			noWinner = true;
			return true;
		}

		return alivePlayers == 1;
	}

	@Override
	public void roundOver() {

		if (!noWinner) {

			Player player = getRoundWinner();
			lastRoundWinner = player;
			player.updatePlayerPoints(PointGiver.RoundWon);
			player.roundWon();

		} else {
			lastRoundWinner = null;
		}
	}

	@Override
	public int getRoundsWon(Player player) {
		return player.getRoundsWon();
	}

	private Player getRoundWinner() {
		Player roundWinner = null;
		for (Player player : players) {
			if (player.isAlive()) {
				roundWinner = player;
			}
		}
		return roundWinner;
	}

	@Override
	public Player getLastRoundWinner() {
		return lastRoundWinner;
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
		Player player = getMatchWinner();
		player.updatePlayerPoints(PointGiver.MatchWon);
		player.matchWon();
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
