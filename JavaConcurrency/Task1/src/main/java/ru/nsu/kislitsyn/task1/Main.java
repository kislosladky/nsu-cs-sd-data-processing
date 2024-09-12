package ru.nsu.kislitsyn.task1;

public class Main {
    private static class Child extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                System.out.println("Child thread");
            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println("Parent process");
        }
        try {
            child.join();
        } catch (InterruptedException ignored) {
        }
    }
}