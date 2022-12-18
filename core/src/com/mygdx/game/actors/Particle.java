package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Particle {
  Texture image;
  Sound collisionSound;
  Rectangle boundary;

  public Particle() {
    image = new Texture(Gdx.files.internal("coin.png"));
    collisionSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
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
      collisionSound.play();
    }
  }

  public void moveDown() {
    boundary.y -= 200 * Gdx.graphics.getDeltaTime();
  }

}
