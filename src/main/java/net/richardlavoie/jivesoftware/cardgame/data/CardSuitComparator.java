package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.Comparator;

/**
 * This comparator compares card suit for the following order : Heart, spade, club, diamond.
 */
public class CardSuitComparator implements Comparator<CardSuit> {

    @Override
    public int compare(CardSuit o1, CardSuit o2) {
        return Integer.compare(o1.getOrder(), o2.getOrder());
    }
}