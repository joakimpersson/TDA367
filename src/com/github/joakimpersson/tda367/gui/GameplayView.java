package com.github.joakimpersson.tda367.gui;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.tiles.Tile;

public class GameplayView implements IView {

	// TODO jocke ask wheater this is ok!
	private IBombermanModel model = null;

	public GameplayView() {
		model = BombermanModel.getInstance();
		init();
	}

	private void init() {
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		drawMap(container, g);

	}

	private void drawMap(GameContainer container, Graphics g) {
		Tile[][] map = model.getMap();
		
	}
}
