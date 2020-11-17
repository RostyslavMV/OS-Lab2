package com.dreamteam.os.lab2.experiment.types;

import com.dreamteam.os.lab2.experiment.Counter;

public class Synchronized implements Counter {
  private final Object lock = new Object();
  private int counter;

  @Override
  public long getCounter() {
    synchronized (lock) {
      return counter;
    }
  }

  @Override
  public void increment() {
    synchronized (lock) {
      ++counter;
    }
  }
}
