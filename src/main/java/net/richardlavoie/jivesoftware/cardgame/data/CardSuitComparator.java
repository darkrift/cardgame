package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.Comparator;

public class CardSuitComparator implements Comparator<CardSuit> {

    @Override
    public int compare(CardSuit o1, CardSuit o2) {
        return Integer.compare(o1.getOrder(), o2.getOrder());
    }
}
