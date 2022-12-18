package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Particle {
  Texture dropImage;
  Sound dropSound;
  Rectangle boundary;

  public Particle() {
    dropImage = new Texture(Gdx.files.internal("coin.png"));
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
  }

  public void spawn() {
    boundary = new Rectangle();
    boundary.x = MathUtils.random(0, 800 - 64);
    boundary.y = 480;
    boundary.width = 16;
    boundary.height = 16;
  }

  public void handleCollision(Rectangle collidingObject) {
    if (boundary.overlaps(collidingObject)) {
      dropSound.play();
    }
  }

  public void moveDown() {
    boundary.y -= 200 * Gdx.graphics.getDeltaTime();
  }

}
