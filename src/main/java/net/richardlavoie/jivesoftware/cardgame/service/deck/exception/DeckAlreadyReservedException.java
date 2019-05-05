package net.richardlavoie.jivesoftware.cardgame.service.deck.exception;

public class DeckAlreadyReservedException extends DeckServiceException {

    public DeckAlreadyReservedException(String id) {
        super(id, "The deck with id '' is already used in another game");
    }
}
