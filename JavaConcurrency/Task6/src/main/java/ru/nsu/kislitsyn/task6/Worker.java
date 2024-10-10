package ru.nsu.kislitsyn.task6;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Worker implements Runnable {
    private final Department department;
    private int result;
    private CyclicBarrier barrier;

    public Worker(CyclicBarrier barrier, Department department) {
        this.department = department;
        this.barrier = barrier;
    }

    public int getResult() {
        return result;
    }

    @Override
    public void run() {
        department.performCalculations();
        result = department.getCalculationResult();

        System.out.println("My result is " + result);

        try {
            barrier.await();
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

    }
}
