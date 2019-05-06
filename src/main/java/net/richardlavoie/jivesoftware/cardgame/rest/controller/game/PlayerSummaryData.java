package net.richardlavoie.jivesoftware.cardgame.rest.controller.game;

/**
 * Class that represents the data for the total hand value of a player.
 */
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
