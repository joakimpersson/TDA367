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

import com.github.joakimpersson.tda367.gui.IUpgradePlayerView;
import com.github.joakimpersson.tda367.gui.UpgradePlayerView;
import com.github.joakimpersson.tda367.model.BombermanModel;
import com.github.joakimpersson.tda367.model.IBombermanModel;
import com.github.joakimpersson.tda367.model.constants.Attribute;
import com.github.joakimpersson.tda367.model.player.Player;

/**
 * 
 * @author joakimpersson
 * 
 */
public class UpgradePlayerState extends BasicGameState {

	private int stateID = -1;
	private IUpgradePlayerView view = null;
	private IBombermanModel model = null;
	private Map<Player, Integer> playersIndex = null;
	private List<Attribute> attributes = null;

	public UpgradePlayerState(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		view = new UpgradePlayerView();
		model = BombermanModel.getInstance();
		// TODO jocke perhaps map it via the player object
		attributes = model.getPlayers().get(0).getAttr().getAttributes();
		playersIndex = new HashMap<Player, Integer>();
		for (Player p : model.getPlayers()) {
			playersIndex.put(p, 0);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		view.render(container, g, playersIndex);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();

		// TODO jocke only used during development
		if (input.isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

		Player p1 = model.getPlayers().get(0);
		if (input.isKeyPressed(Input.KEY_UP)) {
			moveIndex(p1, -1);
			System.out.println("Player1 " + playersIndex.get(p1));
		}

		if (input.isKeyPressed(Input.KEY_DOWN)) {
			moveIndex(p1, 1);
			System.out.println("Player1 " + playersIndex.get(p1));
		}

		if (input.isKeyPressed(Input.KEY_SPACE)) {
			model.upgradePlayer(p1, attributes.get(playersIndex.get(p1)));
			System.out.println("Player1 trying to buy "
					+ attributes.get(playersIndex.get(p1)));
		}

		Player p2 = model.getPlayers().get(1);
		if (input.isKeyPressed(Input.KEY_W)) {
			moveIndex(p2, -1);
			System.out.println("Player2 " + playersIndex.get(p2));
		}

		if (input.isKeyPressed(Input.KEY_S)) {
			moveIndex(p2, 1);
			System.out.println("Player2 " + playersIndex.get(p2));
		}

		if (input.isKeyPressed(Input.KEY_2)) {
			model.upgradePlayer(p2, attributes.get(playersIndex.get(p2)));
			System.out.println("Player2 trying to buy "
					+ attributes.get(playersIndex.get(p2)));
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
