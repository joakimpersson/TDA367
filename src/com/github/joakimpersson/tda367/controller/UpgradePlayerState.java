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
import com.github.joakimpersson.tda367.model.PyromaniacModel;
import com.github.joakimpersson.tda367.model.IPyromaniacModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.constants.EventType;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * Controller for the upgrade player state
 * 
 * @author joakimpersson
 * @modified adderollen
 * 
 */
public class UpgradePlayerState extends BasicGameState {

	/**
	 * A simple enum containing the different states in the upgradeplayerstate
	 * 
	 */
	private enum State {
		USED, UPGRADE_DONE, NOT_USED;
	}

	private int stateID = -1;
	private UpgradePlayerView view = null;
	private IPyromaniacModel model = null;
	private List<Attribute> attributes = null;
	private Map<Integer, Integer> playersIndex = null;
	private Map<Integer, Boolean> playerReadyness = null;
	private Map<Integer, Integer> playerCredits = null;
	private Map<Integer, Map<Attribute, Integer>> upgradeMap = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs;
	private State currentState;

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
		model = PyromaniacModel.getInstance();
		inputManager = InputManager.getInstance();

		this.pcs = new PropertyChangeSupport(this);
		this.pcs.addPropertyChangeListener(AudioEventListener.getInstance());

		currentState = State.NOT_USED;
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

		for (Player player : model.getPlayers()) {
			Map<Attribute, Integer> attributeMap = new HashMap<Attribute, Integer>();
			for (Attribute a : attributes) {
				attributeMap.put(a, player.getAttribute(a));
			}
			playersIndex.put(player.getIndex(), 0);
			playerReadyness.put(player.getIndex(), false);
			playerCredits.put(player.getIndex(), player.getCredits());
			upgradeMap.put(player.getIndex(), attributeMap);
		}
		view.enter();
		currentState = State.USED;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (currentState != State.NOT_USED) {
			view.render(container, g, playersIndex, playerReadyness,
					playerCredits, upgradeMap);
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
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

	/**
	 * Responsible for change the current game state into another using a
	 * fadein/out transition
	 * 
	 * @param game
	 *            The game holding this state
	 */
	private void upgradeDone(StateBasedGame game) {
		int newState = PyromaniacsGame.GAMEPLAY_STATE;
		ControllerUtils.changeState(game, newState);
		currentState = State.NOT_USED;
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
		List<InputData> dataList = inputManager.getMenuInputData(input);

		for (InputData data : dataList) {
			PlayerAction action = data.getAction();

			Player player = data.getPlayer();
			Attribute attribute = attributes.get(playersIndex.get(player
					.getIndex()));
			int value = upgradeMap.get(player.getIndex()).get(attribute);
			int credits = playerCredits.get(player.getIndex());
			boolean playerReady = playerReadyness.get(player.getIndex());

			if (action == PlayerAction.PRIMARY_ACTION) {
				playerReadyness.put(player.getIndex(), !playerReady);
			} else if (!playerReady) {

				switch (action) {
				case MOVE_NORTH:
					moveIndex(player, -1);
					pcs.firePropertyChange("play", null,
							EventType.MENU_NAVIGATE);
					break;
				case MOVE_SOUTH:
					moveIndex(player, 1);
					pcs.firePropertyChange("play", null,
							EventType.MENU_NAVIGATE);
					break;
				case MOVE_EAST:
					if (credits >= attribute.getCost()
							&& value < attribute.getMaxPurchasableAmount()) {
						value++;
						credits -= attribute.getCost();
						upgradeMap.get(player.getIndex()).put(attribute, value);
						playerCredits.put(player.getIndex(), credits);
						pcs.firePropertyChange("play", null,
								EventType.MENU_NAVIGATE);
					}
					break;
				case MOVE_WEST:
					if (value > player.getAttribute(attribute)) {
						value--;
						credits += attribute.getCost();
						upgradeMap.get(player.getIndex()).put(attribute, value);
						playerCredits.put(player.getIndex(), credits);
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
			currentState = State.UPGRADE_DONE;
		}
	}

	/**
	 * Performs the upgrades purchased by the players
	 * 
	 */
	private void performUpgrades() {
		for (Player player : model.getPlayers()) {
			for (Attribute attribute : attributes) {
				int oldValue = player.getAttribute(attribute);
				for (int i = 0; i < upgradeMap.get(player.getIndex()).get(
						attribute)
						- oldValue; i++) {
					model.upgradePlayer(player, attribute);
				}
			}
		}
	}

	/**
	 * Moves the currentIndex for the players navigation
	 * 
	 * @param player
	 *            The player that wants his navigation index to be moved
	 * @param delta
	 *            The number of steps to be moved
	 */
	private void moveIndex(Player player, int delta) {
		int currentIndex = playersIndex.get(player.getIndex());
		int size = attributes.size();
		int newIndex = (currentIndex + delta);

		int r = newIndex % size;
		if (r < 0) {
			r += size;

		}
		playersIndex.put(player.getIndex(), r);
	}

	@Override
	public int getID() {
		return stateID;
	}

}
