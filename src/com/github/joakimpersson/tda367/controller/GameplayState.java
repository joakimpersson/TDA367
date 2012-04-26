package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;
import java.util.List;

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
import com.github.joakimpersson.tda367.controller.input.InputData;
import com.github.joakimpersson.tda367.controller.input.InputManager;
import com.github.joakimpersson.tda367.gui.GameplayView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.ResetType;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameplayState extends BasicGameState {

	private int stateID = -1;
	private IBombermanModel model = null;
	private GameplayView view = null;
	private STATE currentState = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs;

	private enum STATE {
		GAME_RUNNING, ROUND_OVER, MATCH_OVER, GAME_OVER, NOT_STARTED;
	}

	public GameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);

		pcs.firePropertyChange("play", null, EventType.BATTLE_SCREEN);
		currentState = STATE.GAME_RUNNING;

	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		pcs = new PropertyChangeSupport(this);
		AudioEventListener audioEL = AudioEventListener.getInstance();
		pcs.addPropertyChangeListener(audioEL);
		model = BombermanModel.getInstance();

		// TODO maybe models listener should be added in the class where get
		// instance is first called?
		model.addPropertyChangeListener(audioEL);
		view = new GameplayView();
		inputManager = InputManager.getInstance();

		currentState = STATE.NOT_STARTED;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (!currentState.equals(STATE.NOT_STARTED)) {
			view.render(container, g);

			if (currentState.equals(STATE.ROUND_OVER)) {
				view.showRoundStats(container, g);
			}

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO only during development, also test AL.destroy() here.
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			container.exit();
		}

		if (container.getInput().isKeyPressed(Input.KEY_Z)) {
			currentState = STATE.ROUND_OVER;
		}

		switch (currentState) {
		case GAME_RUNNING:

			// simple check to see whether the turn is over or not
			if (model.isRoundOver()) {
				currentState = STATE.ROUND_OVER;
			} else {
				gameRunning(container.getInput());
			}

			break;
		case MATCH_OVER:
			resetState();
			if (model.isGameOver()) {
				currentState = STATE.GAME_OVER;
			} else {
				Transition fadeIn = new FadeInTransition(Color.cyan, 500);
				Transition fadeOut = new FadeOutTransition(Color.cyan, 500);
				game.enterState(BombermanGame.UPGRADE_PLAYER_STATE, fadeOut,
						fadeIn);
			}
			resetState();
			break;
		case ROUND_OVER:

			roundOver(container.getInput());

			break;
		default:
			break;
		}

	}

	private void roundOver(Input input) {

		boolean pressedProceed = inputManager.pressedProceed(input);

		if (pressedProceed) {
			if (model.isMatchOver()) {
				currentState = STATE.MATCH_OVER;
			} else {
				resetState();
				// TODO jocke add an wainting state and restart
			}
		}

	}

	private void resetState() {

		switch (currentState) {
		case ROUND_OVER:
			model.reset(ResetType.Round);
			break;
		case MATCH_OVER:
			model.reset(ResetType.Match);
			break;
		default:
			break;
		}

	}

	private void gameRunning(Input input) {

		List<InputData> data = inputManager.getData(input);

		for (InputData d : data) {
			model.updateGame(d.getPlayer(), d.getAction());
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
