package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

public class GameNotFoundException extends GameServiceException {

    public GameNotFoundException(String id) {
        super(id, "Game with id '" + id + "' cannot be found.");
    }
}
