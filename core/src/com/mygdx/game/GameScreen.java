package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final MyGame game;

	Stage mainStage;

	Player player;
	Rain rain;

	Music rainMusic;
	int dropsGathered;

	public GameScreen(final MyGame game) {
		Gdx.graphics.setSystemCursor(SystemCursor.None);

		this.game = game;

		// load the drop sound effect and the rain background "music"
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3"));
		rainMusic.setLooping(true);

		player = new Player();
		rain = new Rain();
		rain.spawnRaindrop();

		mainStage = new Stage();

		mainStage.addActor(player);
		mainStage.addActor(rain);

	}

	@Override
	public void render(float delta) {
		ScreenUtils.clear(0, 0, 0.1f, 1);

		mainStage.act();
		rain.handleCollision(player.getRectangleBoundary());

		mainStage.draw();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		rainMusic.play();
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
		rainMusic.dispose();
	}

}