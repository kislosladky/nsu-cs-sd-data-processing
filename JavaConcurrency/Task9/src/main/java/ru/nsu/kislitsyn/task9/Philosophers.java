package ru.nsu.kislitsyn.task9;


import java.util.ArrayList;
import java.util.List;


public class Philosophers {
    public static void main(String[] args) {
        Fork[] forks = new Fork[5];
        for (int i = 0; i < 5; i++) {
            forks[i] = new Fork(i);
        }

        List<Philosopher> philosophers = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Philosopher philosopher = new Philosopher(i, new Fork[]{forks[i], forks[(i + 1) % 5]});
            philosopher.start();
            philosophers.add(philosopher);
        }



    }
}