package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.github.joakimpersson.tda367.gui.UpgradePlayerView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerState extends BasicGameState {

	private int stateID = -1;
	private UpgradePlayerView view = null;
	private IBombermanModel model = null;
	private Map<Player, Integer> playersIndex = null;
	private List<Attribute> attributes = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs;
	private STATES currentState;

	private enum STATES {
		USED, UPGRADE_DONE, NOT_USED;
	}

	public UpgradePlayerState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		view = new UpgradePlayerView();
		model = BombermanModel.getInstance();
		inputManager = InputManager.getInstance();

		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(AudioEventListener.getInstance());
		currentState = STATES.NOT_USED;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);

		pcs.firePropertyChange("play", null, EventType.TITLE_SCREEN);
		playersIndex = new HashMap<Player, Integer>();
		attributes = model.getPlayers().get(0).getPermanentAttributes();

		for (Player p : model.getPlayers()) {
			playersIndex.put(p, 0);
		}
		view.enter();
		currentState = STATES.USED;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (currentState != STATES.NOT_USED) {
			view.render(container, g, playersIndex);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

		switch (currentState) {

		case USED:
			updateGame(container.getInput());
			break;
		case UPGRADE_DONE:
			upgradeDone(game);
			break;
		case NOT_USED:
			// do nothing
			break;
		default:
			// should not happen
			break;
		}
	}

	private void upgradeDone(StateBasedGame game) {
		Transition fadeIn = new FadeInTransition(Color.cyan, 500);
		Transition fadeOut = new FadeOutTransition(Color.cyan, 500);
		game.enterState(BombermanGame.GAMEPLAY_STATE, fadeOut, fadeIn);
		currentState = STATES.NOT_USED;

	}

	private void updateGame(Input input) {
		List<InputData> data = inputManager.getData(input);

		for (InputData d : data) {
			PlayerAction action = d.getAction();
			Player p = d.getPlayer();
			switch (action) {
			case MoveUp:
				moveIndex(p, -1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			case MoveDown:
				moveIndex(p, 1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			case Action:
				model.upgradePlayer(p, attributes.get(playersIndex.get(p)));
				break;
			default:
				break;
			}
		}

		if (inputManager.pressedProceed(input)) {
			currentState = STATES.UPGRADE_DONE;
		}

		// jocke TODO really bad solution
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void moveIndex(Player p, int delta) {
		int currentIndex = playersIndex.get(p);
		int n = attributes.size();
		int newIndex = (currentIndex + delta);

		int r = newIndex % n;
		if (r < 0) {
			r += n;

		}
		playersIndex.put(p, r);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
