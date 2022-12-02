package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {
  private Rectangle boundary;
  Animation<TextureRegion> walkAnimation; // Must declare frame type (TextureRegion)
  Texture walkSheet;
  private static final int FRAME_COLS = 1, FRAME_ROWS = 4;
  float stateTime;
  boolean flip;

  public Player() {
    walkSheet = new Texture(Gdx.files.internal("dwarf.png"));
    boundary = new Rectangle();
    boundary.x = 800 / 2 - 64 / 2; // center the bucket horizontally
    boundary.y = 20; // bottom left corner of the bucket is 20 pixels above
    // the bottom screen edge
    boundary.width = 64;
    boundary.height = 64;

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

  public void draw(MyGame game) {
    stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time
    TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);

    game.batch.draw(currentFrame, flip ? boundary.x + boundary.width : boundary.x, boundary.y,
        flip ? -boundary.width : boundary.width, boundary.height);
  }

  public void processUserInput() {
    if (Gdx.input.isKeyPressed(Keys.LEFT)) {
      flip = true;
      boundary.x -= 200 * Gdx.graphics.getDeltaTime();
    }
    if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
      flip = false;
      boundary.x += 200 * Gdx.graphics.getDeltaTime();
    }

    // make sure the bucket stays within the screen bounds
    if (boundary.x < 0)
      boundary.x = 0;
    if (boundary.x > 800 - 64)
      boundary.x = 800 - 64;
  }

  public Rectangle getRectangleBoundary() {
    return this.boundary;
  }

}
