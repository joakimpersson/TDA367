package com.github.joakimpersson.tda367.core;

import java.util.TimerTask;

public class BombTask extends TimerTask {
	Bomb bomb;
	
	public BombTask(Bomb b) {
		this.bomb = b;
	}

	@Override
	public void run() {
		bomb.explode();
	}
}
