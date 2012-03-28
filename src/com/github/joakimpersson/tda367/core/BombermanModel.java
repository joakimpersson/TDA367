package com.github.joakimpersson.tda367.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


import com.github.joakimpersson.tda367.core.PlayerAttributes.UpgradeType;
import com.github.joakimpersson.tda367.core.PlayerPoints.PointGiver;
import com.github.joakimpersson.tda367.core.bombs.Bomb;
import com.github.joakimpersson.tda367.core.tiles.*;

/**
 * 
 * @Date 2012-03-27
 * @author Viktor Anderling
 * 
 */
public class BombermanModel implements IBombermanModel {

	private class FireTimerTask extends TimerTask {
		
		@Override
		public void run() {
			removeFirstFire();
		}
	}
	public class BombTask extends TimerTask {
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

	private BombermanModel() {
		this.players = new ArrayList<Player>();
		this.gameField = new StandardMap();
		this.waitingFirePositions = new LinkedList<Map<Position, Tile>>();
	}
	
	public static BombermanModel getInstance() {
		if(instance == null) {
			instance = new BombermanModel();
		}
		return instance;
	}
	
	public void startGame() {

	}

	public void endGame() {

	}

	public void updateGame(Player player, PlayerAction action) {
		if (action == PlayerAction.PlaceBomb) {
			this.placeBomb(player);
		} else {
			this.move(player, action);
		}
	}

	public void upgradePlayer(Player player, Attribute attr) {
		player.upgradeAttr(attr, UpgradeType.Match);
	}

	private void matchEnd() {

	}

	private void turnEnd() {

	}

	private void move(Player player, PlayerAction action) {
		
	}

	private void placeBomb(Player player) {
		Timer bombTimer= new Timer();		
		Bomb bomb = player.createBomb(bombTimer);
		bombTimer.schedule(new BombTask(bomb), Parameters.INSTANCE.getBombDetonationTime());
	}
	
	private void setFire(List<Position> positions) {
		Map<Position, Tile> waitingTiles = new HashMap<Position, Tile>();
		for(Position firePos : positions) {
			waitingTiles.put(firePos, gameField.getTile(firePos).onFire());
			gameField.setTile(new Fire(), firePos);
		}
		waitingFirePositions.addLast(waitingTiles);
		Timer timer = new Timer();
		timer.schedule(new FireTimerTask(), Parameters.INSTANCE.getFireDuration());
	}
	
	private void removeFirstFire() {
		Map<Position, Tile> fireReplacers = waitingFirePositions.get(0);
		Iterator<Position> iterator = fireReplacers.keySet().iterator();
		Position temp;
		while(iterator.hasNext()) {
			temp = (Position) iterator.next();
			gameField.setTile(fireReplacers.get(temp), temp);
		}
		waitingFirePositions.removeFirst();
	}
	
	/**
	 * This method takes care of what happens to the the positions that the fire strikes.
	 * 
	 * @param positions	The list that contains the positions of where the fire is to be placed.
	 */
	public void handleFire(Player bombOwner, List<Position> positions) {
		List<PointGiver> pg = new ArrayList<PointGiver>();
		Tile tempTile;
		
		for(Position pos : positions) {
			for(Player player : players) {
				if(isPlayerAtPosition(player, pos)) {
					pg.add(PointGiver.PlayerHit);
					player.playerHit();
					if(!player.isAlive()) {
						pg.add(PointGiver.KillPlayer);
					}
				}
			}
			
			tempTile = gameField.getTile(pos);
			if(tempTile instanceof Box) {
				pg.add(PointGiver.Box);
			} else if(tempTile instanceof Pillar) {
				pg.add(PointGiver.Pillar);
			} 
			bombOwner.getPlayerPoints().update(pg);
			setFire(positions);
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public int getTileToughness(Position pos) {
		return gameField.getTile(pos).getToughness();
	}
	
	public int getTileToughness(int x, int y) {
		return getTileToughness(new Position(x, y));
	}
	
	private boolean isPlayerAtPosition(Player player, Position pos) {
		return player.getTilePosition().equals(pos);
	}
}