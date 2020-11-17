package com.dreamteam.os.lab2.benchmarking;

import com.dreamteam.os.lab2.AbstractFixnumLock;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenchmarkRunnable implements Runnable {
  private Lock lock;
  private int operationsCount = 0;
  private volatile boolean isRunning;

  public BenchmarkRunnable(Lock lock) {
    this.lock = lock;
    this.isRunning = true;
  }

  @Override
  public void run() {
    registerLock();
    while (isRunning) {
      lock.lock();
      lock.unlock();
      operationsCount++;
    }
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

  public int getOperationsCount() {
    return operationsCount;
  }
}
