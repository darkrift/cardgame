package net.richardlavoie.jivesoftware.cardgame.service.game.exception;

public abstract class GameServiceException extends Exception {

    private final String id;

    public GameServiceException(String id, String message, Exception parent) {
        super(message, parent);
        this.id = id;
    }

    public GameServiceException(String id, String message) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
