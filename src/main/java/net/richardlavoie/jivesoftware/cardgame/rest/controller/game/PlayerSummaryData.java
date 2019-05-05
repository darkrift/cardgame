package net.richardlavoie.jivesoftware.cardgame.rest.controller.game;

public class PlayerSummaryData {

    public final String id;

    public final String name;

    public final int value;

    public PlayerSummaryData(String id, String name, int value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

}
