package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
  private static final int FRAME_COLS = 1, FRAME_ROWS = 4;

  Animation<TextureRegion> walkAnimation;
  Texture walkSheet;
  float stateTime;
  boolean flip;
  private Rectangle boundary;

  public Player() {
    walkSheet = new Texture(Gdx.files.internal("dwarf.png"));
    setWidth(64);
    setHeight(64);
    boundary = new Rectangle(getX(), getY(), getWidth(), getHeight());
    setX(800 / 2 - 64 / 2); // center the bucket horizontally
    setY(20); // bottom left corner of the bucket is 20 pixels above
    // the bottom screen edge

    // Use the split utility method to create a 2D array of TextureRegions. This is
    // possible because this sprite sheet contains frames of equal size and they are
    // all aligned.
    TextureRegion[][] tmp = TextureRegion.split(walkSheet,
        64,
        64);

    // Place the regions into a 1D array in the correct order, starting from the top
    // left, going across first. The Animation constructor requires a 1D array.
    TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
    int index = 0;
    for (int i = 1; i < 2; i++) {
      for (int j = 0; j < 4; j++) {
        walkFrames[index++] = tmp[i][j];
      }
    }

    // Initialize the Animation with the frame interval and array of frames
    walkAnimation = new Animation<TextureRegion>(0.2f, walkFrames);

    // Instantiate a SpriteBatch for drawing and reset the elapsed animation
    // time to 0
    stateTime = 0f;
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
    TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

    batch.draw(currentFrame, flip ? getX() + getWidth() : getX(), getY(),
        flip ? -getWidth() : getWidth(), getHeight());
  }

  @Override
  public void setX(float x) {
    super.setX(x);
    boundary.setX(x);
  }

  @Override
  public void setY(float y) {
    super.setY(y);
    boundary.setY(y);
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      flip = true;
      setX(getX() - 200 * delta);
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      flip = false;
      setX(getX() + 200 * delta);
    }

    if (getX() < 0)
      setX(0);
    if (getX() > 800 - 64)
      setX(800 - 64);
  }

  public Rectangle getBoundary() {
    return boundary;
  }

}
