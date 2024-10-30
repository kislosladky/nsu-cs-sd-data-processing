package ru.nsu.kislitsyn;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class Philosophers {
    public static void main(String[] args) {
        ReentrantLock forkLock = new ReentrantLock();
        Condition forkCondition = forkLock.newCondition();
        ReentrantLock[] forkArr = new ReentrantLock[5];
        for (int i = 0; i < 5; i++) {
            forkArr[i] = new ReentrantLock();
        }

        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Philosopher philosopher =
                    new Philosopher(i, new ReentrantLock[]{forkArr[i], forkArr[(i + 1) % 5]}, forkLock, forkCondition);
            philosopher.start();
            philosophers.add(philosopher);
        }
    }
}