package com.dreamteam.os.lab2.benchmarking;


import com.dreamteam.os.lab2.DekkersLock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class BenchmarkStatistics {
  public static void main(String[] args) throws InterruptedException {
    ReentrantLock lock = new ReentrantLock();
    BenchmarkLocks benchmarkLocks = new BenchmarkLocks(10, 15, 1000, TimeUnit.MILLISECONDS);
    benchmarkLocks.measurePerformance(lock);
  }
}
