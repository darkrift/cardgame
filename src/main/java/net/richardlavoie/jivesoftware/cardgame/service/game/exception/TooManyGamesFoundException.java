package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

/**
 * This exception is thrown when too many games are found matching an id which should be unique.
 *
 * This should never happen.
 *
 */
public class TooManyGamesFoundException extends GameServiceException {

    public TooManyGamesFoundException(String id) {
        super(id, "Too many games found with id '" + id + "'.");
    }
}
