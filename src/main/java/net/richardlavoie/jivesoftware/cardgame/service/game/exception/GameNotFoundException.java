package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

/**
 * Exception thrown when a game with a given id could not be found.
 */
public class GameNotFoundException extends GameServiceException {

    public GameNotFoundException(String id) {
        super(id, "Game with id '" + id + "' cannot be found.");
    }
}
