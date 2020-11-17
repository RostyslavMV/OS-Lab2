package com.dreamteam.os.lab2.experiment;

public class Producer implements Runnable, RunnableWithCancellation {
  private final Counter counter;
  private long targetNumber;
  private volatile boolean isRunning = true;

  public Producer(Counter counter, long targetNumber) {
    this.counter = counter;
    this.targetNumber = targetNumber;
  }

  @Override
  public void run() {
    while (isRunning) {
      counter.increment();
      if (counter.getCounter() > targetNumber) {
        AccessCounterTest.printResult(System.currentTimeMillis());
        isRunning = false;
      }
    }
  }

  public void stopRunning() {
    isRunning = false;
  }
}
