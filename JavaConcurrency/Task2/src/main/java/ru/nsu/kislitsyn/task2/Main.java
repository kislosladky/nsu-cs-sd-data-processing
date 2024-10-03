package ru.nsu.kislitsyn.task2;


public class Main {
    private static class Child extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.println("Child thread");
            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.start();

        try {
            child.join();
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
            return;
        }

        for (int i = 0; i < 10; i++) {
            System.out.println("Parent process");
        }

    }
}