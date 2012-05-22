package com.github.joakimpersson.tda367.model.tiles.factory;

import java.util.Timer;

import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.tiles.bombs.AreaBomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.NormalBomb;

/**
 * A factory class for creating different kind of bombs
 * 
 * @author joakimpersson
 * 
 */
public class BombFactory {

	/**
	 * Creates a new bomb, the type is determined by the string bombType
	 * 
	 * @param player
	 *            The owner of the bomb
	 * @param timer
	 *            The bombs timer
	 * @param bombType
	 *            What kind of bomb
	 * @return A new bomb
	 */
	public Bomb createBomb(Player player, Timer timer, String bombType) {
		Bomb bomb = null;
		if (bombType.equals("AreaBomb")) {
			bomb = new AreaBomb(player, timer);
		} else if (bombType.equals("NormalBomb")) {
			bomb = new NormalBomb(player, timer);
		}
		return bomb;
	}
}
