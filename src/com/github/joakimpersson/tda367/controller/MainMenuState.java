package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.gui.MainMenuView;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MainMenuState extends BasicGameState {

	private IView view = null;
	private int stateID = -1;

	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		view = new MainMenuView();

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
		if (input.isKeyDown(Input.KEY_ESCAPE)
				|| input.isKeyPressed(Input.KEY_Q)) {
			container.exit();
		}

		if (input.isKeyDown(Input.KEY_G)) {
			game.enterState(BombermanGame.SETUP_GAME_STATE);
			// game.enterState(BombermanGame.GAMEPLAY_STATE);
		}

		if (input.isKeyDown(Input.KEY_H)) {
			game.enterState(BombermanGame.HIGHSCORE_STATE);
		}
		if (input.isKeyDown(Input.KEY_D)) {
			game.enterState(BombermanGame.GAMEPLAY_STATE);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
