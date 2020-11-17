package com.dreamteam.os.lab2.experiment.types;

import com.dreamteam.os.lab2.SpinLock;
import com.dreamteam.os.lab2.experiment.Counter;

public class SpinLockCounter implements Counter {
  private SpinLock lock = new SpinLock();
  private long counter;

  @Override
  public long getCounter()
  {
    try
    {
      lock.lock();
      return counter;
    } finally {
      lock.unlock();
    }

  }

  @Override
  public void increment()
  {
    try
    {
      lock.lock();
      ++counter;
    } finally {
      lock.unlock();
    }
  }
}
