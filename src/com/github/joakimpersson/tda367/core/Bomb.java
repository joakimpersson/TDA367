package com.github.joakimpersson.tda367.core;

import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Tile;
import com.github.joakimpersson.tda367.core.Parameters;
import java.util.Timer;

public abstract class Bomb implements Tile {

	private int toughness;
	private Timer timer;
	private Player player;
	private Position pos;

	public Bomb(Player p, Position pos) {
		this.player = p;
		this.toughness = 1;
		doTimer();
	}

	private void doTimer() {
		this.timer = new Timer();
		this.timer.schedule(new BombTask(this), 0, Parameters.INSTANCE.getBombDetonationTime());
	}

	public Player getPlayer() {
		return player;
	}

	@Override
	public int getToughness() {
		return toughness;
	}

	@Override
	public Tile onFire() {
		this.timer.cancel();
		explode();
		return new Floor();
	}

	@Override
	public boolean isWalkable() {
		return false;
	}

	public abstract void explode();

	public Position getPos() {
		return pos;
	}
}
