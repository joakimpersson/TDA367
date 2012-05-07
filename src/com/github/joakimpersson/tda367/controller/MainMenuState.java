package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

import com.github.joakimpersson.tda367.audio.AudioEventListener;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.gui.MainMenuView;
import com.github.joakimpersson.tda367.model.constants.EventType;

/**
 * 
 * @author joakimpersson
 * 
 */
public class MainMenuState extends BasicGameState {

	private IView view = null;
	private int stateID = -1;
	private PropertyChangeSupport pcs;

	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		clearInputQueue(container.getInput());
		pcs.firePropertyChange("play", null, EventType.TITLE_SCREEN);

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(AudioEventListener.getInstance());
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
		int newState = -1;
		// TODO jocke only used during development
		if (input.isKeyPressed(Input.KEY_ESCAPE)
				|| input.isKeyPressed(Input.KEY_Q)) {
			container.exit();
		}

		if (input.isKeyDown(Input.KEY_G)) {
			pcs.firePropertyChange("play", null, EventType.MENU_ACTION);
			newState = BombermanGame.SETUP_GAME_STATE;
		}

		if (input.isKeyDown(Input.KEY_H)) {
			pcs.firePropertyChange("play", null, EventType.MENU_ACTION);
			newState = BombermanGame.HIGHSCORE_STATE;
		}
		if (input.isKeyDown(Input.KEY_D)) {
			pcs.firePropertyChange("play", null, EventType.MENU_ACTION);
			newState = BombermanGame.GAMEPLAY_STATE;
		}

		if (input.isKeyDown(Input.KEY_U)) {
			pcs.firePropertyChange("play", null, EventType.MENU_ACTION);
			newState = BombermanGame.UPGRADE_PLAYER_STATE;
		}

		if (newState != -1) {
			changeState(game, newState);
		}
	}

	/**
	 * Responsible for changing into an new state
	 * 
	 * @param game
	 *            The Bomberman game container
	 * @param newState
	 */
	private void changeState(StateBasedGame game, int newState) {
		Transition fadeIn = new FadeInTransition(Color.cyan, 500);
		Transition fadeOut = new FadeOutTransition(Color.cyan, 500);
		game.enterState(newState, fadeOut, fadeIn);
	}

	/**
	 * Clear everything in the input queue from previous states
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void clearInputQueue(Input input) {
		input.clearControlPressedRecord();
		input.clearKeyPressedRecord();
		input.clearMousePressedRecord();
	}

	@Override
	public int getID() {
		return stateID;
	}

}
