package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.controller.input.InputManager;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.gui.SplashView;

public class SplashState extends BasicGameState {

	private IView view = null;
	private int stateID = -1;
	private InputManager inputManager = null;

	public SplashState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		view = new SplashView();
		inputManager  = InputManager.getInstance();
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
		if (inputManager.pressedProceed(input)) {
			game.enterState(BombermanGame.MAIN_MENU_STATE);
		}

	}

	@Override
	public int getID() {
		return stateID;
	}
}
