package com.dreamteam.os.lab2.benchmarking;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenchmarkLocks {
  private int iterations;
  private TimeUnit timeUnit;
  private long time;
  private int warmup;
  private boolean showIntermediateData = false;

  public BenchmarkLocks(int iterations, int warmup, long time, TimeUnit timeUnit) {
    this.iterations = iterations;
    this.time = time;
    this.timeUnit = timeUnit;
    this.warmup = warmup;
  }

  public void measurePerformance(Lock lock) throws InterruptedException {
    int totalIterations = iterations + warmup;
    long average = 0;
    for (int i = 0; i < totalIterations; i++) {
      BenchmarkRunnable benchmarkRunnable = new BenchmarkRunnable(lock);

      ExecutorService service = Executors.newFixedThreadPool( 1) ;
      service.submit(benchmarkRunnable);
      service.awaitTermination(time, timeUnit);
      benchmarkRunnable.stopRunning();
      service.shutdown();


      int operationsNumber = benchmarkRunnable.getOperationsCount();
      long currentStepAverage = operationsNumber / time;

      boolean currentlyInWarmup = i >= warmup;
      if (currentlyInWarmup) {
        average += currentStepAverage;
      }

      if (showIntermediateData) {
        if (currentlyInWarmup) System.out.println("Warmup: ");
        System.out.println(
            "Average time for the operation on the current step: "
                + currentStepAverage
                + " ops/" + timeUnit);
      }
    }

    average = average / iterations;
    System.out.println("Average time = " + average + " ops/" + timeUnit);
  }

  public void showIntermediateData(boolean show) {
    showIntermediateData = show;
  }

  public void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public long getTime() {
    return time;
  }
}
