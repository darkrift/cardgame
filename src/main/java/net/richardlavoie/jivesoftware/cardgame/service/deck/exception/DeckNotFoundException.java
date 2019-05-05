package net.richardlavoie.jivesoftware.cardgame.service.deck.exception;

public class DeckNotFoundException extends DeckServiceException {

    public DeckNotFoundException(String id) {
        super(id, "Deck with id '" + id + "' not found");
    }
}
