package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * A simple enum containing the different states in the upgradeplayerstate
	 * 
	 */
	private enum STATE {
		USED, UPGRADE_DONE, NOT_USED;
	}

	private int stateID = -1;
	private UpgradePlayerView view = null;
	private IBombermanModel model = null;
	/**
	 * TODO tmp solution Indexing on the players index, since the player object
	 * is mutable but not his index its non-mutable
	 */
	private Map<Integer, Integer> playersIndex = null;
	private List<Attribute> attributes = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs;
	private STATE currentState;

	/**
	 * Create a new slick BasicGameState controller for the UpgradePlayerState
	 * 
	 * @param stateID
	 *            The states id number
	 */
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

		currentState = STATE.NOT_USED;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);

		ControllerUtils.clearInputQueue(container.getInput());

		playersIndex = new HashMap<Integer, Integer>();
		attributes = model.getPlayers().get(0).getPermanentAttributes();

		for (Player p : model.getPlayers()) {
			playersIndex.put(p.getIndex(), 0);
		}
		view.enter();
		currentState = STATE.USED;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (currentState != STATE.NOT_USED) {
			view.render(container, g, playersIndex);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

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

		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}
	}

	/**
	 * Responsible for change the current game state into another using a
	 * fadein/out transition
	 * 
	 * @param game
	 *            The game holding this state
	 */
	private void upgradeDone(StateBasedGame game) {
		int newState = BombermanGame.GAMEPLAY_STATE;
		ControllerUtils.changeState(game, newState);
		currentState = STATE.NOT_USED;
	}

	/**
	 * Manages all the states input by the player and maps it into an certain
	 * action that the player has requested to perform
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void updateGame(Input input) {
		List<InputData> data = inputManager.getData(input);

		for (InputData d : data) {
			PlayerAction action = d.getAction();
			Player p = d.getPlayer();
			switch (action) {
			case MOVE_NORTH:
				moveIndex(p, -1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			case MOVE_SOUTH:
				moveIndex(p, 1);
				pcs.firePropertyChange("play", null, EventType.MENU_NAVIGATE);
				break;
			case ACTION:
				model.upgradePlayer(p,
						attributes.get(playersIndex.get(p.getIndex())));
				break;
			default:
				break;
			}
		}

		if (inputManager.pressedProceed(input)) {
			currentState = STATE.UPGRADE_DONE;
		}

		// TODO really bad solution
		try {
			Thread.sleep(80);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Moves the currentIndex for the players navigation
	 * 
	 * @param p
	 *            The player that wants his navigation index to be moved
	 * @param delta
	 *            The number of steps to be moved
	 */
	private void moveIndex(Player p, int delta) {
		int currentIndex = playersIndex.get(p.getIndex());
		int n = attributes.size();
		int newIndex = (currentIndex + delta);

		int r = newIndex % n;
		if (r < 0) {
			r += n;

		}
		playersIndex.put(p.getIndex(), r);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
