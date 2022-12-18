package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Particle {
  Texture dropImage;
  Sound dropSound;
  Rectangle raindrop;

  public Particle() {
    dropImage = new Texture(Gdx.files.internal("coin.png"));
    dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
  }

  public void spawn() {
    raindrop = new Rectangle();
    raindrop.x = MathUtils.random(0, 800 - 64);
    raindrop.y = 480;
    raindrop.width = 16;
    raindrop.height = 16;
  }

  public void handleCollision(Rectangle collidingObject) {
    if (raindrop.overlaps(collidingObject)) {
      // dropsGathered++;
      dropSound.play();
    }
  }

  public void moveDown() {
    raindrop.y -= 200 * Gdx.graphics.getDeltaTime();

  }

}
