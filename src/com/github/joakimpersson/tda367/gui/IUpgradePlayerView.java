package com.github.joakimpersson.tda367.gui;

import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public interface IUpgradePlayerView {
	// TODO jocke redo/rethink
	void render(GameContainer container, Graphics g,
			Map<Integer, Integer> playerAttrIndex);
}
