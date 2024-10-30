package ru.nsu.kislitsyn;

import java.time.Duration;
import java.util.Scanner;


public class Main {
    static class Child extends Thread {
        Node list;

        Child(Node list) {
            this.list = list;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(Duration.ofSeconds(5));
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
                list.sort();
            }
        }
    }


    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            Node list = new Node(input);
            Child child = new Child(list);
            child.start();

            while (true) {
                input = sc.nextLine();
                int currentIndex = 0;
                if (input.compareTo("") == 0) {
                    list.printList();
                    continue;
                }
                while (currentIndex < input.length()) {
                    list.addNext(new Node(input.substring(currentIndex, Math.min(currentIndex + 80, input.length()))));
                    currentIndex += 80;
                }
            }
        }
    }
}
