package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * 
 * @author joakimpersson
 *
 */
public class BombermanGame extends StateBasedGame {

	public static final int MAIN_MENU_STATE = 0;
	public static final int GAMEPLAY_STATE = 1;

	public BombermanGame(String name) {
		super(name);

		this.addState(new GameplayState(GAMEPLAY_STATE));

		this.enterState(GAMEPLAY_STATE);

	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(GAMEPLAY_STATE).init(container, this);

	}

}
