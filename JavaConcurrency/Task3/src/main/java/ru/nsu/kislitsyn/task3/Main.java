package ru.nsu.kislitsyn.task3;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class Child extends Thread {
        private final List<String> toPrint;
        private final String postfix;
        public Child(List<String> toPrint, String postfix) {
            this.toPrint = toPrint;
            this.postfix = postfix;
        }

        @Override
        public void run() {
            for (String str : toPrint) {
                System.out.println(str + postfix);
            }
        }
    }
    public static void main(String[] args) {
        List<String> toPrint = new ArrayList<>();
        toPrint.add("Can you hear the silence ");
        toPrint.add("Can you see the dark ");
        toPrint.add("Can you fix the broken ");
        toPrint.add("Can you feel my heart ");
        toPrint.add("I tried so hard and got so far ");
        toPrint.add("But in the end it doesn't even matter ");

        List<Child> children = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            Child child = new Child(toPrint, Integer.toString(i));
            child.start();
            children.add(child);
        }

        try {
            for (Child child : children) {
                child.join();
            }
        } catch (InterruptedException ignored) {
        }
    }
}