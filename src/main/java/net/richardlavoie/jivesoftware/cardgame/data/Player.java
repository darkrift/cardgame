package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Player {

    private final List<Card> cards = new CopyOnWriteArrayList<>();

    private final String id;

    private final String name;

    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
