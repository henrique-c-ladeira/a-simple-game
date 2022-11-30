package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

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

  public void setXPosition(float x) {
    bucket.x = x - 64 / 2;
  }

}
