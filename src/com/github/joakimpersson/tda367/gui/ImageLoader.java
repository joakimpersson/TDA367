package com.github.joakimpersson.tda367.gui;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {

	private static ImageLoader instance = null;

	private Map<String, Image> imageMap = new HashMap<String, Image>();

	private ImageLoader() {
		initHashMap();
	}

	private void loadImage(String s) {
		try {
			imageMap.put(s, new Image("res/images/" + s + ".png"));
		} catch (SlickException e) {
			System.out.println("File not found: " + s + ".png");
		}
	}

	private void loadImage(String s, String f) {
		try {
			imageMap.put(s, new Image("res/images/" + f + ".png"));
		} catch (SlickException e) {
			System.out.println("File not found: " + f + ".png");
		}
	}

	private void initHashMap() {
		loadImage("floor1");
		loadImage("floor2");
		loadImage("floor3");
		loadImage("floor4");
		loadImage("floor5");
		loadImage("floor6");
		loadImage("box1");
		loadImage("box2");
		loadImage("box3");
		loadImage("box4");
		loadImage("box5");
		loadImage("pillar");
		loadImage("wall");
		loadImage("fire-column-north", "fire-column");
		loadImage("fire-column-south", "fire-column");
		loadImage("fire-column-west", "fire-row");
		loadImage("fire-column-east", "fire-row");
		loadImage("fire-column-none", "fire-mid");
		loadImage("fire-area", "fire-mid");
		loadImage("bomb-area");
		loadImage("bomb");
		loadImage("rangeUpItem");
		loadImage("speedUpItem");
		loadImage("bombUpItem");

		// TODO Rolén set the right name to the pics
		// loadImage("player-moving-up");
		// loadImage("player-moving-down");
		// loadImage("player-moving-left");
		// loadImage("player-moving-right");
		// loadImage("player-still-up");
		// loadImage("player-still-down");
		 loadImage("player/still-east");
		 loadImage("player/still-west");
		 loadImage("player/still-vert");
	}

	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}

		return instance;
	}

	public Image getImage(String image) {
		return imageMap.get(image);
	}

}
