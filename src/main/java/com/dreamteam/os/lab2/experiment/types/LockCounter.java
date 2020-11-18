package com.dreamteam.os.lab2.experiment.types;

import com.dreamteam.os.lab2.SpinLock;
import com.dreamteam.os.lab2.experiment.Counter;
import java.util.concurrent.locks.Lock;

public class LockCounter implements Counter {
  private Lock lock = new SpinLock();
  private long counter;

  public LockCounter(Lock lock) {
    this.lock = lock;
  }

  @Override
  public long getCounter() {
    try {
      lock.lock();
      return counter;
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void increment() {
    try {
      lock.lock();
      ++counter;
    } finally {
      lock.unlock();
    }
  }
}
