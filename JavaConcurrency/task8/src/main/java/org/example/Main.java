package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sun.misc.Signal;
import sun.misc.SignalHandler;


public class Main {
    public static class Child extends Thread {
        private final int startIndex;
        private final int step;
        private double result = 0;
        private long iterations = 0;
        private long finalIteration = Long.MAX_VALUE;
        public Child (int startIndex, int step) {
            this.startIndex = startIndex;
            this.step = step;
        }

        public double getResult() {
            return result;
        }

        public long getIterations() {
            return iterations;
        }

        public void setFinalIteration(long finalIteration) {
            this.finalIteration = finalIteration;
        }

        @Override
        public void run() {
            for (long i = startIndex; iterations < finalIteration; i += step) {
                double num = 1.0 / ((2 * i) + 1);

                if (i % 2 != 0) {
                    num = -num;
                }

                result += num;
                iterations++;

                if (Thread.interrupted()) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
            }
        }
    }

    List<Child> children;

    void start(int childNumber) {
        children = new ArrayList<>();
        for (int i = 0; i < childNumber; i++) {
            Child child = new Child(i, childNumber);
            child.start();
            children.add(child);
        }
    }

    void finish() {
        System.out.println("finishing");
        setMaxIteration();
        double result = 0;
        try {
            for (Child child : children) {
                child.join();
                result += child.getResult();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }

        System.out.println(result*4);

//        System.out.println("Result is " + result*4);
    }

    private void setMaxIteration() {
        long maxIteration = 0;
        for (Child child : children) {
            child.interrupt();
            long iteration = child.getIterations();
            if (iteration > maxIteration) {
                maxIteration = iteration;
            }
        }
        for (Child child : children) {
            child.setFinalIteration(maxIteration);
        }


    }

    public static void main(String[] args) {
        Main main = new Main();

        SignalHandler handler = signal -> {
            System.out.println("Получен сигнал: " + signal.getName());
            main.finish();
        };
        Signal.handle(new Signal("INT"), handler);



        int threadNumber;
        System.out.println("How many threads do you want?");
        try (Scanner sc = new Scanner(System.in)) {
            threadNumber = sc.nextInt();
        }

        main.start(threadNumber);


    }
}