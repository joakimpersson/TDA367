package com.github.joakimpersson.tda367.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.Timer;

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
public class BombermanModel implements IBombermanModel, ActionListener {

	private List<Player> players;
	private GameField gameField;
	private boolean roundIsPlaying;
	private static BombermanModel instance = null;
	private LinkedList<Map<Position, Tile>> waitingFirePositions;
	private LinkedList<Timer> fireTimers;

	private BombermanModel() {
		this.players = new ArrayList<Player>();
		this.gameField = new StandardMap();
		this.roundIsPlaying = false;
		this.waitingFirePositions = new LinkedList<Map<Position, Tile>>();
		this.fireTimers = new LinkedList<Timer>();
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
		if(roundIsPlaying) {
			player.upgradeAttr(attr, UpgradeType.Round);
		} else {
			player.upgradeAttr(attr, UpgradeType.Match);
		}
	}

	private void matchEnd() {

	}

	private void turnEnd() {

	}

	private void move(Player player, PlayerAction action) {

	}

	private void placeBomb(Player player) {
		Bomb bomb = player.placeBomb();
		bomb.explode();
	}
	
	private void setFire(List<Position> positions) {
		Map<Position, Tile> waitingTiles = new HashMap<Position, Tile>();
		for(Position firePos : positions) {
			waitingTiles.put(firePos, gameField.getTile(firePos).onFire());
			gameField.setTile(new Fire(), firePos);
		}
		waitingFirePositions.addLast(waitingTiles);
		Timer timer = new Timer(Parameters.INSTANCE.getFireDuration(), this);
		timer.start();
		fireTimers.addLast(timer);
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
		List<PointGiver> pg = new ArrayList();
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() instanceof Timer) {
			Timer timer = (Timer)(arg0.getSource());
			timer.stop();
			fireTimers.removeFirst();
			removeFirstFire();
		}
		
	}
}
