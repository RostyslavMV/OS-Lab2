package com.dreamteam.os.lab2.experiment;

import com.dreamteam.os.lab2.experiment.types.Atomic;
import com.dreamteam.os.lab2.experiment.types.CounterTypes;
import com.dreamteam.os.lab2.experiment.types.ReadWriteLockCounter;
import com.dreamteam.os.lab2.experiment.types.SpinLockCounter;
import com.dreamteam.os.lab2.experiment.types.Synchronized;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AccessCounterTest {
  public static int threadNumber = 10;
  private static ExecutorService service;
  private static long start;
  private static volatile boolean resultPrinted = false;
  private static ArrayList<RunnableWithCancellation> runnables;

  public static void testCounter(CounterTypes counterType) throws InterruptedException {
    resultPrinted = false;
    runnables = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Counter counter = getCounter(counterType);

      service = Executors.newFixedThreadPool(threadNumber);
      start = System.currentTimeMillis();

      for (int j = 0; j < threadNumber; j += 2) {
        RunnableWithCancellation consumer = new Consumer(counter);
        RunnableWithCancellation producer = new Producer(counter, 1000000L);
        runnables.add(consumer);
        runnables.add(producer);
        service.submit((Runnable) consumer);
        service.submit((Runnable) producer);
      }
    }
    service.awaitTermination(10, TimeUnit.MINUTES);
  }

  public static void printResult(long endTime) {
    for (RunnableWithCancellation runnable : runnables) {
      runnable.stopRunning();
    }
    if (!resultPrinted) System.out.println("Result = " + (endTime - start));
    resultPrinted = true;
    service.shutdownNow();
  }

  private static Counter getCounter(CounterTypes counterType) {
    switch (counterType) {
      case ATOMIC:
        return new Atomic();
      case READ_WRITE_LOCK:
        return new ReadWriteLockCounter();
      case SPIN_LOCK:
        return new SpinLockCounter();
      case SYNCHRONIZED:
        return new Synchronized();
    }
    return null;
  }
}
