package ru.nsu.kislitsyn.task9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private int id;
    private final Lock lock = new ReentrantLock();

    public Fork (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void lock() {
        lock.lock();
        System.out.println("Fork " + id + " is locked");
    }

    public void unlock() {
        lock.unlock();
        System.out.println("Fork " + id + " is free");
    }

    public boolean tryLock() {
        boolean result = lock.tryLock();
        if (result) {
            System.out.println("Fork " + id + " was not unlocked yet");
        } else {
            System.out.println("Fork " + id + " is locked");
        }
        return result;
    }
}
