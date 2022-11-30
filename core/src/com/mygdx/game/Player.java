package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player {
  Texture bucketImage;
  Rectangle bucket;

  public Player() {
    bucketImage = new Texture(Gdx.files.internal("bucket.png"));
    bucket = new Rectangle();
    bucket.x = 800 / 2 - 64 / 2; // center the bucket horizontally
    bucket.y = 20; // bottom left corner of the bucket is 20 pixels above
                   // the bottom screen edge
    bucket.width = 64;
    bucket.height = 64;
  }

  public void draw(MyGame game) {
    game.batch.draw(bucketImage, bucket.x, bucket.y, bucket.width, bucket.height);
  }

  public void processUserInput(OrthographicCamera camera) {
    // process user input
    if (Gdx.input.isTouched()) {
      Vector3 touchPos = new Vector3();
      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
      camera.unproject(touchPos);
      bucket.x = touchPos.x - 64 / 2;
    }

    if (Gdx.input.isKeyPressed(Keys.LEFT))
      bucket.x -= 200 * Gdx.graphics.getDeltaTime();
    if (Gdx.input.isKeyPressed(Keys.RIGHT))
      bucket.x += 200 * Gdx.graphics.getDeltaTime();

    // make sure the bucket stays within the screen bounds
    if (bucket.x < 0)
      bucket.x = 0;
    if (bucket.x > 800 - 64)
      bucket.x = 800 - 64;
  }

}
