package ru.nsu.kislitsyn.task6;

import java.util.ArrayList;
import java.util.List;

public final class Founder {
    private final List<Runnable> workers;
    public Founder(final Company company) {
        int departmentsCount = company.getDepartmentsCount();
        this.workers = new ArrayList<>(departmentsCount);

        for (int i = 0; i < departmentsCount; i++) {


        }

    }
    public void start() {
        for (final Runnable worker : workers) {
            new Thread(worker).start();
        }
    }
}