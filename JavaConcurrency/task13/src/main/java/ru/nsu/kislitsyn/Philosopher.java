package ru.nsu.kislitsyn;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread {
    ReentrantLock[] forks;
    Random random = new Random();
    int id;
    ReentrantLock forkLock;
    Condition forkCondition;

    public Philosopher(int id, ReentrantLock[] forks, ReentrantLock forkLock, Condition forkCondition) {
        this.id = id;
        this.forks = forks;
        this.forkLock = forkLock;
        this.forkCondition = forkCondition;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }

            forkLock.lock();
            if (forks[0].isLocked() || forks[1].isLocked()) {
                System.out.println("Philosopher " + id + " is waiting");
                try {
                    forkCondition.await();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
            forks[0].lock();
            forks[1].lock();
            forkLock.unlock();


            System.out.println("Philosopher " + id + " is eating");
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }

            forks[0].unlock();
            forks[1].unlock();

            forkLock.lock();
            forkCondition.signal();
            forkLock.unlock();

            System.out.println("Philosopher " + id + " is thinking");
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }
        }
    }
}