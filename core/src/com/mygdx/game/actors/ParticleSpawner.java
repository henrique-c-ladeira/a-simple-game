package com.mygdx.game.actors;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ParticleSpawner extends Actor {
  Array<Particle> raindrops;
  long lastDropTime;

  public ParticleSpawner() {
    raindrops = new Array<Particle>();
    lastDropTime = TimeUtils.nanoTime();
  }

  @Override
  public void draw(Batch batch, float dt) {
    for (Particle enemy : raindrops) {
      batch.draw(enemy.dropImage, enemy.boundary.x, enemy.boundary.y);
    }
  }

  public void spawnParticle() {
    Particle enemy = new Particle();
    enemy.spawn();
    raindrops.add(enemy);
    lastDropTime = TimeUtils.nanoTime();
  }

  public void moveDown() {
    Iterator<Particle> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Particle particle = iter.next();
      particle.moveDown();
      if (particle.boundary.y + 64 < 0)
        iter.remove();
    }
  }

  public void handleCollision(Rectangle collidingObject) {
    Iterator<Particle> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Particle enemy = iter.next();
      if (enemy.boundary.overlaps(collidingObject)) {
        // dropsGathered++;
        enemy.dropSound.play();
        iter.remove();
      }
    }
  }

  public boolean shouldSpawnParticle() {
    return TimeUtils.nanoTime() - lastDropTime > 1000000000;
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (shouldSpawnParticle())
      spawnParticle();

    moveDown();
  }

}
