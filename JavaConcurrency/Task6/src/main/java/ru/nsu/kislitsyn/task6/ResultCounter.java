package ru.nsu.kislitsyn.task6;

import java.util.List;

public class ResultCounter implements Runnable {
    List<Worker> workers;
    private int result = 0;

    public ResultCounter(List<Worker> workers) {
        this.workers = workers;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        for (Worker worker : workers) {
            result += worker.getResult();
        }
        System.out.println("result is " + result);
    }
}
