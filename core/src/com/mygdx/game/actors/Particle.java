package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Particle extends Actor {
  Texture image;
  Sound collisionSound;
  private Rectangle boundary;

  public Particle() {
    image = new Texture(Gdx.files.internal("coin.png"));
    collisionSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));

    setWidth(16);
    setHeight(16);
    boundary = new Rectangle(getX(), getY(), getWidth(), getHeight());

    setX(MathUtils.random(0, 800 - 64));
    setY(480);
  }

  @Override
  public void setY(float y) {
    super.setY(y);
    boundary.setY(getY() - 200 * Gdx.graphics.getDeltaTime());
  }

  @Override
  public void act(float delta) {
    setY(getY() - 200 * Gdx.graphics.getDeltaTime());
  }

  public boolean overlaps(Rectangle collingObject) {
    return boundary.overlaps(collingObject);
  }

}
