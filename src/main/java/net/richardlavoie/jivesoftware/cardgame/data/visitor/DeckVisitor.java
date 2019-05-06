package net.richardlavoie.jivesoftware.cardgame.data.visitor;

import net.richardlavoie.jivesoftware.cardgame.data.Card;

/**
 * Visitor to traverse a deck of card.
 */
public interface DeckVisitor {

    void visit(Card card);
}
