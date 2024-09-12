package ru.nsu.kislitsyn.task4;

public class Main {
    public static class Child extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("Still printing");
            }
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