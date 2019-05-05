package net.richardlavoie.jivesoftware.cardgame.rest.visitor;

import net.richardlavoie.jivesoftware.cardgame.data.Card;
import net.richardlavoie.jivesoftware.cardgame.data.CardSuitComparator;
import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class UndealtCardsValuePerSuit implements DeckVisitor {

    private static class SuitAndValueComparator implements Comparator<Card> {

        private CardSuitComparator comparator = new CardSuitComparator();

        @Override
        public int compare(Card o1, Card o2) {
            int suitCompare = comparator.compare(o1.getSuit(), o2.getSuit());
            if (suitCompare != 0) {
                return suitCompare;
            }

            // Reverse compare
            return Integer.compare(o2.getValue(), o1.getValue());
        }
    }

    private Map<Card, Counter> cards = new TreeMap<>(new SuitAndValueComparator());

    @Override
    public void visit(Card card) {
        Counter counter = cards.get(card.getSuit());
        if (counter == null) {
            counter = new Counter();
            cards.put(card, counter);
        }

        counter.inc();
    }

    public List<CardsPerSuitData> computeStats() {
        List<CardsPerSuitData> data = new ArrayList<>();
        for (Map.Entry<Card, Counter> entry : this.cards.entrySet()) {
            CardsPerSuitData suitData = new CardsPerSuitData(entry.getKey(), entry.getValue().getValue());
            data.add(suitData);
        }
        return data;
    }

    public static class CardsPerSuitData {

        public final Card card;

        public final int value;

        public CardsPerSuitData(Card card, int value) {
            this.card = card;
            this.value = value;
        }
    }
}
