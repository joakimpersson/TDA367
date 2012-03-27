package com.github.joakimpersson.tda367.core.bombs;

import com.github.joakimpersson.tda367.core.BombermanModel;
import com.github.joakimpersson.tda367.core.Parameters;
import com.github.joakimpersson.tda367.core.Player;
import com.github.joakimpersson.tda367.core.Position;
import com.github.joakimpersson.tda367.core.tiles.Floor;
import com.github.joakimpersson.tda367.core.tiles.Tile;

import java.util.Timer;

public abstract class Bomb implements Tile {
	
	BombermanModel bm = BombermanModel.getInstance();

	int toughness, range, power;
	Timer timer;
	Player player;
	Position pos;

	public Bomb(Player p, Position pos, int range, int power) {
		this.player = p;
		this.toughness = 1;
		this.range = range;
		this.power = power;
		
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

	int tryBreak(Position pos, int power) {
		if (pos.getX()<0 || pos.getY()<0) {
			return -1;
		}
		int toughness = bm.getTileToughness(pos);
		if (power >= toughness)
			return toughness;
		else
			return -1;
	}
}
