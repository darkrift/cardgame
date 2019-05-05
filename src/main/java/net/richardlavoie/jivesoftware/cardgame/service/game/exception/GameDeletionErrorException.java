package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

public class GameDeletionErrorException extends GameServiceException {

    public GameDeletionErrorException(String id) {
        super(id, "An error occurred while deleting game '" + id + "'.");
    }
}
