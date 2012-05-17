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
		loadImage(s, s);
	}

	private void loadImage(String s, String f) {
		try {
			Image image = new Image("res/images/" + f + ".png");
			image.setFilter(Image.FILTER_NEAREST);
			imageMap.put(s, image);
		} catch (SlickException e) {
			System.out.println("File not found: " + f + ".png");
		}
	}

	private void initHashMap() {
		// splash screen
		loadImage("splash/bg");
		loadImage("splash/text");
		
		// menu background
		loadImage("bg");
		
		// floor tiles
		loadImage("floor1");
		loadImage("floor2");
		loadImage("floor3");
		loadImage("floor4");
		loadImage("floor5");
		loadImage("floor6");

		// box tiles
		loadImage("box1");
		loadImage("box2");
		loadImage("box3");
		loadImage("box4");
		loadImage("box5");

		// misc tiles
		loadImage("pillar");
		loadImage("wall");

		// bomb tiles
		loadImage("bomb-area");
		loadImage("bomb");

		// fire tiles
		loadImage("fire-column-north", "fire-column");
		loadImage("fire-column-south", "fire-column");
		loadImage("fire-column-west", "fire-row");
		loadImage("fire-column-east", "fire-row");
		loadImage("fire-column-none", "fire-mid");
		loadImage("fire-area", "fire-mid");

		// power ups
		loadImage("rangeUpItem");
		loadImage("speedUpItem");
		loadImage("bombUpItem");

		// player tiles
		for (int i = 1; i <= 4; i++) {
			loadImage("player/"+i+"/still-north");
			loadImage("player/"+i+"/still-east");
			loadImage("player/"+i+"/still-south");
			loadImage("player/"+i+"/still-west");
		}
		loadImage("player/overlay/still-north");
		loadImage("player/overlay/still-east");
		loadImage("player/overlay/still-south");
		loadImage("player/overlay/still-west");
		
		// info pane support images
		loadImage("info/bomb");
		loadImage("info/fire");
		loadImage("info/heart");
		loadImage("info/power2");
		loadImage("info/power3");
		loadImage("info/speed");
		loadImage("info/skull");
		loadImage("info/winnerEffects1");
		loadImage("info/winnerEffects2");
		
		// TODO: not final
		loadImage("info/chevron");
		loadImage("info/star");
		
		// round info view
		loadImage("round-info/bg");
		loadImage("round-info/overlay");
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
