package com.dreamteam.os.lab2.experiment;

import com.dreamteam.os.lab2.experiment.types.CounterTypes;

public class AccessCounter {

  public static void main(String[] args) {
    System.out.println("Atomic counter: ");
    AccessCounterTest.testCounter(CounterTypes.ATOMIC);
  }
}
