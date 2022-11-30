package com.mygdx.game;

import java.util.Iterator;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Rain {
  Array<Enemy> raindrops;
  long lastDropTime;

  public Rain() {
    raindrops = new Array<Enemy>();
    lastDropTime = TimeUtils.nanoTime();
  }

  public void draw(MyGame game) {
    for (Enemy enemy : raindrops) {
      game.batch.draw(enemy.dropImage, enemy.raindrop.x, enemy.raindrop.y);
    }
  }

  public void spawnRaindrop() {
    Enemy enemy = new Enemy();
    enemy.spawn();
    raindrops.add(enemy);
    lastDropTime = TimeUtils.nanoTime();
  }

  public void moveDown() {
    Iterator<Enemy> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Enemy enemy = iter.next();
      enemy.moveDown();
      if (enemy.raindrop.y + 64 < 0)
        iter.remove();
    }
  }

  public void handleCollision(Rectangle collidingObject) {
    Iterator<Enemy> iter = raindrops.iterator();
    while (iter.hasNext()) {
      Enemy enemy = iter.next();
      if (enemy.raindrop.overlaps(collidingObject)) {
        // dropsGathered++;
        enemy.dropSound.play();
        iter.remove();
      }
    }
  }
}
