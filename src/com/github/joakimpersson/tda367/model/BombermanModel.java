package com.github.joakimpersson.tda367.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.bombs.AreaBomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.NormalBomb;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Box;
import com.github.joakimpersson.tda367.model.tiles.nonwalkable.Pillar;
import com.github.joakimpersson.tda367.model.tiles.walkable.Fire;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @Date 2012-03-27
 * @author Viktor Anderling
 * @modified Joakim Persson
 * 
 */
public class BombermanModel implements IBombermanModel {

	/**
	 * Timer-task that is used for scheduling what happens when the fire is
	 * removed.
	 */
	private class FireTimerTask extends TimerTask {
		@Override
		public void run() {
			removeFirstFire();
		}
	}

	/**
	 * Timer-task that is used for scheduling what happens when a bomb
	 * detonates.
	 */
	private class BombTask extends TimerTask {
		private Bomb bomb;

		public BombTask(Bomb b) {
			this.bomb = b;
		}

		@Override
		public void run() {
			handleFire(bomb.getPlayer(), bomb.explode(gameField.getMap()));
		}
	}

	private List<Player> players;
	private GameField gameField;
	private static BombermanModel instance = null;
	private LinkedList<Map<Position, Tile>> waitingFirePositions;

	/*
	 * This list contains, in the order of each explosion, what is supposed to
	 * be at the positions when the fire of that explosion is to be removed.
	 */

	private BombermanModel() {
		this.players = new ArrayList<Player>();
		this.gameField = new StandardMap();
		this.waitingFirePositions = new LinkedList<Map<Position, Tile>>();
	}

	/**
	 * @return The instance of this bombermanModel.
	 */
	public static BombermanModel getInstance() {
		if (instance == null) {
			instance = new BombermanModel();
		}
		return instance;
	}

	public void startGame() {
		// TODO what exactly does this do?
	}

	public void endGame() {
		// TODO what exactly does this do?
	}

	public void updateGame(Player player, PlayerAction action) {
		if (action == PlayerAction.PlaceBomb) {
			this.placeBomb(player);
		} else {
			this.move(player, action);
		}
	}

	/**
	 * This method upgrades the player for one match,
	 */
	public void upgradePlayer(Player player, Attribute attr) {
		player.upgradeAttr(attr, UpgradeType.Match);
	}

	private void matchEnd() {
		// TODO what exactly does this do?
	}

	private void turnEnd() {
		// TODO what exactly does this do?
	}

	private void move(Player player, PlayerAction action) {
		// TODO write algorithms for players 
	}

	/**
	 * This method tells a player to create a bomb, and starts a timer for that
	 * bomb.
	 * 
	 * @param player
	 *            The player that places the bomb.
	 */
	private void placeBomb(Player player) {
		if (player.canPlaceBomb()) {
			Timer bombTimer = new Timer();
			Bomb bomb = createBomb(player, bombTimer);// player.createBomb(bombTimer);
			bombTimer.schedule(new BombTask(bomb),
					Parameters.INSTANCE.getBombDetonationTime());
		}
	}

	// TODO changed location of createing the bomb from player to bombermodel
	private Bomb createBomb(Player player, Timer bombTimer) {

		switch (player.getAttribute(Attribute.BombType)) {
		case 1:
			return new AreaBomb(player, bombTimer);
		default:
			return new NormalBomb(player, bombTimer);
		}
	}

	/**
	 * This method takes care of what happens to the the positions that the fire
	 * strikes, including giving points and place the fire.
	 * 
	 * @param positions
	 *            The list that contains the positions of where the fire is to
	 *            be placed.
	 * @param bombOwner
	 *            The player that placed the bomb.
	 */
	private void handleFire(Player bombOwner, List<Position> positions) {
		distributePoints(bombOwner, positions);
		setFire(positions);
	}

	/**
	 * This method is called internally by handle fire to distribute points
	 * specifically.
	 * 
	 * @param positions
	 *            The list that contains the positions of where the fire
	 *            strikes.
	 * @param bombOwner
	 *            The player that placed the bomb.
	 */
	private void distributePoints(Player bombOwner, List<Position> positions) {
		List<PointGiver> pg = new ArrayList<PointGiver>();
		Tile tempTile;

		for (Position pos : positions) {
			// Converting positions into PointGivers
			for (Player player : players) {
				if (isPlayerAtPosition(player, pos)) {
					pg.add(PointGiver.PlayerHit);
					player.playerHit();
					if (!player.isAlive()) {
						pg.add(PointGiver.KillPlayer);
					}
				}
			}
			tempTile = gameField.getTile(pos);
			if (tempTile instanceof Box) {
				pg.add(PointGiver.Box);
			} else if (tempTile instanceof Pillar) {
				pg.add(PointGiver.Pillar);
			}
		}
		bombOwner.getPlayerPoints().update(pg);
	}

	/**
	 * This method is called internally by handle fire to set fire at positions
	 * for a fixed time specifically.
	 * 
	 * @param positions
	 *            The list that contains the positions of where the fire is to
	 *            be placed.
	 */
	private void setFire(List<Position> positions) {
		Map<Position, Tile> waitingTiles = new HashMap<Position, Tile>();
		for (Position firePos : positions) {
			waitingTiles.put(firePos, gameField.getTile(firePos).onFire());
			gameField.setTile(new Fire(), firePos);
		}
		waitingFirePositions.addLast(waitingTiles);
		Timer timer = new Timer();
		timer.schedule(new FireTimerTask(),
				Parameters.INSTANCE.getFireDuration());
	}

	/**
	 * This method is called when the fire is dying, witch causes it to be
	 * replaced by the appropriate new tile.
	 */
	private void removeFirstFire() {
		Map<Position, Tile> fireReplacers = waitingFirePositions.get(0);
		Iterator<Position> iterator = fireReplacers.keySet().iterator();
		Position temp;
		while (iterator.hasNext()) {
			temp = (Position) iterator.next();
			gameField.setTile(fireReplacers.get(temp), temp);
		}
		waitingFirePositions.removeFirst();
	}

	public List<Player> getPlayers() {
		return players;
	}

	private boolean isPlayerAtPosition(Player player, Position pos) {
		return player.getTilePosition().equals(pos);
	}
}