package ru.nsu.kislitsyn.task5;

public class Main {
    public static class Child extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Still printing");
            }
            System.out.println("Interrupted");
        }
    }
    public static void main(String[] args) {
        Child child = new Child();
        child.start();
        try {
            Thread.sleep(2000);
            child.interrupt();
            child.join();
        } catch (InterruptedException ignored) {
        }
    }
}