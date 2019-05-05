package net.richardlavoie.jivesoftware.cardgame.data.visitor;

import net.richardlavoie.jivesoftware.cardgame.data.Card;

public interface DeckVisitor {

    public void visit(Card card);
}
