package ru.nsu.kislitsyn.task10;

public class Main {
    private static final Object monitor = new Object();
    static volatile boolean isParentTurn = true;

    private static class Child extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                synchronized (monitor) {
                    while (isParentTurn) {
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                            System.err.println(e.getMessage());
                        }
                    }
                    System.out.println("Child thread");
                    isParentTurn = true;
                    monitor.notify();
                }
            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.start();

        for (int i = 0; i < 10; i++) {
            synchronized (monitor) {
                while (!isParentTurn) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        System.err.println(e.getMessage());
                    }
                }
                System.out.println("Parent process");
                isParentTurn = false;
                monitor.notify();
            }
        }

        try {
            child.join();
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
        }
    }
}
