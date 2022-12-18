package com.mygdx.game;

import com.badlogic.gdx.Game;

public class MyGame extends Game {
	public void create() {
		this.setScreen(new MainMenuScreen(this));
	}
}