package com.github.joakimpersson.tda367.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.Direction;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.Parameters;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.constants.PointGiver;
import com.github.joakimpersson.tda367.model.constants.ResetType;
import com.github.joakimpersson.tda367.model.map.GameMap;
import com.github.joakimpersson.tda367.model.map.IGameMap;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.player.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.model.tiles.Destroyable;
import com.github.joakimpersson.tda367.model.tiles.Tile;
import com.github.joakimpersson.tda367.model.tiles.WalkableTile;
import com.github.joakimpersson.tda367.model.tiles.bombs.AreaBomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.Bomb;
import com.github.joakimpersson.tda367.model.tiles.bombs.NormalBomb;
import com.github.joakimpersson.tda367.model.tiles.walkable.Fire;
import com.github.joakimpersson.tda367.model.tiles.walkable.Floor;
import com.github.joakimpersson.tda367.model.utils.FPosition;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * 
 * @author Viktor Anderling
 * @modified Joakim Persson, Adrian Bjugård
 * 
 */
public class BombermanModel implements IBombermanModel {

	private List<Player> players;
	private IGameMap map;
	private static BombermanModel instance = null;
	private LinkedList<Map<Position, Tile>> waitingFirePositions;
	private PropertyChangeSupport pcs;

	private BombermanModel() {
		this.pcs = new PropertyChangeSupport(this);
		this.players = new ArrayList<Player>();
		this.map = new GameMap();
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

	@Override
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		this.pcs.addPropertyChangeListener(pcl);
	}

	@Override
	public void upgradePlayer(Player player, Attribute attr) {
		if (player.getCredits() >= attr.getCost()) {
			player.upgradeAttr(attr, UpgradeType.Match);

			player.useCredits(attr.getCost());
		}
	}

	@Override
	public void updateGame(Player player, PlayerAction action) {
		if (player.isAlive()) {
			Direction direction = Direction.NONE;
			switch (action) {
			case MoveUp:
				direction = Direction.NORTH;
				break;
			case MoveDown:
				direction = Direction.SOUTH;
				break;
			case MoveLeft:
				direction = Direction.WEST;
				break;
			case MoveRight:
				direction = Direction.EAST;
				break;
			case Action:
				this.placeBomb(player);
				break;
			default:
				break;
			}
			
			if (direction != Direction.NONE) 
				this.move(player, direction);
		}
	}

	/**
	 * This method moves the player in a double grid. If the tile at the chosen
	 * direction is walkable or the player will end up at least at the length of
	 * 0.2 from it, the player will move in that direction and action is taken
	 * if a new tile is entered. Else, nothing happens.
	 * 
	 * @param player
	 *            The player to move.
	 * @param direction
	 *            The direction for the player to move
	 */
	private void move(Player player, Direction direction) {
		Position prevPos = player.getTilePosition();
		Tile tileAtDirection = map.getTile(new Position(prevPos.getX()
				+ (int) direction.getX(), prevPos.getY()
				+ (int) direction.getY()));
		// The tile that the player may walk into.

		if (tileAtDirection.isWalkable()) {
			player.move(direction);
		} else {
			FPosition decimalPos = player.getGamePosition();
			// Removes the integer part of the players position, leaving only
			// the decimal part.
			decimalPos = new FPosition(decimalPos.getX()
					- (int) decimalPos.getX(), decimalPos.getY()
					- (int) decimalPos.getY());

			double stepSize = Parameters.INSTANCE.getPlayerStepSize();
			double xStep = stepSize * direction.getX();
			double yStep = stepSize * direction.getY();
			// Adding the steps to the player's new position.
			decimalPos = new FPosition((float) (decimalPos.getX() + xStep),
					(float) (decimalPos.getY() + yStep));

			// Can't move closer than 0.2 to a non-walkable tile.
			float pD = 0.2F;
			if ((direction == Direction.NORTH && decimalPos.getY() >= pD)
					|| (direction == Direction.SOUTH && decimalPos.getY() <= 1 - pD)
					|| (direction == Direction.WEST && decimalPos.getX() >= pD)
					|| (direction == Direction.EAST && decimalPos.getX() <= 1 - pD)) {
				player.move(direction);
			}
		}

		Position newPos = player.getTilePosition();

		// Handles what happens if the player walks into a new tile.
		if (!prevPos.equals(newPos)) {
			WalkableTile enteredTile = (WalkableTile) map.getTile(newPos);
			map.setTile(enteredTile.playerEnter(player), newPos);
		}
	}

	/**
	 * This method tells a player to create a bomb, and starts a timer for that
	 * bomb.
	 * 
	 * @param player
	 *            The player that places the bomb.
	 */
	private void placeBomb(Player player) {
		if (player.canPlaceBomb()
				&& map.getTile(player.getTilePosition()) instanceof Floor) {
			Timer bombTimer = new Timer();
			Bomb bomb = createBomb(player, bombTimer);// player.createBomb(bombTimer);
			bombTimer.schedule(new BombTask(bomb),
					Parameters.INSTANCE.getBombDetonationTime());

			map.setTile(bomb, player.getTilePosition());

			pcs.firePropertyChange("play", null, EventType.BOMB_PLACED);
		}
	}

