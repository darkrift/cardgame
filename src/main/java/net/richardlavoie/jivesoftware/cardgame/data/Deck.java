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

    /**
     * Create a new standard deck of cards.
     *
     * @param id Unique id of the deck
     */
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

    /**
     * Returns the list of cards from this deck.
     *
     * @return List of cards
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.cards);
    }
}
