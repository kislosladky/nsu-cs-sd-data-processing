package ru.nsu.kislitsyn;

public class Node {
    Node next;
    Node previous;
    String value;


    public Node(String value) {
        this.next = this;
        this.previous = this;
        this.value = value;
    }

    void swapWithNext() {
        Node nextNext = this.next.next;
        this.next.previous = this.previous;
        this.previous.next = this.next;
        this.previous = this.next;
        nextNext.previous = this;
        this.next.next = this;
        this.next = nextNext;
    }

    int compare(Node to) {
        if (to.value == null) {
            return 1;
        } else if (this.value == null){
            return -1;
        }
        return this.value.compareTo(to.value);
    }
}
