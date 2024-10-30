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
            try {
                Thread.sleep(Duration.ofSeconds(5));
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
            list.sort();
        }
    }


    public static void main(String[] args) {
        Node list = new Node();
        Child child = new Child(list);
        child.start();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                String input = sc.nextLine();
                int currentIndex = 0;
                if (input.compareTo("") == 0) {
                    list.printList();
                }
                while (currentIndex + 80 < input.length()) {
                    list.addNext(new Node(input.substring(currentIndex, currentIndex + 80)));
                    currentIndex += 80;
                }
            }
        }
    }
}