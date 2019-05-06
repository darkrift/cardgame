package net.richardlavoie.jivesoftware.cardgame.rest.controller.game;

import net.richardlavoie.jivesoftware.cardgame.data.Player;

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
