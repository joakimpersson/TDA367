package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.HighscoreView;
import com.github.joakimpersson.tda367.gui.IView;

public class HighscoreState extends BasicGameState {

	private int stateID = -1;
	private IView view = null;

	public HighscoreState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.view = new HighscoreView();

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g);

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

	}

	@Override
	public int getID() {
		return stateID;
	}

}
