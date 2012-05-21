package com.github.joakimpersson.tda367.controller;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.controller.input.InputManager;
import com.github.joakimpersson.tda367.controller.utils.ControllerUtils;
import com.github.joakimpersson.tda367.gui.GameOverView;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.model.PyromaniacModel;
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
		model = PyromaniacModel.getInstance();
		inputManager = InputManager.getInstance();
		view = new GameOverView();
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		currentState = STATE.USED;
		ControllerUtils.clearInputQueue(container.getInput());
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
			ControllerUtils.changeState(game, newState);
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

	@Override
	public int getID() {
		return stateID;
	}

}
