package net.richardlavoie.jivesoftware.cardgame.service.deck;

import net.richardlavoie.jivesoftware.cardgame.data.Deck;
import net.richardlavoie.jivesoftware.cardgame.service.deck.exception.DeckNotFoundException;
import net.richardlavoie.jivesoftware.cardgame.service.deck.exception.DeckServiceException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DeckService {

    private final Map<String, Deck> decks = new ConcurrentHashMap<>();

    public Deck createDeck() {
        Deck deck = new Deck(UUID.randomUUID().toString());
        decks.put(deck.getId(), deck);
        return deck;
    }

    public Deck pickDeck(String id) throws DeckServiceException {
        Deck deck = decks.remove(id);
        if (deck == null) {
            throw new DeckNotFoundException(id);
        }

        return deck;
    }

    public List<Deck> getDecks() {
        return Collections.unmodifiableList(new ArrayList<Deck>(decks.values()));
    }
}
