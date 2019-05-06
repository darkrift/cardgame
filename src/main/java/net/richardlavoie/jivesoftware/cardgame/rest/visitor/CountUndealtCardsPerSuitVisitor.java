package net.richardlavoie.jivesoftware.cardgame.rest.visitor;

import net.richardlavoie.jivesoftware.cardgame.data.Card;
import net.richardlavoie.jivesoftware.cardgame.data.CardSuit;
import net.richardlavoie.jivesoftware.cardgame.data.CardSuitComparator;
import net.richardlavoie.jivesoftware.cardgame.data.visitor.DeckVisitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class which gathers the count data of undealt cards, with a pivot on the card suit.
 */
public class CountUndealtCardsPerSuitVisitor implements DeckVisitor {

    public static class SuitCardStatistic {

        public final CardSuit suit;

        public final int count;

        public SuitCardStatistic(CardSuit suit, int count) {
            this.suit = suit;
            this.count = count;
        }

    }

    private Map<CardSuit, Counter> values;

    public CountUndealtCardsPerSuitVisitor() {
        values = new TreeMap<CardSuit, Counter>(new CardSuitComparator());
    }

    @Override
    public void visit(Card card) {
        Counter c = this.values.get(card.getSuit());
        if (c == null) {
            c = new Counter();
            this.values.put(card.getSuit(), c);
        }
        c.inc();
    }

    public List<SuitCardStatistic> computeStats() {
        List<SuitCardStatistic> stats = new ArrayList<SuitCardStatistic>();

        for (Map.Entry<CardSuit, Counter> e : values.entrySet()) {
            stats.add(new SuitCardStatistic(e.getKey(), e.getValue().getValue()));
        }

        return stats;
    }
}
