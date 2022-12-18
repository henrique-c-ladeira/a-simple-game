package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGame;
import com.mygdx.game.actors.ui.MainMenuMessage;

public class MainMenuScreen implements Screen {

	final MyGame game;
	private final Stage uiStage;

	public MainMenuScreen(final MyGame game) {
		this.game = game;
		uiStage = new Stage();
		MainMenuMessage mainMenuMessage = new MainMenuMessage();
		uiStage.addActor(mainMenuMessage);
	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		uiStage.draw();

		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game));
			dispose();
		}
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}
}