package ru.nsu.kislitsyn.task3;


import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class Child extends Thread {
        private final String[] toPrint;

        public Child(String[] toPrint) {
            this.toPrint = toPrint.clone();
        }

        @Override
        public void run() {
            for (String str : toPrint) {
                System.out.println(str);
            }
        }
    }
    public static void main(String[] args) {
        String[] stringGroups = {"first word", "first thread"};
        String[][] stringChanges = {{"second word", "second child"},
                {"third word", "third child"},
                {"forth word", "forth child"}};

        for (int i = 0; i < 4; i++) {

            Child child = new Child(stringGroups);
            child.start();

            if (i < 3) {
                stringGroups = stringChanges[i];
            }
        }
    }
}