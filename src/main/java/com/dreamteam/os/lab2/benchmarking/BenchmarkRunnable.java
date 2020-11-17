package com.dreamteam.os.lab2.benchmarking;

import java.util.concurrent.locks.Lock;

public class BenchmarkRunnable implements Runnable {
  private Lock lock;
  private int operationsCount = 0;
  private volatile boolean isRunning = true;

  public BenchmarkRunnable(Lock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    while (isRunning) {
      lock.lock();
      lock.unlock();
      operationsCount++;
    }
  }

  public void stopRunning()
  {
    isRunning = false;
  }

  public int getOperationsCount() {
    return operationsCount;
  }
}
