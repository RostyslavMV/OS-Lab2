package com.dreamteam.os.lab2.experiment.types;

import com.dreamteam.os.lab2.experiment.Counter;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCounter implements Counter {
  private ReadWriteLock rwlock = new ReentrantReadWriteLock();

  private Lock readLock = rwlock.readLock();
  private Lock writeLock = rwlock.writeLock();
  private long counter;

  @Override
  public long getCounter()
  {
    try
    {
      readLock.lock();
      return counter;
    } finally {
      readLock.unlock();
    }
  }

  @Override
  public void increment()
  {
    try
    {
      writeLock.lock();
      ++counter;
    } finally {
      writeLock.unlock();
    }
  }
}
