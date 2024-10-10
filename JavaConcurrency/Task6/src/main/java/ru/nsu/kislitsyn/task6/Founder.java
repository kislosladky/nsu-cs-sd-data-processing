package ru.nsu.kislitsyn.task6;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public final class Founder {
    private final List<Worker> workers;
    private CyclicBarrier barrier;
    private ResultCounter resultCounter;
    public Founder(final Company company) {
        int departmentsCount = company.getDepartmentsCount();
        this.workers = new ArrayList<>(departmentsCount);
        this.resultCounter = new ResultCounter(this.workers);
        barrier = new CyclicBarrier(departmentsCount, resultCounter);


        for (int i = 0; i < departmentsCount; i++) {
            workers.add(new Worker(barrier, company.getFreeDepartment(i)));
        }

    }
    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }

    public static void main(String[] args) {
        Company company = new Company(7);
        Founder founder = new Founder(company);
        founder.start();
    }
}