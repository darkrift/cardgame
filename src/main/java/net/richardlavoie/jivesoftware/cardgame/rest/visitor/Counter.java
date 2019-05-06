package net.richardlavoie.jivesoftware.cardgame.rest.visitor;

/**
 * Class representing a simple counter.
 */
public class Counter {

    private int value = 0;

    public void inc() {
        this.value++;
    }

    public int getValue() {
        return value;
    }
}
