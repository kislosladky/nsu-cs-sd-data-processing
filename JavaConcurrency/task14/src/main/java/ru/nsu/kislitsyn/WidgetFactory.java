package ru.nsu.kislitsyn;


import java.util.concurrent.Semaphore;

class WidgetFactory {
    private final Semaphore detailA = new Semaphore(0);
    private final Semaphore detailB = new Semaphore(0);
    private final Semaphore detailC = new Semaphore(0);
    private final Semaphore module = new Semaphore(0);

    public void produce() {
        new Thread(this::produceDetailA).start();
        new Thread(this::produceDetailB).start();
        new Thread(this::produceDetailC).start();
        new Thread(this::assembleModule).start();
        new Thread(this::assembleWidget).start();
    }

    private void produceDetailA() {
        try {
            while (true) {
                Thread.sleep(1000);
                detailA.release();
                System.out.println("Part A is done");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void produceDetailB() {
        try {
            while (true) {
                Thread.sleep(2000);
                detailB.release();
                System.out.println("Part B is done");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void produceDetailC() {
        try {
            while (true) {
                Thread.sleep(3000);
                detailC.release();
                System.out.println("Part C is done");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void assembleModule() {
        try {
            while (true) {
                detailA.acquire();
                detailB.acquire();
                module.release();
                System.out.println("Module is assembled from parts A and B");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void assembleWidget() {
        try {
            while (true) {
                module.acquire();
                detailC.acquire();
                System.out.println("Widget is assembled from module and part C");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        WidgetFactory factory = new WidgetFactory();
        factory.produce();
    }
}
