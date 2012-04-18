package com.github.joakimpersson.tda367.controller;

import java.util.List;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.GameplayView;
import com.github.joakimpersson.tda367.gui.IView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.PlayerAction;
import com.github.joakimpersson.tda367.model.constants.ResetType;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class GameplayState extends BasicGameState {

	private int stateID = -1;
	private IBombermanModel model = null;
	private IView view = null;
	private STATE currentState = null;

	private List<Player> players = null;

	private enum STATE {
		GAME_RUNNING, ROUND_OVER, MATCH_OVER, GAME_OVER;
	}

	public GameplayState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		super.enter(container, game);

		resetModel();
		currentState = STATE.GAME_RUNNING;

	}

	private void resetModel() {
		
		switch (currentState) {
		case ROUND_OVER:
			model.reset(ResetType.Round);
			break;
		case MATCH_OVER:
			model.reset(ResetType.Match);
		default:
			break;
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		model = BombermanModel.getInstance();
		view = new GameplayView();
		players = model.getPlayers();
		currentState = STATE.GAME_RUNNING;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		switch (currentState) {
		case GAME_RUNNING:

			// simple check to see whether the turn is over or not
			if (model.isRoundOver()) {
				currentState = STATE.ROUND_OVER;
			} else {
				gameRunning(container);
			}

			break;
		case MATCH_OVER:

			if (model.isGameOver()) {
				currentState = STATE.GAME_OVER;
			} else {
				game.enterState(BombermanGame.UPGRADE_PLAYER_STATE);
			}
			break;
		case ROUND_OVER:
			
			if (model.isMatchOver()) {
				currentState = STATE.MATCH_OVER;
			}
			break;
		default:
			break;
		}

	}

	private void gameRunning(GameContainer gc) {
		Input input = gc.getInput();

		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			gc.exit();
		}

		// player 1 movement/bomb

		Player p1 = players.get(0);

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			model.updateGame(p1, PlayerAction.PlaceBomb);
		}

		if (input.isKeyDown(Input.KEY_UP)) {
			model.updateGame(p1, PlayerAction.MoveUp);
		} else if (input.isKeyDown(Input.KEY_DOWN)) {
			model.updateGame(p1, PlayerAction.MoveDown);
		} else if (input.isKeyDown(Input.KEY_LEFT)) {
			model.updateGame(p1, PlayerAction.MoveLeft);
		} else if (input.isKeyDown(Input.KEY_RIGHT)) {
			model.updateGame(p1, PlayerAction.MoveRight);
		}

		// player 2 movement/bomb

		Player p2 = players.get(1);

		if (input.isKeyPressed(Input.KEY_F)) {
			model.updateGame(p2, PlayerAction.PlaceBomb);
		}

		if (input.isKeyDown(Input.KEY_W)) {
			model.updateGame(p2, PlayerAction.MoveUp);
		} else if (input.isKeyDown(Input.KEY_S)) {
			model.updateGame(p2, PlayerAction.MoveDown);
		} else if (input.isKeyDown(Input.KEY_A)) {
			model.updateGame(p2, PlayerAction.MoveLeft);
		} else if (input.isKeyDown(Input.KEY_D)) {
			model.updateGame(p2, PlayerAction.MoveRight);
		}

		// TODO adrian 360 controller input tests

		X360Input(input, p1, 0);
		X360Input(input, p2, 1);
	}

	private void X360Input(Input input, Player player, int controller) {
		if (input.isButtonPressed(11, controller)) {
			model.updateGame(player, PlayerAction.PlaceBomb);
		}

		if (input.isControllerUp(controller)) {
			model.updateGame(player, PlayerAction.MoveUp);
		} else if (input.isControllerDown(controller)) {
			model.updateGame(player, PlayerAction.MoveDown);
		} else if (input.isControllerLeft(controller)) {
			model.updateGame(player, PlayerAction.MoveLeft);
		} else if (input.isControllerRight(controller)) {
			model.updateGame(player, PlayerAction.MoveRight);
		}
	}

	@Override
	public int getID() {
		return stateID;
	}

}
