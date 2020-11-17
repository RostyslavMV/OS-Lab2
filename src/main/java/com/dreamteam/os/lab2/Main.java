package com.dreamteam.os.lab2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        BakeryLock Lock=new BakeryLock( 20);
        Thread thread1=new Thread(new BakeryLockRunnable( Lock ));
        thread1.start();
        Thread thread2=new Thread(new BakeryLockRunnable( Lock ));
        thread2.start();
        Thread thread3=new Thread(new BakeryLockRunnable( Lock ));
        thread3.start();
       Thread thread4=new Thread(new BakeryLockRunnable( Lock ));
        thread4.start();
    }
}
