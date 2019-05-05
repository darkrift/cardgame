package net.richardlavoie.jivesoftware.cardgame.data;

import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;
import net.richardlavoie.jivesoftware.cardgame.random.RandomSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private final String id;

    private final List<Card> cards;

    private int position = 0;

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

    /**
     * This methods shuffles all the deck of cards. It uses a modern version of Fisher-Yates for computer use
     * by Richard Durstenfeld.
     */
    public void shuffle(RandomSource source) {

        int size = cards.size();
        for (int i = position; i < size - 2; i++) {
            int r = source.nextInt(size - i) + i;

            Card tmp = cards.get(i);
            cards.add(i, cards.get(r));
            cards.add(r, tmp);
        }
    }

    public Card pick() {
        if (isEmpty()) {
            return null;
        }
        return cards.get(position++);
    }

    public boolean isEmpty() {
        return position == cards.size();
    }

    public String getId() {
        return id;
    }

    public int cardsLeft() {
        return cards.size() - position;
    }

    public void accept(DeckVisitor visitor) {
        for (int i = position; i < cards.size(); i++) {
            visitor.visit(cards.get(i));
        }
    }

}
