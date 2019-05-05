package net.richardlavoie.jivesoftware.cardgame.data;

import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;
import net.richardlavoie.jivesoftware.cardgame.random.RandomSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * This class represents a shoe in deck card games. It assumes that all decks in the shoe are
 * put one after the other and that one deck can't mix cards with another.
 */
public class GameDeck {

    public Deque<Deck> decks = new ArrayDeque<>();

    /**
     * Pick the number of specified cards from the cards in the shoe.
     *
     * @param num Number of cards to pick
     * @return List of picked cards
     */
    public List<Card> pick(int num) {
        Deck deck = decks.poll();
        if (deck == null) {
            return null;
        }
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (!deck.isEmpty()) {
                cards.add(deck.pick());
            } else {
                decks.pop();
                deck = decks.poll();
                if (deck == null) {
                    return cards;
                }
            }
        }
        return cards;
    }

    /**
     * Shuffle all the decks in the shoe.
     */
    public void shuffle(RandomSource source) {
        // Assumption: Deck of cards in shoe shouldn't mix together ...
        decks.forEach(deck -> deck.shuffle(source));
    }

    /**
     * Add a deck to the shoe.
     *
     * @param deck Deck to add to the shoe
     */
    public void addDeck(Deck deck) {
        synchronized (decks) {
            decks.addLast(deck);
        }
    }

    public void accept(DeckVisitor visitor) {
        for (Deck d : decks) {
            d.accept(visitor);
        }
    }

}
