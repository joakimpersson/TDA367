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
import com.github.joakimpersson.tda367.gui.HighscoreView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;

public class HighscoreState extends BasicGameState {

	private int stateID = -1;
	private HighscoreView view = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs = null;
	private IBombermanModel model = null;
	private STATES currentState = null;
	private int currentIndex = 0;

	private enum STATES {
		ACTIVE, NOT_ACTIVE;
	}

	public HighscoreState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {

		this.currentState = STATES.NOT_ACTIVE;
		this.model = BombermanModel.getInstance();
		this.view = new HighscoreView();
		this.inputManager = InputManager.getInstance();
		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(AudioEventListener.getInstance());
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);

		currentState = STATES.ACTIVE;
		view.enter();
		pcs.firePropertyChange("play", null, EventType.TITLE_SCREEN);

	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {

		if (currentState != STATES.NOT_ACTIVE) {
			view.render(container, g, currentIndex);
		}

	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		switch (currentState) {
		case ACTIVE:
			updateGame(container.getInput());
			break;
		case NOT_ACTIVE:

			break;
		default:
			break;
		}

		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(BombermanGame.MAIN_MENU_STATE);
		}
	}

	private void updateGame(Input input) {
		List<InputData> data = inputManager.getData(input);

		for (InputData d : data) {
			PlayerAction action = d.getAction();
			switch (action) {
			case MoveUp:
				moveIndex(-1);
				break;
			case MoveDown:
				moveIndex(1);
				break;
			default:
				break;
			}
		}

	}

	private void moveIndex(int delta) {
		int n = model.getHighscoreList().size();
		int newIndex = (currentIndex + delta);
		
		int r = newIndex % n;
		if (r < 0) {
			r += n;

		}
		currentIndex = r;
	}

	@Override
	public int getID() {
		return stateID;
	}

}
