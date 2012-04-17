package com.github.joakimpersson.tda367.gui;


import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {
	
	private static ImageLoader instance = null;
	
	private HashMap<String, Image> imageMap = new HashMap();
	
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
		loadImage("fire-column-up", "fire-column");
		loadImage("fire-column-down", "fire-column");
		loadImage("fire-column-left", "fire-row");
		loadImage("fire-column-right", "fire-row");
		loadImage("fire-colum-none", "fire-mid");
//		loadImage("areaBomb");
		loadImage("bomb");
		loadImage("rangeUpItem", "bomb");
		loadImage("speedUpItem", "bomb");
		loadImage("bombUpItem", "bomb");		
	}
	
	public static ImageLoader getInstance() {
		if (instance == null) {
			instance = new ImageLoader();
		}
		
		return instance;
	}

	public Image getImage(String image, int width, int height) {
		return imageMap.get(image);
	}

}
