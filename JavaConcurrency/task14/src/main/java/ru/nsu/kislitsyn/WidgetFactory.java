package ru.nsu.kislitsyn;


import java.util.concurrent.Semaphore;

class WidgetFactory {
    // Семафоры для управления доступностью деталей
    private final Semaphore detailA = new Semaphore(0);
    private final Semaphore detailB = new Semaphore(0);
    private final Semaphore detailC = new Semaphore(0);
    private final Semaphore module = new Semaphore(0);

    public void produce() {
        // Запуск потоков для производства деталей и сборки
        new Thread(this::produceDetailA).start();
        new Thread(this::produceDetailB).start();
        new Thread(this::produceDetailC).start();
        new Thread(this::assembleModule).start();
        new Thread(this::assembleWidget).start();
    }

    private void produceDetailA() {
        try {
            while (true) {
                Thread.sleep(1000); // 1 секунда на изготовление детали A
                detailA.release();
                System.out.println("Деталь A изготовлена");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void produceDetailB() {
        try {
            while (true) {
                Thread.sleep(2000); // 2 секунды на изготовление детали B
                detailB.release();
                System.out.println("Деталь B изготовлена");
            }
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    private void produceDetailC() {
        try {
            while (true) {
                Thread.sleep(3000); // 3 секунды на изготовление детали C
                detailC.release();
                System.out.println("Деталь C изготовлена");
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
                System.out.println("Модуль собран из деталей A и B");
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
                System.out.println("Винтик (Widget) собран из модуля и детали C");
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
