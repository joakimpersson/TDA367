package com.github.joakimpersson.tda367.model.gamelogic;

import com.github.joakimpersson.tda367.model.player.Player;

/**
 * An interface containing the logic about the round ending, matchwinner etc.
 * 
 * @author adderollen
 * 
 */
public interface IGameLogic {

	/**
	 * Is the current round over. 
	 * 
	 * @return if the round is over
	 */
	boolean isRoundOver();

	/**
	 * Increment the winning players number of rounds win and update his
	 * PlayerPoints object
	 */
	void roundOver();

	/**
	 * Get how many rounds a player has win in the current match
	 * 
	 * @param player
	 *            The player who round wins you want
	 * @return How many rounds the player has win
	 */
	int getRoundsWon(Player player);

	/**
	 * Gets the current match status if it is over or not. The match is over
	 * when one player reaches the maxRoundWin limit
	 * 
	 * @return If the Match is over or not
	 */
	boolean isMatchOver();

	/**
	 * Handling the actions for when the match is over and makes sure that the
	 * game is properly updated
	 */
	void matchOver();

	/**
	 * Reset all the players amount of rounds won to zero
	 */
	void resetRoundStats();

	/**
	 * Get how many matches a player has win in the current game
	 * 
	 * @param player
	 *            The player whose matches win you want
	 * @return How many matches a player has won
	 */
	int getMatchsWon(Player player);

	/**
	 * Get the status of the game
	 * 
	 * @return If the game if over or not
	 */
	boolean isGameOver();

	/**
	 * Perform actions corresponding to that the game is over
	 */
	void gameOver();

	/**
	 * A method that gets the current round winner by looking at the one who is
	 * last man standing
	 * 
	 * @return The player who wins the current round or null of there is no
	 *         winner
	 */
	Player getLastRoundWinner();

}