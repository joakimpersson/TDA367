package com.github.joakimpersson.tda367.gui;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ImageLoader {

	private static final String PATH = "res/images/";
	private static ImageLoader instance = null;

	private Map<String, Image> imageMap = new HashMap<String, Image>();

	private ImageLoader() {
		loadDirectory(PATH);
	}

	private void loadDirectory(String path) {
		String directory = path.substring(PATH.length(), path.length());
		File dir = new File(path);
		for (File child : dir.listFiles()) {
			if (".".equals(child.getName()) || "..".equals(child.getName())) {
				continue;
			} else if (child.isDirectory()) {
				loadDirectory(path+child.getName()+"/");
				continue;
			}
			loadImage(directory+child.getName());
		}
	}

	private void loadImage(String s) {
		try {
			Image image = new Image("res/images/" + s);
			image.setFilter(Image.FILTER_NEAREST);
			imageMap.put(s.substring(0, s.length()-4), image);
		} catch (SlickException e) {
			System.out.println("File not found: " + s);
		}
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
