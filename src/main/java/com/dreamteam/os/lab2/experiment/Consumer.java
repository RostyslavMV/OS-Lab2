package com.dreamteam.os.lab2.experiment;

public class Consumer implements Runnable, RunnableWithCancellation {

  private final Counter counter;
  private volatile boolean isRunning = true;

  public Consumer(Counter counter)
  {
    this.counter = counter;
  }

  @Override
  public void run() {
    while (isRunning) {
      long count = counter.getCounter();
    }
  }

  public void stopRunning()
  {
    isRunning = false;
  }
}
