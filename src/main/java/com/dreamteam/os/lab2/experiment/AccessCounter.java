package com.dreamteam.os.lab2.experiment;

import com.dreamteam.os.lab2.experiment.types.CounterTypes;

public class AccessCounter {

  public static void main(String[] args) throws InterruptedException {
    System.out.println("Atomic counter: ");
    AccessCounterTest.testCounter(CounterTypes.ATOMIC);

    System.out.println("Read-write lock counter: ");
    AccessCounterTest.testCounter(CounterTypes.READ_WRITE_LOCK);

    System.out.println("Synchronized counter: ");
    AccessCounterTest.testCounter(CounterTypes.SYNCHRONIZED);
  }
}
