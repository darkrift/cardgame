package net.richardlavoie.jivesoftware.cardgame.rest.visitor;

public class Counter {

    private int value = 0;

    public void inc() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}
