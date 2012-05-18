package com.github.joakimpersson.tda367.controller;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameplayState extends BasicGameState {

	/**
	 * A simple enum containing the different states in the game while running
	 * 
	 */
	private enum STATE {
		GAME_RUNNING, ROUND_OVER, MATCH_OVER, GAME_OVER, NOT_STARTED, GAME_WAITING, ROUND_INFO_STATE;
	}

	private int stateID = -1;
	private IBombermanModel model = null;
	private GameplayView view = null;
	private STATE currentState = null;
	private InputManager inputManager = null;
	private PropertyChangeSupport pcs;
	private List<EventType> playlist;
	private boolean playlistIsPlaying = false;
	private Timer countDownTimer;
	private boolean readyToStart;

	/**
	 * Create a new slick BasicGameState controller for the Gameplaystate
	 * 
	 * @param stateID
	 *            The states id number
	 */
	public GameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		pcs = new PropertyChangeSupport(this);
		AudioEventListener audioEL = AudioEventListener.getInstance();
		pcs.addPropertyChangeListener(audioEL);
		model = BombermanModel.getInstance();

		this.playlist = new ArrayList<EventType>();
		playlist.add(EventType.ROUND_ENDED);
		playlist.add(EventType.UPGRADE_SCREEN);

		// TODO maybe models listener should be added in the class where get
		// instance is first called?
		model.addPropertyChangeListener(audioEL);
		view = new GameplayView();
		inputManager = InputManager.getInstance();

		currentState = STATE.NOT_STARTED;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);
		clearInputQueue(container.getInput());
		currentState = STATE.GAME_WAITING;
		view.enter();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		if (!currentState.equals(STATE.NOT_STARTED)) {
			view.render(container, g);

			if (currentState.equals(STATE.ROUND_INFO_STATE)) {
				view.showRoundStats(container, g);
			}

			if (currentState.equals(STATE.GAME_WAITING)) {
				view.showWaitingBox(container, g);
			}

		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {

		switch (currentState) {

		case GAME_WAITING:
			gameWaiting(container.getInput());
			break;
		case GAME_RUNNING:
			gameRunning(container.getInput(), game);
			break;
		case MATCH_OVER:
			matchOver(game);
			break;
		case ROUND_OVER:
			roundOver();
			break;
		case ROUND_INFO_STATE:
			roundInfo(container.getInput());
			break;
		case GAME_OVER:
			gameOver(game);
			break;
		default:
			break;
		}

		// TODO perhaps map to some input object
		if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			game.enterState(BombermanGame.MAIN_MENU_STATE);
		}
	}

	/**
	 * 
	 * Listens to player interaction while the game is waiting to send the
	 * player into another State
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void gameWaiting(Input input) {
		
		if(countDownTimer == null) {
			pcs.firePropertyChange("play", null, EventType.BATTLE_SCREEN);
			readyToStart = false;
			countDownTimer = new Timer();
			view.setCountDown(3);
			pcs.firePropertyChange("play", null, EventType.COUNT_DOWN);
			countDownTimer.schedule(new CountDownTask(2), 700);
		} else if(readyToStart) {
				currentState = STATE.GAME_RUNNING;
				readyToStart = false;
				countDownTimer = null;
		}
		
//		boolean pressedProceed = inputManager.pressedProceed(input);
//
//		if (pressedProceed) {
//			currentState = STATE.GAME_RUNNING;
//		}
	}

	private void gameRunning(Input input, StateBasedGame game) {
		// simple check to see whether the turn is over or not
		if (model.isRoundOver()) {
			currentState = STATE.ROUND_OVER;
		} else {
			updateGame(input);
		}

	}

	/**
	 * 
	 * Responsible for updating the game when the game is running and is
	 * responsible for handling the users inputs and notify the model.
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void updateGame(Input input) {

		if (playlistIsPlaying) {
			pcs.firePropertyChange("play", null, EventType.BATTLE_SCREEN);
			playlistIsPlaying = false;
		}

		List<InputData> data = inputManager.getData(input);

		for (InputData d : data) {
			model.updateGame(d.getPlayer(), d.getAction());
		}
	}

	private void roundOver() {
		model.roundOver();
		if (model.isMatchOver()) {
			model.matchOver();
		}
		
		Player winningPlayer = model.getLastRoundWinner();
		view.setRoundWinner(winningPlayer);
		
		currentState = STATE.ROUND_INFO_STATE;
	}

	/**
	 * 
	 * Responsible for determine what state the game should enter when a round
	 * is over and listens for the player to press the proceed button to enter
	 * the next state.
	 * 
	 * @param input
	 *            The input method used by the slick framework that contains the
	 *            latest action
	 */
	private void roundInfo(Input input) {
		if (!playlistIsPlaying) {
			pcs.firePropertyChange("playList", null, playlist);
			playlistIsPlaying = true;
		}

		boolean pressedProceed = inputManager.pressedProceed(input);
		boolean matchOver = model.isMatchOver();

		if (pressedProceed) {
			if (matchOver) {
				currentState = STATE.MATCH_OVER;
			} else {
				currentState = STATE.GAME_WAITING;
			}
		}

	}

	private void matchOver(StateBasedGame game) {
		if (model.isGameOver()) {
			model.gameOver();
			currentState = STATE.GAME_OVER;
		} else {
			int newState = BombermanGame.UPGRADE_PLAYER_STATE;
			changeState(game, newState);
		}
		model.resetRoundStats();
	}

	private void gameOver(StateBasedGame game) {

		int newState = BombermanGame.GAMEOVER_STATE;
		changeState(game, newState);

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

	@Override
	public int getID() {
		return stateID;
	}
	
	private class CountDownTask extends TimerTask {
		private int countDown;
		
		public CountDownTask(int countDown) {
			this.countDown = countDown;
		}
		
		@Override
		public void run() {
			if(countDown == 0) {
				readyToStart = true;
			} else {
				view.setCountDown(countDown);
				if(countDown == 1) {
					pcs.firePropertyChange("play", null, EventType.BOMB_EXPLODED);
				} else {
					pcs.firePropertyChange("play", null, EventType.COUNT_DOWN);
				}
				countDownTimer.schedule(new CountDownTask(countDown - 1), 700);
			}
		}
		
	}
	
}
