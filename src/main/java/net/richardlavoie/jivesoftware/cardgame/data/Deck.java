package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents a deck of cards.
 */
public class Deck {

    private final String id;

    private final List<Card> cards;

    public Deck(String id) {
        this.id = id;
        List<Card> newCards = new ArrayList<>();
        for (CardSuit suit : CardSuit.values()) {
            for (int value = 1; value <= 13; value++) {
                newCards.add(new Card(suit, value));
            }
        }
        this.cards = newCards;
    }

    public String getId() {
        return id;
    }


    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
