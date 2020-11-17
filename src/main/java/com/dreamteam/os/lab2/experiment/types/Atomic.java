package com.dreamteam.os.lab2.experiment.types;

import com.dreamteam.os.lab2.experiment.Counter;
import java.util.concurrent.atomic.AtomicLong;

public class Atomic implements Counter {
  private final AtomicLong atomic = new AtomicLong();

  @Override
  public long getCounter() {
    return atomic.get();
  }

  @Override
  public void increment() {
    atomic.incrementAndGet();
  }
}
