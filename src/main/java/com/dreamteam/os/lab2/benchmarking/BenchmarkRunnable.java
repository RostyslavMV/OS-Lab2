package com.dreamteam.os.lab2.benchmarking;

import com.dreamteam.os.lab2.AbstractFixnumLock;
import com.dreamteam.os.lab2.experiment.AccessCounterTest;
import com.dreamteam.os.lab2.experiment.Counter;
import com.dreamteam.os.lab2.experiment.types.CounterTypes;
import com.dreamteam.os.lab2.experiment.types.LockCounter;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenchmarkRunnable implements Runnable {
  private Lock lock;
  private Counter counter;
  private long operationsCount;
  private volatile boolean isRunning;

  public BenchmarkRunnable(Lock lock) {
    this.lock = lock;
    this.isRunning = true;
    this.counter = new LockCounter(lock);
  }

  // in cases of counters that don't use locks
  public BenchmarkRunnable(CounterTypes counterType) {
    counter = AccessCounterTest.getCounter(counterType);
    isRunning = true;
  }

  @Override
  public void run() {
    registerLock();
    while (isRunning) {
      counter.increment();
    }
    operationsCount = counter.getCounter();
    deregisterLock();
  }

  public void registerLock() {
    if (lock instanceof AbstractFixnumLock) ((AbstractFixnumLock) lock).register();
  }

  public void deregisterLock() {
    if (lock instanceof AbstractFixnumLock) ((AbstractFixnumLock) lock).unregister();
  }

  public void stopRunning() {
    isRunning = false;
  }

  public long getOperationsCount() {
    return operationsCount;
  }

}
