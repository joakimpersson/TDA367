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
	public static final int SETUP_GAME_STATE = 1;
	public static final int GAMEPLAY_STATE = 2;
	public static final int UPGRADE_PLAYER_STATE = 3;
	public static final int HIGHSCORE_STATE = 4;

	public BombermanGame(String name) {
		super(name);

		this.addState(new MainMenuState(MAIN_MENU_STATE));
		this.addState(new SetupGameState(SETUP_GAME_STATE));
		this.addState(new GameplayState(GAMEPLAY_STATE));
		this.addState(new UpgradePlayerState(UPGRADE_PLAYER_STATE));
		this.addState(new HighscoreState(HIGHSCORE_STATE));

		this.enterState(MAIN_MENU_STATE);

	}

	@Override
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(MAIN_MENU_STATE).init(container, this);
		this.getState(SETUP_GAME_STATE).init(container, this);
		this.getState(GAMEPLAY_STATE).init(container, this);
		this.getState(UPGRADE_PLAYER_STATE).init(container, this);
		this.getState(HIGHSCORE_STATE).init(container, this);
	}

}
