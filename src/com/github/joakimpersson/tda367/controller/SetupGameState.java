package com.github.joakimpersson.tda367.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.github.joakimpersson.tda367.gui.SetupGameView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.player.Player;
import com.github.joakimpersson.tda367.model.utils.Position;

/**
 * Sets up the players
 * 
 * @author rekoil
 */
public class SetupGameState extends BasicGameState {

	private SetupGameView view = null;
	private IBombermanModel model = null;
	private int stateID = -1;
	private int selection = 0, progress = 0;
	private int controllerCount, possiblePlayers;
	private int players;
	private List<Player> playerList;

	public SetupGameState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		model = BombermanModel.getInstance();
		playerList = model.getPlayers();
		
		controllerCount = container.getInput().getControllerCount();
		possiblePlayers = 2 + controllerCount;
		
		if (possiblePlayers > 4) {
			possiblePlayers = 4;
		}
		view = new SetupGameView(possiblePlayers, container);
		
		selection = 2;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g, selection);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_Q)) {
			container.exit();
		}

		if (input.isKeyPressed(Input.KEY_UP)) {
			moveIndex(-1);
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			moveIndex(1);
		}

		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyDown(Input.KEY_SPACE)) {
			if (progress == 0) {
				players = selection;
				view.startPlayerCreation(players);
				progress++;
			// TODO to be handled by global input
			} else if (progress == 1 && view.nameFilledIn()) {
				createPlayer(view.getName());
				//createPlayer(view.getName(), controllerUsed);
				if (allPlayersCreated()) {
					game.enterState(BombermanGame.GAMEPLAY_STATE);
				}
				view.playerCreated();
			}
		}
	}

	private void createPlayer(String name) {
		// TODO add player in correct position
		Player player = new Player(name, new Position(1,1));
		playerList.add(player);
		//globalInput.bindPlayer(player, controllerUsed);
	}

	private boolean allPlayersCreated() {
		return playerList.size() == players+2;
		// TODO actually use these players instead of those in bomberman
//		return playerList.size() == players;
	}

	private void moveIndex(int delta) {
		int currentIndex = selection;
		int newIndex = (currentIndex + delta);
		
		if (newIndex < 2) {
			newIndex = 2;
		} else if ( newIndex > possiblePlayers) {
			newIndex = possiblePlayers;
		}
		
		selection = newIndex;
	}

	@Override
	public int getID() {
		return stateID;
	}
}