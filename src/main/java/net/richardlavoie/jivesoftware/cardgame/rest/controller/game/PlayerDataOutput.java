package net.richardlavoie.jivesoftware.cardgame.rest.controller.game;

import net.richardlavoie.jivesoftware.cardgame.data.Player;

/**
 * Facade to restrict the visibility of a player to be more "REST" like and prevent deep traversal.
 */
public class PlayerDataOutput {

    private final Player player;

    public PlayerDataOutput(Player player) {
        this.player = player;
    }

    public String getId() {
        return player.getId();
    }

    public String getName() {
        return player.getName();
    }
}
