package com.mygdx.game.actors;

import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class ParticleSpawner extends Actor {
  private Array<Particle> particlesArray;
  private long lastDropTime;

  public ParticleSpawner() {
    particlesArray = new Array<Particle>();
    lastDropTime = TimeUtils.nanoTime();
  }

  @Override
  public void draw(Batch batch, float dt) {
    for (Particle particle : particlesArray) {
      batch.draw(particle.image, particle.getX(), particle.getY());
    }
  }

  public void spawnParticle() {
    Particle particle = new Particle();
    particlesArray.add(particle);
    this.getStage().addActor(particle);
    lastDropTime = TimeUtils.nanoTime();
  }

  public boolean shouldSpawnParticle() {
    return TimeUtils.nanoTime() - lastDropTime > 1000000000;
  }

  public void handleCollision(Rectangle collidingObject) {
    Iterator<Particle> iter = particlesArray.iterator();
    while (iter.hasNext()) {
      Particle particle = iter.next();
      if (particle.overlaps(collidingObject)) {
        particle.collisionSound.play();
        iter.remove();
      }
      if (particle.getY() < -10) {
        particle.collisionSound.play();
        iter.remove();
      }
    }
  }

  @Override
  public void act(float delta) {
    super.act(delta);

    if (shouldSpawnParticle())
      spawnParticle();
    System.out.println(particlesArray.size);
  }

}
