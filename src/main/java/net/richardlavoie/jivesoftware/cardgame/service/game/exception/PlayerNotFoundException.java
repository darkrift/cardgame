package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

public class PlayerNotFoundException extends GameServiceException {

    public PlayerNotFoundException(String id) {
        super(id, "The player with id '" + id + "' was not found");
    }
}
