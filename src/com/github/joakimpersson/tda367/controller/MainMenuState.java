package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;
import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.audio.AudioEventListener;
import com.github.joakimpersson.tda367.controller.input.InputData;
import com.github.joakimpersson.tda367.controller.input.InputManager;
import com.github.joakimpersson.tda367.controller.utils.ControllerUtils;
import com.github.joakimpersson.tda367.gui.MainMenuView;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;

/**
 * A class representing the MainMenuState
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class MainMenuState extends BasicGameState {

	private MainMenuView view = null;
	private PropertyChangeSupport pcs = null;
	private InputManager inputManager = null;
	private int currentIndex;
	private int stateID = -1;

	/**
	 * Create a new instance of the MainMenuState
	 * 
	 * @param stateID
	 */
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		ControllerUtils.clearInputQueue(container.getInput());
		pcs.firePropertyChange("play", null, EventType.TITLE_SCREEN);
		this.currentIndex = 1;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(AudioEventListener.getInstance());
		view = new MainMenuView();
		inputManager = InputManager.getInstance();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g, currentIndex);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		updateSelection(input);

		if (inputManager.pressedProceed(input)) {
			menuAction(container, game);
		}

	}

	/**
	 * Perform an menu action corresponding to the selected index
	 * 
	 * @param container
	 *            The container holding the game
	 * @param game
	 *            The game holding this state
	 */
	private void menuAction(GameContainer container, StateBasedGame game) {
		int newState = -1;

		if (currentIndex == 1) {
			newState = PyromaniacsGame.SETUP_GAME_STATE;
		} else if (currentIndex == 2) {
			newState = PyromaniacsGame.HIGHSCORE_STATE;
		} else if (currentIndex == 3) {
			container.exit();
		}
		pcs.firePropertyChange("play", null, EventType.MENU_ACTION);
		if (newState != -1) {
			ControllerUtils.changeState(game, newState);
		}

	}

	/**
	 * Manages all the states input by the player and maps it into an certain
	 * action that the player has requested to perform
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void updateSelection(Input input) {
		List<InputData> inputData = inputManager.getMenuInputData(input);

		for (InputData data : inputData) {
			PlayerAction action = data.getAction();
			switch (action) {
			case MOVE_NORTH:
				moveIndex(-1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			case MOVE_SOUTH:
				moveIndex(1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			default:
				break;
			}
		}

	}

	/**
	 * Moves the currentIndex for the players navigation
	 * 
	 * @param delta
	 *            The number of steps to be moved
	 */
	private void moveIndex(int delta) {
		int newIndex = (currentIndex + delta);

		if (newIndex < 1) {
			newIndex = 1;
		} else if (newIndex > 3) {
			newIndex = 3;
		}

		currentIndex = newIndex;

	}

	@Override
	public int getID() {
		return stateID;
	}

}
