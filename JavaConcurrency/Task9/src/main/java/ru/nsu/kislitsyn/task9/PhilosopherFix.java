package ru.nsu.kislitsyn.task9;

import java.util.Random;

public class PhilosopherFix extends Thread implements Philosopher{
    Fork[] forks;
    Random random = new Random();
    int id;
    public PhilosopherFix(int id, Fork[] forks) {
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
            while (!forks[1].tryLock()) {
                forks[0].unlock();
                System.out.println("Philosopher " + id + " is waiting");
                try {
                    Thread.sleep(500L * random.nextInt(1, 10));
                } catch (InterruptedException exception) {
                    return;
                }
                forks[0].lock();
            }

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
