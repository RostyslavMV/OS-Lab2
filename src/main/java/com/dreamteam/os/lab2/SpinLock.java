package com.dreamteam.os.lab2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpinLock implements Lock {
  private AtomicReference<Thread> atomicReference = new AtomicReference<>();

  @Override
  public void lock() {
    Thread thread = Thread.currentThread();
    while (!atomicReference.compareAndSet(null, thread)) {}
  }

  @Override
  public void lockInterruptibly() throws InterruptedException {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean tryLock() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
    throw new UnsupportedOperationException();
  }

  @Override
  public void unlock() {
    atomicReference.compareAndSet(Thread.currentThread(), null);
  }

  @Override
  public Condition newCondition() {
    throw new UnsupportedOperationException();
  }
}