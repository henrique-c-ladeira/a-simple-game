package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.mygdx.game.screens.MainMenuScreen;

public class MyGame extends Game {
	public void create() {
		this.setScreen(new MainMenuScreen(this));
	}
}