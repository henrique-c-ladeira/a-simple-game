package com.mygdx.game.actors.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MainMenuMessage extends Actor {
  private BitmapFont font;

  public MainMenuMessage() {
    font = new BitmapFont(Gdx.files.internal("font.fnt"));
  }

  @Override
  public void draw(Batch batch, float parentAlpha) {
    super.draw(batch, parentAlpha);
    font.draw(batch, "Welcome to Drop!!! ", 100, 150);
    font.draw(batch, "Tap anywhere to begin!", 100, 100);
  }
}
