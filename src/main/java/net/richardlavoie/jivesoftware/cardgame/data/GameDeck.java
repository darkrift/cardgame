package net.richardlavoie.jivesoftware.cardgame.data;

import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;
import net.richardlavoie.jivesoftware.cardgame.random.RandomSource;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents a shoe in deck card games. It assumes that all decks in the shoe are
 * put one after the other and that one deck can't mix cards with another.
 */
public class GameDeck {

    public ArrayDeque<Card> cards = new ArrayDeque<>();

    /**
     * Pick the number of specified cards from the cards in the shoe.
     *
     * @param num Number of cards to pick
     * @return List of picked cards
     */
    public List<Card> pick(int num) {
        List<Card> pickedCards = new ArrayList<>();
        synchronized (this) {
            for (int i = 0; i < num; i++) {
                if (!this.cards.isEmpty()) {
                    pickedCards.add(this.cards.pop());
                } else {
                    return pickedCards;
                }
            }
        }
        return pickedCards;
    }

    /**
     * This methods shuffles all the deck of cards. It uses a modern version of Fisher-Yates for computer use
     * by Richard Durstenfeld.
     */
    public void shuffle(RandomSource source) {
        synchronized(this) {
            Card[] cards = this.cards.toArray(new Card[this.cards.size()]);
            int size = cards.length;
            for (int i = 0; i < size - 2; i++) {
                int r = source.nextInt(size - i) + i;

                if (r != i) {
                    Card tmp = cards[i];
                    cards[i] = cards[r];
                    cards[r] = tmp;
                }
            }
            this.cards = Arrays.stream(cards).collect(Collectors.toCollection(ArrayDeque::new));
        }
    }


    /**
     * Add a deck to the shoe.
     *
     * @param deck Deck to add to the shoe
     */
    public void addDeck(Deck deck) {
        synchronized (this) {
            cards.addAll(deck.getCards());
        }
    }

    public void accept(DeckVisitor visitor) {
        synchronized (this) {
            for (Card d : cards) {
                visitor.visit(d);
            }
        }
    }

}
