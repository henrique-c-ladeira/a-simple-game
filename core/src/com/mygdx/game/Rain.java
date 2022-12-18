package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Rain extends Actor {
  Array<Particle> raindrops;
  long lastDropTime;

  public Rain() {
    raindrops = new Array<Particle>();
    lastDropTime = TimeUtils.nanoTime();
  }

  @Override
  public void draw(Batch batch, float dt) {
    for (Particle enemy : raindrops) {
      batch.draw(enemy.dropImage, enemy.raindrop.x, enemy.raindrop.y);
    }
  }

  public void spawnRaindrop() {
    Particle enemy = new Particle();
    enemy.spawn();
    raindrops.add(enemy);
    lastDropTime = TimeUtils.nanoTime();
  }

  public void moveDown() {
    Iterator<Particle> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Particle enemy = iter.next();
      enemy.moveDown();
      if (enemy.raindrop.y + 64 < 0)
        iter.remove();
    }
  }

  public void handleCollision(Rectangle collidingObject) {
    Iterator<Particle> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Particle enemy = iter.next();
      if (enemy.raindrop.overlaps(collidingObject)) {
        // dropsGathered++;
        enemy.dropSound.play();
        iter.remove();
      }
    }
  }

  public boolean shouldSpawnRaindrop() {
    return TimeUtils.nanoTime() - lastDropTime > 1000000000;
  }

  @Override
  public void act(float delta) {
    // TODO Auto-generated method stub
    super.act(delta);

    // check if we need to create a new raindrop
    if (shouldSpawnRaindrop())
      spawnRaindrop();

    moveDown();
  }

}
