package ru.nsu.kislitsyn.task7;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static class Child extends Thread {
        private final int startIndex;
        private final int finishIndex;
        private double result = 0;

        public Child (int startIndex, int finishIndex) {
            this.startIndex = startIndex;
            this.finishIndex = finishIndex;
        }

        public double getResult() {
            return result;
        }

        @Override
        public void run() {
            for (int i = startIndex; i < finishIndex; i += 1) {
                double num = 1.0 / ((2 * i) + 1);

                if (i % 2 != 0) {
                    num = -num;
                }

                result += num;
            }
        }
    }


    public static void main(String[] args) {
        int sumLength = 10000;

        int threadNumber;
        System.out.println("How many threads do you want?");
        try (Scanner sc = new Scanner(System.in)) {
             threadNumber = sc.nextInt();
        }

        List<Child> children = new ArrayList<>();
        for (int i = 0; i < threadNumber; i++) {
            Child child = new Child(i * sumLength, (i + 1) * sumLength);
            child.start();
            children.add(child);
        }

        double result = 0;

        try {
            for (Child child : children) {
                child.join();
                result += child.getResult();
            }
        } catch (InterruptedException _) {

        }

        System.out.println("Result is " + result * 4);

    }
}