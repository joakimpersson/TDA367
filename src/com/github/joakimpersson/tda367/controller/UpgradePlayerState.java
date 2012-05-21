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
	private List<Attribute> attributes = null;
	/**
	 * TODO tmp solution Indexing on the players index, since the player object
	 * is mutable but not his index its non-mutable
	 */
	private Map<Integer, Integer> playersIndex = null;
	private Map<Integer, Boolean> playerReadyness = null;
	private Map<Integer, Integer> playerCredits = null;
	private Map<Integer, Map<Attribute, Integer>> upgradeMap = null;
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
		attributes = model.getPlayers().get(0).getPermanentAttributes();

		playersIndex = new HashMap<Integer, Integer>();
		playerReadyness = new HashMap<Integer, Boolean>();
		playerCredits = new HashMap<Integer, Integer>();
		upgradeMap = new HashMap<Integer, Map<Attribute, Integer>>();

		for (Player p : model.getPlayers()) {
			// TODO bad code
			Map<Attribute, Integer> attributeMap = new HashMap<Attribute, Integer>();
			for (Attribute a : attributes) {
				attributeMap.put(a, p.getAttribute(a));
			}
			playersIndex.put(p.getIndex(), 0);
			playerReadyness.put(p.getIndex(), false);
			playerCredits.put(p.getIndex(), p.getCredits());
			upgradeMap.put(p.getIndex(), attributeMap);
		}
		view.enter();
		currentState = STATE.USED;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (currentState != STATE.NOT_USED) {
			view.render(container, g, playersIndex, playerReadyness,
					playerCredits, upgradeMap);
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
		List<InputData> data = inputManager.getMenuInputData(input);

		for (InputData d : data) {
			PlayerAction action = d.getAction();

			Player p = d.getPlayer();
			Attribute attr = attributes.get(playersIndex.get(p.getIndex()));
			int value = upgradeMap.get(p.getIndex()).get(attr);
			int credits = playerCredits.get(p.getIndex());
			boolean playerReady = playerReadyness.get(p.getIndex());

			if (action == PlayerAction.ACTION) {
				playerReadyness.put(p.getIndex(), !playerReady);
			} else if (!playerReady) {
				switch (action) {
				case MOVE_NORTH:
					moveIndex(p, -1);
					pcs.firePropertyChange("play", null,
							EventType.MENU_NAVIGATE);
					break;
				case MOVE_SOUTH:
					moveIndex(p, 1);
					pcs.firePropertyChange("play", null,
							EventType.MENU_NAVIGATE);
					break;
				case MOVE_EAST:
					if (credits >= attr.getCost()) {
						value++;
						credits -= attr.getCost();
						upgradeMap.get(p.getIndex()).put(attr, value);
						playerCredits.put(p.getIndex(), credits);
						pcs.firePropertyChange("play", null,
								EventType.MENU_NAVIGATE);
					}
					break;
				case MOVE_WEST:
					if (value > p.getAttribute(attr)) {
						value--;
						credits += attr.getCost();
						upgradeMap.get(p.getIndex()).put(attr, value);
						playerCredits.put(p.getIndex(), credits);
					}
					pcs.firePropertyChange("play", null,
							EventType.MENU_NAVIGATE);
					break;
				default:
					break;
				}
			}
		}

		if (!playerReadyness.containsValue(false)) {
			performUpgrades();
			currentState = STATE.UPGRADE_DONE;
		}
	}

	private void performUpgrades() {
		for (Player p : model.getPlayers()) {
			for (Attribute a : attributes) {
				int oldValue = p.getAttribute(a);
				for (int i = 0; i < upgradeMap.get(p.getIndex()).get(a)
						- oldValue; i++) {
					model.upgradePlayer(p, a);
				}
			}
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
