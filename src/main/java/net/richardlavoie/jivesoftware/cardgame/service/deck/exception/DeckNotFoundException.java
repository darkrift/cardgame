package net.richardlavoie.jivesoftware.cardgame.service.deck.exception;

/**
 * Exception thrown when a deck was not found for the given id.
 */
public class DeckNotFoundException extends DeckServiceException {

    public DeckNotFoundException(String id) {
        super(id, "Deck with id '" + id + "' not found");
    }
}
