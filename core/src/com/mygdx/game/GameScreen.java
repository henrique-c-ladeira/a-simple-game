package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen implements Screen {
	final MyGame game;

	Player player;
	Enemy enemy;

	Music rainMusic;
	OrthographicCamera camera;
	Array<Enemy> raindrops;
	long lastDropTime;
	int dropsGathered;

	public GameScreen(final MyGame game) {
		this.game = game;

		// load the images for the droplet and the bucket, 64x64 pixels each
		player = new Player();
		enemy = new Enemy();

		// load the drop sound effect and the rain background "music"
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
		rainMusic.setLooping(true);

		// create the camera and the SpriteBatch
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		// create a Rectangle to logically represent the bucket

		// create the raindrops array and spawn the first raindrop
		raindrops = new Array<Enemy>();
		spawnRaindrop();

	}

	private void spawnRaindrop() {
		Enemy enemy = new Enemy();
		enemy.spawn();
		raindrops.add(enemy);
		lastDropTime = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {
		// clear the screen with a dark blue color. The
		// arguments to clear are the red, green
		// blue and alpha component in the range [0,1]
		// of the color to be used to clear the screen.
		ScreenUtils.clear(0, 0, 0.2f, 1);

		// tell the camera to update its matrices.
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		// begin a new batch and draw the bucket and
		// all drops
		game.batch.begin();
		game.font.draw(game.batch, "Drops Collected: " + dropsGathered, 0, 480);
		player.draw(game);
		for (Enemy enemy : raindrops) {
			game.batch.draw(enemy.dropImage, enemy.raindrop.x, enemy.raindrop.y);
		}
		game.batch.end();

		player.processUserInput(camera);

		// check if we need to create a new raindrop
		if (TimeUtils.nanoTime() - lastDropTime > 1000000000)
			spawnRaindrop();

		// move the raindrops, remove any that are beneath the bottom edge of
		// the screen or that hit the bucket. In the later case we increase the
		// value our drops counter and add a sound effect.
		Iterator<Enemy> iter = raindrops.iterator();
		while (iter.hasNext()) {
			Enemy enemy = iter.next();
			enemy.raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
			if (enemy.raindrop.y + 64 < 0)
				iter.remove();
			if (enemy.raindrop.overlaps(player.bucket)) {
				dropsGathered++;
				enemy.dropSound.play();
				iter.remove();
			}
		}
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
		enemy.dropImage.dispose();
		enemy.dropSound.dispose();
		player.bucketImage.dispose();
		rainMusic.dispose();
	}

}