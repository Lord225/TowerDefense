package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.TowerDefense;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument

/**
 * Class with main method for gradle usage
 */
public class DesktopLauncher {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("TowerDefense");
		new Lwjgl3Application(new TowerDefense(), config); //komentarz test
	}
}
