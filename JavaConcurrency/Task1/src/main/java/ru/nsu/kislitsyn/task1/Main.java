package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private class Child extends Thread {
        @Override
        public void run() {
            for (int i = 0; i <= 10; i++) {
                System.out.println("Child thread");
            }
        }
    }

    public static void main(String[] args) {
        Child child = new Child();
        child.start();
        for (int i = 0; i <= 10; i++) {
            System.out.println("Parent process");
        }
        child.join();
    }
}