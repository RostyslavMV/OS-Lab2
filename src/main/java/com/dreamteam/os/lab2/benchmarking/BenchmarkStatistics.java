package com.dreamteam.os.lab2.benchmarking;

import com.dreamteam.os.lab2.BakeryLock;
import com.dreamteam.os.lab2.DekkersLock;
import com.dreamteam.os.lab2.SpinLock;
import com.dreamteam.os.lab2.experiment.types.CounterTypes;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.LogManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BenchmarkStatistics {
  public static void main(String[] args) throws InterruptedException {
    LogManager.getLogManager().reset();
    BenchmarkLocks benchmarkPerformance = new BenchmarkLocks(5, 2, 1000, TimeUnit.MILLISECONDS);

    BakeryLock bakeryLock = new BakeryLock(1);
    DekkersLock dekkersLock = new DekkersLock();
    SpinLock spinLock = new SpinLock();
    ReentrantLock reentrantLock = new ReentrantLock();
//    ImprovedBakeryLock improvedBakeryLock = new ImprovedBakeryLock(1);

    System.out.println("Performance of the Bakery Lock: "); // 23960 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(bakeryLock);

//    System.out.println("Performance of the Improved Bakery Lock"); // 11435 ops/MILLISECOND
//    benchmarkPerformance.measurePerformance(improvedBakeryLock);

    System.out.println("Performance of the Dekker's Lock: "); // 35375 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(dekkersLock);

    System.out.println("Performance of the Spin Lock:"); // 48973 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(spinLock);

    System.out.println("Performance of the Reentrant Lock"); // 50690 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(reentrantLock);

    System.out.println("Performance of the Atomic Counter"); // 56690 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(CounterTypes.ATOMIC);

    System.out.println("Performance of the Synchronized Counter"); // 130820 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(CounterTypes.SYNCHRONIZED);

    System.out.println("Performance of the Read-Write lock Counter"); // 30820 ops/MILLISECOND
    benchmarkPerformance.measurePerformance(CounterTypes.READ_WRITE_LOCK);
  }
}