	// TODO refactor
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
	private void handleFire(Player bombOwner,
			Map<Position, Direction> directedFirePositions) {
		List<Position> list = new ArrayList<Position>(
				directedFirePositions.keySet());
		distributePoints(bombOwner, list);
		setFire(directedFirePositions);
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
		Tile tmpTile;

		for (Position pos : positions) {
			// Converting positions into PointGivers
			for (Player player : players) {
				if (isPlayerAtPosition(player, pos) && !player.isImmortal()
						&& !bombOwner.equals(player)) {
					if (player.isAlive()) {
						pg.add(PointGiver.PlayerHit);
						player.playerHit();
					} else {
						pg.add(PointGiver.KillPlayer);
					}
				}
			}

			tmpTile = map.getTile(pos);
			if (tmpTile instanceof Destroyable) {

				if (tmpTile instanceof Bomb) {
					Bomb bomb = (Bomb) tmpTile;
					Player p = bomb.getPlayer();
					if (!bombOwner.equals(p)) {
						// TODO jocke really bad code...
						Destroyable destroyableTile = (Destroyable) tmpTile;

						pg.add(destroyableTile.getPointGiver());
					}
				} else {

					Destroyable destroyableTile = (Destroyable) tmpTile;

					pg.add(destroyableTile.getPointGiver());
				}
			}
		}
		bombOwner.updatePlayerPoints(pg);
	}

	/**
	 * This method is called internally by handle fire to set fire at positions
	 * for a fixed time specifically.
	 * 
	 * @param positions
	 *            The list that contains the positions of where the fire is to
	 *            be placed.
	 */
	private void setFire(Map<Position, Direction> directedFire) {
		Map<Position, Tile> waitingTiles = new HashMap<Position, Tile>();

		Set<Map.Entry<Position, Direction>> entries = directedFire.entrySet();
		Iterator<Map.Entry<Position, Direction>> iter = entries.iterator();

		while (iter.hasNext()) {
			Map.Entry<Position, Direction> entry = iter.next();
			Position pos = entry.getKey();
			Direction direction = entry.getValue();

			Tile currentTile = map.getTile(pos);
			if (currentTile instanceof Destroyable) {

				Destroyable destroyableTile = (Destroyable) currentTile;
				Tile newTile = destroyableTile.onFire();
				waitingTiles.put(pos, newTile);
				map.setTile(new Fire(direction), pos);
			}
		}

		waitingFirePositions.addLast(waitingTiles);
		Timer timer = new Timer();
		int fireDuration = Parameters.INSTANCE.getFireDuration();
		timer.schedule(new FireTimerTask(), fireDuration);
	}

	/**
	 * This method is called when the fire is dying, witch causes it to be
	 * replaced by the appropriate new tile.
	 */
	private void removeFirstFire() {

		if (waitingFirePositions != null && waitingFirePositions.size() > 0) {
			Map<Position, Tile> fireReplacers = waitingFirePositions.get(0);

			Set<Map.Entry<Position, Tile>> entries = fireReplacers.entrySet();
			Iterator<Map.Entry<Position, Tile>> iter = entries.iterator();
			while (iter.hasNext()) {
				Map.Entry<Position, Tile> entry = iter.next();
				Position pos = entry.getKey();
				Tile tile = entry.getValue();
				map.setTile(tile, pos);
			}
			waitingFirePositions.removeFirst();
		}
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public Tile[][] getMap() {
		return map.getMap();
	}

	@Override
	public boolean isRoundOver() {
		int aliveCount = 0;
		for (Player p : players) {
			if (p.isAlive()) {
				aliveCount++;
			}
		}
		if (aliveCount <= 1) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isGameOver() {
		// TODO add implementation/support
		return false;
	}

	@Override
	public boolean isMatchOver() {
		// TODO add implementation/support
		return true;
	}

	@Override
	public void reset(ResetType type) {

		switch (type) {
		case Match:
			matchReset();
			break;
		case Round:
			roundReset();
			break;
		default:
			// Should not happen and therefore we do nothing TODO perhaps
			break;
		}
		resetMap();
	}

	private void resetPlayer(ResetType type) {
		for (Player p : players) {
			p.reset(type);
		}
	}

	private void matchReset() {
		resetPlayer(ResetType.Match);
	}

	private void roundReset() {
		resetPlayer(ResetType.Round);
	}

	private void resetMap() {
		// In order to avoid that tiles are cleared on the new map
		waitingFirePositions.clear();
		map.reset();
	}

	private boolean isPlayerAtPosition(Player player, Position pos) {
		return player.getTilePosition().equals(pos);
	}

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
			handleFire(bomb.getPlayer(), bomb.explode(map.getMap()));

			pcs.firePropertyChange("play", null, EventType.BOMB_EXPLODED);
		}
	}

}