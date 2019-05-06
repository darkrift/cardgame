package net.richardlavoie.jivesoftware.cardgame.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents a player with cards in it's hand.
 *
 */
public class Player {

    private final List<Card> cards = new CopyOnWriteArrayList<>();

    private final String id;

    private final String name;

    /**
     * Create a new player with a given id and a name
     * @param id Player id
     * @param name Player name
     */
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Player's cards
     * @return cards
     */
    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    /**
     * Add cards to this player
     * @param cards List of cards to add
     */
    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    /**
     * Unique if of the player
     * @return Unique id.
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of the player
     * @return Name of the player
     */
    public String getName() {
        return name;
    }
}
