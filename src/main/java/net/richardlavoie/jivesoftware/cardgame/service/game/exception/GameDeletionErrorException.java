package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

/**
 * Exception thrown when a game could not be deleted.
 */
public class GameDeletionErrorException extends GameServiceException {

    public GameDeletionErrorException(String id) {
        super(id, "An error occurred while deleting game '" + id + "'.");
    }
}
