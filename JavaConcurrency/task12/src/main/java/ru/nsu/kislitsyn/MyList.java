package ru.nsu.kislitsyn;

public class MyList {
    final Node head = new Node(null);
    int length = 0;

    void add(String toAdd) {
        synchronized (head) {
            Node nodeToAdd = new Node(toAdd);
            nodeToAdd.next = head.next;
            nodeToAdd.previous = head;
            head.next = nodeToAdd;
            if (length == 0) {
                head.previous = nodeToAdd;
            }
            length++;
        }
    }

    void sort() {
        synchronized (head) {
            boolean swapped;
            Node current;

            if (head == head.next) return;

            do {
                swapped = false;
                current = head;

                while (current.next != head) {

                    if (current.compare(current.next) > 0) {
                        current.swapWithNext();
                        swapped = true;
                    } else {
                        current = current.next;
                    }
                }
            } while (swapped);
        }
    }

    void printList() {
        synchronized (head) {
            Node current = head.next;
            for (int i = 0; i < length; i++) {
                System.out.print(current.value + " ");
                current = current.next;
            }
            System.out.println();
        }
    }
}
