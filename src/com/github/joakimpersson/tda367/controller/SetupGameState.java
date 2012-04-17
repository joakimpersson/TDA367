package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.gui.SetupGameView;

public class SetupGameState extends BasicGameState {

	private IView view = null;
	private int stateID = -1;

	public SetupGameState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		this.view = new SetupGameView();
		
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

		if (input.isKeyDown(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_Q)) {
			container.exit();
		}

		if (input.isKeyDown(Input.KEY_G)) {
			game.enterState(BombermanGame.SETUP_GAME_STATE);
		}

		if (input.isKeyDown(Input.KEY_H)) {
			game.enterState(BombermanGame.HIGHSCORE_STATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}
}