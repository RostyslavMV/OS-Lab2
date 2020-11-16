package com.dreamteam.os.lab2.benchmarking;

import java.util.concurrent.locks.Lock;

public class BenchmarkRunnable implements Runnable {
  private Lock lock;
  private int operationsCount = 0;

  public BenchmarkRunnable(Lock lock) {
    this.lock = lock;
  }

  @Override
  public void run() {
    while (!Thread.currentThread().isInterrupted()) {
      lock.lock();
      lock.unlock();
      operationsCount++;
    }
  }

  public int getOperationsCount() {
    return operationsCount;
  }
}
