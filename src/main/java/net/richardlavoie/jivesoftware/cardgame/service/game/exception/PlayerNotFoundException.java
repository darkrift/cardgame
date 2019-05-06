package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

/**
 * Exception thrown when a player with a given id could not be found in a game.
 */
public class PlayerNotFoundException extends GameServiceException {

    public PlayerNotFoundException(String id) {
        super(id, "The player with id '" + id + "' was not found");
    }
}
