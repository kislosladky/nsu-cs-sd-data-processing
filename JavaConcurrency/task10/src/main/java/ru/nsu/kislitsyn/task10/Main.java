package ru.nsu.kislitsyn.task10;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    static Lock lock = new ReentrantLock();
    static Condition parentTurn = lock.newCondition();
    static Condition childTurn = lock.newCondition();
    static volatile boolean isParentTurn = true;

    private static class Child extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                lock.lock();
                try {
                    while (isParentTurn) {
                        childTurn.await();
                    }
                    System.out.println("Child thread");
                    isParentTurn = true;
                    parentTurn.signal();
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                } finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.start();
        for (int i = 0; i < 10; i++) {
            lock.lock();
            try {
                while (!isParentTurn) {
                    parentTurn.await();
                }

                System.out.println("Parent process");
                isParentTurn = false;
                childTurn.signal();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            } finally {
                lock.unlock();
            }
        }
        try {
            child.join();
        } catch (InterruptedException exception) {
            System.err.println(exception.getMessage());
        }
    }
}