package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Cursor.SystemCursor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGame;
import com.mygdx.game.actors.ParticleSpawner;
import com.mygdx.game.actors.Player;

public class GameScreen implements Screen {
	final MyGame game;

	private Stage mainStage;

	private Player player;
	private ParticleSpawner rain;

	private Music rainMusic;

	public GameScreen(final MyGame game) {
		this.game = game;

		Gdx.graphics.setSystemCursor(SystemCursor.None);

		// load the drop sound effect and the rain background "music"
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("background-music.mp3"));
		rainMusic.setLooping(true);

		player = new Player();
		rain = new ParticleSpawner();
		rain.spawnParticle();

		mainStage = new Stage();

		mainStage.addActor(rain);
		mainStage.addActor(player);

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