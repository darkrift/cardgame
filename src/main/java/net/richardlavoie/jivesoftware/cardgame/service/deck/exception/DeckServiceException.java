package net.richardlavoie.jivesoftware.cardgame.service.deck.exception;

public class DeckServiceException extends Exception {

    private final String id;

    public DeckServiceException(String id, String message) {
        super(message);
        this.id = id;
    }

}
