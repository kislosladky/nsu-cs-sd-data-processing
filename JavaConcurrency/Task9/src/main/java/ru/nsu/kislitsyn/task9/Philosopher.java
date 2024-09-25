package ru.nsu.kislitsyn.task9;

import java.util.Random;
import java.util.concurrent.locks.Lock;


public class Philosopher extends Thread {
    Fork[] forks;
    Random random = new Random();
    int id;
    public Philosopher(int id, Fork[] forks) {
        this.id = id;
        this.forks = forks;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }

            forks[0].lock();
            forks[1].lock();
            System.out.println("Philosopher " + id + " is eating");
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }

            forks[0].unlock();
            forks[1].unlock();

            System.out.println("Philosopher " + id + " is thinking");
            try {
                Thread.sleep(500L * random.nextInt(1, 10));
            } catch (InterruptedException exception) {
                return;
            }
        }
    }
}
