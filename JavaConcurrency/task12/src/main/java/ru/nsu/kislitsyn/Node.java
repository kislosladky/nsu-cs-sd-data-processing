package ru.nsu.kislitsyn;

public class Node {
    Node next;
    Node previous;
    String value;

    public Node() {
        this.next = this;
        this.previous = this;
        this.value = "";
    }

    public Node( String value) {
        this.value = value;
    }

    synchronized void addNext(Node toAdd) {
        toAdd.next = this.next;
        toAdd.previous = this;
        this.next = toAdd;
    }

    synchronized void swapWithNext() {
        Node nextNext = this.next.next;
        this.next.previous = this.previous;
        this.previous.next = this.next;
        this.previous = this.next;
        nextNext.previous = this;
        this.next = nextNext;
    }

    private int compare(Node to) {
        return this.value.compareTo(to.value);
    }

    synchronized void sort() {
        boolean swapped;
        Node current;

        // Если список пуст или содержит только один элемент, сортировать не нужно
        if (this == this.next) return;

        do {
            swapped = false;
            current = this;

            // Проходим по всему списку
            while (current.next != null && current.next != this) {
                // Если текущий элемент больше следующего, меняем их местами
                if (current.compare(current.next) > 0) {
                    current.swapWithNext();
                    swapped = true;
                } else {
                    // Переходим к следующему узлу, если обмена не было
                    current = current.next;
                }
            }
        } while (swapped);
    }

    // Вывод списка для отладки
    synchronized void printList() {
        Node current = this;
        do {
            System.out.print(current.value + " ");
            current = current.next;
        } while (current != null && current != this);
        System.out.println();
    }



}
