package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.Objects;

public class Card {

    private final int value;

    private final CardSuit suit;

    private final int hash;

    public Card(CardSuit suit, int value) {
        this.suit = suit;
        this.value = value;
        this.hash = Objects.hash(suit, value);
    }

    public CardSuit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Card)) {
            return false;
        }
        if (o == this) {
            return true;
        }

        Card card = (Card) o;
        return card.suit == this.suit && card.value == this.value;
    }
}
