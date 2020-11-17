package com.dreamteam.os.lab2;

public class BakeryLockRunnable implements Runnable{

    private BakeryLock bakeryLock;
    static Integer counter=0;
    public  BakeryLockRunnable(BakeryLock l){
        bakeryLock=l;
    }
    @Override
    public void run() {
        bakeryLock.register();
       bakeryLock.lock();
        if(bakeryLock.getId()%2==0){
            counter++;
        } else{
            counter--;
        }
        System.out.println("Current counter = "+counter );
        bakeryLock.unlock();
    }
}
