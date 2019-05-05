package net.richardlavoie.jivesoftware.cardgame.data;

public enum CardSuit {

    HEART(1),
    SPADE(2),
    CLUB(3),
    DIAMOND(4);

    private final int order;

    CardSuit(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

}
