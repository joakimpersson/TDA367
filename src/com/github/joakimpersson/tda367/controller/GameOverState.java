package com.github.joakimpersson.tda367.controller;

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

import com.github.joakimpersson.tda367.controller.input.InputManager;
import com.github.joakimpersson.tda367.gui.GameOverView;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameOverState extends BasicGameState {

	private enum STATE {
		USED, NOT_USED;
	}

	private int stateID = -1;
	private IBombermanModel model = null;
	private InputManager inputManager = null;
	private IView view = null;
	private STATE currentState = null;

	public GameOverState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		currentState = STATE.NOT_USED;
		model = BombermanModel.getInstance();
		inputManager = InputManager.getInstance();
		view = new GameOverView();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		currentState = STATE.USED;
		clearInputQueue(container.getInput());
		view.enter();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (currentState != STATE.NOT_USED) {
			view.render(container, g);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		Input input = container.getInput();
		boolean pressedProcced = inputManager.pressedProceed(input);

		if (pressedProcced) {
			int newState = BombermanGame.MAIN_MENU_STATE;
			changeState(game, newState);
		}

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	@Override
	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.leave(container, game);
		
		currentState = STATE.NOT_USED;
		
		// clean up the model before the next game
		resetGame();
	}

	private void resetGame() {

		inputManager.removeAllInputHandlers();

		model.gameReset();
	}

	/**
	 * 
	 * Responsible for change the current game state into another using a
	 * fadein/out transition
	 * 
	 * @param game
	 *            The game holding this state
	 * @param newState
	 *            The new state to change to
	 */
	private void changeState(StateBasedGame game, int newState) {
		Transition fadeIn = new FadeInTransition(Color.cyan, 300);
		Transition fadeOut = new FadeOutTransition(Color.cyan, 300);
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
